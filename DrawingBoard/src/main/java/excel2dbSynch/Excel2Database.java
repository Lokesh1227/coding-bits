package excel2dbSynch;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;


public class Excel2Database {

    public static void main(String args[]) throws FileNotFoundException {

        String filename = "C:\\Users\\Loki\\IdeaProjects\\coding-bits\\DrawingBoard\\src\\main\\java\\excel2dbSynch\\Datafile.xlsx";

        FileInputStream fis = new FileInputStream(filename);

        String jdbcUrl = "jdbc:mysql://localhost:3306/sample";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, "root", "password1");
            connection.setAutoCommit(false);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet s = workbook.getSheet("DataSheet");

            Iterator<Row> it = s.rowIterator();

            Row headerRow = it.next();

            String insert_sql = "INSERT INTO SampleTable( " + headerRow.getCell(0) + "," + headerRow.getCell(1) + ") VALUES (?,?)";

            String delete_sql = "DELETE FROM SampleTable WHERE Id = (?)";

            String update_sql = "UPDATE SampleTable SET ( " + headerRow.getCell(0) + "= ? WHERE ID= " + "?";

            String fetch_sql = "INSERT INTO SampleTable( " + headerRow.getCell(0) + "," + headerRow.getCell(1) + ") VALUES (?,?)";


            PreparedStatement statement = connection.prepareStatement(fetch_sql);

            PreparedStatement insert_statement = connection.prepareStatement(insert_sql);
            PreparedStatement update_statement = connection.prepareStatement(update_sql);
            PreparedStatement delete_statement = connection.prepareStatement(delete_sql);


            ResultSet rs =  statement.executeQuery();

            HashMap<Integer,String> dbMap = new HashMap<>();
            HashMap<Integer,String> excelMap = new HashMap<>();

            while(rs.next()) {
               int id = rs.getInt(0);
               String name = rs.getString(1);
               dbMap.put(id,name);
            }


            System.out.println(headerRow.getCell(0) + "\t" + headerRow.getCell(1));
            while (it.hasNext()) {
                int flag = 0;
                Row row = it.next();

                Integer id = Integer.parseInt(row.getCell(0).getStringCellValue());
                String name = row.getCell(1).getStringCellValue();

                excelMap.put(id,name);

                if(dbMap.containsKey(id)) {
                    if(!dbMap.get(id).equals(name)) {
                        flag = 1; // update
                    }
                }
                else{
                    flag = 2; //insert
                }

                System.out.println(name + "\t" + row.getCell(1));

                switch(flag) {
                    case 1: {
                        update_statement.setString(1, name);
                        update_statement.setInt(2, id);
                    }
                    case 2: {
                        insert_statement.setInt(1, id);
                        insert_statement.setString(2, name);
                    }
                }
                insert_statement.addBatch();
                update_statement.addBatch();
            }

            for(int i: dbMap.keySet()) {
                if(!excelMap.containsKey(i)) {
                    delete_statement.setInt(1,i);
                    delete_statement.addBatch();
                }
            }

            delete_statement.executeBatch();
            insert_statement.executeBatch();
            update_statement.executeBatch();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

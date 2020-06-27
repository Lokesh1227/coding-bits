package runner;

import command.ICommand;
import command.parser.CommandParser;
import model.ErrorCode;

import java.io.*;
/**
 * This is the Main entry point of this application
 */

public class AppRunner {
    public static void main(String args[]) {

        String filePath = "\\src\\main\\resources\\welcome.txt";
        String welcomeFilePath = System.getProperty("user.dir") + filePath;

        String welcomeMessage = ErrorCode.ERROR_INSTRUCTION_NOT_LOADED;
        try {
            BufferedReader welcomeFileReader = new BufferedReader(new FileReader(new File(welcomeFilePath)));
            welcomeMessage = welcomeFileReader.lines().reduce((x, y) -> x + "\n" + y).get();
            System.out.println(welcomeMessage);
        } catch (FileNotFoundException e) {
            System.out.println(welcomeMessage + welcomeFilePath);
        }

        BufferedReader commandLineReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println();
                System.out.println("Enter command: ");
                String userInput = commandLineReader.readLine();

                if (userInput.isEmpty()) {
                    System.out.println("No input received. Try again !");
                    continue;
                } else if (userInput.equals("Q")) {
                    System.out.println("App will shutdown now. See Ya !!");
                    break;
                } else if (userInput.equals("H")) {
                    System.out.println(welcomeMessage);
                    continue;
                }

                ICommand command = CommandParser.parse(userInput);
                command.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
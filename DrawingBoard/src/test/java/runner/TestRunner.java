package runner;

import command.ICommand;
import command.factory.CommandFactory;
import command.parser.CommandParser;
import exception.UnsupportedCommandException;
import model.Canvas;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class TestRunner {

    @Test
    public void runScenario1() throws UnsupportedCommandException {

        ICommand c1 = CommandParser.parse("C 12 13");
        ICommand c2 = CommandParser.parse("L 2 4 2 6");
        ICommand c3 = CommandParser.parse("R 5 5 10 10");
        ICommand c4 = CommandParser.parse("B 8 8 h");
        ICommand c5 = CommandParser.parse("L 4 6 1 6");
        ICommand c6 = CommandParser.parse("L 2 1 2 6");
        ICommand c7 = CommandParser.parse("B 1 1 p");
        ICommand c8 = CommandParser.parse("L 10 6 12 6");
        ICommand c9 = CommandParser.parse("B 5 2 w");

        Stream.of(c1, c2, c3, c4, c5, c6, c7, c8, c9).forEach(ICommand::execute);

        char [][] result = {
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'|', 'p', 'x', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w','|'},
                {'|', 'p', 'x', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w','|'},
                {'|', 'p', 'x', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w','|'},
                {'|', 'p', 'x', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w','|'},
                {'|', 'p', 'x', 'w', 'w', 'x', 'x', 'x', 'x', 'x', 'x', 'w', 'w','|'},
                {'|', 'x', 'x', 'x', 'x', 'x', 'h', 'h', 'h', 'h', 'x', 'x', 'x','|'},
                {'|','\0','\0','\0','\0', 'x', 'h', 'h', 'h', 'h', 'x','\0','\0','|'},
                {'|','\0','\0','\0','\0', 'x', 'h', 'h', 'h', 'h', 'x','\0','\0','|'},
                {'|','\0','\0','\0','\0', 'x', 'h', 'h', 'h', 'h', 'x','\0','\0','|'},
                {'|','\0','\0','\0','\0', 'x', 'x', 'x', 'x', 'x', 'x','\0','\0','|'},
                {'|','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','|'},
                {'|','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','|'},
                {'|','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','|'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
        };
        assertArrayEquals(result, Canvas.getCanvas().getPixels());

    }
}

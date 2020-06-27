package command.parser;

import command.ICommand;
import command.factory.CommandFactory;
import exception.UnsupportedCommandException;
import model.CommandMode;
import model.ErrorCode;

import java.util.Arrays;

public class CommandParser {

    public static ICommand parse(String userInput) throws UnsupportedCommandException {

        String[] tokens = userInput.split(" ");
        if(tokens.length == 0) {
            throw new UnsupportedCommandException(ErrorCode.ERROR_NOT_RECOGNIZED);
        }
        try {
            return CommandFactory.create(
                    CommandMode.valueOf(tokens[0]),
                    Arrays.copyOfRange(tokens, 1, tokens.length)
            );
        }
        catch (IllegalArgumentException e) {
            throw new UnsupportedCommandException(ErrorCode.ERROR_NOT_SUPPORTED + tokens[0], e);
        }
    }
}
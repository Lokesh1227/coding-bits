package command.creators.impl;

import command.ICommand;
import command.creators.ICommandCreator;
import command.impl.FillColorCommand;
import model.Canvas;
import model.ErrorCode;

public class FillColorCommandCreator implements ICommandCreator {
    @Override
    public ICommand create(String[] commandParams) {
        try {
            if (commandParams.length != 3 || commandParams[2].length() != 1) {
                throw new Exception(ErrorCode.ERROR_PARAM_MISMATCH + " Please provide coordinate and color to fill : B x y c");
            }
            return new FillColorCommand(
                    Canvas.getCanvas(),
                    Integer.parseInt(commandParams[0]),
                    Integer.parseInt(commandParams[1]),
                    commandParams[2].charAt(0)
            );
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.ERROR_INVALID_PARAMS + e.getMessage());
        }
    }
}

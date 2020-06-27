package command.creators.impl;

import command.ICommand;
import command.creators.ICommandCreator;
import command.impl.LineCommand;
import model.Canvas;
import model.ErrorCode;

public class LineCommandCreator implements ICommandCreator {
    @Override
    public ICommand create(String[] commandParams) {

        try {
            if (commandParams.length != 4) {
                throw new Exception(ErrorCode.ERROR_PARAM_MISMATCH + " Please provide start and end coordinates of line: L x1 y1 x2 y2");
            }
            return new LineCommand(
                    Canvas.getCanvas(),
                    Integer.parseInt(commandParams[0]),
                    Integer.parseInt(commandParams[1]),
                    Integer.parseInt(commandParams[2]),
                    Integer.parseInt(commandParams[3])
            );
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.ERROR_INVALID_PARAMS + e.getMessage());
        }
    }

}

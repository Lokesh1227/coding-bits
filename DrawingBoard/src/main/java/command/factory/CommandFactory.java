package command.factory;

import command.ICommand;
import command.creators.ICommandCreator;
import command.creators.impl.DrawCanvasCommandCreator;
import command.creators.impl.FillColorCommandCreator;
import command.creators.impl.LineCommandCreator;
import command.creators.impl.RectangleCommandCreator;
import model.CommandMode;

import static model.CommandMode.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<CommandMode, ICommandCreator> commandMapper = new HashMap<CommandMode, ICommandCreator>();

    static {
        commandMapper.put(C, new DrawCanvasCommandCreator());
        commandMapper.put(B, new FillColorCommandCreator());
        commandMapper.put(L, new LineCommandCreator());
        commandMapper.put(R, new RectangleCommandCreator());
    }

    public static ICommand create(CommandMode mode, String[] commandParams) {
        return getCreator(mode).create(commandParams);
    }

    public static ICommandCreator getCreator(CommandMode mode) {
        return commandMapper.get(mode);
    }
}

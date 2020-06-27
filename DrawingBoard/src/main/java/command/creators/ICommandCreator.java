package command.creators;

import command.ICommand;

public interface ICommandCreator {
    ICommand create(String[] commandParams);
}

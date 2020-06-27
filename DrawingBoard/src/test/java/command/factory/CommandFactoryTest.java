package command.factory;

import command.ICommand;
import model.CommandMode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CommandFactoryTest {

    @Test
    public void getCreatorMethodShouldReturnACreatorForAllCommandModes() {
        Arrays.stream(CommandMode.values()).forEach(
                mode -> Assert.assertNotNull(CommandFactory.getCreator(mode))
        );
    }

    @Test
    public void createShouldReturnCommandReturnedByCreator() {
        ICommand command = CommandFactory.create(CommandMode.C,new String[]{"3", "4"});
        Assert.assertNotNull(command);
    }

    @Test(expected = RuntimeException.class)
    public void createShouldThrowExceptionThrownByCreator() {
        CommandFactory.create(CommandMode.C,new String[]{"3"});
    }
}

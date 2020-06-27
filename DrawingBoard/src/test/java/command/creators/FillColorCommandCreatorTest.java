package command.creators;

import command.ICommand;
import command.creators.impl.FillColorCommandCreator;
import command.impl.FillColorCommand;
import model.ErrorCode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class FillColorCommandCreatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createShouldReturnCommandWhenCalledWithCorrectParams() {
        ICommandCreator commandCreator = new FillColorCommandCreator();

        ICommand command = commandCreator.create(new String[]{"4","5","c"});
        assertNotNull(command);
        assertTrue(command instanceof FillColorCommand);
        assertEquals (((FillColorCommand)command).getX(), 4);
        assertEquals (((FillColorCommand)command).getY(), 5);
        assertEquals (((FillColorCommand)command).getColor(), 'c');
    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithWrongParamCount() {
        ICommandCreator commandCreator = new FillColorCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_PARAM_MISMATCH);

        commandCreator.create(new String[]{"4","7"});
    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithInvalidParams() {
        ICommandCreator commandCreator = new FillColorCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_INVALID_PARAMS);

        commandCreator.create(new String[]{"4","c","6"});
    }
}

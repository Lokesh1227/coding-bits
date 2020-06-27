package command.creators;

import command.ICommand;
import command.creators.impl.LineCommandCreator;
import command.impl.LineCommand;
import model.ErrorCode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LineCommandCreatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createShouldReturnCommandWhenCalledWithCorrectParams() {
        ICommandCreator commandCreator = new LineCommandCreator();

        ICommand command = commandCreator.create(new String[]{"4","5","4","8"});
        assertNotNull(command);
        assertTrue(command instanceof LineCommand);
        assertEquals (((LineCommand)command).getX1(), 4);
        assertEquals (((LineCommand)command).getY1(), 5);
        assertEquals (((LineCommand)command).getX2(), 4);
        assertEquals (((LineCommand)command).getY2(), 8);

    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithWrongParamCount() {
        ICommandCreator commandCreator = new LineCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_PARAM_MISMATCH);

        commandCreator.create(new String[]{"4","7"});
    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithInvalidParams() {
        ICommandCreator commandCreator = new LineCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_INVALID_PARAMS);

        commandCreator.create(new String[]{"4","c","6","5"});
    }
}

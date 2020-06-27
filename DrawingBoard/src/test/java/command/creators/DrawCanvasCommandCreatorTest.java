package command.creators;

import command.ICommand;
import command.creators.impl.DrawCanvasCommandCreator;
import command.impl.DrawCanvasCommand;
import model.ErrorCode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class DrawCanvasCommandCreatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createShouldReturnCommandWhenCalledWithCorrectParams() {
        ICommandCreator commandCreator = new DrawCanvasCommandCreator();

        ICommand command = commandCreator.create(new String[]{"4","5"});
        assertNotNull(command);
        assertTrue(command instanceof DrawCanvasCommand);
        assertEquals (((DrawCanvasCommand)command).getHeight(), 5);
        assertEquals (((DrawCanvasCommand)command).getWidth(), 4);
    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithWrongParamCount() {
        ICommandCreator commandCreator = new DrawCanvasCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_PARAM_MISMATCH);

        commandCreator.create(new String[]{"4"});
    }

    @Test
    public void createShouldThrowExceptionWhenCalledWithInvalidParams() {
        ICommandCreator commandCreator = new DrawCanvasCommandCreator();

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(ErrorCode.ERROR_INVALID_PARAMS);

        commandCreator.create(new String[]{"4","c"});
    }
}

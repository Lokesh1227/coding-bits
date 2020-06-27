package model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CanvasTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void drawCanvasShouldDisplayCorrectly() {
        Canvas canvas = Canvas.getCanvas();
        canvas.resetCanvas();
        assertFalse(canvas.isReady());
        int width = 5;
        int height = 4;
        canvas.drawCanvas(width,height);
        assertEquals(canvas.getWidth(),width);
        assertEquals(canvas.getHeight(),height);
        validateFields(canvas);
        assertTrue(isEmptyCanvas(canvas));
    }

    @Test
    public void drawLineShouldThrowIllegalStateExceptionWhenCanvasIsNotReady() {
        Canvas canvas = Canvas.getCanvas();
        canvas.resetCanvas();
        int x1 = 1; int y1 = 2;
        int x2 = 4; int y2 = 2;

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(ErrorCode.ERROR_CANVAS_NOT_READY);

        canvas.drawLine(x1,y1,x2,y2);

    }

    @Test
    public void drawLineShouldDrawHorizontalLineCorrectly() {
        Canvas canvas = Canvas.getCanvas();
        int width = 5;
        int height = 4;
        canvas.drawCanvas(width,height);
        int x1 = 1; int y1 = 2;
        int x2 = 4; int y2 = 2;
        canvas.drawLine(x1,y1,x2,y2);

        char[][] pixels = canvas.getPixels();
        assertArrayEquals(
            Arrays.copyOfRange(pixels[y1],x1, x2+1),
            new char[]{canvas.getLineMarker(),canvas.getLineMarker(),canvas.getLineMarker(),canvas.getLineMarker()}
        );
    }

    @Test
    public void drawLineShouldDrawVerticalLineCorrectly() {
        Canvas canvas = Canvas.getCanvas();
        int width = 7;
        int height = 9;
        canvas.drawCanvas(width,height);
        int x1 = 2; int y1 = 1;
        int x2 = 2; int y2 = 7;
        canvas.drawLine(x1,y1,x2,y2);

        char [][] result =
                {{'-', '-', '-', '-', '-', '-', '-', '-','-',},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0', 'x','\0','\0','\0','\0','\0','|'},
                {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                {'-', '-', '-', '-', '-', '-', '-', '-','-'}
        };
        assertArrayEquals(canvas.getPixels(), result);
    }

    @Test
    public void drawRectangleShouldThrowIllegalStateExceptionWhenCanvasIsNotReady() {
        Canvas canvas = Canvas.getCanvas();
        canvas.resetCanvas();
        int x1 = 1; int y1 = 2;
        int x2 = 4; int y2 = 7;

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(ErrorCode.ERROR_CANVAS_NOT_READY);

        canvas.drawRectangle(x1,y1,x2,y2);
    }

    @Test
    public void drawRectangleShouldWorkCorrectlyWhenCanvasIsReady() {
        Canvas canvas = Canvas.getCanvas();
        int width = 7;
        int height = 9;
        canvas.drawCanvas(width,height);
        int x1 = 2; int y1 = 3;
        int x2 = 6; int y2 = 7;
        canvas.drawRectangle(x1,y1,x2,y2);

        char [][] result = {{'-', '-', '-', '-', '-', '-', '-', '-','-',},
                            {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                            {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                            {'|','\0', 'x', 'x', 'x', 'x', 'x','\0','|'},
                            {'|','\0', 'x','\0','\0','\0', 'x','\0','|'},
                            {'|','\0', 'x','\0','\0','\0', 'x','\0','|'},
                            {'|','\0', 'x','\0','\0','\0', 'x','\0','|'},
                            {'|','\0', 'x', 'x', 'x', 'x', 'x','\0','|'},
                            {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                            {'|','\0','\0','\0','\0','\0','\0','\0','|'},
                            {'-', '-', '-', '-', '-', '-', '-', '-','-'}
                            };
        assertArrayEquals(canvas.getPixels(), result);
    }

    @Test
    public void fillColorShouldThrowIllegalStateExceptionWhenCanvasIsNotReady() {
        Canvas canvas = Canvas.getCanvas();
        canvas.resetCanvas();
        int x = 1; int y = 2;
        char c = 'o';

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(ErrorCode.ERROR_CANVAS_NOT_READY);

        canvas.fillColor(x,y,c);
    }

    @Test
    public void fillColorShouldFillOutsideRectangleWhenPointIsChosenOutsideRectangle() {
        Canvas canvas = Canvas.getCanvas();
        int width = 7;
        int height = 9;
        canvas.drawCanvas(width,height);
        int x1 = 2; int y1 = 3;
        int x2 = 6; int y2 = 7;
        canvas.drawRectangle(x1,y1,x2,y2);
        canvas.fillColor(2,8,'z');

        char [][] result = {
                {'-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'|', 'z', 'x', 'x', 'x', 'x', 'x', 'z', '|'},
                {'|', 'z', 'x','\0','\0','\0', 'x', 'z', '|'},
                {'|', 'z', 'x','\0','\0','\0', 'x', 'z', '|'},
                {'|', 'z', 'x','\0','\0','\0', 'x', 'z', '|'},
                {'|', 'z', 'x', 'x', 'x', 'x', 'x', 'z', '|'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-'}
        };
        assertArrayEquals(canvas.getPixels(), result);
    }

    @Test
    public void fillColorShouldWorkCorrectlyWhenCanvasContainsRectangleAndLine() {
        Canvas canvas = Canvas.getCanvas();
        int width = 7;
        int height = 9;
        canvas.drawCanvas(width,height);
        int x1 = 2; int y1 = 3;
        int x2 = 6; int y2 = 7;
        canvas.drawRectangle(x1,y1,x2,y2);
        canvas.drawLine(4,1,4,3);
        canvas.drawLine(1,5,2,5);
        canvas.fillColor(2,8,'z');
        canvas.fillColor(1,1,'o');
        canvas.fillColor(3,4,'l');

        char [][] result = {
                {'-', '-', '-', '-', '-', '-', '-', '-', '-'},
                {'|', 'o', 'o', 'o', 'x', 'z', 'z', 'z', '|'},
                {'|', 'o', 'o', 'o', 'x', 'z', 'z', 'z', '|'},
                {'|', 'o', 'x', 'x', 'x', 'x', 'x', 'z', '|'},
                {'|', 'o', 'x', 'l', 'l', 'l', 'x', 'z', '|'},
                {'|', 'x', 'x', 'l', 'l', 'l', 'x', 'z', '|'},
                {'|', 'z', 'x', 'l', 'l', 'l', 'x', 'z', '|'},
                {'|', 'z', 'x', 'x', 'x', 'x', 'x', 'z', '|'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'|', 'z', 'z', 'z', 'z', 'z', 'z', 'z', '|'},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-'}
        };
        assertArrayEquals(canvas.getPixels(), result);
    }

    private void validateFields(Canvas canvas) {
        assertTrue(canvas.isReady());
        assertEquals(canvas.getL_boundary(),0);
        assertEquals(canvas.getR_boundary(),canvas.getWidth()+1);
        assertEquals(canvas.getT_boundary(),0);
        assertEquals(canvas.getB_boundary(),canvas.getHeight()+1);
    }

    private boolean isEmptyCanvas(Canvas canvas) {
        int l_boundary = canvas.getL_boundary();
        int r_boundary = canvas.getR_boundary();
        int t_boundary = canvas.getT_boundary();
        int b_boundary = canvas.getB_boundary();

        boolean result = true;
        char[][] pixels = canvas.getPixels();

        for(int i = t_boundary; i <= b_boundary; i++) {
            for(int j = l_boundary; j <= r_boundary; j++) {
                if(i==t_boundary || i==b_boundary) {
                    result &= (pixels[i][j] == canvas.getRowMarker());
                }
                else if (j==l_boundary || j==r_boundary){
                    result &= (pixels[i][j] == canvas.getColMarker());
                }
                else {
                    result &= (pixels[i][j] == '\0');
                }
            }
        }
        return result;
    }
}
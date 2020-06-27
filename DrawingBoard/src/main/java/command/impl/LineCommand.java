package command.impl;

import command.ICommand;
import model.Canvas;

public class LineCommand implements ICommand {

    Canvas canvas;
    int x1;
    int x2;
    int y1;
    int y2;

    public Canvas getCanvas() {
        return canvas;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public LineCommand(Canvas c, int x1, int y1, int x2, int y2) {
        canvas = c;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void execute() {
        canvas.drawLine(x1,y1,x2,y2);
    }
}

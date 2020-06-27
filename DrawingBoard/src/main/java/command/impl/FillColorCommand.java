package command.impl;

import command.ICommand;
import model.Canvas;

public class FillColorCommand implements ICommand {

    private Canvas canvas;
    private int x;
    private int y;
    private char color;

    public Canvas getCanvas() {
        return canvas;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    }

    public FillColorCommand(Canvas c, int x, int y , char color) {
        canvas = c;
        this.x =x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void execute() {
        canvas.fillColor(x, y, color);
    }
}

package command.impl;

import command.ICommand;
import model.Canvas;

public class DrawCanvasCommand implements ICommand {
    private Canvas canvas;
    private int height;

    public Canvas getCanvas() {
        return canvas;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int width;

    public DrawCanvasCommand(Canvas c, int w, int h) {
        height = h;
        width = w;
        canvas = c;
    }

    @Override
    public void execute() {
        canvas.drawCanvas(width, height);
    }
}

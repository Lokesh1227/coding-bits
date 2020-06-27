package model;

public class Canvas {
    private static class CanvasInstanceHolder {
        public static Canvas canvasInstance = new Canvas();
    }

    private int width;
    private int height;
    private char[][] pixels;
    private int l_boundary;
    private int r_boundary;
    private int t_boundary;
    private int b_boundary;
    private boolean isReady = false;
    private char lineMarker = 'x';
    private char rowMarker = '-';
    private char colMarker = '|';

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getPixels() {
        return pixels;
    }

    public int getL_boundary() {
        return l_boundary;
    }

    public int getR_boundary() {
        return r_boundary;
    }

    public int getT_boundary() {
        return t_boundary;
    }

    public int getB_boundary() {
        return b_boundary;
    }

    public boolean isReady() {
        return isReady;
    }

    public char getLineMarker() {
        return lineMarker;
    }

    public char getRowMarker() {
        return rowMarker;
    }

    public char getColMarker() {
        return colMarker;
    }

    public static Canvas getCanvas() {
        return CanvasInstanceHolder.canvasInstance;
    }

    public void drawCanvas(int w, int h) {
        width = w;
        height = h;
        pixels = new char[h + 2][w + 2];
        l_boundary = 0;
        r_boundary = width + 1;
        t_boundary = 0;
        b_boundary = height + 1;
        markBoundaries();
        isReady = true;

        displayCanvas();
    }

    public void resetCanvas() {
        isReady = false;
    }

    private void drawLine(int c1, int r1, int c2, int r2, boolean display) {
        if (!isReady) {
            throw new IllegalStateException(ErrorCode.ERROR_CANVAS_NOT_READY);
        }
        if (c1 != c2 && r1 != r2) {
            throw new IllegalArgumentException(ErrorCode.ERROR_STRAIGHT_LINE_SUPPORTED);
        }

        if (isValidPoint(r1, c1) && isValidPoint(r2, c2)) {
            if (r1 == r2) {
                int start = c1;
                int end = c2;
                if (c1 > c2) {
                    start = c2;
                    end = c1;
                }
                for (int i = start; i <= end; i++) {
                    mark(r1, i, lineMarker);
                }
            } else {
                int start = r1;
                int end = r2;
                if (r1 > r2) {
                    start = r2;
                    end = r1;
                }
                for (int i = start; i <= end; i++) {
                    mark(i, c1, lineMarker);
                }
            }
        }
        else {
            System.out.println(ErrorCode.ERROR_POINT_OUT_OF_BOUND);
        }
        if (display) {
            displayCanvas();
        }
    }

    public void drawLine(int c1, int r1, int c2, int r2) {
        drawLine(c1, r1, c2, r2, true);
    }

    public void drawRectangle(int c1, int r1, int c2, int r2) {
        if (!isReady) {
            throw new IllegalStateException(ErrorCode.ERROR_CANVAS_NOT_READY);
        }

        if (isValidPoint(r1, c1) && isValidPoint(r2, c2)) {
            drawLine(c1, r1, c1, r2, false);
            drawLine(c1, r1, c2, r1, false);
            drawLine(c1, r2, c2, r2, false);
            drawLine(c2, r1, c2, r2, false);
        }
        else {
            System.out.println(ErrorCode.ERROR_POINT_OUT_OF_BOUND);
        }
        displayCanvas();
    }

    private void markBoundaries() {
        for (int i = l_boundary; i <= r_boundary; i++) {
            pixels[t_boundary][i] = rowMarker;
            pixels[b_boundary][i] = rowMarker;
        }
        for (int i = t_boundary + 1; i < b_boundary; i++) {
            pixels[i][l_boundary] = colMarker;
            pixels[i][r_boundary] = colMarker;
        }
    }

    public void fillColor(int c, int r, char color) {
        if (!isReady) {
            throw new IllegalStateException(ErrorCode.ERROR_CANVAS_NOT_READY);
        }
        if (isValidPoint(r, c)) {
            fillColorUtil(c, r, color);
        }
        else {
            System.out.println(ErrorCode.ERROR_POINT_OUT_OF_BOUND);
        }
        displayCanvas();
    }

    public void fillColorUtil(int c, int r, char color) {
        if (isValidPoint(r, c)) {
            if (pixels[r][c] == lineMarker || pixels[r][c] == color) {
                return;
            } else {
                pixels[r][c] = color;

                fillColorUtil(c, r + 1, color);
                fillColorUtil(c, r - 1, color);
                fillColorUtil(c + 1, r, color);
                fillColorUtil(c + 1, r + 1, color);
                fillColorUtil(c + 1, r - 1, color);
                fillColorUtil(c - 1, r, color);
                fillColorUtil(c - 1, r - 1, color);
                fillColorUtil(c - 1, r + 1, color);
            }
        }
    }

    private void mark(int r, int c, char marker) {
        pixels[r][c] = marker;
    }

    private boolean isValidPoint(int r, int c) {
        return (c < this.r_boundary) && (c > this.l_boundary) && (r < this.b_boundary) && (r > this.t_boundary);
    }

    public void displayCanvas() {
        if (!isReady) {
            throw new IllegalStateException(ErrorCode.ERROR_CANVAS_NOT_READY);
        }
        System.out.println();
        System.out.println("Canvas : " + width + " x " + height);
        for (int i = t_boundary; i <= b_boundary; i++) {
            for (int j = l_boundary; j <= r_boundary; j++) {
                System.out.print(pixels[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

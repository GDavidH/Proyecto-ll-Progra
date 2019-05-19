
package domain;

public class Figure {
    public String identification;
    private Point pointPosition;
    private int length;
    private int width;

    public Figure(String identification, int x, int y, int length, int width) {
        this.identification = identification;
        pointPosition = new Point(x, y);
        this.length = length;
        this.width = width;
    }

    public Figure(String identification, Point pointPosition, int length, int width) {
        this.identification = identification;
        this.pointPosition = pointPosition;
        this.length = length;
        this.width = width;    
    }

    public Figure() {
    }

    public Point getPointPosition() {
        return pointPosition;
    }

    public void setPointPosition(Point pointPosition) {
        this.pointPosition = pointPosition;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

package domain;

// Crea la figura con los siguientes parametros
public class Figure {

    // Declaración de variables
    public String identification;
    private Point pointPosition;
    private int length;
    private int width;

    //Constructor de la clase
    public Figure(String identification, int x, int y, int length, int width) {
        this.identification = identification;
        pointPosition = new Point(x, y);
        this.length = length;
        this.width = width;
    }

    //Sobre carga del constructor
    public Figure(String identification, Point pointPosition, int length, int width) {
        this.identification = identification;
        this.pointPosition = pointPosition;
        this.length = length;
        this.width = width;
    }

    //Sobre carga del constructor
    public Figure() {
    }

    //Devuelve el punto de posición
    public Point getPointPosition() {
        return pointPosition;
    }

    // Reescribe el punto de posición
    public void setPointPosition(Point pointPosition) {
        this.pointPosition = pointPosition;
    }

    //Obtiene la identificacion de la figura
    public String getIdentification() {
        return identification;
    }

    // Reescribre la identificaicon de la figura
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    //Obtiene el tamaño de la figura
    public int getLength() {
        return length;
    }

    // Reescribre el tamaño de la figura
    public void setLength(int length) {
        this.length = length;
    }

    //Obtiene el ancho de la figura
    public int getWidth() {
        return width;
    }

    // Reescribre el ancho de la figura
    public void setWidth(int width) {
        this.width = width;
    }
}

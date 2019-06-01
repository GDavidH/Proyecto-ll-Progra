package domain;

// Clase punto
// Define la posición exacta en pantalla de la figura
public class Point {

    //Declaración de variables
    private int x;
    private int y;

    //Constructor de la clase
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    //  Sobre carga del Constructor 
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}

package visual;

import domain.Figure;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class LandFrame extends JFrame {

    private Figure myFigure;

    //variables
    public ArrayList<Figure> myFigureList;

    //constructor
    public LandFrame(ArrayList<Figure> myFigureList) {
        super("SQUARES");

        this.myFigureList = myFigureList;
        this.setSize(900, 900);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int y = 100;
        for (int i = 1; i <= 10; i++) {
            if(i%2==0)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.gray);
            
            g.fillRect(100, y, 720, 75);
            
            y+= 75;
        }
        

        //iterate over all squares
        for (Figure mySquare : myFigureList) {
            myFigure = mySquare;
            if (myFigure.getIdentification().equalsIgnoreCase("Square")) {
                g.setColor(Color.YELLOW);
                g.fillRect(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        mySquare.getLength(), mySquare.getWidth());
            }

            if (myFigure.getIdentification().equalsIgnoreCase("Oval")) {
                g.setColor(Color.pink);
                g.fillOval(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        mySquare.getLength(), mySquare.getWidth());
            }

        } //end for
    }

    public void paintAgain() {
        repaint();
    }

}

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
        
        Figure mys;
        //iterate over all squares
       for (Figure mySquare : myFigureList) {
            mys = mySquare;
            if (mys.getIdentification().equalsIgnoreCase("Square")) {
                g.setColor(Color.cyan);
                g.fillRect(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        mySquare.getLength(), mySquare.getWidth());
            }

            if (mys.getIdentification().equalsIgnoreCase("Oval")) {
                g.setColor(Color.BLUE);
                g.fillOval(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        mySquare.getLength(), mySquare.getWidth());
            }
            if (mys.getIdentification().equalsIgnoreCase("Arc")) {
                g.setColor(Color.yellow);
                g.fillArc(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        mySquare.getLength(), mySquare.getWidth(),50,300);
                
            }
            if (mys.getIdentification().equalsIgnoreCase("Round")) {
                g.setColor(Color.red);
                g.fillRoundRect(mySquare.getPointPosition().getX(), mySquare.getPointPosition().getY(),
                        50, mySquare.getWidth(),40,20);
            }
        } //end for
    }

    public void paintAgain() {
        repaint();
    }

}

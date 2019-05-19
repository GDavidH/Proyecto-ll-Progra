/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsandgraphics;

import data.XMLStudentManager;
import domain.Figure;
import domain.Point;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jdom.JDOMException;
import threads.RepaintLandThread;
import threads.SquareThread;
import visual.LandFrame;

/**
 *
 * @author David
 */
public class Test {

    private static final int speedFaster = 50;
    private static final int speedFast = 150;
    private static final int speedMedium = 300;
    private static final int speedSlow = 500;

    public void test1() throws IOException, JDOMException {
        XMLStudentManager manager = new XMLStudentManager("./data/Square.xml");
        //create the domain squares
        int y = 125;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                Figure square = new Figure("Square", new Point(100, y), 25, 25);
                manager.insertSquare(square);

            }
            if (i % 2 != 0) {
                Figure square = new Figure("Oval", new Point(100, y), 25, 25);
                manager.insertSquare(square);

            }
            y += 75;
        }

        //create the array list for the frame to paint
        ArrayList<Figure> squareList = new ArrayList();
        Figure[] squares = manager.getAllSquares();
        for (Figure square : squares) {
            squareList.add(square);
        }

        //create the new frame and send the square list
        LandFrame myLand = new LandFrame(squareList);
        myLand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //thread for repainting
        RepaintLandThread repaintThread = new RepaintLandThread(myLand, 50);
        repaintThread.start();

        //thread for controlling the movement of the squares
        int y1 = 125;
        int speed = 0;
        for (Figure square : squares) {
            if(square.getIdentification().equalsIgnoreCase("Oval")){
                speed = speedFaster;
            }else if(square.getIdentification().equalsIgnoreCase("Square"))
                speed = speedFast;
            SquareThread squareThread = new SquareThread(square, speed, y1);
            
            y1 += 75;
            squareThread.start();
        }
    }

}

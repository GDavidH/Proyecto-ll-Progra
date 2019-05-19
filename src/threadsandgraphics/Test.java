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
import threads.FigureThread;
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


    void test1() throws IOException, JDOMException {
        XMLStudentManager manager = new XMLStudentManager("./data/Figures.xml");
        //create the domain squares
        int y = 50;
        for (int i = 1; i <= 4; i++) {
            if (i % 4 == 0) {
                Figure square = new Figure("Square", new Point(10, y), 25, 25);
                manager.insertSquare(square);
                y += 100;
            }
            if (i % 4 == 1) {
                Figure square = new Figure("Oval", new Point(10, y), 25, 25);
                manager.insertSquare(square);
                y += 100;
            }
            if (i % 4 == 2) {
                Figure square = new Figure("Arc", new Point(10, y), 25, 25);
                manager.insertSquare(square);
                y += 100;
            }
            if (i % 4 == 3) {
                Figure square = new Figure("Round", new Point(10, y), 25, 25);
                manager.insertSquare(square);
                y += 100;
            }
        }

        //create the array list for the frame to paint
        ArrayList<Figure> squareList = new ArrayList();
        Figure[] squares = manager.getAllSquares();
        for (int i = 0; i < squares.length; i++) {
            squareList.add(squares[i]);

        }

        //create the new frame and send the square list
        LandFrame myLand = new LandFrame(squareList);
        myLand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //thread for repainting
        RepaintLandThread repaintThread = new RepaintLandThread(myLand, 50);
        repaintThread.start();

        //thread for controlling the movement of the squares
        int p1 = 50;
        Figure mys;
        for (Figure square : squares) {
            mys = square;
            if (mys.getIdentification().equalsIgnoreCase("Square")) {
                FigureThread squareThread = new FigureThread(mys, speedFast, p1);
                p1 += 50;
                squareThread.start();
            }
            if (mys.getIdentification().equalsIgnoreCase("Oval")) {
                FigureThread squareThread = new FigureThread(mys, speedFaster, p1);
                p1 += 50;
                squareThread.start();
            }
            if (mys.getIdentification().equalsIgnoreCase("Arc")) {
                FigureThread squareThread = new FigureThread(mys, speedMedium, p1);
                p1 += 50;
                squareThread.start();
            }
            if (mys.getIdentification().equalsIgnoreCase("Round")) {
                FigureThread squareThread = new FigureThread(mys, speedSlow, p1);
                p1 += 50;
                squareThread.start();
            }
        }
    }

}

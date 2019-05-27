package threadsandgraphics;

import domain.Figure;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jdom.JDOMException;
import threads.RepaintLandThread;
import visual.LandFrame;

public class ThreadsAndGraphics {

    public static void main(String[] args) throws IOException, JDOMException {

        ArrayList<Figure> squareList = new ArrayList();

        //create the new frame and send the square list
        LandFrame myLand = new LandFrame(squareList);
        myLand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //thread for repainting
        RepaintLandThread repaintThread = new RepaintLandThread(myLand, 50);
        repaintThread.start();

    }//end main
}

package threadsandgraphics;

import data.XMLStudentManager;
import visual.LandFrame;
import threads.RepaintLandThread;
import threads.SquareThread;
import domain.Point;
import domain.Figure;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jdom.JDOMException;

public class ThreadsAndGraphics {

    
    public static void main(String[] args) throws IOException, JDOMException {
        Test test =new Test();
        test.test1();
    }//end main
    
    
}

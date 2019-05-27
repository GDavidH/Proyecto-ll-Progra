package threads;

import domain.Point;
import domain.Figure;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visual.LandFrame;

public class FigureThread extends Thread {

    LandFrame myFrame = new LandFrame();

    //variables
    private final Figure myFigure;
    private int delayTime;
    private final int Y;
    private boolean isdiferent;

    public FigureThread(Figure myFigure, int delayTime, int Y) {
        super(myFigure.identification);
        this.myFigure = myFigure;
        this.delayTime = delayTime;
        this.Y = Y;
    }

    @Override
    public void run() {

        if (!myFrame.myFigureList.isEmpty()) {
            int newX = 20;
            while (newX <= 1250) {
                try {
                    isdiferent = true;
                    sleep(delayTime);
                    myFigure.setPointPosition(new Point(newX, Y));

                    //move the object                
                    if (!myFrame.isInterrupted) {
                        for (Figure figure : myFrame.myFigureList) {
                            if (figure != myFigure) {
                                if (figure.getPointPosition().getX() - 30 == (myFigure.getPointPosition().getX())
                                        && figure.getPointPosition().getY() == myFigure.getPointPosition().getY()) {
                                    isdiferent = false;
                                }
                            }
                        }
                        if (LandFrame.isBarrier && newX == 550) {
                        }
                        if (isdiferent) {
                            newX += 2;
                        }
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }//end While
        }//en if(IsEmpty)

        for (Figure figure : myFrame.myFigureList) {
            if (figure == myFigure) {
                myFrame.myFigureList.remove(figure);
            }
        }

    }//End metodo run
}

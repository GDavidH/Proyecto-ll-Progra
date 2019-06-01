package threads;

import domain.Point;
import domain.Figure;
import java.util.ArrayList;
import visual.LandFrame;

public class FigureThread extends Thread {

    //Instancia de la clase LandFrame
    LandFrame myFrame = new LandFrame();

    //Declaración variables
    private final ArrayList<Figure> figureList;
    private final Figure myFigure;
    private final int delayTime;
    private final int Y;
    private boolean isdiferent;
    private boolean isdiferentRevert;

    //Constructor de la clase
    public FigureThread(Figure myFigure, int delayTime, int Y) {
        super(myFigure.identification);
        this.myFigure = myFigure;
        this.delayTime = delayTime;
        this.Y = Y;
        this.figureList = myFrame.myFigureList;
    }

    //Override del metodo Run() de los threads; Memoria compartida
    @Override
    public void run() {

        int newX = 60;
        while (newX <= 1250 && newX >= 60) {
            int barrier = 550;
            try {
                isdiferent = true;
                isdiferentRevert = true;
                sleep(delayTime);
                myFigure.setPointPosition(new Point(newX, Y));
                
                if (!myFrame.isInterrupted) {
                    for (Figure figure : figureList) {
                        if (figure != myFigure) {
                            if (myFigure.getPointPosition().getX() + 32 == figure.getPointPosition().getX()
                                    && myFigure.getPointPosition().getY() == figure.getPointPosition().getY()) {
                                isdiferent = false;
                            }
                        }
                    }
                    //Barrera cuando va en reversa
                    if (LandFrame.isRevert) {
                        barrier = 650;
                    }
                    if (!(LandFrame.isBarrier && newX == barrier)) {
                        if (LandFrame.isRevert) {
                            for (Figure figure : figureList) {
                                if (figure != myFigure) {
                                    if (myFigure.getPointPosition().getX() - 32 == figure.getPointPosition().getX()
                                            && myFigure.getPointPosition().getY() == figure.getPointPosition().getY()) {
                                        isdiferentRevert = false;
                                    }
                                }
                            }
                            if (isdiferentRevert) {
                                newX -= 2;
                            }
                        } else {
                            if (isdiferent) {
                                newX += 2;
                            }
                        }
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            if (newX < 60) {
                newX = 60;
            }
        }//end While
        myFigure.setIdentification("0");
    }//End metodo run
}//End Clase FigureThread

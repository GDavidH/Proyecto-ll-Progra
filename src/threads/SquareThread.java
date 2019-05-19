
package threads;

import domain.Point;
import domain.Figure;



public class SquareThread extends Thread {

    //variables
    private final Figure myFigure;
    private final int delayTime;
    private final int Y;
    

    public SquareThread(Figure mySquare, int delayTime, int Y) {
        super(mySquare.identification);
        this.myFigure = mySquare;
        this.delayTime = delayTime;
        this.Y = Y;
    }
        
    @Override
    public void run() {
        
        int newX=100;
        
        while (newX<=795) {
            try {
               
                //sleep for the animation
                sleep(delayTime);
                
                myFigure.setPointPosition(new Point(newX, Y));                
                //move the object                
                newX+=5;

            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            
        }//end run

    }
}

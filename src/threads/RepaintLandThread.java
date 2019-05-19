

package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import visual.LandFrame;

public class RepaintLandThread extends Thread{
    
    //variables
    private LandFrame landFrame;
    private int paintTime;
    
    public RepaintLandThread(LandFrame landFrame, int paintTime){
        this.landFrame = landFrame;
        this.paintTime = paintTime;
    }
    
    @Override
    public void run(){
        while(true){
            
            try {
                //sleep until next repaint
                this.sleep(paintTime);
                landFrame.paintAgain();
                
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            
        }//end while
    }//end run
}



package threads;

import visual.LandFrame;

// Mantiene la pantalla en actualización
public class RepaintLandThread extends Thread{
    
    //declaración variables
    private LandFrame landFrame;
    private int paintTime;
    
    //Constructor de la clase
    public RepaintLandThread(LandFrame landFrame, int paintTime){
        this.landFrame = landFrame;
        this.paintTime = paintTime;
    }
    
    //Sobre escribe el metodo run de la clase repaint()
    @Override
    public void run(){
        while(true){    
            try {
                //sleep until next repaint
                this.sleep(paintTime);
                landFrame.repaint();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }//end while
    }//end run
    
}

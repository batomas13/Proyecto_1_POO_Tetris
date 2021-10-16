/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;


import static proyecto_1.Tablero.PERIOD_INTERVAL;
/**
 *
 * @author diemo
 */
public class CronoThread extends Thread{
    public static int seconds = 0;
    public static int minutes = 0;
    private Juego refPantalla;
    private boolean isPaused = false;
    private boolean isRunning = true;
    private int next_level = 0;
    public static int level = 1;
    public Tablero a;
    
    public CronoThread(Juego refPantalla) {
        this.refPantalla = refPantalla;
       
    }
    public void run(){   
        while(isRunning){       
            try {
                sleep(1000);
                seconds++;
                while(isPaused == true){
                    sleep(500);
                }
                if (seconds > 59) {
                    seconds = 0;
                    minutes++;
                    next_level++;
                    if (next_level == 2){
                        PERIOD_INTERVAL -= 10;
                        level ++;
                        refPantalla.setTextToLevel(level);
                        next_level = 0;
                    }
                    if(minutes >59){
                        minutes = 0;
                    }
                }
                
                String newTime = setNiceFormat(minutes) + ":" + setNiceFormat(seconds); 
                refPantalla.setTextToCrono(newTime);
            } catch (InterruptedException ex) {
                
            }
        } 
    } 
    private String setNiceFormat(int number){
        if (number < 10)
            return "0" + number;
        return "" + number;
    }
    public void pauseThread(){
        this.isPaused = true;
    }

    public void resumeThread(){
        this.isPaused = false;
    }
}

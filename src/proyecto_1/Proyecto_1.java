/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;




/**
 *
 * @author tcoto
 */
public class Proyecto_1 {

    /**
     * @param args the command line arguments
     */ 
  public static void sonar(String Path){
      try{
          File musicPath = new File(Path);
          if (musicPath.exists()){
              AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
              Clip clip = AudioSystem.getClip();
              clip.open(audioInput);
              clip.start();
              clip.loop(Clip.LOOP_CONTINUOUSLY);
              
          }
          else{
              System.out.println("No existe");
          }
          
      }
      catch (Exception ex){
          ex.printStackTrace();
      }
  }


    public static void main(String[] args){
        Tetris_menu Menu = new Tetris_menu();
        Menu.setLocationRelativeTo(null);
        Menu.setVisible(true);
        sonar("Tetris.wav");
    }
    
}

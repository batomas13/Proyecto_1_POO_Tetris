/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static proyecto_1.Pause_menu.player;
import static proyecto_1.Tablero.curPiece;
import static proyecto_1.Tablero.puntos;
import static proyecto_1.Tablero.numLinesRemoved;
import static proyecto_1.Tablero.colaPiezas;
import static proyecto_1.Tablero.board;
import static proyecto_1.CronoThread.level;
import static proyecto_1.CronoThread.minutes;
import static proyecto_1.CronoThread.seconds;
/**
 *
 * @author Adrian
 */
public class ManejoPartidas {
    public static File file = new File("partida.ser");
    public static String lastLine;
    
    public static void guardarPartida(Tablero partida) throws FileNotFoundException, IOException {
        System.out.println("Guardar partida");
        System.out.println(partida.toString());
        FileOutputStream fout = new FileOutputStream("partida.ser");
        ObjectOutputStream estadoPartida = new ObjectOutputStream(fout);
        estadoPartida.writeObject(partida);
        estadoPartida.flush();
    }
    
    public static Tablero cargaPartida() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("partida.ser");
        Tablero nuevoEstado;
        ObjectInputStream estadoGuardado = new ObjectInputStream(fin);
        while ((nuevoEstado = (Tablero) estadoGuardado.readObject()) != null) {
            System.out.println("Cargar partida");
            System.out.println(nuevoEstado.toString());
        }
        return nuevoEstado;
    }
    
    
    public static void guardarPartidaTexto(String path, String textoBoard, boolean modo) {
        System.out.println(textoBoard);
        try {
            FileWriter myWriter = new FileWriter(path, modo);
            myWriter.write(textoBoard);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   public static String cargaPartidaTexto(String Path, int lines) {
    File file = new File(Path);
    java.io.RandomAccessFile fileHandler = null;
    try {
        fileHandler = 
            new java.io.RandomAccessFile( file, "r" );
        long fileLength = fileHandler.length() - 1;
        StringBuilder sb = new StringBuilder();
        int line = 0;

        for(long filePointer = fileLength; filePointer != -1; filePointer--){
            fileHandler.seek( filePointer );
            int readByte = fileHandler.readByte();

             if( readByte == 0xA ) {
                if (filePointer < fileLength) {
                    line = line + 1;
                }
            } else if( readByte == 0xD ) {
                if (filePointer < fileLength-1) {
                    line = line + 1;
                }
            }
            if (line >= lines) {
                break;
            }
            sb.append( ( char ) readByte );
        }

        String lastLine = sb.reverse().toString();
        ManejoPartidas.lastLine = lastLine;
        return lastLine;
    } catch( java.io.FileNotFoundException e ) {
        e.printStackTrace();
        return null;
    } catch( java.io.IOException e ) {
        e.printStackTrace();
        return null;
    }
    finally {
        if (fileHandler != null )
            try {
                fileHandler.close();
            } catch (IOException e) {
            }
    }
}
    public static void Cargar_partida(String game){
        String[] old_game = game.split("\n");
        for(int i = 0; i < 8; i++){
            switch (i){
                case 0:
                    player = old_game[0];
                    break;
                case 1:
                    String[] juego = old_game[1].split(",");
                    for (int k = 0; k < 220; k++){
                        System.out.println("HOALÑ");
                        board[k] = board[k].setShape(juego[k]);
                    }
                    break;
                case 2:
                    String[] cola = old_game[2].split(",");
                    for (int j = 0; j < 3; j++){
                       colaPiezas.set(j, new Pieza(cola[j]));
                    }
                    break;   
                case 3:  
                   curPiece.SetShape(old_game[3]);
                   break;
                case 4:
                   puntos = Integer.parseInt(old_game[4]);  
                   break;
                case 5:
                   level = Integer.parseInt(old_game[5]);
                   break;
                case 6:
                    double time = Double.parseDouble(old_game[6]);
                    if (time >= 60){
                       minutes = (int) time / 60;
                       seconds = (int) (time / 60) % 1 * 10;
                    }
                    else{
                        minutes = 0;
                        seconds = (int) time;
                    }
               case 7:
                    numLinesRemoved = Integer.parseInt(old_game[7]);
               }
            }
        } 
    }



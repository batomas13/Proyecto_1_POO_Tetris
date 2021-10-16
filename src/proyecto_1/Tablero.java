/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;

import proyecto_1.Pieza.Tetrominoe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import static proyecto_1.CronoThread.level;
import static proyecto_1.CronoThread.minutes;
import static proyecto_1.CronoThread.seconds;
import proyecto_1.Juego.Tetris;
import static proyecto_1.Pause_menu.player;

/**
 *
 * @author Adrian
 */
public class Tablero extends JPanel{
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    // ESTO ES EL TIEMPO ENTRE CICLOS
    public static int PERIOD_INTERVAL = 300;
    public static Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    public static int numLinesRemoved = 0;
    private int curX = 0;
    private int curY = 0;
    private JLabel statusbar;
    // quitar
    public static Pieza curPiece;
    // /quitar
    public static int puntos = 0;
    public static Tetrominoe[] board;
    public  static ArrayList<Pieza> colaPiezas = new ArrayList<Pieza>();
    //private Pieza piezaGuardada;
    private Juego juego;
    
    
    public Tablero(Tetris parent, Juego juego) {
        preparaCola();
        this.juego = juego;
        initTablero(parent);
    }

    
    
    public void preparaCola() {
        Pieza p0 = new Pieza();
        Pieza p1 = new Pieza();
        Pieza p2 = new Pieza();
        
        p0.setRandomShape();
        p1.setRandomShape();
        p2.setRandomShape();
        
        colaPiezas.add(p0);
        colaPiezas.add(p1);
        colaPiezas.add(p2);
        
    }

    private void initTablero(Tetris parent) {
        setFocusable(true);
        statusbar = parent.getStatusBar();
        addKeyListener(new TAdapter());
        System.out.println("Inicia juego");
    }
    private int squareWidth() {

        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {

        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Tetrominoe shapeAt(int x, int y) {

        return board[(y * BOARD_WIDTH) + x];
    }

    public void start() {
        
        // la idea es que inicie con la pieza 0 del array
        curPiece = colaPiezas.get(0);
        //colaPiezas.remove(0);
        
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];

        clearTablero();
        newPiece();

        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        timer.start();
        System.out.println("Inicia de verdad");
    }

    public void pause() {

        isPaused = !isPaused;

        if (isPaused) {

            statusbar.setText("paused");
        } else {
            statusbar.setText(String.valueOf(numLinesRemoved));
        }

        repaint();
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        drawQueueOnce(g);
    }
    
    private void drawQueueOnce(Graphics g) {
        
        juego.SiguientePieza1.removeAll();
        juego.SiguientePieza2.removeAll();
        // refrescar
        drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 25, 75, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 25, 100, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 50, 75, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 75, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 75, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza1.getGraphics(), 75, 75, Tetrominoe.NoShape);
        
        drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 25, 75, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 25, 100, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 50, 75, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 75, 25, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 75, 50, Tetrominoe.NoShape);
        drawSquare(juego.SiguientePieza2.getGraphics(), 75, 75, Tetrominoe.NoShape);
        
        
        switch (colaPiezas.get(0).getShape()) {
            case ZShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.ZShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.ZShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.ZShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 75, 50, Tetrominoe.ZShape);
                break;
            case SShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 75, 25, Tetrominoe.SShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.SShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.SShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.SShape);
                break;
            case LineShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.LineShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.LineShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 75, Tetrominoe.LineShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 100, Tetrominoe.LineShape);
                break;
            case TShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.TShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.TShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.TShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 75, 50, Tetrominoe.TShape);
                break;
            case SquareShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.SquareShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.SquareShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.SquareShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.SquareShape);
                break;
            case LShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.LShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 50, Tetrominoe.LShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 75, Tetrominoe.LShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 75, Tetrominoe.LShape);
                break;
            case MirroredLShape:
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 25, Tetrominoe.MirroredLShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 50, Tetrominoe.MirroredLShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 50, 75, Tetrominoe.MirroredLShape);
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 75, Tetrominoe.MirroredLShape);
                break;
            default:
                drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, Tetrominoe.NoShape);
                break;
        }
        
        switch (colaPiezas.get(1).getShape()) {
            case ZShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 75, 50, colaPiezas.get(1).getShape());
                break;
            case SShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 75, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, colaPiezas.get(1).getShape());
                break;
            case LineShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 75, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 100, colaPiezas.get(1).getShape());
                break;
            case TShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 75, 50, colaPiezas.get(1).getShape());
                break;
            case SquareShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, colaPiezas.get(1).getShape());
                break;
            case LShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 75, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 75, colaPiezas.get(1).getShape());
                break;
            case MirroredLShape:
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 25, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 50, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 50, 75, colaPiezas.get(1).getShape());
                drawSquare(juego.SiguientePieza2.getGraphics(), 25, 75, colaPiezas.get(1).getShape());
                break;
            default:
                break;
        }
        
        
        /*
        for (int i = 0; i < 4; i++) {
            drawSquare(juego.SiguientePieza1.getGraphics(), 25 * i, 25 * i, colaPiezas.get(0).getShape());
            drawSquare(juego.SiguientePieza2.getGraphics(), 25 * i, 25 * i, colaPiezas.get(1).getShape());
        }
        */
        //drawSquare(juego.SiguientePieza1.getGraphics(), 25, 25, colaPiezas.get(0).getShape());
        //drawSquare(juego.SiguientePieza2.getGraphics(), 25, 25, colaPiezas.get(1).getShape());
    }
    
    
    private void doDrawing(Graphics g) {

        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {

            for (int j = 0; j < BOARD_WIDTH; j++) {

                Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Tetrominoe.NoShape) {

            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
        
    }

    private void dropDown() {

        int newY = curY;

        while (newY > 0) {

            if (!tryMove(curPiece, curX, newY - 1)) {

                break;
            }

            newY--;
        }

        pieceDropped();
    }

    private void oneLineDown() {

        if (!tryMove(curPiece, curX, curY - 1)) {

            pieceDropped();
        }
    }

    private void clearTablero() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {

            board[i] = Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; i++) {

            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {
            newPiece();
        }
    }

    private void newPiece() {

        curPiece = colaPiezas.get(0);
        colaPiezas.remove(0);
        colaPiezas.add(new Pieza());
        colaPiezas.get(2).setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {

            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();
            
            var msg = String.format("Game over. Score: %d", numLinesRemoved);
            
            SaveScore ventanaNombre = new SaveScore(this, this.puntos);
            ventanaNombre.setVisible(true);
            
            statusbar.setText(msg);
        }
    }

    private boolean tryMove(Pieza newPiece, int newX, int newY) {

        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

                return false;
            }

            if (shapeAt(x, y) != Tetrominoe.NoShape) {

                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();
        
        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {

                if (shapeAt(j, i) == Tetrominoe.NoShape) {

                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {

                numFullLines++;

                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {
            switch (numFullLines){
                case 1:
                    this.puntos += 1;
                    break;
                case 2:
                    this.puntos += 2;
                    break;
                case 3:
                    this.puntos += 4;
                    break;
                case 4:
                    this.puntos += 5;
                    System.out.println("Tetris");
                    break;
            }
            numLinesRemoved += numFullLines;
            this.juego.setTextToPointsandLines(puntos, numLinesRemoved);
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {

        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        var color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    public class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    public void doGameCycle() {

        update();
        repaint();
    }

    public void update() {

        if (isPaused) {

            return;
        }

        if (isFallingFinished) {

            isFallingFinished = false;
            newPiece();
        } else {

            oneLineDown();
        }
    }

      class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (curPiece.getShape() == Tetrominoe.NoShape) {

                return;
            }

            int keycode = e.getKeyCode();

            // Java 12 switch expressions
           if (KeyEvent.VK_A == e.getKeyChar()){
                tryMove(curPiece, curX - 1, curY);
            }
            if (KeyEvent.VK_D == e.getKeyChar()){
                tryMove(curPiece, curX + 1, curY);
            }
            if (KeyEvent.VK_S == e.getKeyChar()){
                tryMove(curPiece.rotateRight(), curX, curY);
            }
            if (KeyEvent.VK_W == e.getKeyChar()){
                tryMove(curPiece.rotateLeft(), curX, curY);
            }
            if (KeyEvent.VK_SPACE == e.getKeyChar()){
                dropDown();
            }
            if (KeyEvent.VK_F == e.getKeyChar()){
                oneLineDown();
            }
        }
    }
    
    public String toString() {
        String parcial = "";
        parcial += player + "\n";
        for (Tetrominoe t : board) {
            parcial += t.name() + ",";
        }
        parcial = parcial.substring(0, parcial.length() - 1); // elimina el , al final
        parcial += "\n";
        for (Pieza p : colaPiezas) {
            parcial += p.getShape() + ",";
        }
        parcial = parcial.substring(0, parcial.length() - 1); // elimina el , al final
        parcial += "\n";
        parcial += curPiece.getShape() + "\n";
        //parcial += piezaGuardada.getShape() + "\n";
        parcial += this.puntos + "\n";
        parcial += level + "\n";
        if (minutes > 0){
            minutes *= 60;
            seconds += minutes;
        }
        parcial += seconds + "\n";
        parcial += numLinesRemoved + "\n";
        return parcial;
    }
    
}

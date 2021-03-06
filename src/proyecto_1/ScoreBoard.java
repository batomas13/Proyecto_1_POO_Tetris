/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class ScoreBoard extends javax.swing.JFrame {
    
    public String textoScores = null;
    
    // mapa maneja nombre y puntaje en parejas
    public Map<String, Integer> totalScores = new TreeMap<String, Integer>();
    public Map<String, Integer> highScores;
    
    /**
     * Creates new form ScoreBoard
     */
    public ScoreBoard(String nuevoNombre, int nuevoPuntos) {
        initComponents();
        System.out.println(textoScores);
        cargaScoreBoard();
        System.out.println(textoScores);
        totalScores.put(nuevoNombre, nuevoPuntos);
        System.out.println("Nuevo ingresado");
        
        calculaHighScores();
        System.out.println(textoScores);
        generaString();
        System.out.println(textoScores);
        imprimeScores();
        
        //generaString();
        //imprimeScores();
        guardarScoreBoard();
    }
    
    public ScoreBoard() {
        initComponents();
        cargaScoreBoard();
        calculaHighScores();
        generaString();
        imprimeScores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        TituloScoreBoard = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextAreaHighScores = new javax.swing.JTextArea();

        jCheckBox1.setText("jCheckBox1");

        jCheckBox2.setText("jCheckBox2");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TituloScoreBoard.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        TituloScoreBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloScoreBoard.setText("SCORE BOARD");
        TituloScoreBoard.setToolTipText("");

        TextAreaHighScores.setColumns(20);
        TextAreaHighScores.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TextAreaHighScores.setRows(5);
        jScrollPane3.setViewportView(TextAreaHighScores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(TituloScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void imprimeScores() {
        this.TextAreaHighScores.setText(textoScores);
    }
    
    public String leeScores() {
        try {
            return Files.readString(Paths.get("scores.txt"));
        } catch (IOException ex) {
            System.out.println("Hubo un error en la lectura del scoreboard");
            return null;
            //Logger.getLogger(ManejoBitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargaScoreBoard() {
        String textoPlano = leeScores();
        try {
            if (textoPlano != null) {
                String[] lineas = textoPlano.split("\n");
                for (String linea : lineas) {
                    totalScores.put(linea.split(":")[0], Integer.parseInt(linea.split(":")[1]));
                    //System.out.println(totalScores.toString());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            textoScores = null;
        }
    }
    
    public void guardarScoreBoard() {
        System.out.println(textoScores);
        ManejoPartidas.guardarPartidaTexto("scores.txt", textoScores, false);
    }
    
    public void calculaHighScores() {
        highScores = new LinkedHashMap<>();
        totalScores.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
            .forEachOrdered(x -> highScores.put(x.getKey(), x.getValue()));
    }
    
    public void generaString() {
        textoScores = "";
        int n = 10;
        for (var entry : highScores.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            textoScores += entry.getKey() + ":" + entry.getValue() + "\n";
            n--;
            if (n == 0) {
                break;
            }
        }
        System.out.println(textoScores);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextAreaHighScores;
    private javax.swing.JLabel TituloScoreBoard;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}

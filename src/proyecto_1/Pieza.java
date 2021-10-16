/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1;

import java.util.Random;

/**
 *
 * @author Adrian
 */
public class Pieza {

  
    protected enum Tetrominoe {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape;

        Tetrominoe setShape(String string) {
            switch (string){
            case "NoShape":
                return Tetrominoe.NoShape;
            case "ZShape":
                return Tetrominoe.ZShape;
            case "SShape":
                return Tetrominoe.SShape;
            case "LineShape":
                return Tetrominoe.LineShape;
            case "TShape":
                return Tetrominoe.TShape;
            case "SquareShape":
                return Tetrominoe.SquareShape;
            case "LShape":
                return Tetrominoe.LShape;
            case "MirroredLShape":
                return Tetrominoe.MirroredLShape;
        }
            return Tetrominoe.NoShape;
    }
    }

    private Tetrominoe pieceShape;
    private int coords[][];
    private int[][][] coordsTable;


    public Pieza() {
        initShape();
    }
    
    public Pieza(java.lang.String shape) {
        initShape(shape);
    }

    private void initShape() {

        coords = new int[4][2];

        coordsTable = new int[][][] {
                // NoShape = No pieza
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
                // ZShape
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                // SShape
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                // LineShape
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                // TShape
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                // SquareShape
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                // LShape
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                // MirroredLShape
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };

        setShape(Tetrominoe.NoShape);
    }
    private void initShape(String shape) {

        coords = new int[4][2];

        coordsTable = new int[][][] {
                // NoShape = No pieza
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
                // ZShape
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                // SShape
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                // LineShape
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                // TShape
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                // SquareShape
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                // LShape
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                // MirroredLShape
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };
        SetShape(shape);
    }
  

    protected void setShape(Tetrominoe shape) {

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 2; ++j) {
                // contienen las coordenadas de acuerdo a la tabla
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }

        pieceShape = shape;
    }
    void SetShape(String shape){
       switch (shape){
            case "NoShape":
                setShape(Tetrominoe.NoShape);
            case "ZShape":
                setShape(Tetrominoe.ZShape);
            case "SShape":
                setShape(Tetrominoe.SShape);
            case "LineShape":
                setShape(Tetrominoe.LineShape);
            case "TShape":
                setShape(Tetrominoe.TShape);
            case "SquareShape":
                setShape(Tetrominoe.SquareShape);
            case "LShape":
                setShape(Tetrominoe.LShape);
            case "MirroredLShape":
                setShape(Tetrominoe.MirroredLShape);
        }
     }

    private void setX(int index, int x) { coords[index][0] = x; }
    private void setY(int index, int y) { coords[index][1] = y; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    public Tetrominoe getShape()  { return pieceShape; }

    public void setRandomShape() {

        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;

        Tetrominoe[] values = Tetrominoe.values();
        setShape(values[x]);
    }

    public int minX() {

        int m = coords[0][0];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coords[i][0]);
        }

        return m;
    }


    public int minY() {

        int m = coords[0][1];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coords[i][1]);
        }

        return m;
    }

    public Pieza rotateLeft() {

        if (pieceShape == Tetrominoe.SquareShape) {

            return this;
        }

        var result = new Pieza();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;
    }

    public Pieza rotateRight() {

        if (pieceShape == Tetrominoe.SquareShape) {

            return this;
        }

        var result = new Pieza();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;
    }
}

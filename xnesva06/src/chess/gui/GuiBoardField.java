package chess.gui;

import chess.figures.Figure;
import javafx.scene.control.Button;

public class GuiBoardField extends Button {

    private int col;
    private int row;

    private Figure figure = null;

    GuiBoardField(int col, int row){
        this.col = col;
        this.row = row;
    }

    GuiBoardField(Figure figure){
        this.col = figure.getColumn();
        this.row = figure.getRow();
        this.figure = figure;
        this.setText(figure.toString());
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setFigure(Figure figure){
        this.figure = figure;
    }

    public Figure getFigure(){
        return figure;
    };

    @Override
    public String toString() {
        if(this.figure != null){
            return "Col: " + this.col + ",Row: " + this.row + " " + this.figure.toString();
        }else{
            return "Col: " + this.col + ",Row: " + this.row;
        }

    }
}

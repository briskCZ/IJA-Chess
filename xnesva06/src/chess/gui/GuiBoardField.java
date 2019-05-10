package chess.gui;

import chess.figures.Figure;
import chess.figures.FigureType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;

public class GuiBoardField extends Button {

    private int col;
    private int row;

    private boolean isEnabled;

    private Figure figure = null;

    private ContextMenu contextMenu;

    private String base_style = "-fx-font-size: 50px; -fx-background-radius: 0; -fx-padding: 0;  ";
    private String current_style = "";

    GuiBoardField(int col, int row){
        this.col = col;
        this.row = row;
        setBaseStyle();
    }

    GuiBoardField(Figure figure, ContextMenu contextMenu){
        this.col = figure.getColumn();
        this.row = figure.getRow();
        this.figure = figure;
        this.setText(figure.toString());
        this.contextMenu = contextMenu;
        setBaseStyle();
    }

    private void setBaseStyle(){


        current_style = base_style;

        if(((col % 2 == 0)&&(row % 2 == 0))||((col % 2 == 1)&&(row % 2 == 1))){
            current_style += "-fx-base:GRAY;";
        }

        base_style = current_style;
        this.setStyle(current_style);
        updateProperties();
    }

    private void updateProperties(){
        if(isEnabled){
            this.setStyle(current_style + "-fx-border-color: ORANGE;-fx-border-width:3;");
        }
        else{
            this.setStyle(base_style);
        }

        this.setMaxSize(GameController.field_size, GameController.field_size);
        this.setMinSize(GameController.field_size, GameController.field_size);

        if(contextMenu != null && (this.row == 0 || this.row == 7) && this.figure.getType() == FigureType.Pawn){
            this.setOnContextMenuRequested(event -> contextMenu.show(this, event.getScreenX(), event.getScreenY()));
        }
    }

    public void setEnabled(boolean enabled){
        this.isEnabled = enabled;
        updateProperties();
    }

    public boolean getEnabled(){
        return this.isEnabled;
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
    }

    @Override
    public String toString() {
        if(this.figure != null){
            return "Col: " + this.col + ",Row: " + this.row + " " + this.figure.toString();
        }else{
            return "Col: " + this.col + ",Row: " + this.row;
        }

    }
}

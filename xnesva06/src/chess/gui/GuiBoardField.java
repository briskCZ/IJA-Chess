package chess.gui;

import chess.figures.Figure;
import javafx.scene.control.Button;

public class GuiBoardField extends Button {

    private int col;
    private int row;

    private boolean isEnabled;

    private Figure figure = null;

    private String base_style = "-fx-font-size: 40px;-fx-background-radius: 0;";
    private String current_style = "";

    GuiBoardField(int col, int row){
        this.col = col;
        this.row = row;
        setBaseStyle();
    }

    GuiBoardField(Figure figure){
        this.col = figure.getColumn();
        this.row = figure.getRow();
        this.figure = figure;
        this.setText(figure.toString());
        setBaseStyle();
    }

    private void setBaseStyle(){
        this.layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            //this.setWidth(this.getHeight());

           // System.out.println( "Height: " + this.getHeight() + " Width: " + this.getWidth());
        });

        current_style = base_style;

        if(((col % 2 == 0)&&(row % 2 == 0))||((col % 2 == 1)&&(row % 2 == 1))){
            current_style += "-fx-base:GRAY;";
        }

        base_style = current_style;
        this.setStyle(current_style);
    }

    private void updateProperties(){
        if(isEnabled){
           // System.out.println("curent:"+current_style);
           // System.out.println("base:"+base_style);
            this.setStyle(current_style + "-fx-border-color: ORANGE;-fx-border-width:3;");
        }
        else{
            this.setStyle(base_style);
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

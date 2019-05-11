package chess.gui;

import chess.figures.Figure;
import chess.figures.FigureColor;
import chess.figures.FigureType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class GuiBoardField extends Button {

    private int col;
    private int row;

    private boolean isEnabled;

    private Figure figure = null;

    private ContextMenu contextMenu;

    private String base_style = "-fx-font-size: 50px; -fx-background-radius: 0; -fx-padding: 0;  ";
    private String current_style = "";

    private File image_file;
    private Image figure_image ;

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

        if(this.figure.getColor() == FigureColor.White){
            switch (this.figure.getType()){
                case Bishop:
                    image_file = new File("xnesva06/lib/bishop_w.png");
                    break;
                case King:
                    image_file = new File("xnesva06/lib/king_w.png");
                    break;
                case Knight:
                    image_file = new File("xnesva06/lib/knight_w.png");
                    break;
                case Pawn:
                    image_file = new File("xnesva06/lib/pawn_w.png");
                    break;
                case Queen:
                    image_file = new File("xnesva06/lib/queen_w.png");
                    break;
                case Rook:
                    image_file = new File("xnesva06/lib/tower_w.png");
                    break;
            }
        }else{
                switch (this.figure.getType()){
                    case Bishop:
                        image_file = new File("xnesva06/lib/bishop_b.png");
                        break;
                    case King:
                        image_file = new File("xnesva06/lib/king_b.png");
                        break;
                    case Knight:
                        image_file = new File("xnesva06/lib/knight_b.png");
                        break;
                    case Pawn:
                        image_file = new File("xnesva06/lib/pawn_b.png");
                        break;
                    case Queen:
                        image_file = new File("xnesva06/lib/queen_b.png");
                        break;
                    case Rook:
                        image_file = new File("xnesva06/lib/tower_b.png");
                        break;
                }
        }

        try {
            figure_image = SwingFXUtils.toFXImage(ImageIO.read(image_file), null );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView imageView = new ImageView(figure_image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(GameController.field_size - 10);
        imageView.setFitWidth(GameController.field_size - 10);
        this.setGraphic(imageView);

        this.contextMenu = contextMenu;
        setBaseStyle();
    }

    private void setBaseStyle(){

        current_style = base_style;

       if(((col % 2 == 1)&&(row % 2 == 0))||((col % 2 == 0)&&(row % 2 == 1))){
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

    void setEnabled(boolean enabled){
        this.isEnabled = enabled;
        updateProperties();
    }

    boolean getEnabled(){
        return this.isEnabled;
    }

    int getCol() {
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

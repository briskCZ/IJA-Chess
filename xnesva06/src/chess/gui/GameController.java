package chess.gui;

import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureType;
import chess.game.Game;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static chess.figures.FigureType.Pawn;

public class GameController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private GridPane chessBoardGridPane;

    private Game game = new Game(MainController.gameNo);
    private Figure selectedFigure = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println(game.getGameId());

        listView.setOnMouseClicked(event -> ListClicked(listView.getSelectionModel().getSelectedItem()));

        ObservableList<String> items = listView.getItems();
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");

        //Image image = new Image(Main.class.getResource("test.png").toExternalForm(), 100, 100, true, true);
        SetupFigures();
    }

    private void SetupFigures()
    {
        for (int x = 0; x<8;x++){
            for(int y = 0; y< 8; y++){
                GuiBoardField field;

                Field boardfield = game.getBoardField(y,x);

                if(boardfield.isOccupied()){
                    field = new GuiBoardField(boardfield.getFigure());
                }else{
                    field = new GuiBoardField(x,y);
                }
                field.setOnAction(event -> FieldClicked(field));
                chessBoardGridPane.add(field,x,y);
            }
        }
    }

    private void RefreshFigures(){
        chessBoardGridPane.getChildren().clear();
        SetupFigures();
    }

    private void FieldClicked(GuiBoardField field) {
        Figure fig = field.getFigure();
        if (selectedFigure == null)
        {
            selectedFigure = fig;
        }
        else
        {
            game.move(selectedFigure, game.getBoardField(field.getRow(), field.getCol()));
            RefreshFigures();
            selectedFigure = null;
        }
    }

    private void ListClicked(String string){
        System.out.println(string);
    }

    @FXML
    private void BackClicked(){
        game.undoMove();
        RefreshFigures();
    }

    @FXML
    private void ForwardClicked(){
        game.redoMove();
        RefreshFigures();
    }



}

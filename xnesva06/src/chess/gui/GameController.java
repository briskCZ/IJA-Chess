package chess.gui;

import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureType;
import chess.game.Game;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static chess.figures.FigureType.Pawn;

public class GameController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private GridPane chessBoardGridPane;

    private Game game;
    private Figure selectedFigure = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        listView.setOnMouseClicked(event -> ListClicked(listView.getSelectionModel().getSelectedItem()));

        ObservableList<String> items = listView.getItems();
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");

        game = MainController.createGame();
        setupFigures();

    }

    private void setupFigures()
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

                field.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                field.setOnAction(event -> fieldClicked(field));
                chessBoardGridPane.add(field,x,y);
            }
        }
    }

    private void refreshFigures(){
        chessBoardGridPane.getChildren().clear();
        setupFigures();
    }

    private void setAllFieldsDisabled(){
        List<Node> list = chessBoardGridPane.getChildren();

        for(Node node : list){
            if(node instanceof GuiBoardField){
                GuiBoardField field = (GuiBoardField) node;
                field.setEnabled(false);
            }
        }
    }

    private void setFieldEnabled(int col,int row){
        List<Node> list = chessBoardGridPane.getChildren();

        for(Node node : list){
            if(node instanceof GuiBoardField){
                GuiBoardField field = (GuiBoardField) node;
                if((field.getCol() == col) && (field.getRow() == row)){
                    field.setEnabled(true);
                }
            }
        }
    }

    private boolean isFieldEnabled(int col,int row){
        List<Node> list = chessBoardGridPane.getChildren();

        for(Node node : list){
            if(node instanceof GuiBoardField){
                GuiBoardField field = (GuiBoardField) node;
                if((field.getCol() == col) && (field.getRow() == row)){
                    return field.getEnabled();
                }
            }
        }
        return false;
    }


    private void moveFigure(Figure figure, Field field){
        if(isFieldEnabled(field.getColumn(),field.getRow())){
            game.move(figure, field);
        }
    }

    private void fieldClicked(GuiBoardField field) {
      Figure figure = field.getFigure();

      if(selectedFigure == null){
          if(figure != null){
              selectedFigure = figure;
              ArrayList<Field> possibleMoves = game.getPossibleMoves(figure);
              System.out.println(possibleMoves);
              for (Field possible_field : possibleMoves)
              {
                  setFieldEnabled(possible_field.getColumn(),possible_field.getRow());
              }
          }
      }else{
          moveFigure(selectedFigure, game.getBoardField(field.getRow(), field.getCol()));
          refreshFigures();
          selectedFigure = null;
          setAllFieldsDisabled();
      }

    }

    private void listClicked(String string){
        System.out.println(string);
    }

    @FXML
    private void backClicked(){
        game.undoMove();
        refreshFigures();
    }

    @FXML
    private void forwardClicked(){
        game.redoMove();
        refreshFigures();
    }



}

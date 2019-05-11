package chess.gui;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureColor;
import chess.figures.FigureType;
import chess.figures.King;
import chess.game.Game;
import chess.game.ReplayHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML private ListView<String> listView;

    @FXML private GridPane chessBoardGridPane;

    @FXML private TextField intervalTextField;

    @FXML private Label status_label;

    private Game game;
    private Figure selectedFigure = null;

    private ContextMenu contextMenu;
    private ReplayHandler replayHandler;
    static int field_size = 88;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        contextMenu = new ContextMenu();

        MenuItem queen_menu_item = new MenuItem("Queen");
        MenuItem knight_menu_item = new MenuItem("Knight");
        MenuItem rook_menu_item = new MenuItem("Rook");
        MenuItem bishop_menu_item = new MenuItem("Bishop");

        queen_menu_item.setOnAction(event -> System.out.println("Queen"));
        knight_menu_item.setOnAction(event -> System.out.println("Knight"));
        rook_menu_item.setOnAction(event -> System.out.println("Rook"));
        bishop_menu_item.setOnAction(event -> System.out.println("Bishop"));

        contextMenu.getItems().addAll(queen_menu_item,knight_menu_item,rook_menu_item,bishop_menu_item);
        contextMenu.setStyle("-fx-scale-x: 0.4;-fx-scale-y: 0.4;-fx-translate-x: -100;-fx-translate-y: -100");


        listView.setOnMouseClicked(event -> listClicked(listView.getSelectionModel().getSelectedItem()));

        game = MainController.createGame();
        setupFigures();

        replayHandler  = game.getReplayHandler();
        refreshRecord();



    }

    private void setupFigures()
    {
        for (int x = 0; x<8;x++){
            for(int y = 0; y< 8; y++){


                GuiBoardField field;

                Field boardfield = game.getBoardField(y,x);

                if(boardfield.isOccupied()){
                    field = new GuiBoardField(boardfield.getFigure(),contextMenu);
                }else{
                    field = new GuiBoardField(x,y);
                }

                field.setOnAction(event -> fieldClicked(field));
                chessBoardGridPane.add(field,x,7-y);
            }
        }

        for(int a = 0; a < 8; a++){
            Label label_number = new Label();

            Label label_letter = new Label();

            label_number.setText(String.valueOf(8-a));
            label_number.setStyle("-fx-font-weight: bold; -fx-font-size: 30;-fx-text-fill: GRAY;");
            label_number.setMaxSize(field_size, field_size);
            label_number.setMinSize(field_size, field_size);
            label_number.setAlignment(Pos.CENTER);

            label_letter.setText(String.valueOf((char)(a+97)));
            label_letter.setStyle("-fx-font-weight: bold;-fx-font-size: 30;-fx-text-fill: GRAY;");
            label_letter.setMaxSize(field_size, field_size);
            label_letter.setMinSize(field_size, field_size);
            label_letter.setAlignment(Pos.CENTER);

            chessBoardGridPane.add(label_number,8,a);
            chessBoardGridPane.add(label_letter,a,8);

        }



    }
    private void refreshFigures(){
        chessBoardGridPane.getChildren().clear();
        setupFigures();
    }

    protected void refreshRecord()
    {
        listView.getItems().setAll(replayHandler.getCompleteRecord().toStringArray());
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
            game.move(figure, field, null);

            boolean was_black_check = game.getCheck(FigureColor.Black);
            boolean was_white_check = game.getCheck(FigureColor.White);

            game.checkCheck();

            if(was_black_check && game.getCheck(FigureColor.Black)){
                status_label.setText("Checkmate!");
                game.setCheckmate(true);
            }
            else if(was_white_check && game.getCheck(FigureColor.White)){
                status_label.setText("Checkmate!");
                game.setCheckmate(true);
            }else{
                if(game.getCheck(FigureColor.Black) || game.getCheck(FigureColor.White)){
                    status_label.setText("Check!");
                }else{
                    status_label.setText("");
                }
            }
        }
        refreshRecord();
    }

    private void fieldClicked(GuiBoardField field) {
      Figure figure = field.getFigure();
      if(!game.getCheckmate()){
          if(selectedFigure == null){
              if(figure != null){
                  selectedFigure = figure;
                  if (!game.isOnTurn(selectedFigure.getColor())) return;
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

    }

    private void listClicked(String string){
        System.out.println(string);
    }

    @FXML
    private void redoClicked(){
        game.redoMove();
        refreshFigures();
    }

    @FXML
    private void undoClicked(){
        game.undoMove();
        refreshFigures();
    }

    @FXML
    private void prevMoveClicked(){
        replayHandler.playPreviousMove();
        refreshFigures();
    }

    @FXML
    private void nextMoveClicked(){
        replayHandler.playNextMove();
        refreshFigures();
    }

    @FXML
    private void startAutoRunClicked(){
        try{
            replayHandler.playAutomatically(Integer.parseInt(intervalTextField.getText()));
        }
        catch (Exception e ){
            intervalTextField.setText("0");
        }

    }

    @FXML
    private void startAutoRunBackClicked(){
        try{
            replayHandler.playAutomatically(Integer.parseInt(intervalTextField.getText()));
        }
        catch (Exception e ){
            intervalTextField.setText("0");
        }
    }

    @FXML
    private void pauseAutoRunClicked(){

        //TODO
        replayHandler.stopAutomatically();
    }

    @FXML
    private void stopAutoRunClicked(){

        replayHandler.stopAutomatically();
    }

}

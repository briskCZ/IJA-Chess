package chess.gui;

import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureColor;
import chess.figures.FigureType;
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

/**
 * @author Marek Nesvadba, Zdeněk Doležal (xnesva06, xdolez82)
 * <p>Controller for each individual game.
 */

public class GameController implements Initializable
{

    @FXML
    private ListView<String> listView;

    @FXML
    private GridPane chessBoardGridPane;

    @FXML
    private TextField intervalTextField;

    @FXML
    private Label statusLabel;

    private Game game;
    private Figure selectedFigure = null;
    private Figure lastSelectedFigure = null;
    private Field fieldClicked = null;
    private javafx.scene.input.MouseEvent boardFieldClicked = null;

    private ContextMenu contextMenu;
    private ReplayHandler replayHandler;
    final static int fieldSize = 88;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        contextMenu = new ContextMenu();

        MenuItem queenMenuItem = new MenuItem("Queen");
        MenuItem knightMenuItem = new MenuItem("Knight");
        MenuItem rookMenuItem = new MenuItem("Rook");
        MenuItem bishopMenuItem = new MenuItem("Bishop");

        queenMenuItem.setOnAction(event -> promote(FigureType.Queen));
        knightMenuItem.setOnAction(event -> promote(FigureType.Knight));
        rookMenuItem.setOnAction(event -> promote(FigureType.Rook));
        bishopMenuItem.setOnAction(event -> promote(FigureType.Bishop));

        contextMenu.getItems().addAll(queenMenuItem, knightMenuItem, rookMenuItem, bishopMenuItem);
        contextMenu.setStyle("-fx-background-color: white; -fx-text-fill: black;");


        listView.setOnMouseClicked(event -> listClicked(listView.getSelectionModel().getSelectedIndex()));

        game = MainController.createGame();
        setupFields();

        replayHandler = game.getReplayHandler();
    }

    private void setupFields()
    {
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {


                GuiBoardField field;

                Field boardfield = game.getBoardField(y, x);

                if (boardfield.isOccupied())
                {
                    field = new GuiBoardField(boardfield.getFigure());
                }
                else
                {
                    field = new GuiBoardField(x, y);
                }

                field.setOnMouseClicked(event ->
                {
                    boardFieldClicked = event;
                    fieldClicked(field);
                });
                chessBoardGridPane.add(field, x, 7 - y);
            }
        }

        for (int a = 0; a < 8; a++)
        {
            Label labelNumber = new Label();

            Label labelLetter = new Label();

            labelNumber.setText(String.valueOf(8 - a));
            labelNumber.setStyle("-fx-font-weight: bold; -fx-font-size: 30;-fx-text-fill: GRAY;");
            labelNumber.setMaxSize(fieldSize, fieldSize);
            labelNumber.setMinSize(fieldSize, fieldSize);
            labelNumber.setAlignment(Pos.CENTER);

            labelLetter.setText(String.valueOf((char) (a + 97)));
            labelLetter.setStyle("-fx-font-weight: bold;-fx-font-size: 30;-fx-text-fill: GRAY;");
            labelLetter.setMaxSize(fieldSize, fieldSize);
            labelLetter.setMinSize(fieldSize, fieldSize);
            labelLetter.setAlignment(Pos.CENTER);

            chessBoardGridPane.add(labelNumber, 8, a);
            chessBoardGridPane.add(labelLetter, a, 8);

        }

    }


    private void refreshFields()
    {
        chessBoardGridPane.getChildren().clear();
        setupFields();
    }

    /**
     * Reloads record in the listView.
     */
    protected void refreshRecord()
    {
        listView.getItems().setAll(replayHandler.getCompleteRecord().toStringArray());
        setListViewIndex();
    }

    private void setAllFieldsDisabled()
    {
        List<Node> list = chessBoardGridPane.getChildren();

        for (Node node : list)
        {
            if (node instanceof GuiBoardField)
            {
                GuiBoardField field = (GuiBoardField) node;
                field.setEnabled(false);
            }
        }
    }

    private void setFieldEnabled(int col, int row)
    {
        List<Node> list = chessBoardGridPane.getChildren();

        for (Node node : list)
        {
            if (node instanceof GuiBoardField)
            {
                GuiBoardField field = (GuiBoardField) node;
                if ((field.getCol() == col) && (field.getRow() == row))
                {
                    field.setEnabled(true);
                }
            }
        }
    }

    private boolean isFieldEnabled(int col, int row)
    {
        List<Node> list = chessBoardGridPane.getChildren();

        for (Node node : list)
        {
            if (node instanceof GuiBoardField)
            {
                GuiBoardField field = (GuiBoardField) node;
                if ((field.getCol() == col) && (field.getRow() == row))
                {
                    return field.getEnabled();
                }
            }
        }
        return false;
    }

    private void promote(FigureType figureType)
    {
        game.move(lastSelectedFigure, fieldClicked, figureType);
        refreshFields();
        refreshRecord();
    }

    private void moveFigure(Figure figure, Field field, GuiBoardField fieldClicked)
    {
        if (isFieldEnabled(field.getColumn(), field.getRow()))
        {
            if (figure.getType() == FigureType.Pawn && (field.getRow() == 0 || field.getRow() == 7))
            {
                if (boardFieldClicked != null)
                {
                    contextMenu.show(fieldClicked.getParent(), boardFieldClicked.getScreenX(), boardFieldClicked.getScreenY());
                }
            }
            else
            {
                game.move(figure, field, null);
            }

            if (game.wasBlackCheck && game.getCheck(FigureColor.Black))
            {
                statusLabel.setText("Checkmate!");
                game.setCheckmate(true);
            }
            else if (game.wasWhiteCheck && game.getCheck(FigureColor.White))
            {
                statusLabel.setText("Checkmate!");
                game.setCheckmate(true);
            }
            else
            {
                if (game.getCheck(FigureColor.Black) || game.getCheck(FigureColor.White))
                {
                    statusLabel.setText("Check!");
                }
                else
                {
                    statusLabel.setText("");
                }
            }
        }
        refreshRecord();
    }

    private void fieldClicked(GuiBoardField field)
    {
        fieldClicked = game.getBoardField(field.getRow(), field.getCol());
        Figure figure = field.getFigure();
        contextMenu.hide();
        if (!game.getCheckmate())
        {
            if (selectedFigure == null)
            {
                if (figure != null)
                {
                    selectedFigure = figure;
                    if (!game.isOnTurn(selectedFigure.getColor())) return;
                    ArrayList<Field> possibleMoves = game.getPossibleMoves(figure);
                    System.out.println(possibleMoves);
                    for (Field possible_field : possibleMoves)
                    {
                        setFieldEnabled(possible_field.getColumn(), possible_field.getRow());
                    }
                }
            }
            else
            {
                moveFigure(selectedFigure, game.getBoardField(field.getRow(), field.getCol()), field);
                refreshFields();
                lastSelectedFigure = Figure.copyFigure(selectedFigure);
                selectedFigure = null;
                setAllFieldsDisabled();
            }
        }

    }

    private void listClicked(int index)
    {
        replayHandler.movePlayerTo(index);
        refreshFields();
        refreshRecord();
    }

    @FXML
    private void redoClicked()
    {
        game.redoMove();
        refreshRecord();
        refreshFields();
        setListViewIndex();
    }

    @FXML
    private void undoClicked()
    {
        game.undoMove();
        refreshFields();
        refreshRecord();
        setListViewIndex();
    }

    @FXML
    private void prevMoveClicked()
    {
        replayHandler.playPreviousHalfMove();
        setListViewIndex();
        refreshFields();

    }

    @FXML
    private void nextMoveClicked()
    {
        replayHandler.playNextHalfMove();
        setListViewIndex();
        refreshFields();
    }

    private void setListViewIndex()
    {
        listView.getSelectionModel().select(replayHandler.getCompleteRecordIndex() - 1);
    }

    @FXML
    private void startAutoRunClicked()
    {
        try
        {
            replayHandler.playAutomatically(Integer.parseInt(intervalTextField.getText()));
        } catch (Exception e)
        {
            intervalTextField.setText("0");
        }

    }

    @FXML
    private void startAutoRunBackClicked()
    {
        try
        {
            replayHandler.playAutomatically(Integer.parseInt(intervalTextField.getText()));
        } catch (Exception e)
        {
            intervalTextField.setText("0");
        }
    }

    @FXML
    private void pauseAutoRunClicked()
    {

        //TODO
        replayHandler.stopAutomatically();
    }

    @FXML
    private void stopAutoRunClicked()
    {

        replayHandler.stopAutomatically();
    }

}

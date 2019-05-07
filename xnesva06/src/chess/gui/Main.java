package chess.gui;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.game.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("IJA - Chess");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(600.0);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
        Game game = new Game(0);
        game.printGame();
        Figure figure = game.getBoardField(1, 1).getFigure();
        Field field = game.getBoardField(4, 4);
        game.move(figure, field);
        game.printGame();
    }
}

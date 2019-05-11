package chess.gui;

import chess.board.Field;
import chess.figures.Figure;
import chess.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setTitle("IJA - Chess");
        primaryStage.setScene(new Scene(root, 1000, 950));
        primaryStage.setMinHeight(950);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);


/*
        Game game = new Game(0);
        game.printGame();
        Figure figure = game.getBoardField(1,1).getFigure();
        Field field = game.getBoardField(2, 1);
        game.move(figure, field);
        game.printGame();
        System.out.println(game.getPossibleMoves(figure));
*/


    }
}

package chess.gui;

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
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(600.0);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

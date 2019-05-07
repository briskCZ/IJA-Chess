package chess.gui;

import chess.game.GameFileLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;


public class MainController{

    @FXML private TabPane tabPane;

    GameFileLoader gameFileLoader;

    static int gameNo = 1;

    @FXML
    protected void LoadGameClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.stage);
        gameFileLoader = new GameFileLoader(file);
    }
    @FXML
    protected void NewGameClicked(ActionEvent event) {
        Tab tab = new Tab();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("Cannot create new tab!");
        }

        tab.setText("Game " + gameNo);
        MainController.gameNo++;
        tab.setContent(pane);
        tabPane.getTabs().add(tab);
    }
}


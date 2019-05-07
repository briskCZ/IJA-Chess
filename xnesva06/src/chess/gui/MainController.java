package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;


public class MainController{

    @FXML private TabPane tabPane;
    private int gameNo = 0;

    @FXML
    protected void LoadGameClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.stage);

        System.out.println(file);
    }
    @FXML
    protected void NewGameClicked(ActionEvent event) {
        Tab tab = new Tab();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(Exception e){
            System.out.println(e);
        }

        tab.setText("Game " + gameNo);
        gameNo ++;
        tab.setContent(pane);


        tabPane.getTabs().add(tab);

    }
}


package chess.gui;

import chess.game.FileHandler;
import chess.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainController{

    @FXML private TabPane tabPane;

    static int gameNo = 0;
    static ArrayList<Game> games = new ArrayList<>();

    @FXML
    protected void loadGameClicked(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.stage);
        int gameId = Integer.parseInt(tabPane.getSelectionModel().getSelectedItem().getText().split("\\s+")[1]);
        getGameById(gameId).loadGame(file);
    }

    protected void saveGameClicked(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(Main.stage);
        int gameId = Integer.parseInt(tabPane.getSelectionModel().getSelectedItem().getText().split("\\s+")[1]);
        getGameById(gameId).saveGame(file);
    }

    @FXML
    protected void newGameClicked(ActionEvent event) {
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
        tab.setContent(pane);
        tabPane.getTabs().add(tab);
    }

    public static Game createGame()
    {
        return new Game(gameNo++);
    }

    public static Game getGameById(int id)
    {
        for (Game g : games)
        {
            if (g.getGameId() == id)
            {
                return g;
            }
        }
        return null;
    }
}


package chess.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private GridPane chessBoardGridPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ObservableList<String> items = listView.getItems();
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");
        Image image = new Image(Main.class.getResource("test.png").toExternalForm(), 100, 100, true, true);

        for (int x = 0; x<8;x++){
            for(int y = 0; y< 8; y++){
                Button field = new Button();
                field.setText(x + ":" + y);
                field.setGraphic(new ImageView(image));


                chessBoardGridPane.add(field,x,y);
            }
        }


    }








}

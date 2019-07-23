package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.Main;

public class StartController {

    @FXML
    private AnchorPane startPanel;

    @FXML
    private Button joinButtom;

    @FXML
    void joinGame(ActionEvent event) {
        Main.initialize.joinGame();
    }
}
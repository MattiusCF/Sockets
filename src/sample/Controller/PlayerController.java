package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;

public class PlayerController {

    @FXML
    private Button surrenderButton;

    @FXML
    private Button rock;

    @FXML
    private Button paper;

    @FXML
    private Button scissors;

    @FXML
    void selectPaper(ActionEvent event) {
        //El valor a enviar al servidor es 2
        Main.initialize.select(2);
    }

    @FXML
    void selectRock(ActionEvent event) {
        //El valor a enviar al servidor es 1
        Main.initialize.select(1);
    }

    @FXML
    void selectScissors(ActionEvent event) {
        //El valor a enviar al servidor es 3
        Main.initialize.select(3);
    }

    @FXML
    void surrender(ActionEvent event) {

    }

}

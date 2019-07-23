package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.Model.Connection;

public class CreateController {

    @FXML
    private Button comeBackButton;

    @FXML
    private Button nextButton;

    @FXML
    private CheckBox infinite;

    @FXML
    private ChoiceBox<?> fightNumber;

    @FXML
    void comeBack(ActionEvent event) {
        Main.initialize.startPanel();
    }

    @FXML
    void create(ActionEvent event) {
        if(infinite.isSelected()){
            int matches = 0;
            Main.initialize.createGame(matches);
        }else{
            int matches = Integer.parseInt(fightNumber.getValue().toString());
            Main.initialize.createGame(matches);
        }
    }

    @FXML
    void isInfinite(ActionEvent event) {
        if(infinite.isSelected()){
            fightNumber.setDisable(true);
        }else{
            fightNumber.setDisable(false);
        }
    }

}
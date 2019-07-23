package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Main;

public class JoinController {

    @FXML
    private Label label11;

    @FXML
    private Label label21;

    @FXML
    private Label label31;

    @FXML
    private Label label12;

    @FXML
    private Label label22;

    @FXML
    private Label label32;

    @FXML
    private Button button2;

    @FXML
    private Button button1;

    @FXML
    private Button button3;

    @FXML
    private Button back;

    @FXML
    private Button reload;


    @FXML
    void comeBack(ActionEvent event) {
        Main.initialize.startPanel();
    }

    @FXML
    void joinGame1(ActionEvent event) {
        Main.initialize.addPlayer(1);
    }

    @FXML
    void joinGame2(ActionEvent event) {
        Main.initialize.addPlayer(2);
    }

    @FXML
    void joinGame3(ActionEvent event) {
        Main.initialize.addPlayer(3);
    }

    @FXML
    void reload(ActionEvent event){
        String msg = Main.initialize.listPlayers();
        String[] listPlayers = msg.split(";");
        label12.setText(listPlayers[0]);
        label22.setText(listPlayers[1]);
        label32.setText(listPlayers[2]);
        String[] np1 = listPlayers[0].split("/");
        String[] np2 = listPlayers[1].split("/");
        String[] np3 = listPlayers[2].split("/");
        if(Integer.parseInt(np1[0])<2){
            button1.setVisible(true);
        }else{
            button1.setVisible(false);
        }
        if(Integer.parseInt(np2[0])<2){
            button2.setVisible(true);
        }else{
            button2.setVisible(false);
        }
        if(Integer.parseInt(np3[0])<2){
            button3.setVisible(true);
        }else{
            button3.setVisible(false);
        }
        label11.setDisable(false);
        label12.setDisable(false);
        label21.setDisable(false);
        label22.setDisable(false);
        label31.setDisable(false);
        label32.setDisable(false);
    }

}
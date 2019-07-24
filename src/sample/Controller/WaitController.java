package sample.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitController implements Initializable {

    private int room=Main.initialize.getNRoom();
    @FXML
    private Button exit;


    @FXML
    void exitGame(ActionEvent event) {
        try {
            this.finalize();
            Main.initialize.joinGame();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new preloader().start();
    }

    class preloader extends Thread{
        @Override
        public void run(){
            while (Main.initialize.getConnection().players(room)!=2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Main.initialize.playerPanel();
                }
            });
        }
    }
}

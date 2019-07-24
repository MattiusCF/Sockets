package sample.Controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MatchController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new preloader().start();
    }

    class preloader extends Thread{

        @Override
        public void run(){
            while (!Main.initialize.ready()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(Main.initialize.tie()){
                        Main.initialize.tiePanel();
                    }
                    if(Main.initialize.isWinner()) {
                        Main.initialize.winnerPanel();
                    }else{
                        Main.initialize.losserPanel();
                    }
                }
            });
        }
    }
}

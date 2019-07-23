package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Controller.InitializeController;

public class Main extends Application {
    public static InitializeController initialize;

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize = new InitializeController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

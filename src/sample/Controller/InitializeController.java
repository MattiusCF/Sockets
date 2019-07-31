package sample.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;
import sample.Model.Connection;

import java.io.IOException;

public class InitializeController {

    private AnchorPane panel;
    private Stage primaryStage;
    private AnchorPane mainPanel;
    private Scene scene;
    private Connection connection;
    private Thread NThread;
    private String order;
    private int NRoom;

    @FXML
    private Button createButton;

    @FXML
    private Button joinButtom;

    @FXML
    private Button exit;

    @FXML
    private Button next;

    @FXML
    private Button finish;

    @FXML
    private TextField playerName;

    @FXML
    private TextField serverIP;

    public Connection getConnection(){
        return connection;
    }
    public void createConnection(String name, String ip){
        connection = new Connection(ip);
        NThread = new Thread(connection);
        connection.sendName(name);
        NThread.start();

    }
    public InitializeController(){

    }
    //Constructor principal
    public InitializeController(Stage primaryStage){
        //Carga el primaryStage en una variable local para modificarlo mas adelante sin tener que establecerlo como dato de entrada siempre.
        this.primaryStage = primaryStage;
        try{
            //Carga un anchorPane vacio en el que se van a cargar las pantallas a lo largo de la ejecucion.
            mainPanel = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/mainPanel.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Damos valores por defecto al panel, para evitar modificaciones al tamaño, por ejemplo.
        this.primaryStage.setTitle("Piedra, papel o tijera!");
        this.primaryStage.setResizable(false);
        //Carga el pane vacio en una escena y la muestra en la ventana principal
        scene = new Scene(mainPanel);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        try{
            //Panel se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/Home.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Se carga en el pane vacio la nueva vista
        mainPanel.getChildren().setAll(panel);
        primaryStage.setOnCloseRequest(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main.initialize.close();
            }
        });
    }

    public void close(){
        try {
            connection.close(NRoom);
        }catch (Exception e){

        }
    }

    public Boolean isWinner(){
        return connection.isWinner(NRoom);
    }

    public Boolean tie(){ return connection.isTie(NRoom); }
    public void winnerPanel(){
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/WinnerPanel.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el pane del ganador");
        }
        //Pone el panel start sobre el panel principal.
        mainPanel.getChildren().setAll(panel);
    }

    public void tiePanel(){
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/TiePanel.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el panel de empate");
        }
        //Pone el panel start sobre el panel principal.
        mainPanel.getChildren().setAll(panel);
    }
    public void losserPanel(){
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/LosserPanel.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el pane start");
        }
        //Pone el panel start sobre el panel principal.
        mainPanel.getChildren().setAll(panel);
    }


    //Muestra el panel start
    public void startPanel() {
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/Start.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el pane start");
        }
        //Pone el panel start sobre el panel principal.
        mainPanel.getChildren().setAll(panel);

    }

    //Muestra el panel wait
    public void waitPanel(){
        try{
            panel = FXMLLoader.load(getClass().getResource("../View/Wait.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el panel de espera");
        }
        //Pone el panel wait sobre el panel principal.
        mainPanel.getChildren().setAll(panel);
    }

    @FXML
    public void playerPanel() {
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/Player.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Se carga en el pane vacio la nueva vista
        mainPanel.getChildren().setAll(panel);
    }

    @FXML
    void joinGame() {
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/Join.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Se carga en el pane vacio la nueva vista
        mainPanel.getChildren().setAll(panel);
    }
    @FXML
    void FinalPanel(){
        try{
            //Pane se va a usar para tener cargada la pantalla QUE SE QUIERE MOSTRAR
            panel = FXMLLoader.load(getClass().getResource("../View/Final.fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Se carga en el pane vacio la nueva vista
        mainPanel.getChildren().setAll(panel);
    }
    //Crea la conexion con el servidor y cambia a la vista siguiente
    @FXML
    void playON(ActionEvent event){
        Main.initialize.createConnection(playerName.getText(), serverIP.getText());
        Main.initialize.startPanel();
    }
    @FXML
    void continueGame(ActionEvent event) {
        Main.initialize.reset();
        Main.initialize.playerPanel();
    }

    @FXML
    void surrender(ActionEvent event) {
        Main.initialize.deletePlayer();
        Main.initialize.FinalPanel();
    }

    @FXML
    void finishGame(ActionEvent event) {
        Main.initialize.deletePlayer();
        Main.initialize.startPanel();
    }

    public String listPlayers(){
        return connection.listP();
    }

    public void deletePlayer(){
        order = "deletePlayer;"+NRoom+";";
        connection.deletePlayer(order);
    }
    public void addPlayer(int nOrder){
        NRoom = nOrder;
        order = "addPlayer;"+nOrder+";";
        connection.addPlayer(order);
        waitPanel();
    }

    public void select(int selec){

        order = "selection;"+NRoom+";"+selec+";";
        connection.select(order);
        matchPanel();

    }

    public void reset(){
        connection.reset(NRoom);
    }

    public void matchPanel(){
        try{
            panel = FXMLLoader.load(getClass().getResource("../View/Match.fxml"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando el panel del Match");
        }
        //Pone el panel wait sobre el panel principal.
        mainPanel.getChildren().setAll(panel);
    }
    public Boolean ready(){
        return connection.isReady(NRoom);
    }

    public int getNRoom(){
        return NRoom;
    }

}
package sample.Model;

import sample.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {

    private String msg;
    private Boolean isOpen;
    //Declaro variables necesarias para la conexion y comunicacion con el servidor
    private Socket player;
    private DataOutputStream out;
    private DataInputStream in;
    //Puerto de comunicacion con el server
    private int port = 2027;
    //Para trabajar en local: "localhost"
    //Para trabajar en distintos ordenadores "La ip del servidor"
    private String host = "192.168.192.7";

    public Connection(){
        try{
            isOpen=true;
            //Crea el socket con el host y el puerto y declara los streams de comunicacion
            player = new Socket(host, port);
            in = new DataInputStream(player.getInputStream());
            out = new DataOutputStream(player.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendName(String name){
        try {
            out.writeUTF(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createGame(String order){
        try{
            out.writeUTF(order);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public String list(){
        try{
            out.writeUTF("list;0;");
            Thread.sleep(1000);
            System.out.println("se acaba de ejecutar la funcion list");
            return msg;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    public String listP(){
        try {
            out.writeUTF("listP;0");
            Thread.sleep(1000);
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public void addPlayer(String order){
        try {
            out.writeUTF(order);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void select(String order){
        try {
            out.writeUTF(order);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(int room){
        try {
            out.writeUTF("close;"+room);
            isOpen=false;
            player.close();
        } catch (IOException e) {

        }
    }

    public int players(int room){
        msg="1";
        try {
            out.writeUTF("players;"+room);
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(msg);
    }

    public Boolean isReady(int room){
        try {
            out.writeUTF("isReady;"+room);
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("Antes de entrar al if, la sala: "+room+" ->");
        if(msg.equals("2")){
            System.out.print(" Está lista");
            return true;
        }else{
            System.out.println(" No está lista y el mensaje es: "+msg);
            return false;
        }
    }

    public Boolean isWinner(int room){
        msg="0";
        try {
            out.writeUTF("isWinner;"+room);
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("En isWinner, msg retorna= "+msg);
        if (msg.equals("2")){
            return true;
        }else{
            return false;
        }
    }
    public void reset(int room){
        try {
            out.writeUTF("resetSelect;"+room);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        //Ciclo para esperar respuestas del servidor
        while (isOpen){
            try {
                msg = in.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

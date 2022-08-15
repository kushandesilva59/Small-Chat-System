package Controller;



import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public AnchorPane serverFormContext;
    public TextField txtMessage;
    public TextArea txtArea;
    ServerSocket serverSocket;
    final int PORT = 5001;
    Socket socket;

    public void initialize() throws IOException {
        serverStart();
    }

    public void serverStart() throws IOException {
       try{
           serverSocket = new ServerSocket(PORT);

       } catch (IOException e){
        e.printStackTrace();
     }

        try{
            while(!serverSocket.isClosed()){
                socket = serverSocket.accept();
                txtArea.appendText("A Client Connected...");
                Clients clients = new Clients(socket);

                Thread thread = new Thread(clients);
                thread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void sendOnAction(ActionEvent event) {
    }
}

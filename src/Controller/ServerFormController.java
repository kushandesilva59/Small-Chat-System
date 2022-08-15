package Controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    ServerSocket serverSocket;
    final int PORT = 5001;
    Socket socket;

    public void initialize() throws IOException {
        serverStart();
    }

    public void serverStart() throws IOException {
        serverSocket = new ServerSocket(PORT);

        try{
            while(!serverSocket.isClosed()){
                socket = serverSocket.accept();

                Clients clients = new Clients(socket);

                Thread thread = new Thread(clients);
                thread.start();
            }
        }catch (IOException e){

        }


    }
}

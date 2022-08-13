package Controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    ServerSocket serverSocket;
    public TextArea txtArea;
    public TextField txtMassage;
    final int PORT = 5001;
    Socket socket;

    public void initialize() throws IOException {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Server start");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                socket = serverSocket.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

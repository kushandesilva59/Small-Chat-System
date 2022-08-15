package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientOneFormController {
    public AnchorPane client1Context;
    public TextArea txtArea;
    public TextField txtMessage;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String userName;
    String message = "";
    final int PORT = 5001;

    public void initialize(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("localhost",PORT);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());

                    receivedMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public void sendOnAction(ActionEvent event) throws IOException {
       while(socket.isConnected()){
           txtArea.appendText("\nMe :"+ txtMessage.getText());
           dataOutputStream.writeUTF(txtMessage.getText());
           dataOutputStream.flush();
       }
    }

    public void receivedMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   while(socket.isConnected()){
                       message = dataInputStream.readUTF();
                       txtArea.appendText(message);
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                        dataInputStream.close();
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}

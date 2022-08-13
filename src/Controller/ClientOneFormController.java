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

    public void sendOnAction(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("localhost",PORT);
                    txtArea.appendText("\nMe :"+ txtMessage.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

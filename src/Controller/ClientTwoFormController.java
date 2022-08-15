package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientTwoFormController {

    public AnchorPane clientTwoLoginContext;
    public TextArea txtArea;
    public TextField txtMessage;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    public void initialize(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public void sendOnAction(ActionEvent event) {

    }
}

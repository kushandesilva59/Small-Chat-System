package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThreeFormController {
    public AnchorPane clientThreeFormContext;
    public TextField txtMessage;
    public TextArea txtArea;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    final int PORT = 5001;

    public void sendOnAction(ActionEvent event) {

    }
}

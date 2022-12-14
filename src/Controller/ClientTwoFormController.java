package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTwoFormController {

    public AnchorPane clientTwoLoginContext;
    public TextArea txtArea;
    public TextField txtMessage;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    final int PORT = 5001;
    String username;

    public void initialize(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("localhost",PORT);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    setName();
                    receivedMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendOnAction(ActionEvent event) throws IOException {
        try {
            while (socket.isConnected()){
                dataOutputStream.writeUTF(txtMessage.getText());
                txtArea.appendText("\nme : " + txtMessage.getText());
                dataOutputStream.flush();
                break;
            }
        } catch (IOException e) {
            closeEveryThing(socket,dataInputStream,dataOutputStream);
            e.printStackTrace();
        }
    }

    private void closeEveryThing(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        try {
            if (dataInputStream!=null){
                dataInputStream.close();
            }
            if(dataOutputStream!=null){
                dataOutputStream.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setName() throws IOException {
        try {
            while (socket.isConnected()) {
                String name = ClientTwoLoginFormController.getName();
                System.out.println(name);
                dataOutputStream.writeUTF(name);
                dataOutputStream.flush();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receivedMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket.isConnected()){
                        message = dataInputStream.readUTF();
                        txtArea.appendText("\n"+message);
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

    public void mouseClickedOnAction(MouseEvent mouseEvent) {

    }
}

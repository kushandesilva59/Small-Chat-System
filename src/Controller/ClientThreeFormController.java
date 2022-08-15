package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThreeFormController {
    public AnchorPane clientThreeFormContext;
    public TextField txtMessage;
    public TextArea txtArea;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    final int PORT = 5001;
    String message = "";

    public void initialize(){
        try {

            socket = new Socket("localhost",PORT);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            setName();

            receivedMessages();

        } catch (IOException e) {
            closeEveryThing(socket,dataInputStream,dataOutputStream);
        }
    }

    public void sendOnAction(ActionEvent event) {

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
    public void setName() {
        try {
            while (socket.isConnected()) {
                String name = ClientOneLoginFormController.getName();
                System.out.println(name);
                dataOutputStream.writeUTF("\n"+name);
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

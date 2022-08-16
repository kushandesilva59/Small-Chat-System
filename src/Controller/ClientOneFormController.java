package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
    File file;
    int count = 0;

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

    public void sendOnAction(ActionEvent event) throws IOException {

       if(count>0){
           System.out.println("image");
       }else{
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

    public void mouseClickOnAction(MouseEvent mouseEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION) {
            File file1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file1);
            file = file1;
            txtMessage.setText(String.valueOf(file1));
            ++count;

        }
        ImageIcon imageIcon = new ImageIcon();
    }
}

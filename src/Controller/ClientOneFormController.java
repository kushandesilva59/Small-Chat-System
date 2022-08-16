package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class ClientOneFormController {
    public AnchorPane client1Context;
    public TextArea txtArea;
    public TextField txtMessage;
    public AnchorPane emojiPane;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String message = "";
    final int PORT = 5001;
    File file;
    int count = 0;

    public void initialize(){
        emojiPane.setVisible(false);
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
           System.out.println(count);
           System.out.println(file);
           ImageIcon imageIcon = new ImageIcon(String.valueOf(file));

           BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(dataOutputStream);

           Image image = imageIcon.getImage();

           BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);

           Graphics graphics = bufferedImage.createGraphics();
           graphics.drawImage(image,0,0,null);
           graphics.dispose();
           ImageIO.write(bufferedImage,"png",bufferedOutputStream);



           bufferedOutputStream.close();
           closeEveryThing(socket,dataInputStream,dataOutputStream);

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
           // System.out.println(file1);
            file = file1;
            //txtMessage.setText(String.valueOf(file1));
            ImageIcon imageIcon = new ImageIcon(String.valueOf(file1));
            ++count;

        }

    }

    public void emojiClickOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void smile1ClickOnAction(MouseEvent mouseEvent) {
        txtMessage.appendText("\uD83D\uDE42");
    }

    public void smile2ClickOnAction(MouseEvent mouseEvent) {
        txtMessage.appendText("\uD83D\uDE03");
    }

    public void smile3ClickOnAction(MouseEvent mouseEvent) {
        txtMessage.appendText("\uD83D\uDE05");
    }
}

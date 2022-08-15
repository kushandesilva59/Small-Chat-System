package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Clients implements Runnable {
    public static ArrayList<Clients> clients = new ArrayList<>();
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String userName;

    public Clients(Socket socket){
        try{
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            userName = dataInputStream.readUTF();
            clients.add(this);
            appendMessage("Server : "+userName +"has entered the chat");

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    @Override
    public void run() {
        String message ="";

        while(socket.isConnected()){
            try {
                message = dataInputStream.readUTF();
                appendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    closeAll(socket,dataOutputStream,dataInputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private void closeAll(Socket socket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
        removeClientHandler();
        try {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClientHandler() throws IOException {
        clients.remove(this);
        appendMessage("Server : " + userName + " has left the chat");
    }

    private void appendMessage(String message){
        for (Clients clients : clients){
            try{
                if(!clients.userName.equals(userName)){
                    clients.dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}

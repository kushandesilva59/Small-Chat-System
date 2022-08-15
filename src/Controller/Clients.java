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

    public Clients(Socket socket) throws IOException {
        this.socket = socket;

        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        userName = dataInputStream.readUTF();
        clients.add(this);

    }

    public Clients(){}

    @Override
    public void run() {

    }
}
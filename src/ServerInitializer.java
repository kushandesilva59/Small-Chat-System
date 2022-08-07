import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitializer {
    Socket sockets [];
    static int count ;
    ServerSocket serverSocket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    static int length = 2;

    public void initSize(int length){
        sockets = new Socket[length];
    }

    public void setSize(){
        if(count >= length){
            length = length*2;
            Socket [] temp = new Socket[length];

            for (int i=0;i<length;i++){
                temp[i] = sockets[i];
            }
            sockets = temp;
        }
    }


}

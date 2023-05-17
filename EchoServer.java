import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PrimitiveIterator;


public class EchoServer {
    public static void main(String[] args) {

        try {
            System.out.println("Waiting and WAITING for clients....... 🥱🥱🥱🥱");
            ServerSocket serverSock = new ServerSocket(9806);
            Socket soc = serverSock.accept(); // will return a socket object as soon as it gets a connection
            System.out.println("Connection established babeeeyeyy 🐏🐏🐏🐏🎉🎉");
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = in.readLine();
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("Server says: " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

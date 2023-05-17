import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;


public class EchoServer {
    public static void main(String[] args) {

        List<ClientHandler> handlers = new ArrayList<ClientHandler>();

        try {
            System.out.println("Waiting and WAITING for clients....... 🥱🥱🥱🥱");
            ServerSocket serverSock = new ServerSocket(9806);

            while(true) {
                Socket newSoc = serverSock.accept();
                ClientHandler newHandler = new ClientHandler(newSoc);
                newHandler.start();
                handlers.add(newHandler);
            }
            // ClientHandler handler = new ClientHandler(soc);
            // handler.start();

            // handler.join();
            // serverSock.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

class ClientHandler extends Thread {

    private Socket soc;

    public ClientHandler(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        System.out.println("Connection established babeeeyeyy 🐏🐏🐏🐏🎉🎉\nPort no. " + String.valueOf(soc.getPort()));
        try (BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
            String str = in.readLine();
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("Server says: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
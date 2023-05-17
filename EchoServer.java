import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class EchoServer {
    public static void main(String[] args) {

        List<ClientHandler> handlers = new ArrayList<>();

        // List<Message> messages = new ArrayList<>();

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
    // private List<Message> messages;

    public ClientHandler(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        System.out.println("Connection established babeeeyeyy 🐏🐏🐏🐏🎉🎉\nPort no. " + String.valueOf(soc.getPort()));
        try (ObjectInputStream inputStream = new ObjectInputStream(soc.getInputStream())) {
            Object receivedObj = inputStream.readObject();

            if(receivedObj instanceof Message) {
                Message message = (Message) receivedObj;
                System.out.println("Received message '" + message.getMessage() + "' from sender: " + String.valueOf(message.getOrigin()));
            }

            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        // try (BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()))) {
        //     String str = in.readLine();
        //     PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
        //     out.println("Server says: " + str);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

}
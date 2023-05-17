import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class EchoServer {

    static List<Message> messages = new ArrayList<>();
    static List<ClientHandler> handlers = new ArrayList<>();

    public static void printMessages() {
        System.out.println("Messages: \n\n");
        for (Message message : messages) {
            System.out.println(message.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
      
        try {
            System.out.println("Waiting and WAITING for clients....... 🥱🥱🥱🥱");
            ServerSocket serverSock = new ServerSocket(9806);

            while(true) {
                Socket newSoc = serverSock.accept();
                ClientHandler newHandler = new ClientHandler(newSoc, messages);
                newHandler.start();
                handlers.add(newHandler);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

class ClientHandler extends Thread {

    private Socket soc;
    private List<Message> messages;

    public ClientHandler(Socket soc, List<Message> messages) {
        this.soc = soc;
        this.messages = messages;
    }

    @Override
    public void run() {
        System.out.println("Connection established babeeeyeyy 🐏🐏🐏🐏🎉🎉\nPort no. " + String.valueOf(soc.getPort()));

        boolean exit = false;

        try (ObjectInputStream inputStream = new ObjectInputStream(soc.getInputStream())) {
                while(!exit) {
                Object receivedObj = inputStream.readObject();
                if(receivedObj instanceof Message) {
                        Message message = (Message) receivedObj;
                        message.setOrigin(soc.getPort());
                        if(message.getMessage() == "exit") {
                            exit = true;
                        } else {
                            System.out.println("Received message '" + message.getMessage() + "' from sender: " + String.valueOf(message.getOrigin()));
                            messages.add(message);
                        }
                    }
                    // inputStream.close();
                }
                } catch (IOException | ClassNotFoundException e) {
        }
        EchoServer.printMessages();
    }

}

interface ListObserver {
    void onModified();
}
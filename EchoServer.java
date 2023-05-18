import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class EchoServer {

    static List<Message> messages = new ArrayList<>();
    static List<ClientHandler> handlers = new ArrayList<>();

    public static void printMessages() {
        System.out.println("Messages: \n\n");
        for (Message message : messages) {
            System.out.println("Message: " + message.getMessage() + "\nSender: " + message.getSenderName() + "\n\n");
        }
    }

    static Function addMessage = (Message message) -> {
        messages.add(message);
        for (ClientHandler handler : handlers) {
            handler.onModified();
        }
    };

    public static void main(String[] args) {
        try {
            System.out.println("Waiting and WAITING for clients....... 🥱🥱🥱🥱");
            ServerSocket serverSock = new ServerSocket(9806);
            while(true) {
                Socket newSoc = serverSock.accept();
                ClientHandler newHandler = new ClientHandler(newSoc, addMessage);
                newHandler.onModified();
                newHandler.start();
                handlers.add(newHandler);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

class ClientHandler extends Thread implements ListObserver {

    private Socket soc;
    // private List<Message> messages;
    private Function addMessage;
    private ObjectOutputStream out;

    public ClientHandler(Socket soc, Function addMessage) throws IOException {
        this.soc = soc;
        // this.messages = messages;
        this.addMessage = addMessage;
        this.out = new ObjectOutputStream(soc.getOutputStream());
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
                            System.out.println("Received message '" + message.getMessage() + "' from sender: " + message.getSenderName());
                            // messages.add(message);
                            addMessage.execute(message);
                        }
                    }
                    // inputStream.close();
                }
                } catch (IOException | ClassNotFoundException e) {}
        // EchoServer.printMessages();
    }

    @Override
    public void onModified() {
        // EchoServer.printMessages();
        // for (ClientHandler handler : EchoServer.handlers) {
        //     // Socket soc = handler.soc;
            // try (ObjectOutputStream out = new ObjectOutputStream(handler.soc.getOutputStream())) {
            //     for (Message message : EchoServer.messages) {
            //         out.writeObject(message);
            //         out.flush();
            //     }
            //     // out.writeObject(out);
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        // }
        try {
            for (Message message : EchoServer.messages) {
                out.writeObject(message);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n\nMESSAGE SENT\n\n");

    }
}

interface ListObserver {
    void onModified();
}

interface Function {
    void execute(Message argument);
}
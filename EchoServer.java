import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {

    public static void main(String[] args) {
        List<Message> messages = new ArrayList<>();
        List<ClientHandler> handlers = new ArrayList<>();
    
        Function addMessage = (Message message) -> {
            messages.add(message);
            for (ClientHandler handler : handlers) {
                handler.onModified();
            }
        };

        try {
            System.out.println("Waiting and WAITING for clients....... 🥱🥱🥱🥱");
            ServerSocket serverSock = new ServerSocket(9806);
            while(true) {
                Socket newSoc = serverSock.accept();
                ClientHandler newHandler = new ClientHandler(newSoc, addMessage, messages);
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
    private Function addMessage;
    private ObjectOutputStream out;
    private List<Message> messages;

    public ClientHandler(Socket soc, Function addMessage, List<Message> messages) throws IOException {
        this.soc = soc;
        this.addMessage = addMessage;
        this.out = new ObjectOutputStream(soc.getOutputStream());
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
                            System.out.println("Received message '" + message.getMessage() + "' from sender: " + message.getSenderName());
                            addMessage.execute(message);
                        }
                    }
                }
                } catch (IOException | ClassNotFoundException e) {}
    }

    @Override
    public void onModified() {
        try {
            for (Message message : messages) {
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
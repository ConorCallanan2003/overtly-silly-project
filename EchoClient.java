import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoClient {

    static List<Message> messages = new ArrayList<>();

    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        try (Socket soc = new Socket("localhost", 9806)) {
            SenderThread senderThread = new SenderThread(soc);
            senderThread.start();

            boolean exit = false;
            ObjectInputStream inputStream = new ObjectInputStream(soc.getInputStream());

            while(!exit) {
                Object receivedObj = inputStream.readObject();
                if(receivedObj instanceof Message) {
                        Message message = (Message) receivedObj;
                        message.setOrigin(soc.getPort());
                        // EchoClient.messages.add(message);;
                        System.out.println("message: " + message.getMessage());
                    }
                    else {
                        exit = true;
                    }
                } 
            System.out.println(messages.size());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}


class SenderThread extends Thread {

    private Socket soc;

    public SenderThread(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        System.out.println("AYOOOO, client is kicking up!!! 🤩😎💯💯🔥🔥🔥");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream())) {
            boolean exit = false;
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Wassup bbgorl, what is your name: ");
            String name = userInput.readLine();
            while(!exit) {
                System.out.println("Wassup bbgorl, enter a string for me: ");
                String message = userInput.readLine();
                try {
                    outputStream.writeObject(new Message(message, name));
                    outputStream.flush();
                    if(message.equals("exit")) {
                        exit = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
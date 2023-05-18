import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EchoClient {

    static List<Message> messages = new ArrayList<>();
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("IP: ");

        String ip = scanner.nextLine();

        System.out.println("Port Number: ");

        int portnum = Integer.parseInt(scanner.nextLine());

        System.out.println("What is your name? ");

        String name = scanner.nextLine();
        
        // Semaphore mutex = new Semaphore(0, false);

        try (Socket soc = new Socket(ip, portnum)) {
            SenderThread senderThread = new SenderThread(soc, name);
            senderThread.start();

            boolean exit = false;
            ObjectInputStream inputStream = new ObjectInputStream(soc.getInputStream());

            while(!exit) {
                Object receivedObj = inputStream.readObject();
                if(receivedObj instanceof Message) {
                        Message message = (Message) receivedObj;
                        message.setOrigin(soc.getPort());
                        // EchoClient.messages.add(message);
                        System.out.println(message.getSenderName() + ": " + message.getMessage());
                    }
                    else {
                        exit = true;
                    }
                } 
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}


class SenderThread extends Thread {

    private Socket soc;
    private String name;

    public SenderThread(Socket soc, String name) {
        this.soc = soc;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("AYOOOO, client is kicking up!!! 🤩😎💯💯🔥🔥🔥");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream())) {
            boolean exit = false;
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while(!exit) {
                // System.out.print("Send Message: ");
                String message = userInput.readLine();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
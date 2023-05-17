import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {

        // MainWindow mainWindow = new MainWindow();
        // mainWindow.setVisible(true);

        try (Socket soc = new Socket("localhost", 9806);
        ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream());) {
            System.out.println("AYOOOO, client is kicking up!!! 🤩😎💯💯🔥🔥🔥");
            
            boolean exit = false;
            
            while(!exit) {
                
                System.out.println("Wassup bbgorl, enter a string for me: ");
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message = userInput.readLine();
                outputStream.writeObject(new Message(message));
                outputStream.flush();
                if(message.equals("exit")) {
                    exit = true;
                } 
                // String str = userInput.readLine();
            }
            // outputStream.close();
            // soc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class SenderThread extends Thread {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }

}
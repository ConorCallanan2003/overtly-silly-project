import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        try {
            System.out.println("AYOOOO, client is kicking up!!! 🤩😎💯💯🔥🔥🔥");
            Socket soc = new Socket("localhost", 9806);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Wassup bbgorl, enter a string for me: ");
            
            String str = userInput.readLine();
            
            ObjectOutputStream outputStream = new ObjectOutputStream(soc.getOutputStream());

            outputStream.writeObject(new Message("Test message!"));
            outputStream.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(in.readLine());

            soc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // try {
        //     System.out.println("AYOOOO, client is kicking up!!! 🤩😎💯💯🔥🔥🔥");
        //     Socket soc = new Socket("localhost", 9806);
        //     BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        //     System.out.println("Wassup bbgorl, enter a string for me: ");
        //     String str = userInput.readLine();
        //     PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
        //     out.println(str);
        //     BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        //     System.out.println(in.readLine());

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatBotServer {
    public static void main(String[] args) {
        int portNumber = 12345; //choosing a port number uwu

        try {
            // create a ServerSocket object called serverSocket
            // pass port no. into the constructor
            // This initializes the server socket and starts listening on the specified port for incoming client connections.
            ServerSocket serverSocket = new ServerSocket(portNumber);
            // print a message to indicate that the chat bot server is running and listening on the specified port.
            System.out.println("Chat bot server is now running in port " + portNumber);

            // serverSocket.accept() method is called, which blocks until a client connects to the server.
            // Once a connection is established, it returns a Socket object called clientSocket
            // representing the client-server connection.
            Socket clientSocket = serverSocket.accept();
            // We print a message to indicate that a client has connected, displaying the client's host name obtained from
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());
            // To read the client's messages, we create a BufferedReader called inputReader
            // and pass the InputStreamReader created from clientSocket.getInputStream() to its constructor.
            // This allows us to read the client's messages from the input stream of the socket.
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // To send responses back to the client, we create a PrintWriter called outputWriter
            // pass the OutputStream obtained from clientSocket.getOutputStream() to its constructor.
            // The true argument passed as the second parameter enables
            // automatic flushing of the output writer after each print operation.
            PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            // enter a while loop to continuously listen for incoming messages from the client using inputReader.readLine().
            // This method blocks until a message is received from the client.
            // The received message is stored in the clientMessage variable.
            String clientMessage;
            while ((clientMessage = inputReader.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                // Process the client message and generate a response
                String response = processMessage(clientMessage);
                // Send the response back to the client
                outputWriter.println(response);
                System.out.println("Server: " + response);
            }
            // Close resources in the reverse order of their creation
            outputWriter.close();
            inputReader.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to process client messages and generate a response
    private static String processMessage(String message) {
        // Implement your own logic here to process the message
        // and generate an appropriate response


        return "This is the response from the Chat Bot!";
    }
}


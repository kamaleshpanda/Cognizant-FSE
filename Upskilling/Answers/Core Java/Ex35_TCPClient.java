import java.io.*;
import java.net.*;

public class Ex35_TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;

            while (true) {
                System.out.print("Client: ");
                clientMsg = keyboard.readLine();
                output.println(clientMsg);

                if (clientMsg.equalsIgnoreCase("bye")) break;

                serverMsg = input.readLine();
                System.out.println("Server: " + serverMsg);
            }

            socket.close();
            System.out.println("Disconnected.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

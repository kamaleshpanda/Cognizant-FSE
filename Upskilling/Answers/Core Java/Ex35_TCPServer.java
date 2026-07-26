import java.io.*;
import java.net.*;

public class Ex35_TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;

            while (true) {
                clientMsg = input.readLine();
                if (clientMsg == null || clientMsg.equalsIgnoreCase("bye")) break;
                System.out.println("Client: " + clientMsg);

                System.out.print("Server: ");
                serverMsg = keyboard.readLine();
                output.println(serverMsg);
            }

            socket.close();
            serverSocket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

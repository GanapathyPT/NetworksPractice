import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server running on 3000");

            while (true) {
                Socket client = server.accept();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

                writer.println("Welcome to Java EchoServer, Type 'bye' to close connection");

                String str;
                do {
                    str = reader.readLine();
                    if (str != null)
                        writer.println("You sent: " + str);

                    System.out.println("got: " + str);
                } while (!str.trim().equals("bye"));
                
                client.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

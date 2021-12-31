import java.net.*;
import java.io.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);
            // System.out.println("Server started");

            while (true) {
                Socket client = server.accept();
    
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream clientWriter = new PrintStream(client.getOutputStream());
    
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String str;
                while (true) {
                    str = clientReader.readLine();
                    if (str != null) {
                        System.out.println("client: " + str);

                        if (str.equals("bye"))
                            break;
                    }
                    
                    str = reader.readLine();
                    clientWriter.println(str);

                    if (str.equals("bye"))
                        break;
                }   

                client.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

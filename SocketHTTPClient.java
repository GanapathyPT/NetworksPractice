import java.io.*;
import java.net.*;

public class SocketHTTPClient {
    public static void main(String[] args) {
        String hostName = "www.google.com";
        int portNumber = 80;

        try {
            Socket socket = new Socket(hostName, portNumber);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("GET / HTTP/1.1 \nHost:www.google.com\n\n");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str;
            while ((str = reader.readLine()) != null)
                System.out.println(str);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            System.exit(1);
        }
    }
}

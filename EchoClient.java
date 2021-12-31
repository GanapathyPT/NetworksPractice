import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            do {
                str = serverReader.readLine();
                if (str != null) System.out.println(str);
                
                System.out.print("Your text: ");
                str = inputReader.readLine();
                serverWriter.println(str);
            } while (!str.trim().equals("bye"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

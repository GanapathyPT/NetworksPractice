import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.ServerError;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);
            
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream serverWriter = new PrintStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String str;
            while (true) {
                System.out.print("You: ");
                str = reader.readLine();
                serverWriter.println(str);

                if (str.equals("bye"))
                    break;

                str  = serverReader.readLine();
                System.out.println("server: " + str);

                if (str.equals("bye"))
                    break;
            }
            
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

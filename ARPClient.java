import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ARPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream serverWriter = new PrintStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter IP: ");
            String ipAddress = reader.readLine();

            serverWriter.println(ipAddress);

            String macAddress = serverReader.readLine();
            System.out.println("MAC Adrres: " + macAddress);

            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

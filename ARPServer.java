import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ARPServer {
    public static void main(String[] args) {
        try {
            String[] ips = {
                "165.165.80.80","165.165.79.1"
            };
            String[] macs = {
                "6A:08:AA:C2","8A:BC:E3:FA"
            };

            ServerSocket server = new ServerSocket(3000);

            while (true) {
                Socket client = server.accept();

                BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream clientWriter = new PrintStream(client.getOutputStream(), true);

                String ipAddress = clientReader.readLine();
                System.out.println(ipAddress);
                int i=0;
                for (; i<ips.length; i++) {
                    if (ips[i].equals(ipAddress)) {
                        clientWriter.println(macs[i]); 
                        break;
                    }
                }
                if (i == ips.length)
                    clientWriter.println("NOT FOUND");

                client.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

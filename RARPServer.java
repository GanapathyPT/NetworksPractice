import java.net.*;
import java.io.*;

public class RARPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2500);
            System.out.println("Server started");

            Socket client = server.accept();
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream clientWriter = new PrintStream(client.getOutputStream());

            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("ifconfig eth0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String ethAddress = clientReader.readLine();
            
            String ipaddr = "";
            int flag = 0;
            String str;

            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                if ((str.indexOf(ethAddress)) != -1) {
                    flag = 1;
                } else if ((str.indexOf("inet addr")) != -1) {
                    int pos = str.indexOf("inet addr:") + 10;
                    int offset = pos + 13;
                    ipaddr = str.substring(pos, offset);
                }
            }
            if (flag == 1)
                clientWriter.println(ipaddr);

            clientWriter.close();
            clientReader.close();
            reader.close();
            client.close();
            server.close();

        } catch (IOException io) {
            System.err.println("Exception : " + io.toString());
        }
    }
}

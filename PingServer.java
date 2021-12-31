import java.io.*;
import java.net.*;

class PingServer {
    public static void main(String args[]) {
        try {
            System.out.print("Enter IP: ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("ping " + input);
            
            InputStream output = p.getInputStream();
            reader = new BufferedReader(new InputStreamReader(output));

            String str = reader.readLine();
            while (str != null) {
                System.out.println(str);
                str = reader.readLine();
            }
        } catch(Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
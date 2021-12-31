import java.io.*;

public class TraceRoute {
    public static void main(String[] args) {
        try {
            System.out.println("Enter IP:");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();

            Runtime rt = Runtime.getRuntime();
            // traceroute6 is only applicable for this machine
            Process p = rt.exec("traceroute6 " + input);

            InputStream output = p.getInputStream();
            reader = new BufferedReader(new InputStreamReader(output));

            String str;
            while ((str = reader.readLine()) != null)
                System.err.println(str);
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

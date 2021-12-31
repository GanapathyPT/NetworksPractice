import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);

            byte[] content = new byte[10000];
            FileOutputStream fos = new FileOutputStream("/home/ganapathy/Desktop/copied.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            InputStream serverReader = socket.getInputStream();

            //No of bytes read in one read() call int bytesRead = 0;
            int bytesRead;
            while((bytesRead = serverReader.read(content)) != -1) {
                bos.write(content, 0, bytesRead);
                bos.flush();
            }

            System.out.println("File saved successfully!");
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);

            while (true) {
                Socket client = server.accept();

                File file = new File("/home/ganapathy/Pictures/G.jpg");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                // file => file input stream => buffered input stream

                OutputStream clientWriter = client.getOutputStream();

                byte[] contents;
                long fileLength = file.length();
                long current = 0;

                while(current != fileLength) {
                    int size = 10000;
                    if (fileLength - current >= size)
                        current += size;
                    else {
                        size = (int)(fileLength - current);
                        current = fileLength;
                    }
                    contents = new byte[size];
                    bis.read(contents, 0, size);
                    clientWriter.write(contents);

                    System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!");
                }

                client.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

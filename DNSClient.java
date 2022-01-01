import java.net.*;
import java.io.*;

public class DNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipaddress;

            if (args.length == 0)
                ipaddress = InetAddress.getLocalHost();
            else
                ipaddress = InetAddress.getByName(args[0]);
            
            byte[] senddata = new byte[1024];
            byte[] receivedata = new byte[1024];
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the hostname: ");
            String hostName = reader.readLine();
            senddata = hostName.getBytes();

            DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, ipaddress, 3000);
            socket.send(sendpack);

            DatagramPacket recvpack =new DatagramPacket(receivedata, receivedata.length);
            socket.receive(recvpack);

            String hostIPAdress = new String(recvpack.getData());
            System.out.println("IP Address: " + hostIPAdress);

            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

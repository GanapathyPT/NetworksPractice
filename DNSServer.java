import java.net.*;

public class DNSServer {

    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i=0; i < array.length; i++)
            if (array[i].equals(str)) return i;
        return -1;
    }
    public static void main(String[] args) {
        try {
            String[] hosts = {
                "www.google.com", "www.yahoo.com", "www.facebook.com", "www.github.com"
            };
            String[] ips = {
                "172.28.251.59", "172.217.11.5", "172.217.11.14", "31.13.71.36" 
            };
    
            DatagramSocket server = new DatagramSocket(3000);

            while (true) {
                byte[] senddata = new byte[1024];
                byte[] receivedata = new byte[1024];

                DatagramPacket recvpack = new DatagramPacket(receivedata, receivedata.length);
                server.receive(recvpack);

                String hostName = new String(recvpack.getData());
                // client IP and port
                InetAddress ipaddress = recvpack.getAddress();
                int port = recvpack.getPort();

                System.out.println("Request for host " + hostName);
                String hostIPAddress;

                if(indexOf(hosts, hostName) != -1)
                    hostIPAddress = ips[indexOf(hosts, hostName)];
                else
                    hostIPAddress = "Host Not Found";
                    
                senddata = hostIPAddress.getBytes();
                DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, ipaddress, port);
                server.send(sendpack);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

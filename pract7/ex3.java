import java.net.*;
import java.io.*;

class NameClient {
    public static void main(String[] args) {
        try {
            String n = "Name ";
            String ln = "Surname\n";
            DatagramSocket s = new DatagramSocket();
            
            DatagramPacket dp1 = new DatagramPacket(
                n.getBytes(),
                n.getBytes().length,
                InetAddress.getByName("localhost"),
                7777
            );
            
            DatagramPacket dp2 = new DatagramPacket(
                ln.getBytes(),
                ln.getBytes().length,
                InetAddress.getByName("localhost"),
                7777
            );
            
            s.send(dp1);
            s.send(dp2);
        } catch (IOException ioe) {
            System.err.println("Error while trying to send packets");
        } catch (Exception e) {
            System.err.println("Unexpected error happened");
        }
    }
}

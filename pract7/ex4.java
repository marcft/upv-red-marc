import java.net.*;
import java.io.*;

class Ej4 {
    public static void main(String[] args) {
        try {
            String ms = "Holaaaaaaaaaa\n";
            DatagramSocket s = new DatagramSocket();
            
            DatagramPacket dp1 = new DatagramPacket(
                ms.getBytes(),
                ms.getBytes().length,
                InetAddress.getByName("localhost"),
                7777
            );
            
            s.send(dp1);
            s.receive(dp1);
            
            String text = new String(dp1.getData(), 0, dp1.getLength());
            System.out.println(text);
        } catch (IOException ioe) {
            System.err.println("Error while trying to send or receive packets");
        } catch (Exception e) {
            System.err.println("Unexpected error happened");
        }
    }
}

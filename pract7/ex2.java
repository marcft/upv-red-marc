import java.net.*;
import java.io.*;

class Ej2 {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            System.out.println("Port is: " + ds.getLocalPort());
        } catch (SocketException se) {
            System.err.println("Error with socket, check if there are available ports");
        }
    }
}

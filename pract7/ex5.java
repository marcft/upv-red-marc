import java.net.*;
import java.io.*;
import java.util.*;

class Ej5 {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(7777);
            Date time = new Date();
            String now = time.toString() + "\r\n";
            ds.setSoTimeout(5000);
            DatagramPacket p = new DatagramPacket(now.getBytes(), now.getBytes().length);
            ds.receive(p);
            p.setLength(now.length());
            p.setData(now.getBytes());
            ds.send(p);
        } catch (Exception e) {
            System.err.println("Some error happened");
        }
    }
}

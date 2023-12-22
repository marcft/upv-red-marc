import java.util.*;
import java.net.*;
import java.io.*;

class Dnslookup {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName(args[0]);
            System.out.println(ip.toString());
        } catch (UnknownHostException uhe) {
            System.err.println("Host not found");
        } catch (Exception e) {
            System.err.println("Other error happened");
        }
    }
}

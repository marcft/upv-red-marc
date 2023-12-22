// Ex 2
import java.net.*;
import java.io.*;
import java.util.*;

public class ex2 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            while (true) {
                Socket s = ss.accept();
                
                // Printing IP's and ports
                System.out.println("ServerSocket Address: " + ss.getInetAddress() + "\t ServerSocket Port: " + ss.getLocalPort());
                System.out.println("Server InetAddress: " + s.getInetAddress() + "\t Server LocalAddress: " + s.getLocalAddress());
                System.out.println("Server Port: " + s.getPort() + "\t Server LocalPort: " + s.getLocalPort());
                
                // EX1 part
                Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                String line = sc.nextLine();
                pw.print(line + "\r\n");
                pw.flush();
                System.out.println("A client has connected to the server");
                s.close();
            }
        } catch (IOException ioe) {
            System.err.println("IO Exception");
        }
    }
}

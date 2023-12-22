import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.*;

public class ConcurrentServer extends Thread {
    Socket s;

    public static void main(String[] args) {
        try {
            int port = 7777;
            int counter = 0;
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                ConcurrentServer cs = new ConcurrentServer(client);
                cs.setName("Client: " + counter);
                counter++;
                cs.start();
            }
        } catch (Exception e) {
            System.err.println("Error occurred");
        }
    }

    public ConcurrentServer(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("end"))
                    break;
                pw.print(line + "\r\n");
                pw.flush();
            }
            sc.close();
            pw.close();
        } catch (IOException ioe) {
            System.err.println("Error occurred with I/O");
        }
    }
}

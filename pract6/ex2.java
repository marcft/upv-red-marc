import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.*;

public class Chat extends Thread {
    Socket s;

    public static void main(String[] args) {
        try {
            int port = 7777;
            Socket s = new Socket("ecomp00.disca.upv.es", port);
            Chat ch = new Chat(s);
            ch.start();
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String l = sc.nextLine();
                if (l.equalsIgnoreCase("quit"))
                    break;
                pw.print("|Username| -> " + l + "\r\n");
                pw.flush();
            }
            pw.close();
            sc.close();
        } catch (Exception e) {
            System.err.println("Error occurred");
        }
    }

    public Chat(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            Scanner sc = new Scanner(s.getInputStream());
            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("quit"))
                    break;
                System.out.println(line);
            }
            sc.close();
        } catch (IOException ioe) {
            System.err.println("Error occurred with I/O");
        }
    }
}

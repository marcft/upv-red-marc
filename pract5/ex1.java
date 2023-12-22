// Ex 1
import java.net.*;
import java.io.*;
import java.util.*;

public class ex1 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            while (true) {
                Socket s = ss.accept();
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

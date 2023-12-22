// Ex 3
import java.net.*;
import java.io.*;
import java.util.*;

public class ex3 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (true) {
                Socket s = ss.accept();
                
                // Receive the request
                Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                String line = sc.nextLine();
                
                if (line.startsWith("GET")) {
                    pw.print("HTTP/1.0 200 OK \r\n");
                    pw.flush();
                    pw.print("Content-Type: text/plain \r\n\r\n");
                    pw.flush();
                    pw.print(line + "\r\n");
                    pw.flush();
                    
                    line = sc.nextLine();
                    while (!line.isEmpty()) {
                        pw.print(line + "\r\n");
                        pw.flush();
                        line = sc.nextLine();
                    }
                } else {
                    System.out.println("The request does not start with GET");
                }
                
                pw.close();
                sc.close();
                s.close();
            }
        } catch (IOException ioe) {
            System.err.println("IO Exception");
        } catch (Exception e) {
            System.err.println("Other exception occurred");
        }
    }
}

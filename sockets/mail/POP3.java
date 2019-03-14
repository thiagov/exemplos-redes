import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class POP3 {
    public static void main(String[] argc) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 3110);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        String reply = in.readLine();
        System.out.println("S : " + reply);

        send("USER novoTeste@localhost\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);

        send("PASS novoTeste@localhost\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);

        send("STAT\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);

        send("LIST\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);
        readListAndRetr(in);

        send("RETR 1\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);
        readListAndRetr(in);

        send("QUIT\r\n", out);
        reply = in.readLine(); 
        System.out.println("S : " + reply);

        socket.close();
        System.out.println("Closed Connection with Server");
    }

    private static void send(String s, PrintWriter out) {
        out.print(s);
        out.flush();
        System.out.print("C: " + s);
    }

    private static void readListAndRetr(BufferedReader in) throws IOException {
        String line = in.readLine();
        System.out.println("S: " + line);
        while (!line.equals(".")) {
            line = in.readLine();
            System.out.println("S: " + line);
        }
    }
}

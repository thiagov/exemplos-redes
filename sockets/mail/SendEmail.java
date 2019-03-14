import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SendEmail {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 3025);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        printResponse(in);
        send("HELO localhost\r\n", out);
        printResponse(in);
        send("MAIL FROM:<teste@localhost>\r\n", out);
        printResponse(in);
        send("RCPT TO:<novoTeste@localhost>\r\n", out);
        printResponse(in);
        send("DATA\r\n", out);
        printResponse(in);
        send("Subject: Email test\r\n", out);
        send("From: teste@gmail.com\r\n", out);
        send("\r\n", out);
        send("Test 1 2 3\r\n", out);
        send("Corpo da mensagem\r\n", out);
        send(".\r\n", out);
        printResponse(in);
        send("QUIT\r\n", out);
        printResponse(in);

        socket.close();
    }

    private static void send(String s, PrintWriter out) {
        out.print(s);
        out.flush();
        System.out.print("C: " + s);
    }

    private static void printResponse(BufferedReader in) throws IOException {
        String line;
        line = in.readLine();
        System.out.println("S: " + line);
        while (line.charAt(3) == '-') {
            line = in.readLine();
            System.out.println("S: " + line);
        }
    }
}

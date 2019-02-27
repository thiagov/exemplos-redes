import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class HttpClient {
    public static void main(String[] args) throws IOException, UnknownHostException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(address, 3000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Envia dados para o servidor
        out.println("GET /home HTTP/1.1");
        out.println("Host: localhost");
        out.println("User-Agent: Simple Http Client");
        out.println("Accept: text/html");
        out.println("Accept-Language: pt");
        out.println("Connection: close");
        out.println();

        // Le resposta
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
        clientSocket.close();
    }
}

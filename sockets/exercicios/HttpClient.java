import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClient {
    public static void main(String[] args) throws IOException, UnknownHostException {
        String serverName = "httpbin.org";

        InetAddress address = InetAddress.getByName(serverName);
        Socket clientSocket = new Socket(address, 80);

        // Envia dados para o servidor
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.print("GET /get HTTP/1.1\r\n");
        out.print("Host: " + serverName + "\r\n");
        out.print("User-Agent: Http cliente em java\r\n");
        out.print("Accept: */*\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");
        out.flush();

        // Le resposta
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        // Abre nova conexão com o servidor
        clientSocket = new Socket(address, 80);

        // Envia dados para o servidor
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        String corpo = "{\"nome\": \"thiago\", \"sobrenome\": \"vilela\"}";
        out.print("POST /post HTTP/1.1\r\n");
        out.print("Host: " + serverName + "\r\n");
        out.print("User-Agent: Simple Http Client\r\n");
        out.print("Accept: application/json" + "\r\n");
        out.print("Connection: close\r\n");
        out.print("Content-Type: application/json\r\n");
        out.print("Content-Length: " + corpo.length() + "\r\n");
        out.print("\r\n");
        out.print(corpo + "\r\n");
        out.flush();

        // Le resposta
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
        clientSocket.close();
    }
}

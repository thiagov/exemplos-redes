import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPEchoClient {
    public static void main(String[] args) throws IOException, UnknownHostException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(address, 4445);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        boolean running = true;

        while (running) {
            // Pega input do usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entre com uma mensagem: ");
            String message = scanner.nextLine();

            // Envia dados para o servidor
            out.println(message);
            // Le resposta
            String response = in.readLine();
            System.out.println("\nServidor respondeu com: " + response + "\n");

            if (message.equals("end")) {
                running = false;
            }
        }

        in.close();
        out.close();
        clientSocket.close();
    }
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buf = new byte[256];
        boolean running = true;

        try {
            while (running) {
                // Pega input do usuario
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entre com uma mensagem: ");
                String message = scanner.nextLine();

                // Envia mensagem para o servidor
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length,
                        address, 4445);
                socket.send(packet);

                // Recebe resposta do servidor 
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String response = new String(packet.getData(), 0, packet.getLength());

                System.out.println("\nServidor respondeu com: " + response + "\n");

                if (message.equals("end")) {
                    running = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class UdpPinger {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(1000);
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buf = new byte[256];

        try {
            String mensagem = "ping";
            long startTime;
            long endTime;
            for (int i = 0; i < 10; i++) {
                DatagramPacket packet = new DatagramPacket(mensagem.getBytes(), mensagem.getBytes().length,
                        address, 4445);
                startTime = System.currentTimeMillis();
                socket.send(packet);
                try {
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    endTime = System.currentTimeMillis();
                    String response = new String(packet.getData(), 0, packet.getLength());
                    if (response.equals("pong")) {
                        System.out.println("Tempo em milisegundos: " + (endTime - startTime));
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Pacote de resposta possivelmente se perdeu!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

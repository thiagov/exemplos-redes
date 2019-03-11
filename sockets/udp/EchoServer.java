import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(4445);
        boolean running = true;
        byte[] buf = new byte[256];

        try {
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength());
                packet = new DatagramPacket(received.getBytes(), received.getBytes().length, address, port);
                socket.send(packet);

                if (received.equals("end")) {
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

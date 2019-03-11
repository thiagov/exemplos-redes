import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ThreadLocalRandom;

public class UdpPingerServer {
    public static void main(String[] args) throws SocketException, InterruptedException {
        DatagramSocket socket = new DatagramSocket(4445);
        byte[] buf = new byte[256];

        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.equals("ping")) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                    Thread.sleep(randomNum);
                    packet = new DatagramPacket("pong".getBytes(), "pong".getBytes().length, address, port);
                    socket.send(packet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

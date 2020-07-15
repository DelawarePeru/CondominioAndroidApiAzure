package pe.com.condominioandroidapi.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Helper {

    /**
     * Verificamos si tenemos conexión a internet.
     *
     * @return True si tenemos o false en otro caso.
     */
    public static boolean isOnline() {
        try {
            int timeoutMs = 5000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

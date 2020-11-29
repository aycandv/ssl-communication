package server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {
    private final int port;

    private Socket socket;
    private ServerSocket serverSocket;

    public TCPServer(int port) {
        System.out.println("TCPServer is starting...");
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCPServer is started.");
            System.out.println("Listening for connection requests...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        listenAndAccept();
    }

    private void listenAndAccept() {
        try {
            socket = serverSocket.accept();
            System.out.println("A new client is found. Connection is established with " + socket.getRemoteSocketAddress());
            TCPServerThread tcpServerThread = new TCPServerThread(socket);
            tcpServerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

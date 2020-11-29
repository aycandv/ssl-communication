package server.tcp;

public class Main {
    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer(4444);
        tcpServer.start();
    }
}

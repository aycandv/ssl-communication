package server;

import constants.Constants;
import server.ssl.SSLServer;
import server.tcp.TCPServer;

public class Main {
    public static void main(String[] args) {
        new TCPServer(Constants.TLS_CERTIFICATE_PORT);
        new SSLServer(Constants.TLS_SERVER_PORT);
    }
}

package client;

import constants.Constants;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FetchCertificate {
    public static void main(String[] args) {
        FetchCertificate fetchCertificate = new FetchCertificate(Constants.SERVER_NAME, Constants.TLS_CERTIFICATE_PORT);
    }

    private Socket socket;

    public FetchCertificate(String address, int port) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCertificate() {
        Scanner scanner = new Scanner(System.in);


    }
}

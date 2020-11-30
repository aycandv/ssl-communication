package client;

import constants.Constants;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FetchCertificate {
    public static void main(String[] args) {
        FetchCertificate fetchCertificate = new FetchCertificate(Constants.SERVER_NAME, Constants.TLS_CERTIFICATE_PORT);
        fetchCertificate.getCertificate();
    }

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public FetchCertificate(String address, int port) {
        try {
            socket = new Socket(address, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCertificate() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(bufferedReader.readLine());

            String answer = scanner.nextLine();
            printWriter.println(answer);
            printWriter.flush();

            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int len;
        byte[] bytes = new byte[Constants.BUFFER_SIZE];
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/client/data/server_crt.crt")) {
            while ((len = socket.getInputStream().read(bytes)) > 0) {
                if (bytes[len - 1] == 26) {
                    // Break if EOF is received
                    fileOutputStream.write(bytes, 0, len - 1);
                    System.out.println("Certificate is dowloaded and saves in client/data folder.");
                    break;
                } else {
                    fileOutputStream.write(bytes, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package server.tcp;

import constants.Constants;

import java.io.*;
import java.net.Socket;

public class TCPServerThread extends Thread{

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public TCPServerThread(Socket socket) {
        try {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        try {
            if(isAuthorized()) {
                System.out.println("Certificate is going to be sent to user " + socket.getLocalAddress() + ":" + socket.getLocalPort());
                printWriter.println("You are authorized. Certificate is forwarding.");
                printWriter.flush();
                sendCertificate();
            } else {
                printWriter.write("You are not authorized. Closing TCP connection.");
                disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void disconnect() throws IOException {
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
        socket.close();
        System.out.println("Authorization is failed. User is kicked from the server.");
    }

    private void sendCertificate() {
        File file = new File(Constants.SERVER_CERTIFICATE_DIR);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[Constants.BUFFER_SIZE];
            int len;
            while ((len = fileInputStream.read(bytes)) > 0) {
                socket.getOutputStream().write(bytes, 0, len);
            }
            socket.getOutputStream().write(26);
            System.out.println("Certificate is sent to the client via TCP");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isAuthorized() throws IOException {
        String passcode = new BufferedReader(new FileReader(Constants.USER_INFO)).readLine();
        String userInput = userPasscode();

        return userInput.contains(passcode);
    }

    private String userPasscode() {
        String userInput = "<<invalid>>";
        try {
            printWriter.println("You are connected to Server via TCP. Please enter your passcode to get certificate from Server:");
            printWriter.flush();
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User -> " + userInput);
        return userInput;
    }
}

package server.tcp;

import constants.Constants;

import java.io.*;
import java.net.Socket;

public class TCPServerThread extends Thread{
    private boolean isAuthorized = false;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public TCPServerThread(Socket socket) {
        try {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        try {
            if(isAuthorized()) {
                System.out.println("Certificate is going to be sent to user " + socket.getInetAddress());
                sendCertificate();
            } else {
                bufferedWriter.write("You are not authorized. Closing TCP connection.");
                bufferedWriter.close();
                bufferedReader.close();
                socket.close();
                System.out.println("Authorization is failed. User is kicked from the server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void sendCertificate() {
        File file = new File("server_crt.crt");

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
        String passcode = new BufferedReader(new FileReader("src/main/java/server/tcp/user_info.txt")).readLine();
        String userInput = userPasscode();

        return userInput.contains(passcode);
    }

    private String userPasscode() {
        String userInput = "<<invalid>>";
        try {
            bufferedWriter.write("You are connected to Server via TCP. Please enter your passcode to get certificate from Server.\nPasscode");
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User -> " + userInput);
        return userInput;
    }
}

package constants;

public class Constants {
    public static final int BUFFER_SIZE = 2048;
    public static final int TLS_SERVER_PORT = (59998 + 6) % 64512;
    public static final int TLS_CERTIFICATE_PORT = 4444;

    public static final String SERVER_NAME = "localhost";
    public static final String USER_INFO = "src/main/java/server/data/user_info.txt";
    public static final String SERVER_CERTIFICATE_DIR = "src/main/java/server/data/server_crt.crt";
    public static final String CLIENT_CERTIFICATE_DIR = "src/main/java/client/data/server_crt.crt";
    public static final String CLIENT_KEY_STORE_DIR = "src/main/java/client/data/clientkeystore";

}

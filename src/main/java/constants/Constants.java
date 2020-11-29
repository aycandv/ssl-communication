package constants;

public class Constants {
    public static final int BUFFER_SIZE = 2048;
    public static final int TLS_SERVER_PORT = (59998 + 6) % 64512;
    public static final int TLS_CERTIFICATE_PORT = 4444;

    public static final String SERVER_NAME = "localhost";
}

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {
        int port = 8010;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000); // Set the timeout to 10 seconds

            System.out.println("Server is Listening on port " + port);

            while (true) {
                try {
                    Socket acceptedConnection = serverSocket.accept();
                    // Handle the connection here

                    System.out.println("Connection is accepted from the client" + acceptedConnection.getRemoteSocketAddress());
                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run(); // Start the server
    }
}

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    BufferedReader fromClint = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                    toClient.println("Hello from the server");

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
=======

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.util.function.Consumer;



public class Server {

    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{
            try {
               PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
               toClient.println("Hello from the Server");
               toClient.close();
               clientSocket.close(); 
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    
    }

    

     public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is Listening to "+ port);
            while (true) { 
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}   
>>>>>>> 1fd843b (Implemented multithreaded server using lambdas and consumer interface)

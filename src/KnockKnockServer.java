import java.net.*;
import java.io.*;
 
public class KnockKnockServer {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }
 
        int portNumber = Integer.parseInt(args[0]);
 
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
         
            String inputLine;
            int outputLine;
             
            // Initiate conversation with client
            LoggingProtocol lp = new LoggingProtocol();
            outputLine = lp.processInput(null);
            out.println(outputLine);
            int init = 1 ;
            inputLine = in.readLine();
            while (init !=0) 
            {
                init = lp.processInput(inputLine);
                out.println(outputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

import java.io.*;
import java.net.*;

import com.hackbulgaria.corejava.RBIConstants;
import com.hackbulgaria.corejava.RBIProtocol;

public class Rbi_client {
    
    //String hostname = "192.168.1.253";
    public static void main(String[] args) throws UnknownHostException, IOException {
        String ServerIp = "192.168.1.253";
        String messagetoUser = new String();
        
        try (Socket client = new Socket(ServerIp, RBIConstants.PORT); )
        {
        System.out.println("Connection successfull");
        try ( BufferedReader stdIn = new BufferedReader(
                                    new InputStreamReader(System.in))
            ) {
                String userInput;
                System.out.println("ENTER SOMETHING");
                while ((userInput = stdIn.readLine()) != "Q") {
                    RBIProtocol.writeRBIMessage(userInput, client);
                    System.out.println("#####");
                    messagetoUser = RBIProtocol.readRBIMessage(client);
                    System.out.println(messagetoUser + "END");
                    System.out.println("OMG IS IT WORKING ?!");
                }
             }
        }
    }
}


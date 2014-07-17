package com.hackbulgaria.corejava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RBIProtocol {


    public static String readRBIMessage(Socket connection) throws IOException {
       
        final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final StringBuilder builder = new StringBuilder();
        System.out.println("READ RBI MESSAGE ! ");
        String line;
        while (true){
            line = reader.readLine();
            if (line == null || line.equals(RBIConstants.EOM)){
                break;
            }
            builder.append(line).append(System.lineSeparator());
        }
        return builder.toString();
    }

    public static void writeRBIMessage(String message, Socket connection) throws IOException {
        System.out.println(message);
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.write(message + "\n");
        writer.write(RBIConstants.EOM + "\n");
        writer.flush();
        System.out.println(message);
        // writer.close();
    }

}

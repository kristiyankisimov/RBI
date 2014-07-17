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

        String line;
        do {
            line = reader.readLine();
            if (line.equals(RBIConstants.EOM)) {
                builder.append(line + System.lineSeparator());
            } else {
                builder.append(line);
            }

        } while (!line.equals(RBIConstants.EOM));
        // builder.append(RBIConstants.EOM);

        return builder.toString();
    }

    public static void writeRBIMessage(String message, Socket connection) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        message = message + System.lineSeparator() + RBIConstants.EOM;
        writer.write(message);
        writer.flush();
        // writer.close();
    }

}

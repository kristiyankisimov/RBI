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

        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line + System.lineSeparator());
            line = null;
        }
        builder.append(RBIConstants.EOM);
        reader.close();
        return builder.toString();
    }

    public static void writeRBIMessage(String message, Socket connection) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.write(message);
        writer.flush();
        writer.close();
    }

}

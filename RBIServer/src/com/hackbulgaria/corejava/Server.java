package com.hackbulgaria.corejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Server {
    private ServerSocket server;
    // private final int port;
    private Socket client;

    // public Server(int port) {
    // this.port = port;
    // }

    public void runServer() throws IOException {
        // System.out.println("Starting the server at port: " + port);
        server = new ServerSocket(RBIConstants.PORT);
        // System.out.println("Waiting for clients....");
        client = server.accept();
        final String message = String.format(
                "Start of command execution: %s\nCommand output:\n%s\nEnd of command execution: %s\n", getTime(),
                getCommandOutput(), getTime());
        RBIProtocol.writeRBIMessage(message, client);
    }

    // private String getCommandString() throws IOException {
    // final BufferedReader input = new BufferedReader(new
    // InputStreamReader(client.getInputStream()));
    // return input.readLine();
    // }

    private Process executeCommand() throws IOException {
        return Runtime.getRuntime().exec(RBIProtocol.readRBIMessage(client));
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private String getCommandOutput() throws IOException {
        final Process process = executeCommand();
        final BufferedReader stdIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final StringBuffer commandOutput = new StringBuffer();
        String line = null;
        while ((line = stdIn.readLine()) != null) {
            commandOutput.append(line);
            commandOutput.append(System.lineSeparator());
            line = null;
        }
        return commandOutput.toString();
    }

    // private void sendCommandOutput() throws IOException {
    // final BufferedWriter writer = new BufferedWriter(new
    // OutputStreamWriter(client.getOutputStream()));
    // writer.write(String.format(
    // "Start of command execution: %s\nCommand output:\n%s\nEnd of command execution: %s\n",
    // getTime(),
    // getCommandOutput(), getTime()));
    // writer.flush();
    // }
    public static void main(String[] args) {

        final Server server = new Server();
        try {
            server.runServer();
            // server.closeConnection();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}

package com.example.melone;

import javax.swing.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ArrayClient extends Thread
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MultiServer server;
    ArrayList<Socket> client = new ArrayList<Socket>();

    /*
    public ArrayClient(MultiServer server, Socket socket) throws IOException{
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }*/

    
    public ArrayClient() {
    }

    public void AggiungiClient(Socket x)
    {
        client.add(x);
    }


    public ArrayList<Socket> getClient() {
        return this.client;
    }

    public void setClient(ArrayList<Socket> client) {
        this.client = client;
    }
    
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            // TODO: Here you HAVE to check if the connection was closed
            // And if it was closed, call a method in the server class to
            // remove this client.
            e.printStackTrace();
        }
    }
}
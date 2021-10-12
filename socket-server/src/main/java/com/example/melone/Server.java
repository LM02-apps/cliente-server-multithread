package com.example.melone;

import java.io.*;
import java.net.*;
import java.util.*;

//classe main del server di tipo thread
public class Server extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaricevuta = null;
    String stringamodificata = null;
    BufferedReader indalclient;
    DataOutputStream outversoclient;

    public Server(Socket socket, ServerSocket server) {
        this.client = socket;
        this.server = server;
    }

    public void run() {
        try {

            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    // main server
    public static void main(String[] args) {
        MultiServer tcpserver = new MultiServer();
        tcpserver.start();
    }

    // passa la stringa al server e la mette in maiuscolo
    public void comunica() throws Exception {

        indalclient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outversoclient = new DataOutputStream(client.getOutputStream());
        for (;;) {
            stringaricevuta = indalclient.readLine();
            if (stringaricevuta == null || stringaricevuta.equals("FINE") || stringaricevuta.equals("STOP")) {

                outversoclient.writeBytes(stringaricevuta + " server in chiusura ... " + '\n');
                System.out.println("Echo sul server in chiusura: " + stringaricevuta);
                break;
            } else {
                stringamodificata = stringaricevuta.toUpperCase();
                outversoclient.writeBytes(stringamodificata + " ricevuta e trasmessa" + '\n');
                System.out.println("6 Echo sul server: " + stringamodificata);
            }
        }
        outversoclient.close();
        indalclient.close();
        System.out.println("9 chiusura socket" + client);

        client.close();
        if (stringaricevuta.equals("STOP")) {
            server.close();
        }

    }

}

package com.example.melone;

import java.io.*;
import java.net.*;
import java.util.*;

//sottoclasse
public class MultiServer {
    List<ArrayClient> clients = new ArrayList<>();
    Object lock = new Object();

    public void start() {
        try {
            ServerSocket mserversocket = new ServerSocket(6789);
            for (;;) {
                System.out.println("1 Server in attesa ... ");
                Socket msocket = mserversocket.accept();
                ArrayClient client = new ArrayClient(this, msocket);
                synchronized (lock) {
                    clients.add(client);
                }
                new Thread(client).start();

                System.out.println("3 Server socket" + msocket);
                //Server serverthread = new Server(msocket, mserversocket);
                //serverthread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore istanza server");
            System.exit(1);
        }
    }
    void distributeMessage(String message) {
        List<ArrayClient> clientsCopy;
        synchronized (lock) {
            clientsCopy = new ArrayList<>(clients);
        }
        for (ArrayClient client : clientsCopy) {
            client.sendMessage(message);
        }
    }

}
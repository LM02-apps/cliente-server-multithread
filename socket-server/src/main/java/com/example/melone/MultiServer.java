package com.example.melone;

import java.io.*;
import java.net.*;
import java.util.*;

//sottoclasse
public class MultiServer
{
    public void start()
    {
        try
        {
            ServerSocket mserversocket=new ServerSocket(6789);
            for(;;)
            {
                System.out.println("1 Server in attesa ... ");
                Socket msocket=mserversocket.accept();
                System.out.println("3 Server socket"+msocket);
                Server serverthread=new Server(msocket);
                serverthread.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore istanza server");
            System.exit(1);
        }
    }
}
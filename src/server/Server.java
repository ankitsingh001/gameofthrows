/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
/**
/**
 *
 * @author Ankit Singh
 */
public class Server
{
    private int port;
    ObjectInputStream sInput1,sInput2;
    ObjectOutputStream sOutput1,sOutput2;
    JSONObject jobj1=new JSONObject();
    JSONObject jobj2=new JSONObject();
    
    public Server(int port) 
    {
        this.port=port;
    }
    
    public void start()
    {
        Socket socket1=null,socket2=null;
        try
        {
            ServerSocket s=new ServerSocket(port); 
            System.out.println("server listening on port "+port);
            socket1= s.accept();
            String player_name;
            sOutput1=new ObjectOutputStream(socket1.getOutputStream());
            sInput1=new ObjectInputStream(socket1.getInputStream());
            jobj1=(JSONObject) sInput1.readObject();
            System.out.println(jobj1);
            player_name=(String)jobj1.get("pname");
            System.out.println(player_name+" Just connected");
            jobj1.clear();
            jobj1.put("status", "OK");
            jobj1.put("pnum", 1);
            sOutput1.writeObject(jobj1);

            

            System.out.println("server listening on port "+port);
            socket2= s.accept();
            sOutput2=new ObjectOutputStream(socket2.getOutputStream());
            sInput2=new ObjectInputStream(socket2.getInputStream());
            jobj2=(JSONObject) sInput2.readObject();
            player_name=(String)jobj2.get("pname");
            System.out.println(player_name+" Just connected");
            jobj1.clear();
            jobj1.put("status", "OK");
            jobj1.put("pnum", 2);
            sOutput1.writeObject(jobj1);     
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            try
            {
                socket1.close();
                socket2.close();
            }
            catch(IOException t)
            {
                
            }
        }
    }
    
    public static void main(String args[])
    {
        int port=1234;
        Server s=new Server(port);
        s.start();
    }
}

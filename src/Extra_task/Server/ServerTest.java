package Extra_task.Server;

import Extra_task.Client.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class ServerTest {
    private Vector<ClientHandler> clients;

    public ServerTest(){
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                clients.add(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        Iterator<ClientHandler> iter = clients.iterator();
        while (iter.hasNext()) {
            if(iter.next().getSocket().isClosed()) {
                iter.remove();
            }
        }

        for (ClientHandler o: clients) {
                o.sendMsg(msg);
        }
    }

}

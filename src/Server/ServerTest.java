package console_chat.Server;

import console_chat.Client.ClientHandler;
import console_chat.Client.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerTest {
    //private Vector<ClientHandler> clients;

    public ServerTest(){
//        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
//                clients.add(new ClientHandler(this,socket));
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
        ClientHandler.sendMsg(msg);
//        for (ClientHandler o: clients) {
//            o.sendMsg(msg);
//        }
    }
}

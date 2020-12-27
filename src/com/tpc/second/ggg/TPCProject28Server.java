package com.tpc.second.ggg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TPCProject28Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("server ready...");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("client connect success!");
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				String message = dis.readUTF();
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				dos.writeUTF("[ECHO] " + message + "(from Server!)");
				dos.close();
				dis.close();
				socket.close();
				System.out.println("client socket close...");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
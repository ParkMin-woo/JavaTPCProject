package com.tpc.second.ggg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TPCProject29MultiChatClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("118.219.239.136" , 9999);
			Scanner scanner = new Scanner(System.in);
			System.out.print("name : ");
			String name = scanner.nextLine();
			Thread sender = new Thread(new ClientSender(socket , name));
			Thread receiver = new Thread(new ClientReceiver(socket));
			sender.start();
			receiver.start();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	// main
	
	// 보내는 Thread
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;
		/**
		 * @param socket
		 * @param name
		 */
		public ClientSender(Socket socket, String name) {
			// super();
			this.socket = socket;
			this.name = name;
			try {
				out = new DataOutputStream(socket.getOutputStream());
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	// constructor
		
		public void run() {
			Scanner scanner = new Scanner(System.in);
			try {
				if(out != null) {
					out.writeUTF(name);
				}
				while(out != null) {
					String message = scanner.nextLine();
					if(message.equals("quit")) break;
					out.writeUTF("[" + name + "]" + message);
				}	// while
				out.close();
				socket.close();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	// run
	}	// inner class
	
	// 받는 Thread
	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		/**
		 * @param socket
		 */
		public ClientReceiver(Socket socket) {
			// super();
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	// constructor
		
		public void run() {
			while(in != null) {
				try {
					System.out.println(in.readUTF());
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					break;
				}
			}	// while
			try {
				in.close();
				socket.close();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	// run
		
	}	// inner class
}
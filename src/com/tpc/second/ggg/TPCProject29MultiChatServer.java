package com.tpc.second.ggg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TPCProject29MultiChatServer {
	
	HashMap clients;
	
	/**
	 * 
	 */
	public TPCProject29MultiChatServer() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}

	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("Start Server...");
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " Connect!");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void sendToAll(String msg) {
		// 브로드캐스팅 기능
		Iterator iterator = clients.keySet().iterator();
		while(iterator.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream) clients.get(iterator.next());
				out.writeUTF(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	// while
	}
	
	public static void main(String[] args) {
		new TPCProject29MultiChatServer().start();
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		/**
		 * @param socket
		 */
		public ServerReceiver(Socket socket) {
			// super();
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	// constructor
		
		public void run() {
			String name = "";
			try {
				name = in.readUTF();
				if(clients.get(name) != null) {
					// 같은 이름의 사용자가 존재할 경우.
					out.writeUTF("#Already exist name : " + name);
					out.writeUTF("Please reconnect by other name");
					System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " disconnect!");
					in.close();
					out.close();
					socket.close();
					socket = null;
				}
				else {
					// 같은 이름의 사용자가 존재하지 않는 경우.
					sendToAll("#" + name + " join!");
					clients.put(name, out);
					while(in != null) {
						sendToAll(in.readUTF());
					}
				}	// else
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(socket != null) {
					sendToAll("#" + name + " exit!");
					clients.remove(name);
					System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " disconnect!");
				}	// if
			}	// finally
		}	// run
	}	// inner class
}
package com.tpc.second.ggg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TPCProject28Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("118.219.239.136", 9999);
			System.out.println("Connection Success");
			Scanner scanner = new Scanner(System.in);
			String message = scanner.nextLine();
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(message);
			////////////////////////////////////////////////////////
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			System.out.println("Receive : " + dis.readUTF());
			dis.close();
			dos.close();
			socket.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
package com.tpc.second.bbb;

import kr.bit.InitGUI;

public class TPCProject09 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String clientId = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id";
		String clientSecretKey = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key";
		InitGUI initGUI = new InitGUI(clientId , clientSecretKey);
		initGUI.initGUI();
		
	}

}

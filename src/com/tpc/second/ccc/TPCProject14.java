package com.tpc.second.ccc;

import kr.bit.gui.InitGUICrawling;

public class TPCProject14 {
	// TPCProject13과의 차이점 : main 메서드에 logic의 다 넣었나 아니면 class를 분리했느냐의 차이...
	public static void main(String[] args) {
		// new InitGUICrawling();
		InitGUICrawling initGUICrawling = new InitGUICrawling();
		initGUICrawling.init();
	}
}

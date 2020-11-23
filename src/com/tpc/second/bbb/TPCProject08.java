package com.tpc.second.bbb;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.bit.api.naver.NaverGeocodingAPI;
import kr.bit.api.naver.NaverStaticMapAPI;

public class TPCProject08 {
	public static void main(String[] args) {
		String clientId = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id";		// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id
		String clientSecretKey = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key";	// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key
		
		NaverGeocodingAPI geoCodingApi = new NaverGeocodingAPI(clientId, clientSecretKey);
		JSONArray addressJsonArray = geoCodingApi.geocodingAPI();
		// System.out.println("addressJsonArray => " + addressJsonArray);
		String x_point = "";
		String y_point = "";
		String roadAddress = "";
		for(int i = 0 ; i < addressJsonArray.length() ; i++) {
			JSONObject address = (JSONObject) addressJsonArray.get(i);
			System.out.println("jibunAddress => " + address.get("jibunAddress"));
			System.out.println("roadAddress => " + address.get("roadAddress"));
			System.out.println("x => " + address.get("x"));
			System.out.println("y => " + address.get("y"));
			System.out.println("englishAddress => " + address.get("englishAddress"));
			x_point = (String) address.get("x");
			y_point = (String) address.get("y");
			roadAddress = (String) address.get("roadAddress");
		}
		NaverStaticMapAPI staticMapApi = new NaverStaticMapAPI(clientId, clientSecretKey);
		staticMapApi.staticMapAPI(x_point, y_point, roadAddress);
	}
}

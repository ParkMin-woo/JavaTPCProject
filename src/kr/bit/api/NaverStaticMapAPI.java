package kr.bit.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class NaverStaticMapAPI {
	
	private String clientId;
	private String clientSecretKey;
	private static final String NAVER_STATIC_MAP_URL = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
	
	/**
	 * 
	 */
	public NaverStaticMapAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param clientId
	 * @param clientSecretKey
	 */
	public NaverStaticMapAPI(String clientId, String clientSecretKey) {
		super();
		this.clientId = clientId;
		this.clientSecretKey = clientSecretKey;
	}

	public void staticMapAPI(String x , String y , String address) {
		// System.out.println("x => " + x);
		// System.out.println("y => " + y);
		// System.out.println("address => " + address);
		// String naverApiUrl = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		// 네이버 클라우드 플랫폼을 만들었을 때의 클라이언트id, secret key를 담을 변수를 선언한다.
		// 절대 이 부분은 git에 commit 후 merge시 공개하지 않는다.
		// 개인정보 유출의 위험이 있기 때문이다.
		/*
		String clientId = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id";		// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id
		String clientSecretKey = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key";	// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key
		*/
		try {
			String position = URLEncoder.encode(x + " " + y, "UTF-8");
			String url = NAVER_STATIC_MAP_URL;
			url += "center=" + x + "," + y;
			url += "&level=16&w=700&h=500";
			url += "&markers=type:t|size:mid|pos:" + position + "|label:" + URLEncoder.encode(address , "UTF-8");
			// System.out.println("11111");
			URL connectedUrl = new URL(url);
			// System.out.println("22222");
			HttpURLConnection connection = (HttpURLConnection) connectedUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", this.clientId);
			connection.setRequestProperty("X-NCP-APIGW-API-KEY", this.clientSecretKey);
			
			int responseCode = connection.getResponseCode();
			if(responseCode == 200) {
				InputStream inputStream = connection.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				String fixedFileName = "map";
				String tmpFileName = Long.valueOf(new Date().getTime()).toString();
				File file = new File(fixedFileName + tmpFileName + ".jpg");
				file.createNewFile();
				OutputStream outputStream = new FileOutputStream(file);
				while((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				inputStream.close();
			}
			else {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while((inputLine = bufferedReader.readLine()) != null) {
					response.append(inputLine);
				}
				bufferedReader.close();
				System.out.println("response => " + response.toString());
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("//// 에러 발생 e => " + e);
		}
	}
}

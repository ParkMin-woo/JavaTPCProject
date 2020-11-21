package kr.bit.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import kr.bit.InitGUI;
import kr.bit.structure.AddressVO;

public class NaverMapAPI implements ActionListener {
	
	private InitGUI initGUI;
	private String clientId;
	private String clientSecretKey;

	public NaverMapAPI(InitGUI initGUI) {
		// TODO Auto-generated constructor stub
		this.initGUI = initGUI;
	}

	public NaverMapAPI(InitGUI initGUI, String clientId, String clientSecretKey) {
		// TODO Auto-generated constructor stub
		this.initGUI = initGUI;
		this.clientId = clientId;
		this.clientSecretKey = clientSecretKey;
		/*
		System.out.println("===== NaverMapAPI 객체를  생성하면서 파라미터에 세팅된 값들 =====");
		System.out.println("this.initGUI => " + this.initGUI);
		System.out.println("this.clientId => " + this.clientId);
		System.out.println("this.clientSecretKey => " + this.clientSecretKey);
		*/
	}

	// @SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// String clientId = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id";		// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id
		// String clientSecretKey = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key";	// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key
		AddressVO addressVO = null;
		try {
			// InitGUI 객체 내부에 있는 address를 읽어서 String address 변수에 저장.
			String address = this.initGUI.address.getText();
			// 주소에는 공백이 들어가 있는데, 공백은 컴퓨터 프로그램이 못읽어들인다.
			// 그러므로 UTF-8로 입력한 주소를 변환시켜준다.
			String tranAddress = URLEncoder.encode(address, "UTF-8");
			// 연결 요청 URL을 만든다.
			// String requestUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + tranAddress;
			String requestUrl = NaverUrlConstants.NAVER_GEOCODING_URL + tranAddress;
			
			// requestUrl이 정확한 URL인지 아닌지 알아본다.(얘가 제대로된 URL인지 어디 나사 하나 빠진 URL인지 검사)
			// 잘못된 URL이면 "Welcome to URLException 에러 호출.
			URL url = new URL(requestUrl);
			// openConnection() -> 연결해주는 메서드
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 네이버 클라우드 플랫폼의 Geocoding application이 API URL을 GET방식으로 지원
			connection.setRequestMethod("GET");
			// 요청 헤더로 클라이언트id, secret key를 보내준다.
			connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", this.clientId);
			connection.setRequestProperty("X-NCP-APIGW-API-KEY", this.clientSecretKey);
			// System.out.println("connection => " + connection);
			
			// connection 변수에 setting한 3개의 정보가 connection이 성공적으로 되면
			// connection 변수에 네이버 클라우드 플랫폼 서버가 성공적으로 연결되었음을 의미한다.
			
			// 응답이 정상적인지, 비정상적인지 알아보기 위해서 int 변수 선언.
			// 200이 나오면 정상적인 응답
			// 400은 Bad Request Exception(요청 오류), 500은 Unexpected Error(예외처리가 안된 오류)
			// 401은 발급받은 client id , secret key가 틀린 경우
			int responseCode = connection.getResponseCode();
			System.out.println("responseCode => " + responseCode);
			
			// 성공이면 서버에서 json 데이터를 응답받는데, 아래 URL을 참고하시길 바란다.
			// https://apidocs.ncloud.com/ko/ai-naver/maps_geocoding/geocode/
			// 에서 '응답 예시' 참고.
			// json 데이터를 한 줄 씩 읽어오므로 새로운 BufferedReader 객체가 필요하다.
			BufferedReader responseJsonReader;	// = new BufferedReader(in)
			if(responseCode == 200) {
				// 성공이면 responseJsonReader 변수에 연결되어있는 connection에 String형 데이터를 얻어와야 한다.
				// 이떼, 문자 stream과 byte stream을 연결해주는 InputStreamReader()를 이용해서 stream을 변경.
				// 변경할 때, 한글 데이터가 있다면 한글이 깨지지 않기 위해서 "UTF-8"로 Character Set을 설정한다.
				responseJsonReader = new BufferedReader(new InputStreamReader(connection.getInputStream() , "UTF-8"));
			}
			else {
				// 에러가 나면 error stream으로 받는다.
				System.out.println("error 발생!");
				responseJsonReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			
			// 정상적으로 stream이 만들어지면, BufferedReader를 이용하여 json을 한 line씩 읽어와야 한다.
			String readLine;
			// 한줄마다 저장하기 위해서 StringBuffer객체를 생성해서 담아둬야 한다.
			StringBuffer responseJson = new StringBuffer();
			// while문을 이용하여 데이터를 한 line씩 처리
			// readLine에 responseJsonReader.readLine()의 값을 넣어서 null이 아닐때까지 계속 looping하게 만든다.
			while((readLine = responseJsonReader.readLine()) != null) {
				// StringBuffer의 append() 메서드를 이용하여 한 line씩 읽은 데이터들을 계속 담아둔다.
				responseJson.append(readLine);
			}
			
			// while문이 정상적으로 끝나면 responseJsonReader는 close() 메서드로 닫는다.
			responseJsonReader.close();
			
			// 이 시점에서 responseJson 변수에 json 데이터가 다 저장된다.
			// 여기서부터는 user들이 json에서 원하는 데이터들을 추출할 수 있도록 한다.
			// JSONObject, JSONArray 객체를 이용하면 된다.(json.org)
			// maven(https://mvnrepository.com/)에서 제공하는 다른 라이브러리로 json에서 원하는 데이터를 추출해도 상관은 없다.
			JSONTokener tokener = new JSONTokener(responseJson.toString());
			JSONObject jsonObj = new JSONObject(tokener);
			// System.out.println("jsonObj : " + jsonObj.toString(2));
			JSONArray addressArray = (JSONArray) jsonObj.get("addresses");
			
			// return addressArray;
			for( int i = 0 ; i < addressArray.length() ; i++ ) {
				JSONObject addresses = (JSONObject) addressArray.get(i);
				/*
				System.out.println("도로명주소 : " + addresses.get("roadAddress"));
				System.out.println("지번주소 : " + addresses.get("jibunAddress"));
				System.out.println("영문주소 : " + addresses.get("englishAddress"));
				System.out.println("위도 : " + addresses.get("x"));
				System.out.println("경도 : " + addresses.get("y"));
				*/
				// System.out.println("addresses : " + addresses.toString(2));
				addressVO = new AddressVO();
				addressVO.setRoadAddress((String) addresses.get("roadAddress"));
				addressVO.setJibunAddress((String) addresses.get("jibunAddress"));
				addressVO.setEnglishAddress((String) addresses.get("englishAddress"));
				addressVO.setX((String) addresses.get("x"));
				addressVO.setY((String) addresses.get("y"));
			}
			this.mapService(addressVO);
		}
		catch (Exception err) {
			// TODO: handle exception
			err.printStackTrace();
			// return null;
		}
	}

	private void mapService(AddressVO addressVO) {
		// String clientId = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id";		// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id
		// String clientSecretKey = "네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key";	// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key
		// TODO Auto-generated method stub
		try {
			String position = URLEncoder.encode(addressVO.getX() + " " + addressVO.getY(), "UTF-8");
			// String url = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
			String url = NaverUrlConstants.NAVER_STATIC_MAP_URL;
			url += "center=" + addressVO.getX() + "," + addressVO.getY();
			url += "&level=16&w=700&h=500&public_transit&maptype=terrain";
			url += "&markers=type:t|size:mid|pos:" + position + "|label:" + URLEncoder.encode(addressVO.getRoadAddress() , "UTF-8");
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
				
				ImageIcon imageIcon = new ImageIcon(file.getName());
				this.initGUI.imageLabel.setIcon(imageIcon);
				this.initGUI.roadAddress.setText(addressVO.getRoadAddress());
				this.initGUI.jibunAddress.setText(addressVO.getJibunAddress());
				this.initGUI.englishAddress.setText(addressVO.getEnglishAddress());
				this.initGUI.x.setText(addressVO.getX());
				this.initGUI.y.setText(addressVO.getY());
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

package kr.bit.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


// TPCProject07에서 했던 프로젝트를 NaverGeocodingAPI라는 class를 새로 만들어서 옮김.
public class NaverGeocodingAPI {
	public JSONArray geocodingAPI() {
		// 네이버 오픈 API의 주소를 가져온다.
		String naverApiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
		// 네이버 클라우드 플랫폼을 만들었을 때의 클라이언트id, secret key를 담을 변수를 선언한다.
		// 절대 이 부분은 git에 commit 후 merge시 공개하지 않는다.
		// 개인정보 유출의 위험이 있기 때문이다.
		String clientId = "qrp9o1edcq";		// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 클라이언트id
		String clientSecretKey = "s7gz4k8Zt2rUhdDs3dWten40KltUOKgCD0EMqRkw";	// 네이버 클라우드 플랫폼에서 application 등록시 발급받은 secret key
		
		// 키보드로부터 주소를 입력받는다.
		// BufferedReader -> 문자 Stream, System.in -> byte Stream
		// InputStreamReader -> bridge Stream(문자 Stream과 byte Stream을 연결)
		BufferedReader inputDataReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader reader = new BufferedReader(System.in);
		// try catch문을 이용하여 예외처리를 한다.
		try {
			// 주소를 입력받는다.
			System.out.print("주소를 입력하세요. : ");
			// 주소는 inputDataReader에서 읽어들인다.
			// readLine() -> 주소를 한 줄 단위로 읽어들인다.
			String inputAddress = inputDataReader.readLine();
			// 주소에는 공백이 들어가 있는데, 공백은 컴퓨터 프로그램이 못읽어들인다.
			// 그러므로 UTF-8로 입력한 주소를 변환시켜준다.
			String tranAddress = URLEncoder.encode(inputAddress, "UTF-8");
			// 연결 요청 URL을 만든다.
			String requestUrl = naverApiUrl + tranAddress;
			
			// requestUrl이 정확한 URL인지 아닌지 알아본다.(얘가 제대로된 URL인지 어디 나사 하나 빠진 URL인지 검사)
			// 잘못된 URL이면 "Welcome to URLException 에러 호출.
			URL url = new URL(requestUrl);
			// openConnection() -> 연결해주는 메서드
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 네이버 클라우드 플랫폼의 Geocoding application이 API URL을 GET방식으로 지원
			connection.setRequestMethod("GET");
			// 요청 헤더로 클라이언트id, secret key를 보내준다.
			connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			connection.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecretKey);
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
			
			return addressArray;
			/*
			for( int i = 0 ; i < addressArray.length() ; i++ ) {
				JSONObject addresses = (JSONObject) addressArray.get(i);
				// System.out.println("addresses : " + addresses.toString(2));
				System.out.println("도로명주소 : " + addresses.get("roadAddress"));
				System.out.println("지번주소 : " + addresses.get("jibunAddress"));
				System.out.println("영문주소 : " + addresses.get("englishAddress"));
				System.out.println("위도 : " + addresses.get("x"));
				System.out.println("경도 : " + addresses.get("y"));
			}
			*/
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		// return null;
	}
}

package kr.bit.mqtt;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttClass implements MqttCallback {
	
	private MqttClient client = null;
	private ReceiveEventListener listener = null;

	/**
	 * 
	 */
	public MqttClass() {
		new Thread(task1).start();
	}
	
	 Runnable task1 = new Runnable() {
		 @Override
		 public void run() {
			try {
				String clientId = UUID.randomUUID().toString();
				client = new MqttClient("tcp://218.50.92.97:1883" , clientId);
				MqttConnectOptions connopt = new MqttConnectOptions();
				connopt.setCleanSession(true);
				client.connect(connopt);
				client.setCallback(MqttClass.this);
				client.subscribe("dht11");
			
				new IoTFrame(MqttClass.this);
			}
			catch (MqttException e) {
				System.out.println("ERR0" + e.getStackTrace());
			}
		 }
	 };
	 
	 public void sendMessage(String payload) {
		 MqttMessage message = new MqttMessage();
		 message.setPayload(payload.getBytes());

		 try {
			 if(client.isConnected()) {
				 client.publish("led", message);
			 }
		}
		 catch (MqttException e) {
			 System.out.println("error1-" + e.getStackTrace());
		}
	 }

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("Disconnect! 연결이 끊어졌습니다.");
		try {
			client.close();
		} catch (MqttException e) {
			System.out.println("error" + e.getMessage());
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		
	}

	public void setMyEventListener(ReceiveEventListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		listener.recvMsg(topic, msg);
	}
	 
	 
}

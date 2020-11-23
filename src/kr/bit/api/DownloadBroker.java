package kr.bit.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DownloadBroker implements Runnable {
	private String urlAddress;
	private String fileName;
	
	/**
	 * @param urlAddress
	 */
	public DownloadBroker(String urlAddress) {
		super();
		this.urlAddress = urlAddress;
	}

	/**
	 * @param urlAddress
	 * @param fileName
	 */
	public DownloadBroker(String urlAddress, String fileName) {
		super();
		this.urlAddress = urlAddress;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try {
			FileOutputStream fos = new FileOutputStream(this.fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			URL url = new URL(this.urlAddress);
			InputStream is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			int data;
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
			bos.close();
			bis.close();
			System.out.println("Download Complete");
			System.out.println(this.fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

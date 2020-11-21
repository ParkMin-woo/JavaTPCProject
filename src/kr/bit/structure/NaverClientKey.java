package kr.bit.structure;

public class NaverClientKey {
	private String clientId;
	private String clientSecretKey;
	
	/**
	 * 
	 */
	public NaverClientKey() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param clientId
	 * @param clientSecretKey
	 */
	public NaverClientKey(String clientId, String clientSecretKey) {
		super();
		this.clientId = clientId;
		this.clientSecretKey = clientSecretKey;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecretKey
	 */
	public String getClientSecretKey() {
		return clientSecretKey;
	}

	/**
	 * @param clientSecretKey the clientSecretKey to set
	 */
	public void setClientSecretKey(String clientSecretKey) {
		this.clientSecretKey = clientSecretKey;
	}
}

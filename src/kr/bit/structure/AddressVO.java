package kr.bit.structure;

public class AddressVO {
	private String jibunAddress;
	private String roadAddress;
	private String englishAddress;
	private String x;
	private String y;
	
	/**
	 * 
	 */
	public AddressVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param jibunAddress
	 * @param roadAddress
	 * @param englishAddress
	 * @param x
	 * @param y
	 */
	public AddressVO(String jibunAddress, String roadAddress, String englishAddress, String x, String y) {
		super();
		this.jibunAddress = jibunAddress;
		this.roadAddress = roadAddress;
		this.englishAddress = englishAddress;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the jibunAddress
	 */
	public String getJibunAddress() {
		return jibunAddress;
	}

	/**
	 * @param jibunAddress the jibunAddress to set
	 */
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}

	/**
	 * @return the roadAddress
	 */
	public String getRoadAddress() {
		return roadAddress;
	}

	/**
	 * @param roadAddress the roadAddress to set
	 */
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	/**
	 * @return the englishAddress
	 */
	public String getEnglishAddress() {
		return englishAddress;
	}

	/**
	 * @param englishAddress the englishAddress to set
	 */
	public void setEnglishAddress(String englishAddress) {
		this.englishAddress = englishAddress;
	}

	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(String y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "AddressVO [jibunAddress=" + jibunAddress + ", roadAddress=" + roadAddress + ", englishAddress="
				+ englishAddress + ", x=" + x + ", y=" + y + "]";
	}
}

package kr.bit.structure;

public class MemberDTO {
	// 멤버는 이름, 나이, 성별, 주소, 전화번호를 포함하고 있음
	private String name;
	private int age;
	private String gender;
	private String address;
	private String phone;
	
	/**
	 * 
	 */
	public MemberDTO() {
		super();
	}



	/**
	 * @param name
	 * @param age
	 * @param gender
	 * @param address
	 * @param phone
	 */
	public MemberDTO(String name, int age, String gender, String address, String phone) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}



	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}



	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberDTO [name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address + ", phone="
				+ phone + "]";
	}
}

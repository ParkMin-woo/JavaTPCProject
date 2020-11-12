package kr.bit.structure;

public class BookDTO {
	public String title;
	public int price;
	public String company;
	public int page;
	
	// 추가
	public BookDTO() {
		// 객체를 생성하는 작업을 한다.(기계어코드)
		super();
	}
	
	/*
	public BookDTO(String title2, int price2, String company2, int page2) {
		// TODO Auto-generated constructor stub
	}
	*/

	/**
	 * @param title
	 * @param price
	 * @param company
	 * @param page
	 */
	public BookDTO(String title, int price, String company, int page) {
		// super();
		this.title = title;
		this.price = price;
		this.company = company;
		this.page = page;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "BookDTO [title=" + title + ", price=" + price + ", company=" + company + ", page=" + page + "]";
	}
}

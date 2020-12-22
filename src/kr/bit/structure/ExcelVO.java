package kr.bit.structure;

public class ExcelVO {
	// title	author	company	isbn	imageUrl
	private String title;
	private String author;
	private String company;
	private String isbn;
	private String imageUrl;
	
	/**
	 * 
	 */
	public ExcelVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param author
	 * @param company
	 * @param isbn
	 * @param imageUrl
	 */
	public ExcelVO(String title, String author, String company, String isbn, String imageUrl) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
		this.isbn = isbn;
		this.imageUrl = imageUrl;
	}

	/**
	 * @param title
	 * @param author
	 * @param company
	 */
	public ExcelVO(String title, String author, String company) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "ExcelVO [title=" + title + ", author=" + author + ", company=" + company + ", isbn=" + isbn
				+ ", imageUrl=" + imageUrl + "]";
	}
}

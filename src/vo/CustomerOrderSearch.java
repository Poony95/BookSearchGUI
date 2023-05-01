package vo;
//bookname,publisher,saleprice,orderdate

import java.util.Date;

public class CustomerOrderSearch {
	private String bookname;
	private String publisher;
	private int saleprice;
	private Date orderdate;
	public CustomerOrderSearch(String bookname, String publisher, int saleprice, Date orderdate) {
		super();
		this.bookname = bookname;
		this.publisher = publisher;
		this.saleprice = saleprice;
		this.orderdate = orderdate;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	
	
}









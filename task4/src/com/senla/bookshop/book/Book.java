package com.senla.bookshop.book;

public class Book {
	private String name;
	private String author;
	private Date dateSupply;
	private int price;
	private Date dateOld;
	private boolean inStock;
	private int requests;

	public Book(String name, String author, Date date, int price) {
		this.name = name;
		this.author = author;
		this.dateSupply = date;
		this.price = price;
		dateOld = date.afterSixMonth();
		inStock = false;
		requests = 0;
	}
	public Book(String description){
		String[] book = description.split("/");
		name  = book[0];
		author = book[1];
		dateSupply = new Date(Integer.parseInt(book[2]),Integer.parseInt(book[3]),Integer.parseInt(book[4]));
		price = Integer.parseInt(book[5]);
		dateOld = new Date (Integer.parseInt(book[6]),Integer.parseInt(book[7]),Integer.parseInt(book[8]));
		if(book[9].equals("true")){
			inStock = true;
		} else{
			inStock = false;
		}
		requests = Integer.parseInt(book[10]);
		
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public Date getDateSupply() {
		return dateSupply;
	}

	public int getPrice() {
		return price;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public boolean inStock() {
		return inStock;
	}

	public void setStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isOld(Date today) {
		if (today.isLess(dateOld)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append(name).append("/").append(author).append("/").append(dateSupply).append("/").append(price)
				.append("/").append(dateOld).append("/").append(inStock).append("/").append(requests).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			return ((Book) obj).getName() == name && ((Book) obj).getAuthor().equals(author);
		}
		return super.equals(obj);
	}

}

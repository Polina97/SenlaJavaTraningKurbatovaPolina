package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IBaseAction;
import com.senla.bookshopui.controller.Runner;

public class BooksPrinter implements IBaseAction{
	String[] books;
	

	public BooksPrinter() {
		this.books = Runner.shop.getBooks();
	}


	@Override
	public void execute() {
		for(String book: books){
			System.out.println(book);
		}
	}

}

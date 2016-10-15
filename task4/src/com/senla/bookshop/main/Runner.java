package com.senla.bookshop.main;


import com.senla.bookshop.entity.Book;
import com.senla.bookshop.manager.Stock;

public class Runner {
	

	public static void main(String[] args) {
		

	}
	private static void workBookManager(){
		Stock stock = new Stock();
		Book b1 = new Book("The Boston Girl: A Novel", "Anita Diamant", 120_000);
		Book b2  = new Book("", "",  100_000);
		Book b3  = new Book("", "",  100_000);
		Book b4  = new Book("", "",  100_000);
		Book b5  = new Book("", "",  100_000);

	}

}

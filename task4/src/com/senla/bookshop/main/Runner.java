package com.senla.bookshop.main;

import com.senla.bookshop.book.*;

public class Runner {
	

	public static void main(String[] args) {
		

	}
	private static void workBookManager(){
		Stock stock = new Stock();
		Book b1 = new Book("The Boston Girl: A Novel", "Anita Diamant", stock.getNowDate(), 120_000);
		Book b2  = new Book("", "", stock.getNowDate(), 100_000);

	}

}

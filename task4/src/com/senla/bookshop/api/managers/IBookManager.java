package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.entity.Book;

public interface IBookManager extends IBaseManager {
	void showAllBooks();

	Book[] getOldBooks();

	Book[] getStockBooks();

	Book[] getApplicationBooks();

	void addToStock(IBook book);

	void deleteFromStock(IBook book);

	public void submitApplication(Book book);

}

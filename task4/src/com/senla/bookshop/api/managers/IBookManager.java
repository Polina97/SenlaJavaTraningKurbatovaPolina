package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.entity.Book;

public interface IBookManager extends IBaseManager {
	void showAllBooks();

	Book[] getOldBooks();

	void setOldBooks(Book[] oldBooks);

	Book[] getStockBooks();

	void setStockBooks(Book[] stockBooks);

	Book[] getApplicationBooks();

	void addToStock(IBook book);

	void deleteFromStock(IBook book);

	public void submitApplication(Book book);

	public void sortAlphabet();

	boolean isInStock(Book book);

	void sortPrice();

	void sortDate();

	void sortStock();

	void sortDateOld();

	void sortPriceOld();

}

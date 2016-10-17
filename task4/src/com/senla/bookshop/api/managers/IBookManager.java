package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.entity.Book;

public interface IBookManager extends IBaseManager {
	void showAllBooks();

	IBook[] getOldBooks();

	void setOldBooks(IBook[] oldBooks);

	IBook[] getStockBooks();

	void setStockBooks(Book[] stockBooks);

	IBook[] getApplicationBooks();

	void addToStock(IBook book);

	void deleteFromStock(IBook book);

	public void submitApplication(Book book);

	public void sortAlphabet();

	boolean isInStock(IBook book);

	void sortPrice();

	void sortDate();

	void sortStock();

	void sortDateOld();

	void sortPriceOld();

}

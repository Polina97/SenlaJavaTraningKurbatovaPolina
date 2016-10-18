package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.Book;

public interface IBookManager extends IBaseManager {
	public void showAllBooks();

	public IBook[] getOldBooks();

	public void setOldBooks(IBook[] oldBooks);

	public IBook[] getStockBooks();

	public void setStockBooks(Book[] stockBooks);

	public IBook[] getApplicationBooks();

	public void addToStock(IBook book);

	public void deleteFromStock(IBook book);

	public void submitApplication(Book book);

	public void sortAlphabet();

	public boolean isInStock(IBook book);

	public void sortBooks(TypeBookComparator comparator);

}

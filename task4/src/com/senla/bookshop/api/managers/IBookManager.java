package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.Book;

public interface IBookManager extends IBaseManager {
	public IBook[] getBooks();

	public void setBooks(Book[] books);

	public IBook getById(Integer id);

	public IBook[] getOldBooks();

	public IBook[] getStockBooks();

	public IBook[] getApplicationBooks();

	public Boolean isInStock(IBook book);


	public void sortBooks(TypeBookComparator comparator);

	void addToStock(Integer id);

	void deleteFromStock(Integer id);

	void submitApplication(Integer id);

}

package com.senla.bookshop.api.managers;

import java.util.List;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.comparators.TypeBookComparator;

public interface IBookManager extends IBaseManager {
	public List<IBook> getBooks();

	public IBook getById(Integer id);

	public List<IBook> getOldBooks();

	public List<IBook> getStockBooks();

	public List<IBook> getApplicationBooks();

	void addToStock(Integer id) throws Exception;

	void deleteFromStock(Integer id) throws Exception;

	void submitApplication(Integer id) throws Exception;

	public List<IBook> sortBooks(TypeBookComparator comparator) throws Exception;

	public List<IBook> sortOldBooks(TypeBookComparator comparator) throws Exception;
}

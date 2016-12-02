package com.senla.bookshop.api.manager;

import java.util.List;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.typecomparator.TypeBookComparator;

public interface IBookManager extends IBaseManager {
	public List<IBook> getBooks();

	public IBook getById(Integer id);

	public List<IBook> getOldBooks();

	public List<IBook> getStockBooks();

	public List<IBook> getApplicationBooks();

	void addToStock(Integer id) throws Exception;

	void deleteFromStock(Integer id) throws Exception;

	void submitApplication(Integer id) throws Exception;

	public List<IBook> exportBooks();

	public IBook importBook(Integer id) throws Exception;
	
	public List<IBook> sortBooks(TypeBookComparator comparator) throws Exception;

	public List<IBook> sortOldBooks(TypeBookComparator comparator) throws Exception;
}

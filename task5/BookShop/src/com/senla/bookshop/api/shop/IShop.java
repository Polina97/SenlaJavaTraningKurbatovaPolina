package com.senla.bookshop.api.shop;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;

public interface IShop {

	public String[] getBooks();

	public String[] sortBooks(TypeBookComparator comparator);

	public String[] getOldBooks();

	public String[] sortOldBooks(TypeBookComparator comparator);

	public String[] getOrders();

	public String[] sortOrders(TypeOrderComparator comparator);

	public String[] getDeliveredOrders();

	public String[] sortDeliveredOrders(TypeOrderComparator comparator);

	public Integer getPrice();

	public Integer countOrders();

}

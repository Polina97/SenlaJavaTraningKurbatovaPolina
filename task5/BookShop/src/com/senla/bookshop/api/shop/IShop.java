package com.senla.bookshop.api.shop;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.StatusOrder;

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

	public String getDescriptionBook(Integer index);

	public String getDescriptionOrder(Integer index);

	public String addToStock(String name, String author, GregorianCalendar datePublication, Integer price);

	public String deleteFromStock(Integer index);

	public String submitApp(Integer index);

	public String addOrder(String nameBuyer, List<Integer> ids, StatusOrder status);

	public String deliverOrder(Integer index);

	public String cancelOrder(Integer index);

}

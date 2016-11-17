package com.senla.bookshop.api.shop;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshop.typecomparator.TypeOrderComparator;


public interface IShop {

	public List<String> getBooks();

	public List<String> getBuyers();

	public List<String> sortBooks(TypeBookComparator comparator);

	public List<String> getOldBooks();

	public List<String> sortOldBooks(TypeBookComparator comparator);

	public List<String> getOrders();

	public List<String> sortOrders(TypeOrderComparator comparator);

	public List<String> getDeliveredOrders();

	public List<String> sortDeliveredOrders(TypeOrderComparator comparator);

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

	public String copyOrder(Integer id);

	public List<String> exportOrders();

	public List<String> exportBooks();

	public List<String> exportBuyers();

	public String importOrder(Integer id);

	public String importBook(Integer id);

	public String importBuyer(Integer id);

	public void exit();


}

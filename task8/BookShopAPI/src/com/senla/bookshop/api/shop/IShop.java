package com.senla.bookshop.api.shop;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.bookshop.api.entity.StatusOrder;


public interface IShop {

	public List<String> getBooks();

	public List<String> getBuyers();

	public List<String> getOldBooks();


	public List<String> getOrders();


	public List<String> getDeliveredOrders();


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

	public List<String> sortBooks(String comparator);

	public List<String> sortOldBooks(String comparator);

	public List<String> sortOrders(String comparator);

	public List<String> sortDeliveredOrders(String comparator);


}

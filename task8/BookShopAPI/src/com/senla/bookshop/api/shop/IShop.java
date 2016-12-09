package com.senla.bookshop.api.shop;


public interface IShop {

	public String getBooks();

	public String getBuyers();

	public String getOldBooks();

	public String getOrders();

	public String getDeliveredOrders();

	public String getPrice();

	public String countOrders();

	public String addToStock(String bookString);

	public String addOrder(String orderString);

	public String deliverOrder(String index);

	public String cancelOrder(String index);

	public String copyOrder(String id);

	public String exportOrders();

	public String exportBooks();

	public String exportBuyers();

	public String importOrder(String id);

	public String importBook(String id);

	public String importBuyer(String id);

	public void exit();

	public String sortBooks(String comparator);

	public String sortOldBooks(String comparator);

	public String sortOrders(String comparator);

	public String sortDeliveredOrders(String comparator);

	public String getDescriptionBook(String indexStr);

	public String getDescriptionOrder(String indexStr);

	public String deleteFromStock(String index);

	public String submitApp(String index);


}

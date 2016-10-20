package com.senla.bookshop.main;

import com.senla.bookshop.resources.FileWorker;

public class Runner {
	public static FileWorker fileWorker;
	private static Shop shop;

	public static void main(String[] args){
		fileWorker = new FileWorker(args[0], args[1], args[2]);
		shop = new Shop();
		shop.sortBooks();
		shop.sortOrders();
		shop.sortDeliveredOrders();
		shop.getGeneralPrice();
		shop.getDeliveredOrders();
		shop.sortOldBooks();
		shop.getOrder();
		shop.getBook();
		shop.addToStock();
		shop.deleteFromStock();
		shop.addOrder();
		shop.deliverOrder();
		shop.cancelOrder();
		shop.submitApplication();
	}
}

package com.senla.bookshop.main;

import java.util.List;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.api.shop.IShopWorker;
import com.senla.bookshop.api.shop.ShopMethods;
import com.senla.bookshop.di.DIBookShop;

public class ShopWorker implements IShopWorker {
	private final String SLASH = "/";
	private static IShop shop = (IShop) DIBookShop.load(IShop.class.getName(), false);

	public ShopWorker() {
		super();
	}
	@Override
	public String runShop(String str) {
		String[] list = str.split(SLASH);
		switch (ShopMethods.valueOf(list[0])) {
		case getBooks:
			return listToString(shop.getBooks());
		case getBuyers:
			return listToString(shop.getBuyers());
		case sortBooks:
			return listToString(shop.sortBooks(list[1]));
		default:
			return null;
		}
	}
	private String listToString(List<String> list){
		StringBuilder builder = new StringBuilder();
		for(String s: list){
			builder.append(s).append(SLASH);
		}
//		builder.delete(builder.length()-2, builder.length()-1);
		return builder.toString();
	}

}

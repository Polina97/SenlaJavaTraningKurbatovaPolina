package com.senla.bookshopui.navigator;


import org.apache.log4j.Logger;

import com.senla.bookshop.main.Messages;
import com.senla.bookshopui.api.IBaseItem;
import com.senla.bookshopui.api.IBaseNavigator;
import com.senla.bookshopui.api.IMenu;

public class Navigator implements IBaseNavigator{
	private static Logger log = Logger.getLogger(Navigator.class.getName());
	public IMenu currentMenu;
	

	public Navigator(IMenu currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void printMenu() {
		System.out.println(this.currentMenu.getType().toString().toLowerCase());
		int i=1;
		for(IBaseItem baseItem : this.currentMenu.getItems()){
			System.out.println((i++)+baseItem.getName());
		}
		
	}

	@Override
	public void navigate(Integer index) {
		try{
		this.currentMenu.getItems().get(index-1).doAction();
		}catch(Exception e){
			log.error(e);
			System.out.println(Messages.ERROR);
		}
	}

}

package com.senla.bookshopui.api;

public interface INavigator {
	public IMenu getCurrentMenu();

	public void setCurrentMenu(IMenu currentMenu);

	public void printMenu();

	public void navigate(Integer index);
}

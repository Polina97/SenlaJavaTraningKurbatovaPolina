package com.senla.bookshopui.controller;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.resources.Printer;

public class Runner {
	private static Logger log = Logger.getLogger(Runner.class);
	public static IBaseController controller;

	public static void main(String[] args) {
		Printer.print("Welcome to our book shop!");
		controller = new MenuController();
		try {
			controller.run();
		} catch (Exception e) {
			log.error(e);
			Printer.print("An error has occurred. Sorry");
		}
	}


}

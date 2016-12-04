package com.senla.bookshopui.controller;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.resources.Printer;

public class Runner {
	private static Logger log = Logger.getLogger(Runner.class);
	private IClientWorker clientWorker;
	private IBaseController controller;

	public Runner(IClientWorker clientWorker) {
		this.clientWorker = clientWorker;
	}

	public void run() {
		Printer.print("Welcome to our book shop!");
		try {
			controller = new MenuController();
			controller.run(clientWorker);
		} catch (Exception e) {
			log.error(e);
			Printer.print("Sorry.An error has occurred! ");
		}

	}

}

package com.senla.bookshopui.controller;

import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.bookshop.client.ClientThread;
import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.resources.Printer;

public class Runner {
	private static Logger log = Logger.getLogger(Runner.class);
	public static IBaseController controller;

	public static void main(String[] args) {
		Printer.print("Welcome to our book shop!");
		controller = new MenuController();
		try {
			Socket s = new Socket(InetAddress.getLocalHost(), 8071);
			ClientThread clientThread = new ClientThread(s);
			controller.run(clientThread);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			Printer.print("An error has occurred. Sorry");
		}
	}

}

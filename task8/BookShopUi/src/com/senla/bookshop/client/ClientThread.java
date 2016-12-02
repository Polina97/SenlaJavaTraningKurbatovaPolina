package com.senla.bookshop.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ClientThread extends Thread {
	private Logger log = Logger.getLogger(ClientThread.class);
	private PrintStream ps;
	private BufferedReader br;

	public ClientThread(Socket s) {
		try {
			ps = new PrintStream(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			log.error(e);

		}
	}

	public String runShop(String methodShopSring) {
		try {
			ps.println(methodShopSring);
			return br.readLine();
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}

}

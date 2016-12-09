package com.senla.bookshop.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;

public class ClientWorker implements IClientWorker{
	private Logger log = Logger.getLogger(ClientWorker.class);
	private PrintStream ps;
	private BufferedReader br;

	public ClientWorker(PrintStream ps, BufferedReader br) {
		this.ps = ps;
		this.br = br;
	}

	public String sendToShop(String methodShopSring) {
		try {
			ps.println(methodShopSring);
			return br.readLine();
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}

}

package com.senla.bookshop.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IBookClient;
import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.controller.Runner;

public class BookClient implements IBookClient{
	private static Logger log = Logger.getLogger(BookClient.class);
	private static PrintStream ps;
	private static BufferedReader br;
	private static IClientWorker clientWorker;

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Socket s = new Socket(InetAddress.getLocalHost(), 8071);
			ps = new PrintStream(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			clientWorker = new ClientWorker(ps, br);
			Runner  runner = new Runner(clientWorker);
			runner.run();
		} catch (Exception e) {
			log.error(e);
		}
	}
}

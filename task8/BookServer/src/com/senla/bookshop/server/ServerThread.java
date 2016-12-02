package com.senla.bookshop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.shop.IShopWorker;
import com.senla.bookshop.di.DIBookShop;

public class ServerThread extends Thread {
	private Logger log = Logger.getLogger(ServerThread.class);
	private PrintStream os;
	private BufferedReader is;
	private IShopWorker shopWorker = (IShopWorker) DIBookShop.load(IShopWorker.class.getName(), false);

	public ServerThread(Socket s) throws IOException {
		os = new PrintStream(s.getOutputStream());
		is = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		String str = null;
		try {
			while ((str = is.readLine()) != null) {
				os.println(shopWorker.runShop(str));
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			disconnect();
		}
	}

	private void disconnect() {
		try {
			os.close();
			is.close();
		} catch (IOException e) {
			log.error(e);
		} finally {
			this.interrupt();
		}

	}

}

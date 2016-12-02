package com.senla.bookshop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class BookServer {
	private static Logger log = Logger.getLogger(BookServer.class);
	public static void main(String[] args){
		try{
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8071);
			while(true){
				Socket socket = serverSocket.accept();
				ServerThread server = new ServerThread(socket); 
				server.start();
			}
		}catch(IOException e){
			log.error(e);
		}
	}

}

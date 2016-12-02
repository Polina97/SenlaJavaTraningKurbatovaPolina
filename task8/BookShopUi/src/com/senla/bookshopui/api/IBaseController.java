package com.senla.bookshopui.api;

import com.senla.bookshop.client.ClientThread;

public interface IBaseController {

	public void run(ClientThread thread) throws Exception;
}

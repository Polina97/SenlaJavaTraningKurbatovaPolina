package com.senla.bookshopui.api;

import com.senla.bookshop.api.client.IClientWorker;

public interface IBaseController {

	public void run(IClientWorker worker) throws Exception;
}

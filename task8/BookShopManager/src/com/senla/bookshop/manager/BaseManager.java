package com.senla.bookshop.manager;

import com.senla.bookshop.api.manager.IBaseManager;
import com.senla.bookshop.utils.CSVWorker;

public abstract class BaseManager implements IBaseManager {
	public static CSVWorker csvWorker = new CSVWorker();

}

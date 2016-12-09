package com.senla.bookshop.main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.*;
import com.senla.bookshop.api.manager.*;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.di.DIBookShop;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.idgenerator.IdGenerator;
import com.senla.bookshop.idgenerator.TypeId;
import com.senla.bookshop.serialization.SerialWorker;
import com.senla.bookshop.serialization.StorageLoader;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshop.typecomparator.TypeOrderComparator;
import com.senla.bookshop.worker.ConfigPropertyWorker;

public class Shop implements IShop {
	private static Logger log = Logger.getLogger(Shop.class);
	private static List<String> ERROR_LIST = new ArrayList<String>();
	private static final GregorianCalendar TODAY = new GregorianCalendar();
	private static final String SPACE = " ";
	private static final String COMA = ",";
	private static final String POINT = ".";
	private static final Object SLASH = "/";
	private static final String SPLITTER = "&";
	private static SerialWorker serialWorker = new SerialWorker();
	private static IBookManager bookManager = (IBookManager) DIBookShop.load(IBookManager.class.getName(), false);
	private static IOrderManager orderManager = (IOrderManager) DIBookShop.load(IOrderManager.class.getName(), false);
	private static IBuyerManager buyerManager = (IBuyerManager) DIBookShop.load(IBuyerManager.class.getName(), false);
	private static IBookShopStorage storage;

	static {
		try {
			storage = StorageLoader.getStorage();
			ConfigPropertyWorker.configurate(Config.getInstance());
		} catch (Exception e) {
			log.error(e);
		}
		ERROR_LIST.add(Messages.ERROR);
	}

	@Override
	public String getBooks() {
		try {
			StringBuilder builder = new StringBuilder();
			List<String> books = new ArrayList<String>();
			for (IBook book : bookManager.getBooks()) {
				builder.append(book.getName()).append(SPACE).append(book.getAuthor());
				books.add(builder.toString());
				builder.delete(0, builder.length());
			}
			return listToStr(books);
		} catch (Exception e) {
			return Messages.ERROR;
		}
	}

	@Override
	public String getBuyers() {
		try {
			List<String> buyers = new ArrayList<String>();
			for (IBuyer buyer : buyerManager.getBuyers()) {
				buyers.add(buyer.getName());
			}
			return listToStr(buyers);
		} catch (Exception e) {
			return Messages.ERROR;
		}

	}

	@Override
	public String sortBooks(String comparator) {
		try {
			return listToString(bookManager.sortBooks(TypeBookComparator.valueOf(comparator)));
		} catch (Exception e) {
			log.error(e);
			return Messages.ERROR;
		}
	}

	@Override
	public String getOldBooks() {
		String oldBooks = listToString(bookManager.getOldBooks());
		if (oldBooks != null) {
			return listToString(bookManager.getOldBooks());
		} else {
			return Messages.ERROR;
		}
	}

	@Override
	public String sortOldBooks(String comparator) {
		try {
			String oldBooks = listToString(bookManager.sortOldBooks(TypeBookComparator.valueOf(comparator)));
			if (oldBooks != null)
				return oldBooks;
			else {
				return Messages.ERROR;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.ERROR;
		}
	}

	@Override
	public String getOrders() {
		String orders = listToString(orderManager.getOrders());
		if (orders != null) {
			return orders;
		} else {
			return Messages.ERROR;
		}
	}

	@Override
	public String sortOrders(String comparator) {
		String orders = listToString(orderManager.sortOrders(TypeOrderComparator.valueOf(comparator)));
		if (orders != null) {
			return orders;
		} else {
			return Messages.ERROR;
		}
	}

	@Override
	public String getDeliveredOrders() {
		String orders = listToString(orderManager.getDeliveredOrders());
		if (orders != null) {
			return orders;
		} else {
			return Messages.ERROR;
		}
	}

	@Override
	public String sortDeliveredOrders(String comparator) {
		String orders = listToString(orderManager.sortDeliveredOrders(TypeOrderComparator.valueOf(comparator)));
		if (orders != null) {
			return orders;
		} else {
			return Messages.ERROR;
		}
	}

	@Override
	public String getPrice() {
		return orderManager.getGeneralPrice().toString();
	}

	@Override
	public String countOrders() {
		try {
			return new Integer(orderManager.getDeliveredOrders().size()).toString();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getDescriptionBook(String indexStr) {
		try {
			Integer index = Integer.parseInt(indexStr);
			String description = bookManager.getBooks().get(index).getDescription();
			return description;
		} catch (Exception e) {
			log.error(e);
			return Messages.NOT_FOUND;
		}
	}

	@Override
	public String getDescriptionOrder(String indexStr) {
		try {
			Integer index = Integer.parseInt(indexStr);
			String description = orderManager.getOrderById(index).getDescription();
			return description;
		} catch (Exception e) {
			log.error(e);
			return Messages.NOT_FOUND;
		}
	}

	@Override
	public synchronized String addToStock(String bookString) {
		try {
			IBook book = parseBook(bookString);
			bookManager.add((BaseEntity) book);
			bookManager.addToStock(book.getId());
			return Messages.BOOK_ADD;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return Messages.BOOK_NOT_ADD;
		}
	}

	@Override
	public synchronized String deleteFromStock(String indexStr) {
		try {
			Integer index = Integer.parseInt(indexStr);
			bookManager.deleteFromStock(index);
			return Messages.BOOK_DELETED;
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_DELETED;
		}
	}

	@Override
	public String submitApp(String indexStr) {
		try {
			Integer index = Integer.parseInt(indexStr);
			bookManager.submitApplication(index);
			return Messages.APP_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.APP_NOT_ADD;
		}
	}

	@Override
	public synchronized String addOrder(String orderString) {
		try {
			IOrder order = parseOrder(orderString);
			orderManager.add((BaseEntity) order);
			buyerManager.getById(order.getBuyer().getId()).setOrder(order);
			return Messages.ORDER_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_ADD;
		}
	}

	@Override
	public  synchronized String deliverOrder(String index) {
		try {
			orderManager.changeStatus(Integer.parseInt(index), StatusOrder.DELIVERED);
			return Messages.ORDER_DELIVERED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_DELIVERED;
		}

	}

	@Override
	public  synchronized String cancelOrder(String index) {
		try {
			orderManager.changeStatus(Integer.parseInt(index), StatusOrder.CANCELED);
			return Messages.ORDER_CANCELED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_CANCELED;
		}
	}

	@Override
	public void exit() {
		serialWorker.writeStorage(storage);

	}

	@Override
	public  synchronized String copyOrder(String id) {
		try {
			int orderId = orderManager.getOrders().get(Integer.parseInt(id)).getId();
			orderManager.cloneOrder(orderId);
			return Messages.ORDER_COPIED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_COPIED;
		}
	}

	@Override
	public  synchronized String exportOrders() {
		try {
			List<IOrder> list = orderManager.exportOrders();
			if (list != null) {
				return listToString(list);
			} else {
				return Messages.ERROR;
			}
		} catch (Exception e) {
			return Messages.ERROR;
		}
	}

	@Override
	public  synchronized String exportBooks() {
		try {
			List<IBook> list = bookManager.exportBooks();
			if (list != null) {
				return listToString(list);
			} else {
				return Messages.ERROR;
			}
		} catch (Exception e) {
			return Messages.ERROR;
		}
	}

	@Override
	public  synchronized String exportBuyers() {
		try {
			List<IBuyer> list = buyerManager.exportBuyers();
			if (list != null) {
				return listToString(list);
			} else {
				return Messages.ERROR;
			}
		} catch (Exception e) {
			return Messages.ERROR;
		}
	}

	@Override
	public  synchronized String importOrder(String id) {
		try {
			int orderId = orderManager.getOrders().get(Integer.parseInt(id)).getId();
			if (orderManager.importOrder(orderId) != null) {
				return Messages.ORDER_IMPORTED;
			} else {
				return Messages.ORDER_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_IMPORTED;
		}
	}

	@Override
	public  synchronized String importBook(String id) {
		try {
			int bookId = bookManager.getBooks().get(Integer.parseInt(id)).getId();
			if (bookManager.importBook(bookId) != null) {
				return Messages.BOOK_IMPORTED;
			} else {
				return Messages.BOOK_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_IMPORTED;
		}
	}

	@Override
	public  synchronized String importBuyer(String id) {
		try {
			int buyerId = buyerManager.getBuyers().get(Integer.parseInt(id)).getId();
			if (buyerManager.importBuyer(buyerId) != null) {
				return Messages.BUYER_IMPORTED;
			} else {
				return Messages.BUYER_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.BUYER_NOT_IMPORTED;
		}
	}

	private static String listToString(List<?> entities) {
		if (entities != null) {
			StringBuilder array = new StringBuilder();
			for (Object entity : entities) {
				array.append(((IBaseEntity) entity).getDescription()).append(SLASH);
			}
			return array.toString();
		} else {
			return null;
		}
	}

	private String listToStr(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for (String s : list) {
			builder.append(s).append(SLASH);
		}
		builder.delete(builder.length() - 2, builder.length() - 1);
		return builder.toString();
	}
	private GregorianCalendar toGregorianCalendar(String dateString){
		String[] date = dateString.split(POINT);
		try{
		return new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
				Integer.parseInt(date[0]));
		}catch(Exception e){
			log.error(e);
			return new GregorianCalendar();
		}
	}
	private IOrder parseOrder(String orderString) throws Exception{
		try {
			String[] orderArr = orderString.split(SPLITTER);
			List<Integer> ids = new ArrayList<Integer>();
			String[] idsArray = orderArr[1].split(COMA);
			for (String s : idsArray) {
				ids.add(Integer.parseInt(s));
			}
			IOrder order = (IOrder) DIBookShop.load(IOrder.class.getName(), true);
			IBuyer buyer = (IBuyer) DIBookShop.load(IBuyer.class.getName(), true);
			buyer.setId(IdGenerator.getId(TypeId.BUYER));
			buyer.setName(orderArr[0]);
			buyerManager.add((BaseEntity) buyer);
			List<IBook> books = new ArrayList<IBook>();
			List<IBook> listBooks = bookManager.getBooks();
			for (int i = 0; i < ids.size(); i++) {
				books.add(listBooks.get(ids.get(i) - 1));
			}
			int price = 0;
			try {
				for (IBook book : books) {
					price += book.getPrice();
				}
			} catch (NullPointerException e) {
				log.error(e);
			}
			order.setId(IdGenerator.getId(TypeId.ORDER));
			order.setBuyer(buyer);
			order.setBooks(books);
			order.setDate(TODAY);
			order.setStatus(StatusOrder.valueOf(orderArr[2]));
			order.setPrice(price);
			return order;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	private IBook parseBook(String bookString) throws Exception{
		try {
			String[] bookArr = bookString.split(SPLITTER);
			IBook book = (IBook) DIBookShop.load(IBook.class.getName(), true);
			book.setId(IdGenerator.getId(TypeId.BOOK));
			book.setName(bookArr[0]);
			book.setAuthor(bookArr[1]);
			book.setDatePublication(toGregorianCalendar(bookArr[2]));
			book.setDateSupply(TODAY);
			book.setPrice(Integer.parseInt(bookArr[3]));
			return book;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}

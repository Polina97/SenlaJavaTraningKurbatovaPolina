package restaurant;

import cook.*;
import menu.*;

public class Restaurant {

	private static Menu menu = new Menu();
	private static Drink juice = new Drink(EDrinks.JUICE, 4);
	private static Drink coffee = new Drink(EDrinks.COFFEE, 7);
	private static Drink water = new Drink(EDrinks.WATER, 2);
	private static MainDish pizza = new MainDish(EMainDishes.PIZZA, 25);
	private static MainDish salad = new MainDish(EMainDishes.SALAD, 10);

	private static CookManager cookManager = new CookManager();
	private static Cook alenCook = new Cook("Alen");
	private static Cook mattCook = new Cook("Matt");
	private static Cook benCook = new Cook("Ben");

	private static Table[] tables = new Table[4];

	public static void main(String[] args) {
		System.out.println("a) Add / remove the dish from the menu");
		fillingMenu();
		System.out.println("b) Add / remove Cook");
		workWithCookManager();
		System.out.println("c) Make out / cancel the order on a certain table");
		workWithTablesAndOrders();
		System.out.println("d) Give a free cook to order");
		workWithCooksAndOrders();
		System.out.println("e) Order dishes with prices from the certain tables");
		printOrders();
		System.out.println("f)-g) Cooks and their orders");
		cookManager.printCooks();
		System.out.println("---------------");
		System.out.println("h) Print Menu");
		menu.printMenu();
		

	}

	private static void fillingMenu() {
		menu.deleteDish(water);
		menu.addDish(coffee);
		menu.addDish(pizza);
		menu.addDish(juice);
		menu.addDish(salad);
		menu.addDish(water);
		menu.deleteDish(water);
		System.out.println("---------------");
	}

	private static void workWithCookManager() {
		cookManager.addCook(alenCook);
		cookManager.addCook(benCook);
		cookManager.addCook(mattCook);
		cookManager.deleteCook(benCook);
		System.out.println("---------------");
	}

	private static void workWithTablesAndOrders() {
		for (int i = 0; i < 4; i++)
			tables[i] = new Table(i + 1);
		tables[0].createNewOrder(123, new ADish[] { menu.getDish(coffee), menu.getDish(pizza) });
		tables[1].createNewOrder(333, new ADish[] { menu.getDish(coffee) });
		tables[2].createNewOrder(143,
				new ADish[] { menu.getDish(pizza), menu.getDish(water), menu.getDish(juice) });
		tables[3].createNewOrder(111, new ADish[] { menu.getDish(juice), menu.getDish(salad) });
		tables[1].cancelOrder();
		System.out.println("---------------");
	}

	private static void workWithCooksAndOrders() {
		cookManager.addOrderToFreeCook(tables[0].getOrder());
		cookManager.addOrderToFreeCook(tables[3].getOrder());//it was deleted
		System.out.println("---------------");
	}
	private static void printOrders(){
		for (Table t : tables)
			t.printOrder();
		System.out.println("---------------");
	}

}

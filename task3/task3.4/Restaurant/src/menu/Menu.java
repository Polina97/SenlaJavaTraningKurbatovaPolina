package menu;

public class Menu {
	private ADish[] dishes;
	private int quantityDishes;

	public Menu() {
	}

	public Menu(ADish[] dishes) {
		this.dishes = dishes;
		this.quantityDishes = dishes.length;
	}

	public ADish[] getDishes() {
		return dishes;
	}

	public void addDish(ADish newDish) {
		if (dishes != null) {
			if (!isInMenu(newDish)) {
				ADish[] dishes2 = new ADish[quantityDishes + 1];
				for (int i = 0; i < quantityDishes; i++) {
					dishes2[i] = dishes[i];
				}
				dishes2[quantityDishes] = newDish;
				dishes = dishes2;
				quantityDishes = dishes.length;
				System.out.println(newDish.getName() + " was added to menu.");
			} else {
				System.out.println(newDish.getName() + " is already in Menu!");
			}

		} else {
			quantityDishes = 1;
			dishes = new ADish[quantityDishes];
			dishes[0] = newDish;
			System.out.println(newDish.getName() + " was added to menu.");
		}
	}

	public void deleteDish(ADish oldDish) {
		if (dishes != null && isInMenu(oldDish)) {
			ADish[] dishes2 = new ADish[quantityDishes - 1];
			int i = 0;
			for (ADish d : dishes) {
				if (d.getName() != oldDish.getName()) {
					dishes2[i++] = d;
				}
			}
			dishes = dishes2;
			quantityDishes = dishes.length;
			System.out.println(oldDish.getName() + " was deleted from menu.");

		} else {
			System.out.println("Your Menu hasn't " + oldDish.getName());
		}
	}

	public void printMenu() {
		System.out.println("Our Menu:");
		for (ADish d : dishes)
			d.printInform();
	}

	/** Check whether there is a dish on the menu */
	private boolean isInMenu(ADish dish) {
		boolean answer = false;
		for (ADish d : dishes) {
			if (d.getName() == dish.getName()) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	public ADish getDish(ADish dish) {
		for (ADish d : dishes) {
			if (d.getName() == dish.getName()) {
				return dish;
			}
		}
		return null;
	}

}

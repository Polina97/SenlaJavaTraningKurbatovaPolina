package cook;

import menu.ADish;

public class Order {
	private int numberOfOrder;
	private ADish[] dishes;

	public Order(int numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	public Order(int numberOfOrder, ADish[] dishes) {
		this(numberOfOrder);
		this.dishes = dishes;
	}

	public int getNumberOfOrder() {
		return numberOfOrder;

	}

	public void addDish(ADish newDish) {
		if (dishes != null) {
			ADish[] dishes2 = new ADish[dishes.length + 1];
			for (int i = 0; i < dishes.length; i++) {
				dishes2[i] = dishes[i];
			}
			dishes2[dishes.length] = newDish;
			dishes = dishes2;

		} else {
			dishes = new ADish[1];
			dishes[0] = newDish;
		}
	}

	public void printPrices() {
		StringBuilder stringBuilder = new StringBuilder();
		for (ADish aDish : dishes) {
			stringBuilder.append(aDish.getName()).append(" - ").append(aDish.getPrice()).append("\n");
		}
		System.out.println(stringBuilder);
	}
	

	
	

}

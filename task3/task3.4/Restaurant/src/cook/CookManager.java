package cook;

public class CookManager {
	private Cook[] cooks;

	public CookManager() {

	}

	public CookManager(Cook[] cooks) {
		this.cooks = cooks;
	}

	public Cook[] getCooks() {
		return cooks;
	}

	public void addCook(Cook newCook) {
		if (cooks != null) {
			if (!isExistCook(newCook)) {
				Cook[] cooks2 = new Cook[cooks.length + 1];
				for (int i = 0; i < cooks.length; i++) {
					cooks2[i] = cooks[i];
				}
				cooks2[cooks.length] = newCook;
				cooks = cooks2;
				System.out.println(newCook.getName() + " was added.");

			} else {
				System.out.println(newCook.getName() + " is already exist.");
			}

		} else {
			cooks = new Cook[1];
			cooks[0] = newCook;
			System.out.println(newCook.getName() + " was added.");
		}
	}

	public void deleteCook(Cook oldCook) {
		if (cooks != null && isExistCook(oldCook)) {
			Cook[] cooks2 = new Cook[cooks.length - 1];
			int i = 0;
			for (Cook c : cooks) {
				if (c.getName() != oldCook.getName())
					cooks2[i++] = c;
			}
			cooks = cooks2;
			System.out.println(oldCook.getName() + " was deleted.");

		} else {
			System.out.println(oldCook.getName() + " does not exist.");
		}
	}

	private boolean isExistCook(Cook cook) {
		boolean answer = false;
		for (Cook c : cooks) {
			if (c.getName() == cook.getName()) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	public void addOrderToFreeCook(Order newOrder) {
		int counter = 0;
		for (Cook c : cooks) {
			if (c.isFree()) {
				c.addOrder(newOrder);
				counter = 1;
				break;
			}
		}
		if (counter == 0)
			System.out.println("All cooks are busy now!");
	}

	public void printCooks() {
		System.out.println("Our cooks:");
		for (Cook c : cooks) {
			if(c.getOrder()!=null)
			System.out.println(c.getName() + " makes order " + c.getOrder().getNumberOfOrder());
			else{
				System.out.println(c.getName() + " don't make order! ");
			}
		}
	}

}

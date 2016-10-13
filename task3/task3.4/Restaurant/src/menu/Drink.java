package menu;

public class Drink extends ADish {
	private StringBuilder stb = new StringBuilder();

	public Drink(EDrinks name, int price) {
		super(name.toString().toLowerCase(), price);
	}

	@Override
	public void printInform() {
		stb.delete(0, stb.length());
		stb.append("Drink ").append(super.getName()).append(" Price: ").append(super.getPrice());
		System.out.println(stb.toString());
	}

}

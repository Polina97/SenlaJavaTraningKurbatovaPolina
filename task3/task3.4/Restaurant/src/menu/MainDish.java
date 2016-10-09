package menu;

public class MainDish extends ADish {

	private StringBuilder stb = new StringBuilder();

	public MainDish(EMainDishes name, int price) {
		super(name.toString().toLowerCase(), price);
	}

	@Override
	public void printInform() {
		stb.delete(0, stb.length());
		stb.append("Dish ").append(super.getName()).append(" Price: ").append(super.getPrice());
		System.out.println(stb.toString());

	}

}

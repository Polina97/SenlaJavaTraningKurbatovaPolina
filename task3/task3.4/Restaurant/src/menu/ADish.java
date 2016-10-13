package menu;

public abstract class ADish implements IDish{

	private final String name;
	private int price;
	
	public ADish(String name, int price) {
		this.name = name;
		this.price = price;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getPrice() {
		return price;
	}
	@Override
	public int getDiscountPrice() {
		return (int)(price*0.1);
	}
	@Override
	public abstract void printInform();

	
	
}

package product;

public abstract class AProductPart implements IProductPart {
	private String color;
	private String name;

	public AProductPart(String color, String name) {
		this.name = name;
		this.color = color;

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void changeColor(String color) {
		this.color = color;
	}

}

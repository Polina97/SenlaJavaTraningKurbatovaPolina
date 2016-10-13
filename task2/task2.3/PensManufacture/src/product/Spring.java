package product;

public class Spring extends AProductPart {
	private static final String name = "Spring";
	private String material;

	public Spring(String color, String material) {
		super(color, name);
		this.material = material;
		System.out.println(getFullInformation());

	}

	@Override
	public String getFullInformation() {
		return "This is a spring, color of spring is " + super.getColor() + " material is " + material;
	}

}

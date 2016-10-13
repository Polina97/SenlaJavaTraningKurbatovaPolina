package product;

public class Body extends AProductPart {
	private static final String name = "Body";

	public Body(String color) {
		super(color, name);
		System.out.println(getFullInformation());
	}

	@Override
	public String getFullInformation() {
		return "This is a body, color of body is " + super.getColor();
	}

}

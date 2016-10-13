package product;

public class Kernel extends AProductPart{
	private final static String name = "Kernel";
	private String type;
	
	public Kernel(String color, String type) {
		super(color, name);
		this.type = type;
		System.out.println(getFullInformation());
		
	}

	@Override
	public String getFullInformation() {
		return "This is a kelner, color of ink is "+super.getColor()+" , type is "+ type;
	}
	


}

package product;


public class Pen implements IProduct{
	private Body body;
	private Kernel kelner;
	private  Spring spring;
	
	public Body getBody() {
		return body;
	}

	public Kernel getKelner() {
		return kelner;
	}

	public Spring getSpring() {
		return spring;
	}

	@Override
	public void installFirstPart(IProductPart body) {
		this.body = (Body)body;
		System.out.println("Install first part: "+body.getName());
	}

	@Override
	public void installSecondPart(IProductPart kelner) {
		this.kelner = (Kernel) kelner;
		System.out.println("Install second part: "+kelner.getName());
	}

	@Override
	public void installThirdPart(IProductPart spring) {
		this.spring = (Spring) spring;
		System.out.println("Install third part: "+spring.getName());
	}

	@Override
	public String getName() {
		return "Pen";
	}

}

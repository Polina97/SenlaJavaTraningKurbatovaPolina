package assemblyLine;

import product.IProductPart;
import product.Spring;

public class ThirdLineStep implements ILineStep{
	private Spring spring;

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build spring.");
		spring = new Spring("gray", "metal");
		return spring;
	}

}

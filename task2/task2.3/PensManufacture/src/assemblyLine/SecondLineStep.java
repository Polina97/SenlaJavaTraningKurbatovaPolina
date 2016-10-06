package assemblyLine;

import product.*;

public class SecondLineStep implements ILineStep {
	private Kernel kernel;

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build kelner.");
		kernel =  new Kernel("blue", "gel");
		return kernel;
	}

}

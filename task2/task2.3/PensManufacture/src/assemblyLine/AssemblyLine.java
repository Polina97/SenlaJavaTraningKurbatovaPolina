package assemblyLine;

import product.IProduct;

public class AssemblyLine implements IAssemblyLine{
	private FirstLineStep fStep = new FirstLineStep();
	private SecondLineStep sStep = new SecondLineStep();
	private ThirdLineStep thStep = new ThirdLineStep();
	

	public AssemblyLine(FirstLineStep fStep, SecondLineStep sStep, ThirdLineStep thStep) {
		this.fStep = fStep;
		this.sStep = sStep;
		this.thStep = thStep;
				
	}

	@Override
	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(fStep.buildProductPart());
		product.installSecondPart(sStep.buildProductPart());
		product.installThirdPart(thStep.buildProductPart());
		System.out.println("Assembly of "+product.getName() + " is completed.");
		return product;
	}

}

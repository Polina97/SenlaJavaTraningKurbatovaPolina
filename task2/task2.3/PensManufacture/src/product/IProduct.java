package product;

public interface IProduct {
	public String getName();

	public void installFirstPart(IProductPart productPart);

	public void installSecondPart(IProductPart productPart);

	public void installThirdPart(IProductPart productPart);
}

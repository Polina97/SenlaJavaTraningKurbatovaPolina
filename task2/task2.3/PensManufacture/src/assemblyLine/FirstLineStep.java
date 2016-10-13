package assemblyLine;

import product.*;

public class FirstLineStep  implements ILineStep{
	private Body body; 

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build body.");
		body = new Body("black");
		return body;
	}

}

package runner;

import assemblyLine.*;
import product.*;

public class Runner {
	private static IAssemblyLine assemblyLine;
	private static FirstLineStep fStep = new FirstLineStep();
	private static SecondLineStep sStep = new SecondLineStep();
	private static ThirdLineStep thStep = new ThirdLineStep();
	private static IProduct pen = new Pen();

	public static void main(String[] args) {
		assemblyLine = new AssemblyLine(fStep,sStep,thStep);
		assemblyLine.assembleProduct(pen);
		
		

	}

}

package main;

public class Runner {
	private static String[] arrayString;
	private static StringManager manager;

	public static void main(String[] args) {
		arrayString = new String[] {"monday","tuesday","wednesday","thursday"};
		manager = new StringManager(arrayString);
		System.out.println(manager.makeString());

	}

}

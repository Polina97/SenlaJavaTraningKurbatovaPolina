package main;

public class StringManager {
	private String[] arrayString;
	private StringBuilder builder = new StringBuilder();

	public StringManager(String[] arrayString) {
		this.arrayString = arrayString;
	}

	public String makeString(){
		for(String s : arrayString){
			builder.append(toFirstCapitalLetter(s)).append(" ");
		}
		return builder.toString();
	}
	private String toFirstCapitalLetter(String s){
		s.toLowerCase();
		return s.substring(0, 1).toUpperCase()+ s.substring(1);
	}
}

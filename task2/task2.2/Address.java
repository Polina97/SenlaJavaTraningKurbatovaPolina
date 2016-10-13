public class Address{
	private String city;
	private String street;
	private int numberOfHouse;

	public Address (String city, String street, int house){
		System.out.println("Hello from Address");
		this.city = city;
		this.street = street;
		this.numberOfHouse = house;
	}

	public String getCity(){
		return city;
	}
	public String getStreet(){
		return street;
	}
	public int getNumberOfHouse(){
		return numberOfHouse;
	}
	public String getFullAddress(){
		return "City: "+city+", street: "+street +", house: "+numberOfHouse;
	}
}
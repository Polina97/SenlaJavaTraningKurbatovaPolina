public class Man{
	private String name;
	private String lastName;
	private Address address;
	private int phoneNumber;

	public Man(String name, String lastName, Address address, int phoneNumber){
		System.out.println("Hello from Man");
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	public Address getAddress(){
		return address;
	}

	public String getFullInformation(){
		return "Name: "+name+" Lastname: "+lastName+" "
		 		+address.getFullAddress()+", Phone number: "+phoneNumber;
	}
}
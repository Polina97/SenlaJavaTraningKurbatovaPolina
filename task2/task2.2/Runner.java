public class Runner {
	private static Address address;
	private static Client client;
	private static Account account;

	public static void main(String[] args){
		address = new Address("London","Baker Street",221);
		client = new Client("Sherlock","Holms",address,103, 11111);
		account = new Account(12345, 1000, client);
		System.out.println(account.getOwner().getFullInformation());

	}
}
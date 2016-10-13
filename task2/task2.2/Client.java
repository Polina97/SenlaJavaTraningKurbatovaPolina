import java.util.*;

public class Client extends Man{
	private long numberClient;
	private List<Account> accounts = new ArrayList<Account>();

	public Client (String name, String lastName, Address address, int phoneNumber,long numberClient){
		super(name, lastName,address,phoneNumber);
		System.out.println("Hello from Client");
		this.numberClient = numberClient;
	}

	public void addAccount(Account newAccount){
		accounts.add(newAccount);
	}
	public void deleteAccount(Account oldAccount){
		accounts.remove(oldAccount);
	}
	public List<Account> getAccounts(){
		return accounts;
	}
	public String getFullInformation(){
		return "Number client: "+ numberClient+" "+super.getFullInformation();
	}
}
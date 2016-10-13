public class Account implements IAccount{
	private int number;
	private int sum;
	private Client owner;
	private boolean activity = true;
	

	public Account(int number, int sum, Client owner) {
		System.out.println("Hello from Account");
		this.number = number;
		this.sum = sum;
		this.owner = owner;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Client getOwner() {
		return owner;
	}

	@Override
	public long getSum() {
		return sum;
	}

	@Override
	public boolean isActive() {
		return activity;
	}
	

}

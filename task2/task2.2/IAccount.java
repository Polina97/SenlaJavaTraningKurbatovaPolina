public interface IAccount {
	public final int maxSum = 1_000_000;

	public int getNumber();

	public Client getOwner();

	public long getSum();

	public boolean isActive();

}

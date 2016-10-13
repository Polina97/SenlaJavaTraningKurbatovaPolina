package cook;

public class Cook {
	private final String name;
	private Order order;
	private boolean isFree = true;
	

	public Cook(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void addOrder(Order newOrder){
		if(newOrder!= null){
			this.order = newOrder;
			isFree = false;
			StringBuilder s =new StringBuilder();
			s.append("Order ").append(getOrder().getNumberOfOrder()).append(" was added to ").append(name); 
			System.out.println(s);
		}else{
			System.out.println("Order is not exist!");
		}
	}
	public void deleteOrder(){
		isFree = true;
	}
	
	public Order getOrder() {
		return order;
	}

	public boolean isFree(){
		return isFree;
	}
	
	
	
	

}

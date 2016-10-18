package com.senla.bookshop.entity;



import com.senla.bookshop.api.entities.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity{
	
	private final String BOOK = "Book";
	private final String ORDER = "Order";
	private final String BUYER = "Buyer";
	
	@Override
	public String getType() {
		if(this instanceof Book){
			return BOOK;
		}else if(this instanceof Order){
			return ORDER;
		}else if(this instanceof Buyer){
			return BUYER;
		}else
		return null;
	}

	@Override
	public abstract boolean equals(Object obj) ;

	@Override
	public abstract String toString();
	
	

}

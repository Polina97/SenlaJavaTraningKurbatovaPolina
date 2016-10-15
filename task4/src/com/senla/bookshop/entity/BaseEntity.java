package com.senla.bookshop.entity;

import com.senla.bookshop.api.entities.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity{

	@Override
	public String getType() {
		if(this instanceof Book){
			return "Book";
		}else if(this instanceof Buyer){
			return "Buyer";
		}
		return null;
	}

	@Override
	public abstract boolean equals(Object obj) ;

	@Override
	public abstract String toString();
	
	

}

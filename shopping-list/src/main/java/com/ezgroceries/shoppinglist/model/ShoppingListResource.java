package com.ezgroceries.shoppinglist.model;

import java.util.UUID;

public class ShoppingListResource {
	public UUID shoppingListId;
	public String name;

	public ShoppingListResource(UUID shoppingListId, String name){
		this.shoppingListId = shoppingListId;
		this.name = name;
	}
}

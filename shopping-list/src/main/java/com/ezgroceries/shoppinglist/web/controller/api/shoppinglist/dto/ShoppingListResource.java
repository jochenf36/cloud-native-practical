package com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.dto;

import java.util.UUID;

public class ShoppingListResource {
	public UUID shoppingListId;
	public String name;

	public ShoppingListResource(UUID shoppingListId, String name){
		this.shoppingListId = shoppingListId;
		this.name = name;
	}
}

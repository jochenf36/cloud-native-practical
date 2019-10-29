package com.ezgroceries.shoppinglist.web.controller.api.shoppinglist.dto;

import java.util.List;

public class ShoppingList {
	public String shoppingListId;
	public String name;
	public List<String> ingredients;

	public ShoppingList(String shoppingListId, String name, List<String> ingredients){
		this.shoppingListId = shoppingListId;
		this.name  = name;
		this.ingredients = ingredients;
	}
}

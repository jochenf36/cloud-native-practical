package com.ezgroceries.shoppinglist.web.controller.api.cocktail.dto;

import java.util.List;
import java.util.UUID;

public class CocktailResource {
	public UUID cocktailId;
	public String name;
	public String glass;
	public String instructions;
	public String image;
	public List<String> ingredients;

	public CocktailResource(UUID cocktailId, String name, String glass, String instructions, String image, List<String> ingredients){
		this.cocktailId = cocktailId;
		this.name = name;
		this.glass = glass;
		this.instructions = instructions;
		this.image = image;
		this.ingredients = ingredients;
	}
}

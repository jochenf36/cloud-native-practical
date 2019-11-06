package com.ezgroceries.shoppinglist.persistence.entity;

import com.ezgroceries.shoppinglist.helper.StringSetConverter;
import com.ezgroceries.shoppinglist.persistence.entity.ShoppingListEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity{

	@Id
	@Column
	private UUID id;

	@Column(name = "id_drink")
	private String idDrink;

	@Column
	private String name;

	@Convert(converter = StringSetConverter.class)
	private Set<String> ingredients;

	@ManyToMany(mappedBy = "cocktails")
	private Set<ShoppingListEntity> shoppingLists;

	@Column
	private String glass;

	@Column
	private String instructions;

	@Column
	private String imagelink;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIdDrink() {
		return idDrink;
	}

	public void setIdDrink(String idDrink) {
		this.idDrink = idDrink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGlass() {
		return glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImagelink() {
		return imagelink;
	}

	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}

	public Set<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<String> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<ShoppingListEntity> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}
}


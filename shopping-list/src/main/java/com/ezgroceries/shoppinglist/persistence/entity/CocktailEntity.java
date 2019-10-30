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


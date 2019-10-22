package com.ezgroceries.shoppinglist.repository.shoppingList;

import com.ezgroceries.shoppinglist.repository.cocktail.CocktailEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {
	@Id
	@Column
	private UUID id;

	@Column
	private String name;

	@ManyToMany
	@JoinTable(
			name = "cocktail_shopping_list",
			joinColumns = @JoinColumn(name = "shopping_list_id"),
			inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
	private Set<CocktailEntity> cocktails;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CocktailEntity> getCocktails() {
		return cocktails;
	}

	public void setCocktails(Set<CocktailEntity> cocktails) {
		this.cocktails = cocktails;
	}
}

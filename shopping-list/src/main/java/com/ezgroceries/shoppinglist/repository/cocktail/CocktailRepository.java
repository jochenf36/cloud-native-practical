package com.ezgroceries.shoppinglist.repository.cocktail;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {
	List<CocktailEntity> findByIdDrinkIn(List<String> ids);
	CocktailEntity save(CocktailEntity cocktailEntity);
}

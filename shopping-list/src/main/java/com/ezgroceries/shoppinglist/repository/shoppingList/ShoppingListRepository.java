package com.ezgroceries.shoppinglist.repository.shoppingList;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {
	ShoppingListEntity save(ShoppingListEntity shoppingListEntity);

	ShoppingListEntity findById(UUID id);

	List<ShoppingListEntity> findAll();
}

package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import com.ezgroceries.shoppinglist.service.internal.CocktailServiceDummyImpl;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListService;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListServiceDummyImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ShoppingListTestConfig {
	@Bean
	public CocktailService cocktailService(){
		return new CocktailServiceDummyImpl();
	}

	@Bean
	public ShoppingListService shoppingListService(){
		return new ShoppingListServiceDummyImpl();
	}
}

package config;

import com.ezgroceries.shoppinglist.persistence.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.persistence.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import com.ezgroceries.shoppinglist.service.internal.CocktailServiceImpl;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListService;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListServiceImpl;
import com.ezgroceries.shoppinglist.service.external.CocktailDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingListConfig {

	private CocktailDBClient cocktailDBClient;
	private CocktailRepository cocktailRepository;
	private ShoppingListRepository shoppingListRepository;

	@Autowired
	ShoppingListConfig(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository, ShoppingListRepository shoppingListRepository){
		this.cocktailRepository = cocktailRepository;
		this.cocktailDBClient = cocktailDBClient;
		this.shoppingListRepository = shoppingListRepository;
	}

	@Bean
	public CocktailService cocktailService(){
		return new CocktailServiceImpl(cocktailDBClient, cocktailRepository);
	}

	@Bean
	public ShoppingListService shoppingListService(){
		return new ShoppingListServiceImpl(shoppingListRepository, cocktailRepository);
	}
}

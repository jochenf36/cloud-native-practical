package config;

import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailServiceCocktailDBImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingListConfig {

	@Bean
	public CocktailService cocktailService(){
		return new CocktailServiceCocktailDBImpl();
	}
}

package com.ezgroceries.shoppinglist.service.internal;

import com.ezgroceries.shoppinglist.model.CocktailResource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CocktailServiceDummyImpl implements CocktailService {
	public List<CocktailResource> searchCocktails(String search) {
		return Arrays.asList(
				new CocktailResource(
						UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
						"Cocktail glass",
						"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
						"https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
						Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
				new CocktailResource(
						UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
						"Cocktail glass",
						"Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
						"https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
						Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
	}
}

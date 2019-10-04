create table COCKTAIL_SHOPPING_LIST (
    cocktail_id UUID NOT NULL,
    shopping_list_id UUID NOT NULL,
    PRIMARY KEY (cocktail_id, shopping_list_id),
    FOREIGN KEY (cocktail_id) REFERENCES cocktail(id) ON UPDATE CASCADE,
    FOREIGN KEY (shopping_list_id) REFERENCES shopping_list(id) ON UPDATE CASCADE
);
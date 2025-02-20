package br.ufjf.b4hive.model.inventory;

public record Recipe(int resultID, Ingredient[] ingredients) {
    public Recipe {
        ingredients = ingredients.clone();
    }
}

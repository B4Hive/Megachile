package br.ufjf.b4hive.model.inventory;

import java.util.ArrayList;
import java.util.List;
import br.ufjf.b4hive.model.inventory.*;

public class Crafting {
	private static List<Recipe> recipes = new ArrayList<>();

	public static void addRecipe(Recipe recipe){
		recipes.add(recipe);
	}

	public static List<Recipe> getRecipes(){
		return recipes;
	}
	
	public static boolean canCraft(int resultID, Inventory inventory){
		// TODO: Implement this method
		return false;
	}
}

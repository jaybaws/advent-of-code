package org.jaybaws.adventofcode.y2020.day21;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

public class Food {

    private List<String> ingredients = new ArrayList<String>();
    private List<String> allergens = new ArrayList<String>();

    public Food(String line) {
        this.ingredients = Arrays.asList(StringUtils.substringBefore(line, " (contains").split(" "));
        this.allergens = Arrays.asList(StringUtils.substringBetween(line, " (contains ", ")").split(", "));
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean containsIngredient(String ingredient) { return this.ingredients.contains(ingredient); }

    public boolean containsAllergen(String allergen) {
        return this.allergens.contains(allergen);
    }

}
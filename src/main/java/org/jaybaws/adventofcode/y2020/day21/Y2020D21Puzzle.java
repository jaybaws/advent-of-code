package org.jaybaws.adventofcode.y2020.day21;
import org.jaybaws.adventofcode.*;
import java.util.*;
import java.util.stream.Collectors;

public class Y2020D21Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D21Puzzle(List<String> altInput) {
        super(altInput);
    }

    List<Food> allFoods;
    Set<String> allIngredients;
    Set<String> allAllergens;
    Map<String, Set<String>> shizzle;

    @Override
    protected void prepare() {
        allFoods = new ArrayList<Food>();
        allAllergens = new HashSet<String>();
        allIngredients = new HashSet<String>();
        shizzle = new HashMap<String, Set<String>>();

        for (String line : puzzleInput) {
            Food f = new Food(line);
            allFoods.add(f);
            allAllergens.addAll(f.getAllergens());
            allIngredients.addAll(f.getIngredients());
        }
    }

    public Y2020D21Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        long occurrences = 0;

        Set<String> certainAllergens = new HashSet<String>();
        for (String allergen : allAllergens) {

            List<Food> foodsWithTheAllergen =  allFoods.stream().filter(f -> f.containsAllergen(allergen)).collect(Collectors.toList());

            Set<String> potentialAllergenicIngredients = new HashSet<String>();
            foodsWithTheAllergen.stream().forEach(f -> potentialAllergenicIngredients.addAll(f.getIngredients()));
            Set<String> certainAllergenicIngredients = potentialAllergenicIngredients.stream()
                    .filter(f -> foodsWithTheAllergen.stream().filter(f2 -> f2.containsIngredient(f)).collect(Collectors.toList()).size() == foodsWithTheAllergen.size())
                    .collect(Collectors.toSet());

            shizzle.put(allergen, certainAllergenicIngredients);
            certainAllergens.addAll(certainAllergenicIngredients);
        }

        Set<String> certainlyNot = new HashSet<String>(allIngredients);
        certainlyNot.removeAll(certainAllergens);

        for (String s : certainlyNot) {
            for (Food f : allFoods) {
                if (f.containsIngredient(s)) {
                    occurrences++;
                }
            }
        }

        return occurrences;
    }

    @Override
    public String solution2() {
        Set<String> seen = new HashSet<String>();
        Map<String, String> solution = new HashMap<String, String>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String key : shizzle.keySet()) {
                Set<String> options = shizzle.get(key);
                if (options.size() == 1) {
                    solution.put(key, options.toArray(new String[0])[0]);
                    seen.addAll(options);
                    changed = true;
                }
            }

            shizzle.values().stream().forEach(v -> v.removeAll(seen));
        }

        return String.join(",", solution.keySet().stream().sorted().map(s -> solution.get(s)).collect(Collectors.toList()));

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D21Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}
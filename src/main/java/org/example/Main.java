package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> availableIngredients = new ArrayList<>();
        System.out.println("=== Smart Recipe Recommender ===");
        System.out.print("How many ingredients do you have? ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your ingredients:");

        for (int i = 0; i < n; i++) {
            String ingredient = sc.nextLine().trim().toLowerCase();
            availableIngredients.add(ingredient);
        }
        System.out.print("Enter maximum preparation time (minutes): ");
        int maxTime = sc.nextInt();
        System.out.print("Enter minimum matching percentage: ");
        double minMatch = sc.nextDouble();

        // Read recipes from CSV file
        ArrayList<Recipe> recipes = loadRecipes();
        System.out.println("\nYour available ingredients:");
        System.out.println(availableIngredients);

        // Calculate matching percentage
        for (Recipe recipe : recipes) {
            int matchCount = 0;
            for (String ingredient : recipe.ingredients) {
                if (availableIngredients.contains(ingredient)) {
                    matchCount++;
                }
            }

            double percentage =
                    (matchCount * 100.0) / recipe.ingredients.size();
            recipe.matchPercentage = percentage;
        }

        // Sort recipes by matching percentage
        recipes.sort((r1, r2) ->
                Double.compare(r2.matchPercentage, r1.matchPercentage));

        // Store recommended recipes
        ArrayList<Recipe> recommendedRecipes = new ArrayList<>();
        System.out.println("\nRecipe Recommendations:");
        for (Recipe recipe : recipes) {
            if (recipe.matchPercentage >= minMatch &&
                    recipe.preparationTime <= maxTime) {
                recommendedRecipes.add(recipe);
            }
        }

        // Check whether recipes are available
        if (recommendedRecipes.isEmpty()) {
            System.out.println("Sorry! No matching recipes found.");
        } else {

            // Display numbered recipes
            for (int i = 0; i < recommendedRecipes.size(); i++) {
                Recipe recipe = recommendedRecipes.get(i);
                System.out.println("\n" + (i + 1) + ". " + recipe.name);
                System.out.printf("Matching Percentage: %.2f%%\n",
                        recipe.matchPercentage);
                System.out.println("Preparation Time: "
                        + recipe.preparationTime + " minutes");
            }

            // Allow user to view multiple recipe
            while (true) {
                System.out.print("\nEnter recipe number (0 to exit): ");
                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();
                    //Exit the loop
                    if (choice == 0) {
                        System.out.println("Thank you for using Smart Recipe Recommender");
                        break;
                    }

                    //check whether selection is valid
                    if (choice >= 1 &&
                            choice <= recommendedRecipes.size()) {
                        Recipe selectedRecipe =
                                recommendedRecipes.get(choice - 1);
                        System.out.println("\nSelected Recipe Details:");
                        System.out.println("Recipe Name: "
                                + selectedRecipe.name);
                        System.out.println("Ingredients: "
                                + selectedRecipe.ingredients);
                        System.out.printf("Matching Percentage: %.2f%%\n",
                                selectedRecipe.matchPercentage);
                        System.out.println("Preparation Time: "
                                + selectedRecipe.preparationTime + " minutes");
                        System.out.println("Cooking Instructions: "
                                + selectedRecipe.instructions);
                    } else {
                        System.out.println("Invalid recipe number!");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                }
            }
        }
        sc.close();
    }

    // Method to read recipes from CSV file
    public static ArrayList<Recipe> loadRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("data/recipes.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",", 4);

                // Check whether all four details are available
                if (details.length < 4) {
                    System.out.println("Invalid recipe data: " + line);
                    continue;
                }
                String name = details[0].trim();

                // Separate ingredients
                String[] ingredientArray = details[1].split(";");
                ArrayList<String> ingredients = new ArrayList<>();
                for (String ingredient : ingredientArray) {
                    ingredients.add(ingredient.trim().toLowerCase());
                }
                // Convert preparation time
                int preparationTime;
                try {
                    preparationTime = Integer.parseInt(details[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Invalid preparation time: " + details[2]);
                    continue;
                }
                String instructions = details[3].trim();

                // Create Recipe object
                Recipe recipe = new Recipe(
                        name, ingredients, preparationTime, instructions);

                // Add Recipe object to ArrayList
                recipes.add(recipe);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(
                    "Error reading recipe file: " + e.getMessage());
        }
        return recipes;
    }
}
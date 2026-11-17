package org.example;
import java.util.ArrayList;
public class Recipe {
    String name;
    ArrayList<String> ingredients;
    double matchPercentage;
    int preparationTime;
    String instructions;

    public Recipe(String name,ArrayList<String> ingredients,
                  int preparationTime,String instructions){
        this.name = name;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.instructions = instructions;
    }
}

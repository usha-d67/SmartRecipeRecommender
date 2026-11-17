# Smart Recipe Recommender

## Project Description
Smart Recipe Recommender is a Java-based application that suggests recipes based on the ingredients available to the user.

Users can enter their available ingredients, maximum preparation time, and minimum matching percentage. The application recommends suitable recipes and displays their cooking instructions.

## Technologies Used
- Java 21
- Java Collections (ArrayList)
- File Handling
- CSV (Comma-Separated Values)
- IntelliJ IDEA
- Maven

## Features
- Accepts ingredients from users.
- Stores recipe information in a CSV file.
- Calculates ingredient matching percentages.
- Sorts recipes based on matching percentage.
- Filters recipes by preparation time.
- Filters recipes by minimum matching percentage.
- Displays recommended recipes.
- Allows users to view complete recipe details.
- Handles invalid recipe selections and invalid preparation times in the CSV file.

## Recipe Categories
- Rice recipes
- Paneer recipes
- Mushroom recipes
- Biryani recipes
- Egg recipes
- Vegetable recipes
- Sweets and desserts

## Project Structure

```text
SmartRecipeRecommender/
│
├── data/
│   └── recipes.csv
│
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── Main.java
│                   └── Recipe.java
│
└── README.md
```

## How to Run
1. Clone or download the project.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 21 is configured.
4. Ensure the `data/recipes.csv` file exists.
5. Run `Main.java`.
6. Enter your available ingredients.
7. Enter the maximum preparation time.
8. Enter the minimum matching percentage.
9. View the recommended recipes.

## Future Improvements
- Database integration using MySQL.
- User-friendly graphical interface.
- Recipe search by category.
- Improved ingredient matching.

## Author
Developed as a Java project.
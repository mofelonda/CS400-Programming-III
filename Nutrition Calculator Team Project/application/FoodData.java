package application;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

    // List of all the food items.
    private List<FoodItem> foodItemList;

    //Map of nutrients and their corresponding index
    //String is the name of the nutrient - as in which nutrients BP to look at, double the nutrient value, 
    //and fooditem the corresponding food item
    private HashMap<String, BPTree<Double, FoodItem>> indexes;

    BPTree<Double,FoodItem> fat;
    BPTree<Double,FoodItem> protein;
    BPTree<Double,FoodItem> calories;
    BPTree<Double,FoodItem> carbohydrate;
    BPTree<Double,FoodItem> fiber;

    /**
     * Public constructor
     */
    public FoodData() {
        foodItemList = new LinkedList<FoodItem>();
        indexes = new HashMap<String, BPTree<Double, FoodItem>>();
        //Initializing all BPtrees with a branching factor of 3
        //Each will store the value and the FoodItem it corresponds to
        fat = new BPTree<>(3);
        protein = new BPTree<>(3);
        calories = new BPTree<>(3);
        carbohydrate = new BPTree<>(3);
        fiber = new BPTree<>(3);
    }

    /**
     * Loads the data in the .csv file
     * 
     * file format:
     * <id1>,<name>,<nutrient1>,<value1>,<nutrient2>,<value2>,...
     * <id2>,<name>,<nutrient1>,<value1>,<nutrient2>,<value2>,...
     * 
     * Example:
     * 556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
     * 
     * Note:
     *  1. All the rows are in valid format.
     *  2. All IDs are unique.
     *  3. Names can be duplicate.
     *  4. All columns are strictly alphanumeric (a-zA-Z0-9_).
     *  5. All food items will strictly contain 5 nutrients in the given order:    
     *     calories,fat,carbohydrate,fiber,protein
     *  6. Nutrients are CASE-INSENSITIVE. 
     * 
     * @param filePath path of the food item data file 
     *        (e.g. folder1/subfolder1/.../foodItems.csv) 
     * @throws  
     */
    @Override
    public void loadFoodItems(String filePath)  {
        File inputFile;
        BufferedReader reader;
        Scanner sc;
        String line = null;

        try {
            //clear data when importing new file
            foodItemList.clear();
            indexes.clear();
            inputFile = new File(filePath);
            reader = new BufferedReader(new FileReader(inputFile));
            //Read one line at a time
            while((line = reader.readLine()) != null) {
                ArrayList<String> foodColle = new ArrayList<String>(); //To temp store data values
                sc = new Scanner(line); //Setting scanner to read from one line
                sc.useDelimiter(","); //Use comma to find separate words

                while(sc.hasNext()) {
                    String data = sc.next();
                    foodColle.add(data);
                }//Checks if string is in valid format if not does not add to list
                if(foodColle.size() == 12 && foodColle.get(2).equals("calories") 
                    && foodColle.get(4).equals("fat") && foodColle.get(6).equals("carbohydrate")
                    && foodColle.get(8).equals("fiber") && foodColle.get(10).equals("protein")) {
                    //Creates the food with the correct values 
                    FoodItem addFood = new FoodItem(foodColle.get(0), foodColle.get(1));
                    addFood.addNutrient(foodColle.get(2), Double.parseDouble(foodColle.get(3)));
                    addFood.addNutrient(foodColle.get(4), Double.parseDouble(foodColle.get(5)));
                    addFood.addNutrient(foodColle.get(6), Double.parseDouble(foodColle.get(7)));
                    addFood.addNutrient(foodColle.get(8), Double.parseDouble(foodColle.get(9)));
                    addFood.addNutrient(foodColle.get(10), Double.parseDouble(foodColle.get(11)));

                    addFoodItem(addFood);
                }

            }

            reader.close();
            //        } catch (FileNotFoundException e) {
            //            System.out.println(filePath + " is not a valid fileName");
        }
        catch (IOException e) {
        	//In the case of an exception a warning screen pops up
			Stage warn = new Stage();
			//stage is given the title ("Warning!")
			warn.setTitle("Warning!");
			//VBox is created and given the warning text
			VBox warning = new VBox(20);
			warning.setPadding(new Insets(40, 30, 40, 30));
			Text warnText = new Text("Please input a valid filename!");
			warnText.setFill(Color.WHITE);
			warning.getChildren().add(warnText);
			warning.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
			
			//creates new scene with the warning
			Scene popup = new Scene(warning, 235, 100);
			//sets the scene in the stage
			warn.setScene(popup);
			//displays the popup
			warn.show();
        }
    }
    /**
     * Gets all the food items that have name containing the substring.
     * 
     * Example:
     *     All FoodItem
     *         51c38f5d97c3e6d3d972f08a,Similac_FormulaSoyforDiarrheaReadytoFeed,calories,100,fat,0,carbohydrate,0,fiber,0,protein,3
     *         556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
     *     Substring: soy
     *     Filtered FoodItem
     *         51c38f5d97c3e6d3d972f08a,Similac_FormulaSoyforDiarrheaReadytoFeed,calories,100,fat,0,carbohydrate,0,fiber,0,protein,3
     * 
     * Note:
     *     1. Matching should be CASE-INSENSITIVE.
     *     2. The whole substring should be present in the name of FoodItem object.
     *     3. substring will be strictly alphanumeric (a-zA-Z0-9_)
     * 
     * @param substring substring to be searched
     * @return list of filtered food items; if no food item matched, return empty list
     */

    @Override
    public List<FoodItem> filterByName(String substring) {
        //List for filtered item storage
        ArrayList<FoodItem> filteredList = new ArrayList<>();
        for (int i = 0; i < foodItemList.size(); i++) {
            String foodName = foodItemList.get(i).getName();
            //Check if the name of the food contains the substring
            if (foodName.contains(substring)) {
                //If so, add to the filtered list
                filteredList.add(foodItemList.get(i));
            }
        }
        //Alphabetize the filtered list before returning
        filteredList = (ArrayList<FoodItem>) alphabetize(filteredList);
        return filteredList;
    }

    /**
     * Gets all the food items that fulfill ALL the provided rules
     *
     * Format of a rule:
     *     "<nutrient> <comparator> <value>"
     * 
     * Definition of a rule:
     *     A rule is a string which has three parts separated by a space:
     *         1. <nutrient>: Name of one of the 5 nutrients [CASE-INSENSITIVE]
     *         2. <comparator>: One of the following comparison operators: <=, >=, ==
     *         3. <value>: a double value
     * 
     * Note:
     *     1. Multiple rules can contain the same nutrient.
     *         E.g. ["calories >= 50.0", "calories <= 200.0", "fiber == 2.5"]
     *     2. A FoodItemADT object MUST satisfy ALL the provided rules i
     *        to be returned in the filtered list.
     *
     * @param rules list of rules
     * @return list of filtered food items; if no food item matched, return empty list
     */

    @Override
    public List<FoodItem> filterByNutrients(List<String> rules) {
        String[] ruleString;
        String nutrient;
        String comparator;
        Double value;
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        try {
            //Loop iterated through each rule in the list
            for (String rule : rules) {
                //Split the rule into an array of 3 items
                ruleString = rule.split(" ");
                //Invalid rule - does not contain 3 items
                if (ruleString.length != 3) {
                    continue;
                }
                nutrient = ruleString[0];
                comparator = ruleString[1];
                value = Double.parseDouble(ruleString[2]);
                //Navigate through switch statement based on nutrient in rule
                switch (nutrient) {
                    case "protein" :
                        //If there isn't anything in the filtered list, add everything we find in rangesearch
                        if (foodItems.isEmpty()) {
                            foodItems.addAll((ArrayList<FoodItem>) protein.rangeSearch(value, comparator));
                        }
                        else {
                            //Iterate through the list of filtered foods and if they also are on the list returned
                            //by rangesearch then keep them in the list
                            Iterator<FoodItem> i = foodItems.iterator();
                            while (i.hasNext()) {
                                FoodItem s = i.next();
                                //If they are not in the rangesearch remove them from the filtered list
                                if(!(protein.rangeSearch(value, comparator).contains(s))) {
                                    i.remove();
                                }
                            }
                        }
                        break;
                    case "carbohydrate" :
                        if (foodItems.isEmpty()) {
                            foodItems.addAll((ArrayList<FoodItem>) carbohydrate.rangeSearch(value, comparator));
                        }
                        else {
                            Iterator<FoodItem> i = foodItems.iterator();
                            while (i.hasNext()) {
                                FoodItem s = i.next();
                                if(!(carbohydrate.rangeSearch(value, comparator).contains(s))) {
                                    i.remove();
                                }
                            }
                        }
                        break;
                    case "fiber" :
                        if (foodItems.isEmpty()) {
                            foodItems.addAll((ArrayList<FoodItem>) fiber.rangeSearch(value, comparator));
                        }
                        else {
                            Iterator<FoodItem> i = foodItems.iterator();
                            while (i.hasNext()) {
                                FoodItem s = i.next();
                                if(!(fiber.rangeSearch(value, comparator).contains(s))) {
                                    i.remove();
                                }
                            }
                        }
                        break;
                    case "calories" :
                        if (foodItems.isEmpty()) {
                            foodItems.addAll((ArrayList<FoodItem>) calories.rangeSearch(value, comparator));
                        }
                        else {
                            Iterator<FoodItem> i = foodItems.iterator();
                            while (i.hasNext()) {
                                FoodItem s = i.next();
                                if(!(calories.rangeSearch(value, comparator).contains(s))) {
                                    i.remove();
                                }
                            }
                        }
                        break;
                    case "fat" :
                        if (foodItems.isEmpty()) {
                            foodItems.addAll((ArrayList<FoodItem>) fat.rangeSearch(value, comparator));
                        }
                        else {
                            Iterator<FoodItem> i = foodItems.iterator();
                            while (i.hasNext()) {
                                FoodItem s = i.next();
                                if(!(fat.rangeSearch(value, comparator).contains(s))) {
                                    i.remove();
                                }
                            }
                        }
                        break;
                }
            }
        } catch (NumberFormatException e) {
        	Stage warn = new Stage();
			//stage is given the title ("Warning!")
			warn.setTitle("Warning!");
			//VBox is created and given the warning text
			VBox warning = new VBox(20);
			warning.setPadding(new Insets(40, 30, 40, 30));
			Text warnText = new Text("Only numbers can be entered as nutrient values");
			warnText.setFill(Color.WHITE);
			warning.getChildren().add(warnText);
			warning.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
			
			//creates new scene with the warning
			Scene popup = new Scene(warning, 325, 100);
			//sets the scene in the stage
			warn.setScene(popup);
			//displays the popup
			warn.show();
        }
        //Alphabetize the filtered list before returning
        foodItems = (ArrayList<FoodItem>) alphabetize(foodItems);
        return foodItems;
    }

    /**
     * Adds a food item to the loaded data.
     * @param foodItem the food item instance to be added
     */

    @Override
    public void addFoodItem(FoodItem foodItem) {
        //Adding to food list & inserting values into each nutrients BPtree
        foodItemList.add(foodItem);
        fat.insert(foodItem.getNutrientValue("fat"), foodItem);
        indexes.put("fat", fat);
        protein.insert(foodItem.getNutrientValue("protein"), foodItem);
        indexes.put("protein", protein);
        carbohydrate.insert(foodItem.getNutrientValue("carbohydrate"), foodItem);
        indexes.put("carbohydrate", carbohydrate);
        fiber.insert(foodItem.getNutrientValue("fiber"), foodItem);
        indexes.put("fiber", fiber);
        calories.insert(foodItem.getNutrientValue("calories"), foodItem);
        indexes.put("calories", calories);
        //Alphabetize the list after adding new item
        foodItemList = alphabetize(foodItemList);
    }

    /**
     * Gets the list of all food items.
     * @return List<FoodItem> list of FoodItem
     */

    @Override
    public List<FoodItem> getAllFoodItems() {
        //Alphabetize the list before returning
        foodItemList = alphabetize(foodItemList);
        return foodItemList;
    }

    /**
     * Save the list of food items in ascending order by name
     * 
     * @param filename name of the file where the data needs to be saved 
     */
    @Override
    public void saveFoodItems(String filename) {
        try {
            FileWriter writer = new FileWriter(filename + ".csv");
            //Writing each food item to a new file and save to default dir
            for (FoodItem item : foodItemList) {
                writer.write(item.getID() + "," + item.getName() + ",");
                writer.write("calories," + (int) item.getNutrientValue("calories") + ",");
                writer.write("fat," + (int) item.getNutrientValue("fat") + ",");
                writer.write("carbohydrate," + (int) item.getNutrientValue("carbohydrate") + ",");
                writer.write("fiber," + (int) item.getNutrientValue("fiber") + ",");
                writer.write("protein," + (int) item.getNutrientValue("protein") + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.print("IO Exception was thrown, it is possible the file you are trying to create is already open.");
        }

    }

    /**
     * Helper method that alphabetizes a list
     * 
     * @param List<FoodItem> list - list to be alphabetized 
     * @return List<FoodItem> - the alphabetized list to be returned
     */
    private List<FoodItem> alphabetize(List<FoodItem> list) {
        Collections.sort(list, new Alphabetize());
        return list;
    }
}

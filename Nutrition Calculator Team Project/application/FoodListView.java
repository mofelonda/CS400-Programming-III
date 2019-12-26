package application;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * Class represents the JavaFX that displays list of FoodItemViews
 */
public class FoodListView {

	List<FoodItem> foodItemList;
	ListView<FoodItemView> lv;
	ObservableList<FoodItemView> items = FXCollections.observableArrayList();

	/*
	 * Constructor:
	 * Creates an empty list
	 * 
	 */
	public FoodListView() {
		foodItemList = new ArrayList<FoodItem>();
		lv = new ListView<FoodItemView>(items);
	}
	
	/*
	 * Constructor: 
	 * 
	 * @param data - FoodData to initialize list
	 */
	public FoodListView(FoodData data) {
		foodItemList = data.getAllFoodItems();
		//Loop through each foodItem in list
		for (FoodItem f : foodItemList) {
			FoodItemView item = new FoodItemView(f);
			items.add(item);//Add to observable list
		}
		lv = new ListView<FoodItemView>(items);
	}

	/*
	 * Method to add foodItems to lists
	 */
	public void addFoodItem(FoodItemView item) {
		foodItemList.add(item.getFoodItem());
		items.add(item);
		//Makes sure the items are added in alphabatical order
		items.sort((FoodItemView i1, FoodItemView i2) ->
				i1.getFoodName().compareTo(i2.getFoodName()));
	}
	
	
	/*
	 * Method to create list based on FoodData
	 * 
	 * @param data - FoodData to createList from
	 */
	public void createList(FoodData data) {
		items.clear();
		foodItemList = data.getAllFoodItems();
		//Loop through each foodItem in list
		for (FoodItem f : foodItemList) {
			FoodItemView item = new FoodItemView(f);
			items.add(item); //Add to observable list
		}
	}

	/*
	 * Method to create list based on List of FoodItems
	 * 
	 * @param list - List<FoodItem> 
	 */
	public void createList(List<FoodItem> list) {
		items.clear();
		for (FoodItem f : list) {
			FoodItemView item = new FoodItemView(f);
			items.add(item);
		}
	}
	
	/*
	 * Method to create GUI to display
	 * 
	 * @return lv - ListView to be displayed
	 */
	public ListView<FoodItemView> createUI() {
		lv.setPrefHeight(525);
		return lv;
	}
	
	/*
	 * Method to clear list 
	 */
	public void clear() {
		items.clear();
	}
	
	/*
	 * Accessor Method
	 * 
	 * @return lv - ListView of FoodItemView
	 */
	
	public ListView<FoodItemView> getFoodListView() {
		return lv;
	}

	/*
	 * Accessor Method
	 * 
	 * @return items - ObservableList
	 */
	public ObservableList<FoodItemView> getObvList() {
		return items;
	}
	
	/*
	 * Accessor Method
	 * 
	 * @return foodItemList - List of FoodItems
	 */
	
	public List<FoodItem> getFoodList() {
		return foodItemList;
	}
	
	/*
	 * Method to get total foods in list
	 */
	public int getTotal() {
		return items.size();
	}
	
	/*
	 * Method to get total Calories in list
	 */
	public double getCaloriesTotal() {
		double totalCalories = 0.0;
		//Iterate through each foodItem in list
		for (FoodItemView f : items) {
			//Get calories of each item and add to total
			totalCalories += f.getFoodItem().getNutrientValue("calories");
		}
			
		return totalCalories;
		
	}
	
	/*
	 * Method to get total Carbs in list
	 */
	public double getCarbohydrateTotal() {
		double totalCarbohydrate = 0.0;
		//Iterate through each foodItem in list
		for (FoodItemView f : items) {
			//Get calories of each item and add to total
			totalCarbohydrate += f.getFoodItem().getNutrientValue("carbohydrate");
		}
		
		return totalCarbohydrate;
		
	}
	
	/*
	 * Method to get total Fat in list
	 */
	public double getFatTotal() {
		double totalFat = 0.0;
		//Iterate through each foodItem in list
		for (FoodItemView f : items) {
			//Get calories of each item and add to total
			totalFat += f.getFoodItem().getNutrientValue("fat");
		}
		
		return totalFat;
		
	}
	
	/*
	 * Method to get total Fiber in list
	 */
	public double getFiberTotal() {
		double totalFiber = 0.0;
		//Iterate through each foodItem in list
		for (FoodItemView f : items) {
			//Get calories of each item and add to total
			totalFiber += f.getFoodItem().getNutrientValue("fiber");
		}
		
		return totalFiber;
		
	}
	
	/*
	 * Method to get Protein in list
	 */
	public double getProteinTotal() {
		double totalProtein = 0.0;
		//Iterate through each foodItem in list
		for (FoodItemView f : items) {
			//Get calories of each item and add to total
			totalProtein += f.getFoodItem().getNutrientValue("protein");
		}
		
		return totalProtein;
		
	}

	
}

package application;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
 * Class represents the main GUI contains all components 
 * and connects all components together
 */
public class PrimaryGUI {
	
	private FoodListView foodList;
	private FoodListView mealList;
	private Stage primaryStage;

	/*
	 * Constructor
	 * 
	 * @param data - FoodData used to filter
	 * @param primaryStage - Main Stage (window) used to put all GUI components
	 */
	public PrimaryGUI(FoodData data, Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("FoodQuery");
		foodList = new FoodListView();
		mealList = new FoodListView();
		
		//Initializing FoodList
		VBox leftSide = new VBox();
		leftSide.setPrefWidth(600);
		leftSide.setAlignment(Pos.TOP_CENTER);
		leftSide.setStyle("-fx-background-color: #444444");

		
		//Initializing MealList
		VBox rightSide = new VBox();
		rightSide.setPrefWidth(600);
		rightSide.setAlignment(Pos.TOP_CENTER);
		rightSide.setStyle("-fx-background-color: #444444");

		
		
		//FoodList components
		//Label
		Label foodLbl = new Label("AVAILABLE FOODS");
		foodLbl.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
		foodLbl.setTextFill(Color.WHITE);
		foodLbl.setUnderline(true);
		
		//Counter
		Label totalFood = new Label("Total Foods: " + foodList.getTotal());
		totalFood.setTextFill(Color.WHITE);
		
		//Buttons
		Button select = new Button("SELECT");
		select.setStyle("-fx-background-color: #5F9EA0");
		select.setTooltip(new Tooltip("Add the selected food to the meal list"));
		select.setOnMouseEntered((e) -> {
			select.setStyle("-fx-background-color: #78C7C9");
		});
		select.setOnMouseExited((e) -> {
			select.setStyle("-fx-background-color: #5F9EA0");
		});
		select.setPrefWidth(225);
		
		//FoodList buttons bar
		Button info = new Button("INFO");
		info.setPrefWidth(225);
		info.setStyle("-fx-background-color: #5F9EA0");
		info.setTooltip(new Tooltip("Display nutrition info of the selected food"));
		info.setOnMouseEntered((e) -> {
			info.setStyle("-fx-background-color: #78C7C9");
		});
		info.setOnMouseExited((e) -> {
			info.setStyle("-fx-background-color: #5F9EA0");
		});
		info.setOnAction((ActionEvent e) -> {	
			//Retrieves the selected FoodItem from list
			FoodItemView selected = foodList.getFoodListView().getSelectionModel().getSelectedItem();
			if (selected != null) {
				//Takes the selected and store food info into a temp FoodItem
				FoodItem temp = selected.getFoodItem();
				//Create a FoodIitemView to be put used to create a separate window
				FoodItemView addSelect = new FoodItemView(temp);
				//Create a window displaying foodItem info
				FoodItemWindow itemWindow = new FoodItemWindow(addSelect);
				itemWindow.createGUI();
			}
		});
		//Buttons for FoodList
		HBox foodButtons = new HBox(select, info);
		foodButtons.setStyle("-fx-background-color: #444444");
		foodButtons.setPadding(new Insets(20, 50, 20, 50));
		foodButtons.setSpacing(50);
		
		
		
		//MealList components
		//Label
		Label mealLbl = new Label("MEAL LIST");
		mealLbl.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
		mealLbl.setTextFill(Color.WHITE);
		mealLbl.setUnderline(true);
				
		//Counter
		Label totalMeal = new Label("Total Foods: " + mealList.getTotal());
		totalMeal.setTextFill(Color.WHITE);
			
		//Buttons
		Button remove = new Button("REMOVE");
		remove.setStyle("-fx-background-color: #5F9EA0");
		remove.setTooltip(new Tooltip("Remove the selected food from the meal list"));
		remove.setOnMouseEntered((e) -> {
			remove.setStyle("-fx-background-color: #78C7C9");
		});
		remove.setOnMouseExited((e) -> {
			remove.setStyle("-fx-background-color: #5F9EA0");
		});
		remove.setPrefWidth(133.3);
			
		//Button Actions for FoodList
		select.setOnAction((ActionEvent e) -> {
			//Retrieves the selected FoodItem from list
			FoodItemView selected = foodList.getFoodListView().getSelectionModel().getSelectedItem();
			FoodItem temp = selected.getFoodItem();
			FoodItemView addSelect = new FoodItemView(temp);
			if (selected != null) {
				//Adds the foodItem that has same info of selected and added to mealList
				mealList.addFoodItem(addSelect);
				//Updates the total Meal counter
				totalMeal.setText("Total Foods: " + mealList.getTotal());

			}
		});
		
		//Button Actions for MealList
		remove.setOnAction((ActionEvent e) -> {
			//Retrieves the selected FoodItem from list
			FoodItemView selected = mealList.getFoodListView().getSelectionModel().getSelectedItem();
			if (selected != null) {
				//Removes the list from the mealList
				mealList.getFoodListView().getSelectionModel().clearSelection();
				mealList.getObvList().remove(selected);
				//Updates the total Meal counter
				totalMeal.setText("Total Foods: " + mealList.getTotal());
				

			}
		});
		
		//Button Bar for mealList
		Button clear = new Button("CLEAR");
		clear.setPrefWidth(133.3);
		clear.setStyle("-fx-background-color: #5F9EA0");
		clear.setTooltip(new Tooltip("Remove all food from the meal list"));
		clear.setOnMouseEntered((e) -> {
			clear.setStyle("-fx-background-color: #78C7C9");
		});
		clear.setOnMouseExited((e) -> {
			clear.setStyle("-fx-background-color: #5F9EA0");
		});
		clear.setOnAction((ActionEvent e) ->{
			mealList.clear();
			totalMeal.setText("Total Foods: " + mealList.getTotal());
		});
		Button infoMeal = new Button("INFO");
		infoMeal.setPrefWidth(133.3);
		infoMeal.setStyle("-fx-background-color: #5F9EA0");
		infoMeal.setTooltip(new Tooltip("Display nutrition info of the selected food"));
		infoMeal.setOnMouseEntered((e) -> {
			infoMeal.setStyle("-fx-background-color: #78C7C9");
		});
		infoMeal.setOnMouseExited((e) -> {
			infoMeal.setStyle("-fx-background-color: #5F9EA0");
		});
		infoMeal.setOnAction((ActionEvent e) -> {	
			//Retrieves the selected FoodItem from list
			FoodItemView selected = mealList.getFoodListView().getSelectionModel().getSelectedItem();
			if (selected != null) {
				FoodItem temp = selected.getFoodItem();
				FoodItemView addSelect = new FoodItemView(temp);
				//Creates food info window for selected foodItem
				FoodItemWindow itemWindow = new FoodItemWindow(addSelect);
				itemWindow.createGUI();
			}
		});
		//Buttons for the mealList
		HBox mealButtons = new HBox(remove, infoMeal, clear);
		mealButtons.setPadding(new Insets(20, 50, 20, 50));
		mealButtons.setSpacing(50);
		mealButtons.setStyle("-fx-background-color: #444444");

		//Menu Bar
		HBox menuBar = new HBox();
		menuBar.setSpacing(20);
		menuBar.setPadding(new Insets(20,20,20,20));
		menuBar.setStyle("-fx-background-color: #444444");
		
		//Import Button
		Button loadFile = new Button("IMPORT");
		loadFile.setPrefSize(216, 20);
		loadFile.setStyle("-fx-background-color: #5F9EA0");
		loadFile.setTooltip(new Tooltip("Import foods via a .csv or .txt file"));
		loadFile.setOnMouseEntered((e) -> {
			//Creates a generic window
			loadFile.setStyle("-fx-background-color: #78C7C9");
		});
		loadFile.setOnMouseExited((e) -> {
			loadFile.setStyle("-fx-background-color: #5F9EA0");
		});
		loadFile.setOnAction((ActionEvent e) -> {
			MultiForm importFile = new MultiForm("Import");
			Label importLabel = new Label("Enter Filename: ");
			importLabel.setTextFill(Color.WHITE);
			importFile.addComponents(importLabel);
			TextField importText = new TextField();
			importFile.addComponents(importText);
			Button importButton = new Button("Import");
			importButton.setStyle("-fx-background-color: #5F9EA0");
			importButton.setOnMouseEntered((f) -> {
				importButton.setStyle("-fx-background-color: #78C7C9");
			});
			importButton.setOnMouseExited((f) -> {
				importButton.setStyle("-fx-background-color: #5F9EA0");
			});
			importButton.setOnAction((ActionEvent f) -> {
				//Load file into data
				data.loadFoodItems(importText.getText());
				foodList.createList(data);
				//Updates the total food counter
				totalFood.setText("Total Foods: " + foodList.getTotal());
				importFile.close();
			});
			importFile.addComponents(importButton);
			
			importFile.createGUI();
		
		});
		
		//Export Button
		Button saveFile = new Button("EXPORT");
		saveFile.setPrefSize(216, 20);
		saveFile.setStyle("-fx-background-color: #5F9EA0");
		saveFile.setTooltip(new Tooltip("Save food list as a .csv file"));
		saveFile.setOnMouseEntered((e) -> {
			saveFile.setStyle("-fx-background-color: #78C7C9");
		});
		saveFile.setOnMouseExited((e) -> {
			saveFile.setStyle("-fx-background-color: #5F9EA0");
		});
		saveFile.setOnAction((ActionEvent e) -> {
			//Creates generic window
			MultiForm exportFile = new MultiForm("Export");
			Label exportLabel = new Label("Enter Filename: ");
			exportLabel.setTextFill(Color.WHITE);
			exportFile.addComponents(exportLabel);
			TextField exportText = new TextField();
			exportFile.addComponents(exportText);
			Button exportButton = new Button("Export");
			exportButton.setStyle("-fx-background-color: #5F9EA0");
			exportButton.setOnMouseEntered((f) -> {
				exportButton.setStyle("-fx-background-color: #78C7C9");
			});
			exportButton.setOnMouseExited((f) -> {
				exportButton.setStyle("-fx-background-color: #5F9EA0");
			});
			exportButton.setOnAction((ActionEvent f) -> {
				//Exports foodlist into .csv file
				data.saveFoodItems(exportText.getText());
				exportFile.close();
			});
			exportFile.addComponents(exportButton);
			exportFile.createGUI();
		});
		//Add food button
		Button addFood = new Button("ADD FOOD");
		addFood.setPrefSize(216, 20);
		addFood.setStyle("-fx-background-color: #5F9EA0");
		addFood.setTooltip(new Tooltip("Add new food to the list of available foods"));
		addFood.setOnMouseEntered((e) -> {
			addFood.setStyle("-fx-background-color: #78C7C9");
		});
		addFood.setOnMouseExited((e) -> {
			addFood.setStyle("-fx-background-color: #5F9EA0");
		});
		addFood.setOnAction((ActionEvent e) -> {
			FoodItemAddForm n = new FoodItemAddForm();
			n.createAddBox(foodList, data, totalFood);
		});
		//Filter Button
		Button filter = new Button("FILTER");
		filter.setPrefSize(216, 20);
		filter.setStyle("-fx-background-color: #5F9EA0");
		filter.setTooltip(new Tooltip("Filter available foods by name or nutrient"));
		filter.setOnMouseEntered((e) -> {
			filter.setStyle("-fx-background-color: #78C7C9");
		});
		filter.setOnMouseExited((e) -> {
			filter.setStyle("-fx-background-color: #5F9EA0");
		});
		filter.setOnAction((ActionEvent e) -> {
			FilterRulesForm n = new FilterRulesForm();
			n.createFilterBox(foodList, data);
		});
		//Analyze button 
		Button analyze = new Button("ANALYZE");
		analyze.setStyle("-fx-background-color: #5F9EA0");
		analyze.setTooltip(new Tooltip("Display nutrient totals in a bar graph"));
		analyze.setOnMouseEntered((e) -> {
			analyze.setStyle("-fx-border-radius: 30;");
			analyze.setStyle("-fx-background-color: #78C7C9");
		});
		analyze.setOnMouseExited((e) -> {
			analyze.setStyle("-fx-border-radius: 30;");
			analyze.setStyle("-fx-background-color: #5F9EA0");
		});
		analyze.setPrefSize(216, 20);
		analyze.setOnAction((ActionEvent e) -> {
			MealListAnalysis barChart = new MealListAnalysis();
			barChart.createBarChart(mealList);
		});
		//Adding components to menu bar
		menuBar.getChildren().addAll(addFood, loadFile, saveFile, filter, analyze);
		

		
		
		//Adding to VBox
		leftSide.getChildren().addAll(foodLbl, totalFood, foodButtons, foodList.createUI());
		rightSide.getChildren().addAll(mealLbl, totalMeal, mealButtons, mealList.createUI());
		
	
		
		//Scene
		BorderPane bPane = new BorderPane();
		bPane.setStyle("-fx-background-color: #444444");
		Scene scene = new Scene(bPane, 1200, 600, Color.valueOf("#444444"));
		primaryStage.setScene(scene);
		
		
		//Adding components to border pane
		bPane.setLeft(leftSide);
		bPane.setCenter(rightSide);
		bPane.setTop(menuBar);
		
	}
	/*
	 * Method that displays the GUI
	 */
	public void createGUI() {
		primaryStage.show();
	}
}

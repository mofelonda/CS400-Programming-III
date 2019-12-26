package application;
import java.io.File;
import java.util.ArrayList;
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
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
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

/**
 * Class provides the JavaFX functionality for adding a food item
 */
public class FoodItemAddForm extends Stage{
	//GridPane that all JavaFx items are stored in
	VBox addFoodBox = new VBox();
	Button addFood = new Button();
	//foodItem that is added
	private FoodItem newFood;
	
	/**
	 * Constructor that sets the size of a gridPane
	 */
	public FoodItemAddForm() {
		addFoodBox.setMaxSize(500, 300);
		addFoodBox.setPadding(new Insets(30, 50, 30, 50));
		addFoodBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
			
	}
	
	/**
	 * Shows the stage that will appear when the Add Food button is pressed
	 * @param foodList - represents the list of food that is added to
	 * @param data - the data structure the food items are stored in
	 * @param totalFood - represents the number of foods in the list
	 */
	public void createAddBox(FoodListView foodList, FoodData data, Label totalFood) {
		
		// creates the scene size
		Scene addFoodScene = new Scene(addFoodBox, 355, 500);
		// sets stage title
		setTitle("Add Food");
		// adds scene to stage
		setScene(addFoodScene);
		
		
		
		//Name of Food
		Label nameLabel = new Label("Name of food:");
		addFoodBox.getChildren().add(nameLabel);
		nameLabel.setTextFill(Color.WHITE);
		//creates TextField to input name
		TextField name = new TextField();
		addFoodBox.getChildren().add(name);
		
		//ID of food
		Label IdLabel = new Label("ID: ");
		addFoodBox.getChildren().add(IdLabel);
		IdLabel.setTextFill(Color.WHITE);
		//creates TextField to input ID
		TextField ID = new TextField();
		addFoodBox.getChildren().add(ID);
		
		//Nutrition Facts
		Label nutLabel = new Label("Nutrients");
		nutLabel.setFont(Font.font(20));
		nutLabel.setUnderline(true);
		nutLabel.setTextFill(Color.WHITE);
		addFoodBox.getChildren().add(nutLabel);
		
		//Calories of food
		Label calLabel = new Label("Calories:");
		addFoodBox.getChildren().add(calLabel);
		calLabel.setTextFill(Color.WHITE);
		//creates TextField to input calories
		TextField calText = new TextField();
		addFoodBox.getChildren().add(calText);
		
		//fat of food
		Label fatLabel = new Label("Fat:");
		addFoodBox.getChildren().add(fatLabel);
		fatLabel.setTextFill(Color.WHITE);
		//creates TextField to input fat
		TextField fatText = new TextField();
		addFoodBox.getChildren().add(fatText);
		
		//carbs of food
		Label carbLabel = new Label("Carbs:");
		addFoodBox.getChildren().add(carbLabel);
		carbLabel.setTextFill(Color.WHITE);
		//creates TextField to input carbs
		TextField carbText = new TextField();
		addFoodBox.getChildren().add(carbText);
		
		//protein of food
		Label proLabel = new Label("Protein:");
		addFoodBox.getChildren().add(proLabel);
		proLabel.setTextFill(Color.WHITE);
		//creates TextField to input protein
		TextField proText = new TextField();
		addFoodBox.getChildren().add(proText);
		
		//fiber of food
		Label fibLabel = new Label("Fiber:");
		addFoodBox.getChildren().add(fibLabel);
		fibLabel.setTextFill(Color.WHITE);
		//creates TextField to input fiber
		TextField fibText = new TextField();
		addFoodBox.getChildren().add(fibText);
		
		//Button to submit the information inputted by the user
		Button submit = new Button("Submit");
		submit.setStyle("-fx-background-color: #5F9EA0");
		submit.setOnMouseEntered((e) -> {
			submit.setStyle("-fx-background-color: #78C7C9");
		});
		submit.setOnMouseExited((e) -> {
			submit.setStyle("-fx-background-color: #5F9EA0");
		});
		submit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					//new FoodItem created which will be returned and passed into the FoodList
					newFood = new FoodItem(ID.getText(), name.getText());
					//the following lines take the doubles from the strings that the user entered, and 
					//set them to their proper value in FoodItem
					newFood.addNutrient("calories", Double.parseDouble(calText.getText()));
					newFood.addNutrient("fat", Double.parseDouble(fatText.getText()));
					newFood.addNutrient("protein", Double.parseDouble(proText.getText()));
					newFood.addNutrient("carbohydrate", Double.parseDouble(carbText.getText()));
					newFood.addNutrient("fiber", Double.parseDouble(fibText.getText()));
				} catch(Exception z) {
						//In the case of an exception a warning screen pops up
					Stage warn = new Stage();
					//stage is given the title ("Warning!")
					warn.setTitle("Warning!");
					//VBox is created and given the warning text
					VBox warning = new VBox(20);
					warning.setPadding(new Insets(40, 30, 40, 30));
					Text warnText = new Text("Please don't add non-numerical nutrient values!");
					warnText.setFill(Color.WHITE);
					warning.getChildren().add(warnText);
					warning.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
					
					//creates new scene with the warning
					Scene popup = new Scene(warning, 350, 100);
					//sets the scene in the stage
					warn.setScene(popup);
					//displays the popup
					warn.show();
					//calls the add box again for user to input again
					createAddBox(foodList, data, totalFood);
				}
				FoodItemView newFoodItemView = new FoodItemView(newFood);
				data.addFoodItem(newFood);
				foodList.createList(data);
				totalFood.setText("Total Foods: " + foodList.getTotal());
				close();
				
			}
		});
		// adds the submit button
		addFoodBox.getChildren().add(submit);
		addFoodBox.setSpacing(5);
		
		//shows the entire form
		show();
	}
	
	
}

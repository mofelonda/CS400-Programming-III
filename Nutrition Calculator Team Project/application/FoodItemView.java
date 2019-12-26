package application;
import java.io.File;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
 * Class represents the JavaFX that displays FoodItem info
 */
public class FoodItemView extends VBox{
	
	private FoodItem item;
	private Label display; 
	private Label name;
	private Label id;
	private Text nutrition;
	private Label calories;
	private Label fat;
	private Label carbs;
	private Label fiber;
	private Label protein;
	
	/*
	 * Constructor:
	 * 
	 * @param item - FoodItem used to compile a JavaFX view
	 */
	
	public FoodItemView(FoodItem item) {
		this.item = item;
		display = new Label(item.getName());
		
		//Creating Components
		name = new Label("Name: " + item.getName());
		name.setTextFill(Color.WHITE);
		id = new Label("ID: " + item.getID());
		id.setTextFill(Color.WHITE);
		nutrition = new Text("Nutrition");
		nutrition.setFont(Font.font(20));
		nutrition.setUnderline(true);
		nutrition.setFill(Color.WHITE);
		calories = new Label("Calories: " + item.getNutrientValue("calories"));
		calories.setTextFill(Color.WHITE);
		fat = new Label("Fat: " + item.getNutrientValue("fat"));
		fat.setTextFill(Color.WHITE);
		carbs = new Label("Carbs: " + item.getNutrientValue("carbohydrate"));
		carbs.setTextFill(Color.WHITE);
		fiber = new Label("Fiber: " + item.getNutrientValue("fiber"));
		fiber.setTextFill(Color.WHITE);
		protein = new Label("Protein: " + item.getNutrientValue("protein"));
		protein.setTextFill(Color.WHITE);
		
		getChildren().addAll(display);
		setSpacing(10);
	}

	public void createUI() {
		getChildren().clear();
		getChildren().addAll(name, id, nutrition, calories, fat, carbs, fiber, protein);
	}
	
	/*
	 * Accessor method
	 * 
	 * @return item - FoodItem, JavaFX is based off
	 */
	public FoodItem getFoodItem() {
		return item;
	}
	
	/*
	 * Accessor method
	 * 
	 * @return String - get name of Food Item
	 */
	public String getFoodName() {
		return item.getName();
	}
	
	
	
	
	
}

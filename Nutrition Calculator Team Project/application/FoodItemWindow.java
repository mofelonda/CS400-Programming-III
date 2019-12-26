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
 * Class represents window of Food Item details
 * 
 */
public class FoodItemWindow extends Stage {
	private Scene foodScene;
	
	/*
	 * Constructor: 
	 * 
	 * @param foodItem - FoodItemView that will be viewed in a separate window
	 */
	public FoodItemWindow(FoodItemView foodItem) {
		foodItem.createUI();
		foodItem.setPadding(new Insets(20, 30, 20 ,30));
		foodItem.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
		foodScene = new Scene(foodItem, 400, 255);
		
		setTitle(foodItem.getFoodName());
		setScene(foodScene);
	}
	
	/*
	 * Creates window
	 */
	public void createGUI() {
		show();
	}
}

package application;
/**
 * Filename:   MealListAnalysis.java
 * Authors:    Mo Felonda
 * Email:      felonda@wisc.edu
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Version:    1.4
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.FontConverter.FontWeightConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

/*
 * Uses nutrient data of selected items to create a 
 * bar chart depicting total values of each nutrient
 * in the meal.
 * Also prints the total values of each nutrient for
 * easy reading.
 * 
 * 
 */
public class MealListAnalysis extends Stage {
	
	public void createBarChart(FoodListView mealList) {
			//Define the x-axis
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Nutrients");
			
			//Define the y-axis
			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("grams");
			
			//Define the bar chart
			BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
			barChart.setTitle("Nutrient Totals");
			
			//Fat bar in chart
			XYChart.Series<String, Number> totalFat = new XYChart.Series<>();
			totalFat.setName("Total Fat");
			totalFat.getData().add(new XYChart.Data<>("", mealList.getFatTotal())); 
			
			//Carbohydrate bar in chart
			XYChart.Series<String, Number> totalCarbohydrate = new XYChart.Series<>();
			totalCarbohydrate.setName("Total Carbohydrates");
			totalCarbohydrate.getData().add(new XYChart.Data<>("", mealList.getCarbohydrateTotal())); 
			
			//Fiber bar in chart
			XYChart.Series<String, Number> totalFiber = new XYChart.Series<>();
			totalFiber.setName("Total Fiber");
			totalFiber.getData().add(new XYChart.Data<>("", mealList.getFiberTotal())); 
			
			//Protein bar in chart
			XYChart.Series<String, Number> totalProtein = new XYChart.Series<>();
			totalProtein.setName("Total Protein");
			totalProtein.getData().add(new XYChart.Data<>("", mealList.getProteinTotal())); 
			
			//Adds bars to chart
			barChart.getData().addAll(totalFat, totalCarbohydrate, totalFiber, totalProtein);
			
			Group root = new Group(barChart);
			
			Scene scene = new Scene(root, 700, 425);
			
			//Displays total values of meal next to chart
			VBox nutrientVals = new VBox();
			nutrientVals.setSpacing(20);
			nutrientVals.setPadding(new Insets(45, 500, 45, 500));
			
			//Total calories in meal
			Label caloriesTotal = new Label("Total Calories: " + mealList.getCaloriesTotal());
			caloriesTotal.setUnderline(true);
			nutrientVals.getChildren().add(caloriesTotal);
			
			//Total fat in meal
			Label fatTotal = new Label("Total Fat: " + mealList.getFatTotal());
			nutrientVals.getChildren().add(fatTotal);
			
			//Total carbs in meal
			Label carbsTotal = new Label("Total Carbohydrates: " + mealList.getCarbohydrateTotal());
			nutrientVals.getChildren().add(carbsTotal);
			
			//Total fiber in meal
			Label fiberTotal = new Label("Total Fiber: " + mealList.getFiberTotal());
			nutrientVals.getChildren().add(fiberTotal);
			
			//Total protein in meal
			Label proteinTotal = new Label("Total Protein: " + mealList.getProteinTotal());
			nutrientVals.getChildren().add(proteinTotal);
			
			root.getChildren().add(nutrientVals);
			
			setTitle("Bar Chart");
			
			setScene(scene);
			
			if (mealList.getObvList().isEmpty()) {
				//In the case of an exception a warning screen pops up
				Stage warn = new Stage();
				//Stage is given the title("Warning!")
				warn.setTitle("Warning!");
				//VBox is created and given the warning text
				VBox warning = new VBox(20);
				warning.setPadding(new Insets(35, 45, 35, 45));
				Text warnText = new Text("No food has been added!");
				warnText.setFill(Color.WHITE);
				warning.getChildren().add(warnText);
				warning.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
				//Creates new scene with the warning
				Scene popup = new Scene(warning, 240, 90);
				//Sets the scene in the stage
				warn.setScene(popup);
				//Displays the pop-up
				warn.show();
			}
			else {
				
			show();
			}
	}
}

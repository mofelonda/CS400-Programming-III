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
 * Create window for filter rules to apply
 * 
 * Name Filter: (String)
 * 
 * Nutrient Filter: (rules: nutricent name, <= == >=, value)
 * 
 * Reset/Clear filter: (Shows all food items)
 */
public class FilterRulesForm extends Stage {
	
	VBox addFilterBox = new VBox();
	Button addFood = new Button();
	
	public FilterRulesForm() {
		addFilterBox.setMaxSize(600, 400);
		addFilterBox.setPadding(new Insets(20, 30, 20 ,30));
		addFilterBox.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void createFilterBox(FoodListView foodList, FoodData data) {
		
		Scene addFilterScene = new Scene(addFilterBox, 500, 600);
		setTitle("FILTER");
		setScene(addFilterScene);
		
		Label nameFilterLabel = new Label("Filter by name:");
		nameFilterLabel.setTextFill(Color.WHITE);
		addFilterBox.getChildren().add(nameFilterLabel);
		TextField nameFilter = new TextField();
		
		addFilterBox.getChildren().add(nameFilter);
		Label nutrientFilterLabel = new Label("Filter by nutrient:");
		nutrientFilterLabel.setTextFill(Color.WHITE);
		addFilterBox.getChildren().add(nutrientFilterLabel);
		
		class NutrientFilter extends HBox {
			ComboBox nutrientSelectionBox;
			ComboBox comparatorSelectionBox;
			ObservableList<String> nutrientOptions;
			ObservableList<String> comparatorOptions;
			TextField nutrientValue;
			
			public NutrientFilter() {
				
				nutrientOptions = 
						FXCollections.observableArrayList(
								"calories",
						        "fat",
						        "carbohydrates",
						        "fiber",
						        "protein"
						    );
				
				comparatorOptions = 
					    FXCollections.observableArrayList(
					    		">=",
					        "<=",
					        "=="
					    );
				
				comparatorSelectionBox = new ComboBox(comparatorOptions);
				comparatorSelectionBox.setPrefWidth(100);
 				comparatorSelectionBox.setStyle("-fx-background-color: #5F9EA0");
 				comparatorSelectionBox.setOnMouseEntered((e) -> {
 					comparatorSelectionBox.setStyle("-fx-background-color: #78C7C9");
 				});
 				comparatorSelectionBox.setOnMouseExited((e) -> {
 					comparatorSelectionBox.setStyle("-fx-background-color: #5F9EA0");
 				});
 				
				comparatorSelectionBox.setOnMouseExited((e) -> {
					comparatorSelectionBox.setStyle("-fx-background-color: #5F9EA0");
				});
				nutrientSelectionBox = new ComboBox(nutrientOptions);
				nutrientSelectionBox.setPrefWidth(160);
 				nutrientSelectionBox.setStyle("-fx-background-color: #5F9EA0");
 				nutrientSelectionBox.setOnMouseEntered((e) -> {
 					nutrientSelectionBox.setStyle("-fx-background-color: #78C7C9");
 				});
 				nutrientSelectionBox.setOnMouseExited((e) -> {
 					nutrientSelectionBox.setStyle("-fx-background-color: #5F9EA0");
 				});
				nutrientValue = new TextField();
				
				this.nutrientSelectionBox.getSelectionModel().selectFirst();
				this.comparatorSelectionBox.getSelectionModel().selectFirst();
				this.getChildren().addAll(nutrientSelectionBox, comparatorSelectionBox, nutrientValue);
				this.setSpacing(10);
			}
		}
		
		VBox NutrientFilterList = new VBox();
		NutrientFilterList.getChildren().add(new NutrientFilter());
		NutrientFilterList.setSpacing(10);
		
		//Filter Buttons - Add and Remove
		HBox FilterButtons = new HBox();
		//Add filter button
		Text addButton = new Text("+");
		addButton.setFont(Font.font(null, FontWeight.BLACK, 13));
		
		Button addFilter = new Button("+");
		addFilter.setTooltip(new Tooltip("Add filter rule"));
		addFilter.setPrefWidth(45);
		addFilter.setStyle("-fx-background-color: #5F9EA0");
		addFilter.setOnMouseEntered((e) -> {
			addFilter.setStyle("-fx-background-color: #78C7C9");
		});
		addFilter.setOnMouseExited((e) -> {
			addFilter.setStyle("-fx-background-color: #5F9EA0");
		});
		addFilter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				NutrientFilterList.getChildren().add(new NutrientFilter());
			}
		});
		
		//Remove filter button
		Text removeButton = new Text("-");
		removeButton.setFont(Font.font(null, FontWeight.BLACK, 13));
		
		Button removeFilter = new Button("-");
		removeFilter.setTooltip(new Tooltip("Remove filter rule"));
		removeFilter.setPrefWidth(45);
		removeFilter.setStyle("-fx-background-color: #5F9EA0");
		removeFilter.setOnMouseEntered((e) -> {
			removeFilter.setStyle("-fx-background-color: #78C7C9");
		});
		removeFilter.setOnMouseExited((e) -> {
			removeFilter.setStyle("-fx-background-color: #5F9EA0");
		});
		removeFilter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				NutrientFilterList.getChildren().remove(NutrientFilterList.getChildren().size() - 1);
			}
		});
		FilterButtons.getChildren().addAll(addFilter, removeFilter);
		FilterButtons.setSpacing(10);
		
		addFilterBox.getChildren().addAll(NutrientFilterList, FilterButtons);
		addFilterBox.setSpacing(10);
		
		HBox filterCommands = new HBox();
		Button apply = new Button("Apply");
		apply.setStyle("-fx-background-color: #5F9EA0");
		apply.setOnMouseEntered((e) -> {
			apply.setStyle("-fx-background-color: #78C7C9");
		});
		apply.setOnMouseExited((e) -> {
			apply.setStyle("-fx-background-color: #5F9EA0");
		});
		apply.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				List<FoodItem> nameFilteredList;
				List<FoodItem> nutrientFilteredList;
				List<FoodItem> filteredList = new ArrayList<FoodItem>();
				List<String> rules = new ArrayList<String>();
				String rule;
				
				//Filter by nutrient rules
				for (Node child : NutrientFilterList.getChildren()) {
					NutrientFilter filter = (NutrientFilter)child;
					if (!filter.nutrientValue.getText().isEmpty()) {
						rule = filter.nutrientSelectionBox.getValue().toString() + " "
								+ filter.comparatorSelectionBox.getValue().toString() + " "
									+ filter.nutrientValue.getText();
					} else {
					rule = filter.nutrientSelectionBox.getValue().toString() + " "
						+ filter.comparatorSelectionBox.getValue().toString() + " "
							+ "0";
					}
					rules.add(rule);
				}
				nutrientFilteredList = data.filterByNutrients(rules);

				
				//Filter by name
 				String nameQuery = nameFilter.getText();
 				nameFilteredList = data.filterByName(nameQuery);
 				if (nameQuery.equals("")) {
 				    nameFilteredList = new ArrayList<>();
 				}
 				if (!(nameQuery.equals(""))) {
 				    nameFilteredList = data.filterByName(nameQuery);
 				}
 				
				//Mesh the two filter types together
				if ((nameFilteredList.size() == 0) && (nutrientFilteredList.size() != 0)) {
				    foodList.createList(nutrientFilteredList);
				    close();
				    return;
				}
				if ((nutrientFilteredList.size() == 0) && (nameFilteredList.size() != 0)) {
                    foodList.createList(nameFilteredList);
                    close();
                    return;
                }	
				if (nameFilteredList.size() >= nutrientFilteredList.size()) {
					for (FoodItem item : nameFilteredList) {
						if (nutrientFilteredList.contains(item)) {
							filteredList.add(item);
						}
					} 
				}
				else {
					for (FoodItem item : nutrientFilteredList) {
						if (nameFilteredList.contains(item)) {
							filteredList.add(item);
						}
					}
				}
				
				foodList.createList(filteredList);

				close();
			}
		});
		
		Button reset = new Button("Reset");
		reset.setStyle("-fx-background-color: #5F9EA0");
		reset.setTooltip(new Tooltip("Remove all filters from food list"));
		reset.setOnMouseEntered((e) -> {
			reset.setStyle("-fx-background-color: #78C7C9");
		});
		reset.setOnMouseExited((e) -> {
			reset.setStyle("-fx-background-color: #5F9EA0");
		});
		reset.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				foodList.createList(data.getAllFoodItems());
				close();
			}
		});
		
		filterCommands.getChildren().addAll(apply, reset);
		filterCommands.setSpacing(10);
		filterCommands.setAlignment(Pos.CENTER);
		filterCommands.setPadding(new Insets(5, 0, 0, 0));
		addFilterBox.getChildren().add(filterCommands);
		show();	
	}
}

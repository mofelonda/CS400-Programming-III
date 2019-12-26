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
 * Class represents a generic window form for import and export
 */
public class MultiForm extends Stage {
	
	VBox vb;
	Scene multiScene;
	
	/*
	 * Constructor
	 * 
	 * @param name - window title
	 */
	public MultiForm(String name) {
		vb = new VBox();
		vb.setPadding(new Insets(20, 30, 20, 30));
		vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#444444"), CornerRadii.EMPTY, Insets.EMPTY)));
		vb.setSpacing(10);
		
		multiScene = new Scene(vb, 500, 140);

		setTitle(name);
		setScene(multiScene);
	}
	
	/*
	 * Method allows components to be added to form
	 */
	public void addComponents(Node node) {
		vb.getChildren().add(node);
	}

	/*
	 * Method allows window to be be displayed
	 */
	public void createGUI() {
		show();
	}
	
}
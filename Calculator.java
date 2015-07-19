/* Author: Luigi Vincent
*
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane layout = new BorderPane();

		TextField result = new TextField();
		result.setStyle("-fx-font-size: 40");
		result.setMaxWidth(400);
		result.setEditable(false);
		layout.setTop(result);
		
		GridPane buttonLayout = new GridPane();
		layout.setCenter(buttonLayout);

		Button backButton = new Button("\u2190");
		backButton.setMinSize(100, 100);
		buttonLayout.add(backButton, 2, 0);

		Button clearButton = new Button("Clear");
		clearButton.setMinSize(200, 100);
		GridPane.setColumnSpan(clearButton, 2);	
		buttonLayout.add(clearButton, 0, 0);

		Button[] numberButtons = new Button[10];
		for (int i = 3, target = 1; i >= 1; i--) {
			for (int j = 0; j <= 2; j++) {
				numberButtons[target] = new Button(Integer.toString(target));
				numberButtons[target].setMinSize(100, 100);
				buttonLayout.add(numberButtons[target++], j, i);
			}	
		}


		String[] operators = {"\u00F7", "x", "-", "+", "="};
		Button[] operatorButtons = new Button[operators.length];

		for (int i = 0; i < operators.length; i++) {
			operatorButtons[i] = new Button(operators[i]);
			operatorButtons[i].setMinSize(100, 100);
			operatorButtons[i].setStyle("-fx-color: orange");
			buttonLayout.add(operatorButtons[i], 3, i);
		}

		stage.setScene(new Scene(layout));
		stage.setTitle("Legato's Calculator");
		stage.show();
	}
} 
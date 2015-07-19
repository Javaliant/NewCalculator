/* Author: Luigi Vincent
* TODO: 
*  Add consideration for whether the decimal is already placed
*  After the equals sign is clicked, do not append text, but start anew
*  error checking for division by 0
*  not too long decimals
*/

import javafx.application.Application;
import javafx.geometry.Insets;
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
		result.setMaxWidth(415); // total width + margins of buttons
		result.setEditable(false);
		layout.setTop(result);
		
		GridPane buttonLayout = new GridPane();
		buttonLayout.setPadding(new Insets(10, 0, 0, 0));
        buttonLayout.setHgap(5);
        buttonLayout.setVgap(5);
		layout.setCenter(buttonLayout);

		Button backButton = new Button("\u2190");
		backButton.setMinSize(100, 100);
		backButton.setOnAction(e -> {
			String currentText = result.getText();
			if (!currentText.isEmpty()){
				result.setText(currentText.substring(0, currentText.length() - 1));
			}
		});
		buttonLayout.add(backButton, 2, 0);

		Button clearButton = new Button("Clear");
		clearButton.setMinSize(200, 100);
		clearButton.setOnAction(e -> {
			result.clear();
		});
		GridPane.setColumnSpan(clearButton, 2);	
		buttonLayout.add(clearButton, 0, 0);

		Button[] numberButtons = new Button[10];
		for (int i = 3, target = 1; i >= 1; i--) {
			for (int j = 0; j <= 2; j++) {
				String str = Integer.toString(target);
				numberButtons[target] = new Button(str);
				numberButtons[target].setMinSize(100, 100);
				numberButtons[target].setOnAction(e -> {
					result.appendText(str);
				});
				buttonLayout.add(numberButtons[target++], j, i);
			}	
		}

		numberButtons[0] = new Button("0");
		numberButtons[0].setMinSize(200, 100);
		numberButtons[0].setOnAction(e -> {
			result.appendText("0");
		});
		GridPane.setColumnSpan(numberButtons[0], 2);
		buttonLayout.add(numberButtons[0], 0, 4);


		Button decimalButton = new Button(".");
		decimalButton.setMinSize(100, 100);
		decimalButton.setOnAction(e -> {
			if (result.getText().indexOf('.') == -1) {
				result.appendText(".");
			}
		});
		buttonLayout.add(decimalButton, 2, 4);


		String[] operators = {"\u00F7", "x", "-", "+", "="};
		Button[] operatorButtons = new Button[operators.length];

		for (int i = 0; i < operators.length; i++) {
			String op = operators[i];
			operatorButtons[i] = new Button(op);
			operatorButtons[i].setMinSize(100, 100);
			operatorButtons[i].setOnAction(e -> {
				result.appendText(op);
			});
			operatorButtons[i].setStyle("-fx-color: orange");
			buttonLayout.add(operatorButtons[i], 3, i);
		}

		stage.setScene(new Scene(layout));
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Legato's Calculator");
		stage.show();
	}
} 
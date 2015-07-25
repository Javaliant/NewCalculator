/* Author: Luigi Vincent
* TODO: 
*  After the equals sign is clicked, do not append text, but start anew
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
	static double val1;
	static double val2;
	static Operator currentOperator;
	static boolean operatorSelected;
	static StringBuilder valueBuilder = new StringBuilder();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		//boolean operatorSelected = false;

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
			val1 = 0;
			val2 = 0;
			operatorSelected = false;
			valueBuilder.setLength(0);
		});
		GridPane.setColumnSpan(clearButton, 2);	
		buttonLayout.add(clearButton, 0, 0);

		Button[] numberButtons = new Button[10];
		for (int i = 3, target = 1; i >= 1; i--) {
			for (int j = 0; j <= 2; j++) {
				String number = Integer.toString(target);

				numberButtons[target] = new Button(number);
				numberButtons[target].setMinSize(100, 100);
				numberButtons[target].setOnAction(e -> {
					result.appendText(number);
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

		for (Operator op : Operator.values()) {
			String symbol = op.toString();

			Button button = new Button(symbol);
			button.setMinSize(100, 100);
			button.setStyle("-fx-color: orange");
			button.setOnAction(e -> {
				val1 = Double.parseDouble(result.getText());
				result.clear();
				currentOperator = op;
				operatorSelected = true;
			});
			buttonLayout.addColumn(3, button);
		}

		Button equalsButton = new Button("=");
		equalsButton.setStyle("-fx-color: orange");
		equalsButton.setMinSize(100, 100);
		equalsButton.setOnAction(e -> {
			val2 = Double.parseDouble(result.getText());
			result.setText(compute());
		});
		buttonLayout.addColumn(3, equalsButton);

		stage.setScene(new Scene(layout));
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Legato's Calculator");
		stage.show();
	}

	private static String compute() {
		if (val2 == 0 && currentOperator == Operator.DIVIDE) {
			return "Cannot divide by 0";
		}

		double result = currentOperator.compute(val1, val2);

		return result == (int)result ? 
			Integer.toString((int)result) : String.format("%.2f", result);	
	}
} 

package view;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Patterns extends Designs {
	// Components
	private StackPane spTxt;

	private Button btnExit;

	/**
	 * Init Stack pane for text
	 * 
	 * @param text - String
	 */
	public StackPane initTxt(String text) {
		spTxt = new StackPane();
		Label centerTitle = new Label(text);
		centerTitle.setStyle(
				"-fx-background-color: ALICEBLUE; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-size: 18; ");
		spTxt.getChildren().add(centerTitle);
		spTxt.setAlignment(Pos.TOP_CENTER);
		return spTxt;
	}

	/**
	 * Init Exit button for bye bye
	 */
	public Button initExitBt() {
		btnExit = newButton("exit");
		btnExit.setOnAction(e -> {
			javafx.application.Platform.exit();
		});
		return btnExit;

	}

	/**
	 * Making sure where text field is suuposed to be only numbers, nothing else
	 * will be avaliable to type (including negetive numbers)
	 */
	public TextField onlyNumaric() {
		DecimalFormat format = new DecimalFormat("#.0");
		TextField tx = new TextField();
		tx.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty())
				return c;
			ParsePosition parsepos = new ParsePosition(0);
			Object obj = format.parse(c.getControlNewText(), parsepos);
			if (obj == null || parsepos.getIndex() < c.getControlNewText().length())
				return null;
			else
				return c;
		}));
		return tx;
	}

	/**
	 * Making sure in any text field, the amount of characters will be valid and
	 * make sense (espically for phone number and prices)
	 * 
	 * @param limitLength - integer
	 */

	public EventHandler<KeyEvent> lengthRequested(final Integer limitLength) {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				TextField tx = (TextField) arg0.getSource();
				if (tx.getText().length() >= limitLength) {
					arg0.consume();
				}

			}

		};

	}

	/**
	 * Making sure where text field is suuposed to be only letter, nothing else will
	 * be avaliable to type
	 */
	public TextField onlyAlphabetic() {
		TextField tx = new TextField();
		tx.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				tx.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});
		return tx;
	}

	/**
	 * Reset the components in charge by patterns from screen
	 */
	public void cleanScreen() {
		spTxt.getChildren().clear();
	}
}

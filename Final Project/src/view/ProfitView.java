/**
 * Showing store profit; by product OR for the whole store.
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ProfitView implements ViewType {
	// Components

	private BorderPane bp;

	private StackPane sp;

	private VBox vtTxt;

	private HBox hbDownBtn, hbBarcode;

	private TextField txBarcode;

	private Label lbBarcode;

	private Patterns patterns = new Patterns();

	private Button btnFind, btnReturn,btnShowAll;

	private Scene sneFind;

	private Text textFind = new Text();
	// Constructor

	public ProfitView() {
		bp = new BorderPane();
		initProfitView();
	}

	// Init for each view page
	private void initProfitView() {
		bp.setTop(patterns.initTxt("Show profit by product"));

		initButton();

		initTxtField();
	}

	// Init Buttons
	private void initButton() {
		btnFind = patterns.newButton("Show profit for product(if exist)");
		btnShowAll = patterns.newButton("Show store profits");
		btnReturn = patterns.newButton("Back to main menu");
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().addAll(patterns.initExitBt(), btnFind, btnReturn);
		bp.setBottom(hbDownBtn);
	}

	// Init Text Fields
	private void initTxtField() {
		hbBarcode = patterns.HBox();

		lbBarcode = new Label("Barcode:");
		txBarcode = new TextField();
		txBarcode.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(20));

		hbBarcode.getChildren().addAll(lbBarcode, txBarcode);
		hbBarcode.setSpacing(10);

		vtTxt = new VBox();
		vtTxt.getChildren().addAll(hbBarcode, textFind,btnShowAll);
		vtTxt.setAlignment(Pos.CENTER);
		vtTxt.setSpacing(10);

		sp = new StackPane();
		sp.getChildren().add(vtTxt);

		bp.setCenter(sp);
	}

	// Showing text on same screen view
	public void showText(String str) {
		textFind.setText(str);
		textFind.setFill(Color.BLACK);
		textFind.setFont(Font.font("Arial", FontWeight.BOLD, 18));
	}

	@Override
	public Scene SceneInit() {
		sneFind = new Scene(bp, 750, 500);
		return sneFind;
	}

	@Override
	public void cleanScreen() {
		bp.getChildren().clear();
		patterns.cleanScreen();
	}

	// Variables validation
	public boolean isValid() {
		if (txBarcode.getText().isEmpty())
			return true;
		return false;
	}

	// Getters
	public Button getBtnFind() {
		return btnFind;
	}

	public Button getBtnReturn() {
		return btnReturn;
	}

	public TextField getTxBarcode() {
		return txBarcode;
	}

	public Button getBtnShowAll() {
		return btnShowAll;
	}

	// Setters
	public void setTextFind(String text) {
		this.textFind.setText(text);
	}

}

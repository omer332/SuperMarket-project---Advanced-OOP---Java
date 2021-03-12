/**
 * Main menu view
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView implements ViewType {
	// Components
	private BorderPane bp;

	private VBox vtButtoms;

	private HBox hbDownBtn;

	private Patterns patterns = new Patterns();

	private Button btnShowMap, btnAddProduct, btnDeleteProduct, btnProductByBarcode, btnUndo, btnDeleteAll,
			btnSendMessage,btnShowProfit;

	private Scene sneMain;
	// Constructor

	public MainView() {
		bp = new BorderPane();
		initMainView();
	}

	// Init for each view page
	private void initMainView() {
		bp.setTop(patterns.initTxt("Please Choose from the following"));

		initButtons();
	}

	// Init Buttons
	private void initButtons() {
		vtButtoms = new VBox();
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().add(patterns.initExitBt());

		bp.setBottom(hbDownBtn);
		btnShowMap = patterns.newButton("Show all products");
		btnAddProduct = patterns.newButton("Add a product to store");
		btnProductByBarcode = patterns.newButton("Find a product in store by barcode");
		btnDeleteProduct = patterns.newButton("Delete a product from store by barcode");
		btnDeleteAll = patterns.newButton("Delete all products");
		btnShowProfit = patterns.newButton("Show store Profit");
		btnSendMessage = patterns.newButton("Send message to all subscribed customers");
		btnUndo = patterns.newButton("Undo");

		vtButtoms.setSpacing(10);
		vtButtoms.setAlignment(Pos.CENTER);
		vtButtoms.getChildren().addAll(btnShowMap, btnAddProduct, btnProductByBarcode, btnDeleteProduct, btnUndo,
				btnDeleteAll, btnSendMessage,btnShowProfit);

		bp.setCenter(vtButtoms);

	}

	@Override
	public Scene SceneInit() {
		sneMain = new Scene(bp, 750, 750);
		return sneMain;
	}

	public void cleanScreen() {

		bp.getChildren().clear();
		patterns.cleanScreen();
	}

	// Getters
	public Button getBtnShowMap() {
		return btnShowMap;
	}

	public Button getBtnAddProduct() {
		return btnAddProduct;
	}

	public Button getBtnDeleteProduct() {
		return btnDeleteProduct;
	}

	public Button getBtnProductByBarcode() {
		return btnProductByBarcode;
	}

	public Button getBtnUndo() {
		return btnUndo;
	}

	public Button getBtnDeleteAll() {
		return btnDeleteAll;
	}

	public Button getBtnSendMessage() {
		return btnSendMessage;
	}

	public Button getBtnShowProfit() {
		return btnShowProfit;
	}

}

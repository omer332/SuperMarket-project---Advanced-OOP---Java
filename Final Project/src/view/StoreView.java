/**
 * Store view, which holds all views as components, and manging the view
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listener.ListenerView;

public class StoreView {
	// Components
	private ArrayList<ListenerView> viewListener = new ArrayList<>();
	private FirstView firstChoosenView;
	private MainView mainChoosenView;
	private AddproductView addProductChoosenView;
	private ShowproductView showProductChoosenView;
	private FindproductView findProductChoosenView;
	private DeleteProductView deleteProductChoosenView;
	private SendAndApproveView sendChoosenView;
	private ProfitView profitChoosenView;
	private Stage storeStage;
	private Scene currentScene;
	private JCheckBox yes, no;
	private boolean undo = false, noProducts = false;

	// Constructor
	public StoreView(Stage primaryStage) throws Exception {
		storeStage = new Stage();
		storeStage.setTitle("Omer Sananes & Vladi Karasov");
		firstView();
	}

	// Creating listener
	public void registerListener(ListenerView listener) {
		viewListener.add(listener);
	}

	// Screens
	/**
	 * First view activating
	 */
	private void firstView() {
		firstChoosenView = new FirstView();
		currentScene = firstChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();

		firstChoosenView.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				firstChoosenView.cleanScreen();
				firstChoosenView.sortChs();
				for (ListenerView v : viewListener)
					try {
						v.lisSortChoice(firstChoosenView.getChoice());
					} catch (IOException e) {
						e.printStackTrace();
					}
				mainView();

			}
		});

	}

	/**
	 * Main view activating
	 */
	private void mainView() {
		mainChoosenView = new MainView();
		currentScene = mainChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		mainChoosenView.getBtnAddProduct().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			addProductView();
		});
		mainChoosenView.getBtnShowMap().setOnAction(e -> {
			if (isProductsInStore()) {
				mainChoosenView.cleanScreen();
				showProductView();
			}
		});
		mainChoosenView.getBtnProductByBarcode().setOnAction(e -> {
			if (isProductsInStore()) {
				mainChoosenView.cleanScreen();
				findProductView();
			}
		});
		mainChoosenView.getBtnDeleteProduct().setOnAction(e -> {
			if (isProductsInStore()) {
				mainChoosenView.cleanScreen();
				deleteProductView();
			}
		});
		mainChoosenView.getBtnSendMessage().setOnAction(e -> {
			if (isProductsInStore()) {
				mainChoosenView.cleanScreen();
				sendview();
			}
		});
		mainChoosenView.getBtnDeleteAll().setOnAction(e -> {
			if (isProductsInStore()) {
				for (ListenerView v : viewListener)
					try {
						v.deleteAllFromView();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
			}
		});
		mainChoosenView.getBtnShowProfit().setOnAction(e -> {
			if (isProductsInStore()) {
				mainChoosenView.cleanScreen();
				profitView();
			}
		});

		mainChoosenView.getBtnUndo().setOnAction(e -> {
			if (undo) {
				undo = false;
				for (ListenerView v : viewListener)
					try {
						v.deleteLastFromView();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				popMessage("last deleted");
			} else
				popMessage("Unable");
		});
	}

	/**
	 * Add product view activating
	 */
	private void addProductView() {
		addProductChoosenView = new AddproductView();
		currentScene = addProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		addProductChoosenView.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (addProductChoosenView.isValid()) {
					addProductChoosenView.cleanScreen();
					mainView();
				} else {
					for (ListenerView v : viewListener) {
						boolean sub = false;
						int cusPrice = Integer.parseInt(addProductChoosenView.getTxPCPrice().getText());
						int stoPrice = Integer.parseInt(addProductChoosenView.getTxPSPrice().getText());
						if (addProductChoosenView.getGroup().getSelectedToggle()
								.equals(addProductChoosenView.getrButtonSub()))
							sub = true;
						try {
							v.addProductFromView(addProductChoosenView.getTxBarcode().getText(),
									addProductChoosenView.getTxPName().getText(), stoPrice, cusPrice, sub,
									addProductChoosenView.getTxCusName().getText(),
									addProductChoosenView.getTxCusPhone().getText());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					addProductChoosenView.cleanScreen();

					mainView();
				}
			}
		});
	}

	/**
	 * Show all products view activating
	 */
	private void showProductView() {
		showProductChoosenView = new ShowproductView();
		currentScene = showProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		for (ListenerView v : viewListener)
			v.showAllProductsOnView();
		storeStage.show();
		showProductChoosenView.getBtnReturn().setOnAction(e -> {
			showProductChoosenView.cleanScreen();
			mainView();
		});
	}

	/**
	 * Find a product by barocde view activating
	 */
	private void findProductView() {
		findProductChoosenView = new FindproductView();
		currentScene = findProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		findProductChoosenView.getBtnFind().setOnAction(e -> {
			findProductChoosenView.setTextFind("");
			if (findProductChoosenView.isValid()) {
				popMessage("Please enter barcode!!");
				findProductChoosenView.cleanScreen();
				mainView();
			} else {
				for (ListenerView v : viewListener)
					v.ProductFoundByBarcodeFromView(findProductChoosenView.getTxBarcode().getText());
			}
		});
		findProductChoosenView.getBtnReturn().setOnAction(e -> {
			findProductChoosenView.cleanScreen();
			mainView();
		});

	}

	/**
	 * Delete a product by barocde view activating
	 */
	private void deleteProductView() {
		deleteProductChoosenView = new DeleteProductView();
		currentScene = deleteProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		deleteProductChoosenView.getBtnDel().setOnAction(e -> {
			if (deleteProductChoosenView.isValid()) {
				popMessage("Please enter a barcode!!");
				deleteProductChoosenView.cleanScreen();
				mainView();
			} else {
				String barcode = deleteProductChoosenView.getTxBarcode().getText();
				for (ListenerView v : viewListener) {
					try {
						v.deleteByBarcodeFromView(barcode);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				deleteProductChoosenView.cleanScreen();
				mainView();
			}
		});
	}

	/**
	 * Sending message and customer approval view activating
	 */
	private void sendview() {
		sendChoosenView = new SendAndApproveView();
		currentScene = sendChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		sendChoosenView.getBtnSend().setOnAction(e -> {
			if (sendChoosenView.isValid())
				popMessage("Cannot send empty message!!");
			else {
				for (ListenerView v : viewListener)
					try {
						String message = sendChoosenView.getTxMessage().getText();
						v.SendMessageFromView(message);
						if (showingApproveText()) {
							v.approveMessageFromView();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
			sendChoosenView.cleanScreen();
			mainView();

		});
	}

	/**
	 * Showing profit per product or for all - view activating
	 */
	private void profitView() {
		profitChoosenView = new ProfitView();
		currentScene = profitChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		profitChoosenView.getBtnFind().setOnAction(e -> {
			profitChoosenView.setTextFind("");
			if (profitChoosenView.isValid()) {
				popMessage("Please enter barcode!!");
				profitChoosenView.cleanScreen();
				mainView();
			} else {
				for (ListenerView v : viewListener)
					try {
						v.showProfitFromView(profitChoosenView.getTxBarcode().getText());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
			}
		});
		profitChoosenView.getBtnShowAll().setOnAction(e -> {
			for (ListenerView v : viewListener)
				try {
					v.showAllProfit();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		});
		profitChoosenView.getBtnReturn().setOnAction(e -> {
			profitChoosenView.cleanScreen();
			mainView();
		});
	}

	/**
	 * pop messages
	 */
	// Pop up message asking if to show customer approval or not.
	public boolean showingApproveText() {
		yes = new JCheckBox("Yes");
		no = new JCheckBox("no");
		String choose = "choose";
		Object[] options = { choose, yes, no };
		JOptionPane.showOptionDialog(null, "Would you like to see who approved the message?", null,
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (yes.isSelected() && no.isSelected()) {
			popMessage("Cannot choose both options!!");
			return false;
		} else if (yes.isSelected())
			return true;
		else if (no.isSelected())
			return false;
		else {
			popMessage("Please choose an option!!");
			return false;
		}
	}

	/**
	 * Adding product Succesfully pop message.
	 */
	public void succesAddingMessage() {
		popMessage("Product added succesfully");
	}

	/**
	 * Checking if any products in store.
	 * 
	 * @return true if there exists. False otherwise, and pop message
	 */
	public boolean isProductsInStore() {
		if (noProducts) {
			popMessage("No products or customers in store!");
			return false;
		}
		return true;
	}

	/**
	 * Regular pop message
	 * 
	 * @param text - String
	 */
	public void popMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}

	/**
	 * customer approval pop message by request.
	 * 
	 * @param name - String
	 */
	public void approveMessage(String name) {
		JOptionPane am = new JOptionPane("customer " + name + " Approved message", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
		JDialog dAm = am.createDialog("");
		dAm.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				dAm.setVisible(false);
			}
		}).start();
		dAm.setVisible(true);
	}

	// Getters
	public ShowproductView getShowProductChoosenView() {
		return showProductChoosenView;
	}

	public FindproductView getFindProductChoosenView() {
		return findProductChoosenView;
	}

	public SendAndApproveView getSendChoosenView() {
		return sendChoosenView;
	}

	public ProfitView getProfitChoosenView() {
		return profitChoosenView;
	}

	// Setters
	public void setUndo(boolean undo) {
		this.undo = undo;
	}

	public void setNoProducts(boolean noProducts) {
		this.noProducts = noProducts;
	}

}

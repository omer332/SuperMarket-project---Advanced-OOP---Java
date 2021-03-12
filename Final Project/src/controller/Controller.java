/**
 * Controller for view (StoreView) and model (Store)
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Customer;
import classes.Product;
import classes.Store;
import command.AddProductCommand;
import command.AllProfitCommand;
import command.DeleteAllCommand;
import command.DeleteByBarcodeCommand;
import command.FindCommand;
import command.RecieveCommand;
import command.SendMessageCommand;
import command.ShowAllProductsCommand;
import command.ProductProfitCommand;
import command.UndoCommand;
import listener.ListenerModel;
import listener.ListenerView;
import view.StoreView;

public class Controller implements ListenerModel, ListenerView {
	// Components
	private Store model;
	private ShowAllProductsCommand showCommand;
	private AddProductCommand addCommand;
	private FindCommand findCommand;
	private UndoCommand undoCommand;
	private DeleteAllCommand deleteAllCommand;
	private DeleteByBarcodeCommand deleteByBarcodeCommand;
	private SendMessageCommand sendMessageCommand;
	private RecieveCommand recieveCommand;
	private ProductProfitCommand showProfitCommand;
	private AllProfitCommand allProfitCommand;
	private StoreView storeView;

	// Constructor
	public Controller(Store model, StoreView storeView) {
		this.model = model;
		this.storeView = storeView;
		model.registerListener(this);
		storeView.registerListener(this);
	}

	/**
	 * @Cancelling_yellow_errors_JUST_IGNORE
	 *
	 */
	public void cancelYellowError() {
	}

	@Override
	public void lisSortChoice(int temp) throws FileNotFoundException, IOException {
		model.setSortChoice(temp);
		model.sortMap();
	}

	@Override
	public void showAllProductsOnView() {
		showCommand = new ShowAllProductsCommand(this.model);
		showCommand.excute();
	}

	@Override
		public void sendProducts(String str) {
		this.storeView.getShowProductChoosenView().showText(str);
	}

	@Override
	public void addProductFromView(String barcode, String productName, int storePrice, int customerPrice, boolean sub,
			String customerName, String phone) throws FileNotFoundException, IOException {
		addCommand = new AddProductCommand(this.model, barcode,
				new Product(productName, storePrice, customerPrice, new Customer(customerName, phone, sub)));
		addCommand.excute();

	}

	@Override
	public void succesAddingToModel() {
		storeView.setUndo(true);
		storeView.setNoProducts(false);
		this.storeView.succesAddingMessage();
	}

	@Override
	public void duplicateBarcode() {
		this.storeView.popMessage("Barcode is already in store. Replaced all details for that barcode.");
	}

	@Override
	public void ProductFoundByBarcodeFromView(String barcode) {
		findCommand = new FindCommand(this.model, barcode);
		findCommand.excute();
	}

	@Override
	public void deleteLastFromView() throws FileNotFoundException, IOException {
		undoCommand = new UndoCommand(this.model);
		undoCommand.excute();
	}

	@Override
	public void deleteAllFromView() throws FileNotFoundException, IOException {
		deleteAllCommand = new DeleteAllCommand(model);
		deleteAllCommand.excute();
	}

	@Override
	public void deleteByBarcodeFromView(String barcode) throws FileNotFoundException, IOException {
		deleteByBarcodeCommand = new DeleteByBarcodeCommand(model, barcode);
		deleteByBarcodeCommand.excute();
	}

	@Override
	public void errorMessage() {
		storeView.popMessage("ERROR!!");
	}

	@Override
	public void noBarcodeFound() {
		storeView.popMessage("Product by barcode is not in store!!");
	}

	@Override
	public void succesRemovingFromModel() {
		storeView.setUndo(false);
		if (this.model.getBarcodesList().size() == 0)
			storeView.setNoProducts(true);
		storeView.popMessage("Product by barcode removed succesfully");

	}

	@Override
	public void succesRemovingAllFromModel() {
		storeView.setUndo(false);
		storeView.setNoProducts(true);
		storeView.popMessage("All Products deleted");

	}

	@Override
	public void SendMessageFromView(String commercial) throws FileNotFoundException, IOException {
		sendMessageCommand = new SendMessageCommand(model, storeView);
		sendMessageCommand.excute();
	}

	@Override
	public void messageApprove(String name) {
		storeView.approveMessage(name);
	}

	@Override
	public void approveMessageFromView() throws FileNotFoundException, IOException {
		recieveCommand = new RecieveCommand(model);
		recieveCommand.excute();
	}

	@Override
	public void messageSent() {
		storeView.popMessage("Message sent to all subscribers!!");
	}

	@Override
	public void foundProductByBarcode(Product product) {
		storeView.getFindProductChoosenView().showText(product.toString());

	}

	@Override
	public void showProfitFromView(String barcode) throws FileNotFoundException, IOException {
		showProfitCommand = new ProductProfitCommand(this.model, barcode);
		showProfitCommand.excute();

	}

	@Override
	public void sendProfit(int profit) {
		storeView.popMessage("Total profit for store: $ " + profit);
	}

	@Override
	public void sendProductProfit(int profit, String barcode) {
		storeView.getProfitChoosenView().showText("Profit for product with barcode " + barcode + " is: $ " + profit);
		;

	}

	@Override
	public void showAllProfit() throws FileNotFoundException, IOException {
		allProfitCommand = new AllProfitCommand(this.model);
		allProfitCommand.excute();
	}

	@Override
	public void noSubstribesToView() {
		storeView.popMessage("No subscribers in store!!");
	}
}

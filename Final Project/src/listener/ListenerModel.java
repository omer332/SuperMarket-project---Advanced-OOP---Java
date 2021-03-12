
package listener;

import classes.Product;

public interface ListenerModel {
	/**
	 * send Product back to view
	 * 
	 * @param str - String
	 */
	void sendProducts(String str);

	/**
	 * Success adding product pop message to view
	 */
	void succesAddingToModel();

	/**
	 * Switching product pop message due to duplicate barcode to view
	 */
	void duplicateBarcode();

	/**
	 * Failing adding product pop message because barcode is not existed to view
	 */
	void noBarcodeFound();

	/**
	 * Success removing product pop message to view
	 */
	void succesRemovingFromModel();

	/**
	 * Success sending message pop message to view
	 */
	void messageSent();

	/**
	 * Pop message for approving customers to view
	 * 
	 * @param name - String
	 */
	void messageApprove(String name);

	/**
	 * Printing found product to view
	 * 
	 * @param product - Product
	 */
	void foundProductByBarcode(Product product);

	/**
	 * Unexpected error
	 */
	void errorMessage();

	/**
	 * Success removing product pop message to view
	 */
	void succesRemovingAllFromModel();

	void sendProductProfit(int profit, String barcode);

	void sendProfit(int profit);

	void noSubstribesToView();
}


package listener;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ListenerView {
	/**
	 * Setting up sorting upon request
	 * 
	 * @param temp - int
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void lisSortChoice(int temp) throws FileNotFoundException, IOException;

	/**
	 * Showing all products option from view
	 */
	void showAllProductsOnView();

	/**
	 * Adding product option from view
	 * 
	 * @param barcode       - String
	 * @param productName   - String *ONLY LETTERS*
	 * @param storePrice    - int *ONLY NUMBERS*
	 * @param customerPrice - int *ONLY NUMBERS*
	 * @param sub           - true/false
	 * @param customerName  - String *ONLY LETTERS*
	 * @param phone         - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void addProductFromView(String barcode, String productName, int storePrice, int customerPrice, boolean sub,
			String customerName, String phone) throws FileNotFoundException, IOException;

	void ProductFoundByBarcodeFromView(String barcode);

	/**
	 * UNDO For inserting option from view
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void deleteLastFromView() throws FileNotFoundException, IOException;

	/**
	 * Deleting all products option from view
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void deleteAllFromView() throws FileNotFoundException, IOException;

	/**
	 * Deleting product by barcode option from view
	 * 
	 * @param barcode - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void deleteByBarcodeFromView(String barcode) throws FileNotFoundException, IOException;

	/**
	 * Seding message to customers option from view
	 * 
	 * @param commercial - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void SendMessageFromView(String commercial) throws FileNotFoundException, IOException;

	/**
	 * Approving message from customers option from view
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void approveMessageFromView() throws FileNotFoundException, IOException;

	/**
	 * Showing profit per product by barcode from view
	 * 
	 * @param barcode - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void showProfitFromView(String barcode) throws FileNotFoundException, IOException;

	/**
	 * Showing profit for the whole store from view
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void showAllProfit() throws FileNotFoundException, IOException;
}


package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Store;

public class ProductProfitCommand implements Command {
	// Components
	private Store store;
	private String barcode;

	// Constructor
	public ProductProfitCommand(Store store, String barcode) {
		this.store = store;
		this.barcode = barcode;
	}

	@Override
	public void excute() throws FileNotFoundException, IOException {
		store.profitProduct(this.barcode);
	}

}

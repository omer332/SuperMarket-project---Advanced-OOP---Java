
package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Store;

public class DeleteByBarcodeCommand {
	// Components
	private Store store;
	private String barcode;

	// Constructor
	public DeleteByBarcodeCommand(Store store, String barcode) {
		this.store = store;
		this.barcode = barcode;
	}

	public void excute() throws FileNotFoundException, IOException {
		store.removeBybarcode(barcode);
	}

}

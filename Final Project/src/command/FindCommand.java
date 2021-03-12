/**
 * Find product in store command
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package command;

import classes.Store;

public class FindCommand implements Command {
	// Components
	private Store store;
	private String barcode;

	// Constructor
	public FindCommand(Store store, String barcode) {
		this.store = store;
		this.barcode = barcode;
	}

	@Override
	public void excute() {
		store.findProductByKey(barcode);
	}

}

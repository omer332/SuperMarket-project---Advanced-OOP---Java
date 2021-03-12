/**
 * Showing products in store command
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package command;

import classes.Store;

public class ShowAllProductsCommand implements Command {
	// Componenets
	private Store store;

	// Constructor
	public ShowAllProductsCommand(Store store) {
		this.store = store;
	}

	@Override
	public void excute() {
		store.AllProducts();
	}

}

/**
 * Store Profit Command
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Store;

public class AllProfitCommand implements Command {
	// Components
	private Store store;

	// Constructor
	public AllProfitCommand(Store store) {
		this.store = store;
	}

	@Override
	public void excute() throws FileNotFoundException, IOException {
		store.profitAll();
	}
}

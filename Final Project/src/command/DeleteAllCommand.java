
package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import classes.Store;

public class DeleteAllCommand implements Command {
	// Components
	private Store store;

	// Constructor
	public DeleteAllCommand(Store store) {
		this.store = store;
	}

	@Override
	public void excute() throws FileNotFoundException, IOException {
		store.removeAllProductsFromStore();
	}

}

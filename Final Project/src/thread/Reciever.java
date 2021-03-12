
package thread;

import java.util.ArrayList;
import classes.Store;
import listener.ListenerModel;
import singletone.Sender;

public class Reciever extends Thread {
	// Components
	private ArrayList<String> subNames;
	private Store store;

	// Constructor
	public Reciever(Store store, ArrayList<String> subNames) {
		this.store = store;
		this.subNames = subNames;
	}

	/**
	 * run the thread and start getting messages back.
	 */
	@Override
	public void run() {
		try {
			for (String name : subNames) {
				for (ListenerModel m : store.getModelListener())
					m.messageApprove(name);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			for (ListenerModel m : store.getModelListener())
				m.errorMessage();
		} finally {
			Sender.setInstance(null);
		}
	}
}

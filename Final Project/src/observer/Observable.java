/**
 * Store as the observable
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package observer;

public interface Observable {
	/**
	 * Add subscribed customer
	 * 
	 * @param o - Customer
	 */
	void addObserver(Observer o);

	/**
	 * Remove subscribed customer
	 * 
	 * @param o - Customer
	 */
	void removeObserver(Observer o);

	/**
	 * Sending message to subscribed customer
	 * 
	 * @param o - Customer
	 */
	void notifyObservers(String commercial);
}

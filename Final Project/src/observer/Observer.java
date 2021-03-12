/**
 * Customer as the observer
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package observer;

public interface Observer {
	/**
	 * Updating customer got a message
	 * 
	 * @param o          - Customer
	 * @param commercial - String
	 */
	String updateObserver(Observer o, String commercial);
}
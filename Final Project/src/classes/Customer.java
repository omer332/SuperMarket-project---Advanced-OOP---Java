/**
 * Customers
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package classes;

import java.io.Serializable;

import observer.Observable;
import observer.Observer;

public class Customer implements Observer {
	// Components
	// private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private boolean subscribe;

	// Constructor
	public Customer(String name, String phoneNumber, boolean subscribe) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		setSubscribe(subscribe);
	}

	// Copy constructor
	public Customer(Customer other) {
		this.name = other.name;
		this.phoneNumber = other.phoneNumber;
		setSubscribe(other.subscribe);
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	// Setter
	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	// toString
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(
				" Customer:\nname: " + name + " Phone Number: 05" + phoneNumber + " Subscribe: " + subscribe);
		return sb.toString();
	}

	@Override
	public String updateObserver(Observer o, String commercial) {
		if (this.subscribe)
			return this.name;
		return null;
	}
}

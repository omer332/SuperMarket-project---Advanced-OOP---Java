
package classes;

import java.io.Serializable;


public class Product implements Serializable {
	//Components
	private static final long serialVersionUID = 1L;
	private String name;
	private int storePrice;
	private int customerPrice;
	private Customer customer;
//Consturactor
	public Product(String name, int storePrice, int customerPrice, Customer customer) {
		this.name = name;
		this.storePrice = storePrice;
		this.customerPrice = customerPrice;
		this.customer = new Customer(customer);
	}
//Copy constructor
	public Product(Product other) {
		this.name = other.name;
		this.customerPrice = other.customerPrice;
		this.storePrice = other.storePrice;
		this.customer = new Customer(other.customer);
	}
//Getters
	public Customer getCustomer() {
		return customer;
	}

	public int getStorePrice() {
		return storePrice;
	}

	public int getCustomerPrice() {
		return customerPrice;
	}
	public String getName() {
		return name;
	}
//toString
	@Override
	public String toString() {
		return " Product name: " + name + " Store Price: " + storePrice + " Customer Price: " + customerPrice + "\n"
				+ customer.toString() + "\n";
	}
}

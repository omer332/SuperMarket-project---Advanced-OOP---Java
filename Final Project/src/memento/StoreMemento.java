
package memento;

import java.util.ArrayList;
import java.util.HashMap;

import classes.Product;
import classes.Store;
import observer.Observer;

public class StoreMemento {
	// Components
	private Store store;
	private HashMap<String, Product> productsMap;
	private ArrayList<String> barcodesList;
	private ArrayList<Observer> obsList;

	// Constructor
	public StoreMemento(Store store) {
		this.store = store;

	}

	/**
	 * Saving before adding for the UNDO.
	 */
	public void save() {
		this.barcodesList = new ArrayList<>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<>();
		setBarcodesList(this.store.getBarcodesList());
		setObsList(this.store.getObsList());
		setProductsMap(this.store.getProductsMap());
	}

	/**
	 * Loading for UNDO upon request.
	 */
	public void load() {
		this.store.setBarcodesList(this.barcodesList);
		this.store.setObsList(this.obsList);
		this.store.setProductsMap(this.productsMap);
	}

	// Getters
	public HashMap<String, Product> getProductsMap() {
		return this.productsMap;
	}

	public ArrayList<String> getBarcodesList() {
		return this.barcodesList;
	}

	public ArrayList<Observer> getObsList() {
		return this.obsList;
	}

	// Setters
	public void setObsList(ArrayList<Observer> obsList) {
		this.obsList.addAll(obsList);
	}

	public void setProductsMap(HashMap<String, Product> productsMap) {
		this.productsMap.putAll(productsMap);
	}

	public void setBarcodesList(ArrayList<String> barcodesList) {
		this.barcodesList.addAll(barcodesList);
	}

}

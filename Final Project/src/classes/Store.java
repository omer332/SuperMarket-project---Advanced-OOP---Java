package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import file.FileClass;
import listener.ListenerModel;
import memento.StoreMemento;
import observer.Observable;
import observer.Observer;
import thread.Reciever;

public class Store implements Observable {
	// Components
	public final String fileName = "products.txt";
	private ArrayList<ListenerModel> modelListener;
	private HashMap<String, Product> productsMap;
	private ArrayList<String> barcodesList;
	private ArrayList<Observer> obsList;
	private StoreMemento storeMemento;
	private ArrayList<String> subNames;
	private int sortChoice;
	private FileClass fi;

	// Constractor
	public Store() throws FileNotFoundException, IOException {
		modelListener = new ArrayList<>();
		this.barcodesList = new ArrayList<String>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<Observer>();
		storeMemento = new StoreMemento(this);
		initFile();
	}

	/**
	 * Init file and reading
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void initFile() throws FileNotFoundException, IOException {
		fi = new FileClass(fileName);
		this.productsMap = fi.readFromFile();
		FillFromFile();
	}

	/**
	 * Filling all information from file after reading.
	 */
	private void FillFromFile() {
		for (Entry<String, Product> p : this.productsMap.entrySet()) {
			this.barcodesList.add(p.getKey());
			if (p.getValue().getCustomer().isSubscribe())
				obsList.add(p.getValue().getCustomer());
		}
	}

	/**
	 * Adding model listener
	 * 
	 * @param listener - ListenerModel
	 */
	public void registerListener(ListenerModel listener) {
		modelListener.add(listener);
	}

	/**
	 * Sorting map as requested
	 */
	public void sortMap() {
		if (sortChoice == 1)
			Collections.sort(barcodesList);
		else if (sortChoice == -1)
			Collections.sort(barcodesList, Collections.reverseOrder());
	}

	/**
	 * Adding product to file, to map, to arrays
	 * 
	 * @param barcode - String
	 * @param product - Product
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addProduct(String barcode, Product product) throws FileNotFoundException, IOException {
		boolean duplicate = false;
		if (barcodesList.size() > 0)
			storeMemento.save();
		if (barcodesList.contains(barcode)) {
			duplicate = true;
			fi.removeByBarcode(barcode);
			obsList.remove(productsMap.get(barcode).getCustomer());
		} else
			barcodesList.add(barcode);
		productsMap.put(barcode, product);
		// if (product.getCustomer().isSubscribe())
		obsList.add(product.getCustomer());
		// addObserver(o);
		sortMap();
		fi.writeToFileAProduct(barcode, product);
		if (duplicate) {
			for (ListenerModel m : modelListener)
				m.duplicateBarcode();
		} else {
			for (ListenerModel m : modelListener)
				m.succesAddingToModel();
		}
	}

	// Removing
	/**
	 * Removing all products from store
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeAllProductsFromStore() throws FileNotFoundException, IOException {
		this.barcodesList = new ArrayList<>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<>();
		storeMemento = new StoreMemento(this);
		fi.removeAll();
		for (ListenerModel m : modelListener)
			m.succesRemovingAllFromModel();

	}

	/**
	 * Removing a product from store by its barcode
	 * 
	 * @param barcode - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeBybarcode(String barcode) throws FileNotFoundException, IOException {
		if (!barcodesList.contains(barcode)) {
			for (ListenerModel m : modelListener)
				m.noBarcodeFound();
		} else {
			obsList.remove(productsMap.get(barcode).getCustomer());
			productsMap.remove(barcode);
			barcodesList.remove(barcode);
			storeMemento.save();
			fi.removeByBarcode(barcode);
			for (ListenerModel m : modelListener)
				m.succesRemovingFromModel();
		}
	}

	/**
	 * UNDO
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeLast() throws FileNotFoundException, IOException {
		storeMemento.load();
		fi.removeLast();

	}

	/**
	 * Finding product by key
	 * 
	 * @param barcode - String
	 */
	public void findProductByKey(String barcode) {
		if (barcodesList.contains(barcode)) {
			for (ListenerModel m : modelListener)
				m.foundProductByBarcode(productsMap.get(barcode));
		} else {
			for (ListenerModel m : modelListener)
				m.noBarcodeFound();
		}
	}

	/**
	 * Showing all products
	 */
	public void AllProducts() {
		StringBuffer sb = new StringBuffer("List of products:\n");
		if (productsMap.isEmpty())
			sb.append("No products to present!!\n");
		int i = 1;
		for (String barcode : barcodesList) {
			sb.append("###############Product " + i + "################\n");
			sb.append("Barcode: " + barcode + productsMap.get(barcode).toString());
			i++;
		}
		for (ListenerModel m : modelListener)
			m.sendProducts(sb.toString());
	}

	/**
	 * Showing profit per product
	 * 
	 * @param barcode - String
	 */
	public void profitProduct(String barcode) {
		if (barcodesList.contains(barcode)) {
			int profit = this.productsMap.get(barcode).getCustomerPrice()
					- this.productsMap.get(barcode).getStorePrice();
			for (ListenerModel m : modelListener)
				m.sendProductProfit(profit, barcode);
		} else {
			for (ListenerModel m : modelListener)
				m.noBarcodeFound();
		}
	}

	/**
	 * Showing profit for the whole store
	 */
	public void profitAll() {
		int profit = 0;
		for (int i = 0; i < this.productsMap.size(); i++)
			profit += (this.productsMap.get(barcodesList.get(i)).getCustomerPrice()
					- this.productsMap.get(barcodesList.get(i)).getStorePrice());
		for (ListenerModel m : modelListener)
			m.sendProfit(profit);
	}

	@Override
	public void addObserver(Observer o) {
		obsList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		obsList.remove(o);
	}

	@Override
	public void notifyObservers(String commercial) {
		subNames = new ArrayList<>();
		String name = null;
		for (Observer o : obsList) {
			name = o.updateObserver(o, commercial);
			if (!name.equals(null))
				subNames.add(name);
		}
	}

	public void recieveMessage() {
		if (subNames.size() <= 0)
			for (ListenerModel m : modelListener)
				m.noSubstribesToView();
		else {
			Reciever sms = new Reciever(this, subNames);
			sms.run();
		}

	}

	// Getters
	public HashMap<String, Product> getProductsMap() {
		return productsMap;
	}

	public ArrayList<String> getBarcodesList() {
		return barcodesList;
	}

	public ArrayList<Observer> getObsList() {
		return obsList;
	}

	public ArrayList<ListenerModel> getModelListener() {
		return modelListener;
	}

	// Setters
	public void setProductsMap(HashMap<String, Product> productsMap) {
		this.productsMap = productsMap;
	}

	public void setSortChoice(int sortChoice) {
		this.sortChoice = sortChoice;
	}

	public void setBarcodesList(ArrayList<String> barcodesList) {
		this.barcodesList = barcodesList;
	}

	public void setObsList(ArrayList<Observer> obsList) {
		this.obsList = obsList;
	}
}

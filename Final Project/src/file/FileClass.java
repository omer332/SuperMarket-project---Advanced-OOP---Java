
package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;

import classes.Customer;
import classes.Product;

public class FileClass {
	// Components
	private File file;
	private RandomAccessFile raf;

	// Constructor
	public FileClass(String fileName) {
		this.file = new File(fileName);
	}

	/**
	 * Writing to bin file a product.
	 * 
	 * @param barcode - String
	 * @param product - Product
	 * @throws IOException
	 */
	public void writeToFileAProduct(String barcode, Product product) throws IOException {
		raf = new RandomAccessFile(file, "rw");
		raf.seek(file.length());
		raf.writeUTF(barcode);
		raf.writeUTF(product.getCustomer().getName());
		raf.writeUTF(product.getCustomer().getPhoneNumber());
		raf.writeBoolean(product.getCustomer().isSubscribe());
		raf.writeUTF(product.getName());
		raf.writeInt(product.getStorePrice());
		raf.writeInt(product.getCustomerPrice());
		raf.close();

	}

	/**
	 * Reading all products from file
	 * 
	 * @return All products in file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public HashMap<String, Product> readFromFile() throws FileNotFoundException, IOException {
		HashMap<String, Product> productMap = new HashMap<>();
		Product p;
		String bar;
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			p = (Product) it.next();
			bar = it.getBarcode();
			productMap.put(bar, p);
		}
		return productMap;
	}

	/***
	 * Creating own iterator
	 * 
	 * @return the iterator itself (kinda like constructor)
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Iterator<Product> iterator() throws FileNotFoundException, IOException {
		return new MyFileIterator();
	}

	/**
	 * UNDO Action on file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeLast() throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (true) {
			if (it.hasNext())
				it.next();
			else
				break;
		}

		it.remove();
	}

	/**
	 * Removing product by barcode from file
	 * 
	 * @param barcode - String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeByBarcode(String barcode) throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			it.next();
			if (it.getBarcode().equals(barcode)) {
				it.remove();
			}
		}
	}

	/**
	 * Removing all products from file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void removeAll() throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	/**
	 * Creating own iterator
	 */
	public class MyFileIterator implements Iterator<Product> {
		// Componenets
		private long last;
		private long pos;
		private String barcode;

		// Constructor
		public MyFileIterator() {
			last = -1;
			pos = 0;
		}

		/**
		 * Checking if there such a product to read next in file.
		 * 
		 * @return true if there is still next in file, false otherwise
		 */
		@Override
		public boolean hasNext() {

			return pos < file.length();
		}

		/**
		 * Reading next product from file
		 * 
		 * @return Next product
		 */
		@Override
		public Product next() {
			try {
				raf = new RandomAccessFile(file, "rw");
				while (hasNext()) {
					last = pos;
					raf.seek(pos);
					barcode = raf.readUTF();
					String cusName = raf.readUTF();
					String cusPhone = raf.readUTF();
					boolean isSub = raf.readBoolean();
					String proName = raf.readUTF();
					int storePrice = raf.readInt();
					int cusPrice = raf.readInt();
					pos = raf.getFilePointer();
					raf.close();
					return new Product(proName, storePrice, cusPrice, new Customer(cusName, cusPhone, isSub));
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			return null;
		}

		/**
		 * Getter for barcode
		 * 
		 * @return barcode
		 */
		public String getBarcode() {
			return barcode;
		}

		/**
		 * removing product/s from file
		 */
		public void remove() {
			try {
				raf = new RandomAccessFile(file, "rw");

				if (file.length() - pos > 0) {
					byte arr[] = new byte[(int) (file.length() - pos)];
					raf.seek(pos);
					raf.read(arr);
					raf.setLength(last);
					raf.write(arr);
				} else {
					raf.setLength(last);
				}
				pos = 0;
				raf.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}
}

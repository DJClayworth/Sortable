package challenge;

import java.util.Set;

public class ProductCollection {
	
	private Set<Product> productSet;
	
	public boolean addProduct(Product p) {
		
		return productSet.add(p);
	}

}

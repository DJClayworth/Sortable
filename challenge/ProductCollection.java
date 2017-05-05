package challenge;

import java.util.HashSet;
import java.util.Set;

public class ProductCollection {
	
	private Set<Product> productSet = new HashSet<Product>();
	
	public boolean addProduct(Product p) {
		
		return productSet.add(p);
	}

}

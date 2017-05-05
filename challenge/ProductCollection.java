package challenge;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONWriter;

public class ProductCollection {
	
	private Set<Product> productSet = new HashSet<Product>();
	
	/**
	 * Products grouped by manufacturer. Key is the manufacturer name (lowercased), and the value is the set of products for that manufacturer.
	 * 
	 * Note that we are assuming a certain amount of control and standardisation over the manufacturer's name in the products file - i.e.
	 * that there are no synonyms.
	 */
	private Map<String,Set<Product>> productsByManufacturer = new HashMap<String,Set<Product>>();
	
	/**
	 * This map acts as a cache of synonyms for manufacturers detected in processing the listings. All Set<Product> objects in here also appear in productsByManufacturer.
	 */
	private Map<String,Set<Product>> manufacturerSynonyms = new HashMap<String,Set<Product>>();
	
	/** Stores the matching listings for each product */
	private Map<Product,List<Listing>> matchLists = new HashMap<Product,List<Listing>>();
	
	/** These are words that will be ignored in the determination of whether there is a match of manufacturers or not */
	private static final Set<String> manufacturerNoiseWords = new HashSet<String>();
	
	static {
		manufacturerNoiseWords.add("Canada");
	}
	
	
	public Map<Product,List<Listing>> getMatchingListing() {
		return matchLists;
	}
	
	
	public void addProduct(Product p) {
		
		String manufacturer = p.getManufacturer().toLowerCase();
		
		Set<Product> productsForThisManufacturer = productsByManufacturer.get(manufacturer);
		
		if (productsForThisManufacturer == null) {
			productsForThisManufacturer = new HashSet<Product>();
			productsByManufacturer.put(manufacturer, productsForThisManufacturer);
		}
		
		productsForThisManufacturer.add(p);

	}
	
	private Set<Product> findManufacturer(Listing l) {
		
		String manufacturer = l.getManufacturer().toLowerCase();
		
		// Look for canonical manufacturer names
		Set<Product>  products = productsByManufacturer.get(manufacturer.toLowerCase());
		
		if (products == null) {
			// See if this is a synonym of a manufacturer
			products = manufacturerSynonyms.get(manufacturer);
		}
		if (products == null) {
			// Exact match not found. Look for another possible match by checking occurrences of manufacturer names in the listing
			
			String manufacturerWords[] = manufacturer.split("\\s+");
			
			for (String s : manufacturerWords) {
				products = productsByManufacturer.get(s.toLowerCase());
				if (products != null) {
					// Note this as a synonym of the manufacturer for future use
					manufacturerSynonyms.put(manufacturer, products);
				}
				break;
			}
		}
		
		return products;
	}
	
	private boolean matches(Listing listing, Product product) {
		
		if (listing.getTitle().contains(product.getModel())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void addToMatchingProduct(Listing listing) {
		
		Set<Product> productSet = findManufacturer(listing);
		
		if (productSet != null) {
			for (Product p: productSet) {
				if (matches(listing, p)) {
					List<Listing> matchList = matchLists.get(p);
					if (matchList == null) {
						matchList = new ArrayList<Listing>();
						matchLists.put(p, matchList);
					}
					matchList.add(listing);
					break;
				}
			}
		}
	}
	
	public static String listingOutput(Listing listing) {
		StringWriter sw = new StringWriter();
		
		JSONWriter jw = new JSONWriter(sw).object();
		
		jw.key("title").value(listing.getTitle())
		.key("manufacturer").value(listing.getManufacturer())
		.key("currency").value(listing.getCurrency())
		.key("price").value(listing.getPrice());
		jw.endObject();

		
		return sw.toString();
		
	}

}

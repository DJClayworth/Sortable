package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private static final Set<Pattern> forSynonyms = new HashSet<Pattern>();
	
	static {
		forSynonyms.add(Pattern.compile("\\sfor\\s"));
		forSynonyms.add(Pattern.compile("\\sf�r\\s"));
		forSynonyms.add(Pattern.compile("\\spour\\s"));
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
	
	/**
	 * Determines if the Listing is a match for the product p. It assumes that there is already a match for the manufacturer.
	 * @param listing the listing to match
	 * @param product the product to match
	 * @return if there is a match
	 */
	private boolean matches(Listing listing, Product product) {
		
		String title =  withoutFor(listing.getTitle());
		
		Matcher modelMatcher = product.getModelPattern().matcher(title);
		
		if (!modelMatcher.matches()) {
			return false;
		}
		
		Pattern familyPattern = product.getFamilyPattern();
		if (familyPattern != null) {
			Matcher familyMatcher = familyPattern.matcher(title);
			if (!familyMatcher.matches()) {
				return false;
			}
		}
		
		return true;
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

	/**
	 * Returns the part of a title that occurs before the first occurrence of the word 'for' or various synonyms
	 * @param title the title
	 * @return substring of the title before the word 'for'. May be 'title' if no synonyms are found.
	 */
	private static String withoutFor(String title) {
		
		for (Pattern p: forSynonyms) {
			Matcher m = p.matcher(title);
			boolean found = m.find();
			if (found) {
				title = title.substring(0,m.start());
			}
		}

		return title;
		
	}

}

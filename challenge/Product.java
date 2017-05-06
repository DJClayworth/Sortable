package challenge;

import java.util.Set;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Product {
	
	public class ProductException extends Exception {
		
		public ProductException(String error) {
			super(error);
		}
	}
	
	public final static String PRODUCT_NAME = "product_name";
	
	public final static String MANUFACTURER = "manufacturer";
	
	public final static String MODEL = "model";
	
	public final static String FAMILY = "family";
	
	private String name;
	
	private Set<String> nameWordSet;
	
	private String manufacturer;
	
	private String model;
	
	private Pattern modelPattern;
	
	private Set<String> modelWordSet;
	
	private String family;
	
	private Pattern familyPattern;
	
	public Product(JSONObject obj) throws ProductException {
		
		if (obj.has(PRODUCT_NAME)) {
		  name = obj.getString(PRODUCT_NAME);
		} else {
			throw new ProductException("Product without name.");
		}
		
		if (obj.has(MANUFACTURER)) {
		  manufacturer = obj.getString(MANUFACTURER);
		} else {
			throw new ProductException("Product without manufacturer.");
		}
		
		if (obj.has(FAMILY)) {
	    	family = obj.getString(FAMILY);
	    	// The literal family string preceded by either a whitespace char or the beginning of input, and followed by either a whitespace char or the end of input
	    	familyPattern = Pattern.compile("((.*\\s)|(\\A))" + Pattern.quote(family) + "((\\s.*)|(\\Z))", Pattern.CASE_INSENSITIVE);
		}
		
		if (obj.has(MODEL)) {
	    	model = obj.getString(MODEL);
	    	// The literal model string preceded by either a whitespace char or the beginning of input, and followed by either a whitespace char or the end of input
	    	modelPattern = Pattern.compile("((.*\\s)|(\\A))" + Pattern.quote(model) + "((\\s.*)|(\\Z))", Pattern.CASE_INSENSITIVE);
		} else {
			throw new ProductException("Product without model.");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getModel() {
		return model;
	}
	
	public Pattern getModelPattern() {
		return modelPattern;
	}
	
	public boolean isWordInModelSet(String word) {
		return false;
	}
	
	public String getFamily() {
		return family;
	}

	public Pattern getFamilyPattern() {
		return familyPattern;
	}
	

	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String toString() {
		return "{ \"name\":\"" + getName() + "}";
	}

}

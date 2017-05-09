package challenge;

import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * Class that represents an entry in the products file
 * 
 * @author David
 *
 */
public class Product implements IProduct {
	
	public class ProductException extends Exception {
		
		/**
		 * Exception thrown while generating the product class
		 */
		private static final long serialVersionUID = 1L;

		public ProductException(String error) {
			super(error);
		}
	}
	
	/**
	 * JSON keys
	 */
	public final static String PRODUCT_NAME = "product_name";
	
	public final static String MANUFACTURER = "manufacturer";
	
	public final static String MODEL = "model";
	
	public final static String FAMILY = "family";
	
	
	private String name;
	
	private String manufacturer;
	
	private String model;
	
	/**
	 * Regex pattern that will be used to match the model
	 */
	private Pattern modelPattern;
	
	private String family;
	
	/**
	 * Regex pattern that will be used to match the model
	 */
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
	
	/* (non-Javadoc)
	 * @see challenge.IProduct#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see challenge.IProduct#getModel()
	 */
	@Override
	public String getModel() {
		return model;
	}
	
	public Pattern getModelPattern() {
		return modelPattern;
	}

	
	/* (non-Javadoc)
	 * @see challenge.IProduct#getFamily()
	 */
	@Override
	public String getFamily() {
		return family;
	}

	public Pattern getFamilyPattern() {
		return familyPattern;
	}
	

	
	/* (non-Javadoc)
	 * @see challenge.IProduct#getManufacturer()
	 */
	@Override
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String toString() {
		return "{ \"name\":\"" + getName() + "}";
	}

}

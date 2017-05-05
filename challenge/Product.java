package challenge;

import java.util.Set;

import org.json.JSONObject;

public class Product {
	
	public final static String PRODUCT_NAME = "product_name";
	
	public final static String MANUFACTURER = "manufacturer";
	
	public final static String MODEL = "model";
	
	public final static String FAMILY = "family";
	
	private String name;
	
	private Set<String> nameWordSet;
	
	private String manufacturer = null;
	
	private String model = null;
	
	private Set<String> modelWordSet;
	
	private String family = null;
	
	public Product(JSONObject obj) {
		
		if (obj.has(PRODUCT_NAME)) {
		  name = obj.getString(PRODUCT_NAME);
		}
		
		if (obj.has(MANUFACTURER)) {
		  manufacturer = obj.getString(MANUFACTURER);
		}
		
		if (obj.has(FAMILY)) {
	    	family = obj.getString(FAMILY);
		}
		
		if (obj.has(MODEL)) {
	    	model = obj.getString(MODEL);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isWordInName(String word) {
		return false;
	}
	
	public String getModel() {
		return model;
	}
	
	public boolean isWordInModelSet(String word) {
		return false;
	}
	
	public String getFamily() {
		return family;
	}
	
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String toString() {
		return "{ \"name\":\"" + getName() + "}";
	}

}

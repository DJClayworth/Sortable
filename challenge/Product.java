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
	
	private String manufacturer;
	
	private String model;
	
	private Set<String> modelWordSet;
	
	private String family;
	
	public Product(JSONObject obj) {
		
		name = obj.getString(PRODUCT_NAME);
		
		manufacturer = obj.getString(MANUFACTURER);
		
		family = obj.getString(FAMILY);
		
		model = obj.getString(MODEL);
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
	
	public String toString() {
		return "{ \"name\":\"" + getName() + "}";
	}

}

package challenge;

import org.json.JSONObject;

public class Listing {
	
	private static final String TITLE = "title";
	
	private static final String MANUFACTURER = "manufacturer";
	
	private static final String CURRENCY = "currency";
	
	private static final String PRICE = "price";
	
	private String title = "";
	
	private String manufacturer = "";
	
	private String currency = "";
	
	/** If we needed to do any work with the price, we could convert this to a standardised numerical value. But we don't. */
	
	private String price = "";

	
	public Listing(JSONObject obj) {
		
		if (obj.has(TITLE)) {
		  title = obj.getString(TITLE);
		}
		
		if (obj.has(MANUFACTURER)) {
		  manufacturer = obj.getString(MANUFACTURER);
		}
		
		if (obj.has(CURRENCY)) {
	    	currency = obj.getString(CURRENCY);
		}
		
		if (obj.has(PRICE)) {
	    	price = obj.getString(PRICE);
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public String getPrice() {
		return price;
	}
	

	public String toString() {
		return "{ \"title\":\"" + getTitle() + ", \"manufacturer\":" + getManufacturer() + "}";
	}



}

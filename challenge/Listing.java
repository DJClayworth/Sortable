package challenge;

import org.json.JSONObject;

/**
 * Class that represents an entry in the listing file
 * 
 * @author David
 *
 */
public class Listing implements IListing {
	
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
	
	/* (non-Javadoc)
	 * @see challenge.IListing#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	
	/* (non-Javadoc)
	 * @see challenge.IListing#getManufacturer()
	 */
	@Override
	public String getManufacturer() {
		return manufacturer;
	}
	
	/* (non-Javadoc)
	 * @see challenge.IListing#getCurrency()
	 */
	@Override
	public String getCurrency() {
		return currency;
	}
	
	/* (non-Javadoc)
	 * @see challenge.IListing#getPrice()
	 */
	@Override
	public String getPrice() {
		return price;
	}
	

	public String toString() {
		return "{ \"title\":\"" + getTitle() + ", \"manufacturer\":" + getManufacturer() + "}";
	}



}

package challenge;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.json.JSONWriter;

/**
 * Writes results to an output file.
 * 
 * @author David
 *
 */
public class Writer {
	
	private static String TITLE_KEY = "title";
	
	private static String MANUFACTURER_KEY = "manufacturer";
	
	private static String CURRENCY_KEY = "currency";
	
	private static String PRICE_KEY = "price";
	
	private static String PRODUCTNAME_KEY = "product_name";
	
	private static String LISTINGS_KEY = "price";
	

	public static void writeResults(PrintStream outfile, Map<Product, List<Listing>> matchingListings) {
		
		  for (Map.Entry<Product,List<Listing>> e: matchingListings.entrySet()) {
			  StringWriter writer = new StringWriter();
			  JSONWriter jWriter = new JSONWriter(writer);
			  
			  jWriter.object().key(PRODUCTNAME_KEY).value(e.getKey().getName()).key(LISTINGS_KEY).array();
			  

			  for (IListing listing: e.getValue()) {
				  
				  jWriter.object().key(TITLE_KEY).value(listing.getTitle())
					.key(MANUFACTURER_KEY).value(listing.getManufacturer())
					.key(CURRENCY_KEY).value(listing.getCurrency())
					.key(PRICE_KEY).value(listing.getPrice())
					.endObject();

				  
			  }
			  jWriter.endArray().endObject();
			  outfile.println(writer.toString());
		  }
		  

	}

}

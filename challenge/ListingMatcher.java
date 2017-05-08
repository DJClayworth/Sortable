package challenge;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.json.JSONWriter;

public class ListingMatcher {

	public static void main(String[] args) {
		
		ProductCollection products = null;
		
		try {
		 products  = Reader.readProducts("products.txt");
		
		 products.createManufacturerSynonyms();
		 
		 BufferedReader reader = new BufferedReader(new FileReader("listings.txt"));
		  
		  while (true) {
			  Listing listing = Reader.readListing(reader);
			  
			  products.addToMatchingProduct(listing);
		  }
			  
			  
		  
		}
		
		catch (EOFException e) {
			// normal termination
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (products != null) {
		  Map<Product,List<Listing>> matchingListings = products.getMatchingListing();
		  
		  for (Map.Entry<Product,List<Listing>> e: matchingListings.entrySet()) {
			  StringWriter writer = new StringWriter();
			  JSONWriter jWriter = new JSONWriter(writer);
			  
			  jWriter.object().key("product_name").value(e.getKey().getName()).key("listings").array();
			  

			  for (Listing listing: e.getValue()) {
				  
				  jWriter.object().key("title").value(listing.getTitle())
					.key("manufacturer").value(listing.getManufacturer())
					.key("currency").value(listing.getCurrency())
					.key("price").value(listing.getPrice())
					.endObject();

				  
			  }
			  jWriter.endArray().endObject();
			  System.out.println(writer.toString());
		  }
		  
		}
		  

	}

}

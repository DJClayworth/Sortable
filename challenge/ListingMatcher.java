package challenge;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class ListingMatcher {

	
	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.println("Requires 2 or 3 arguments: <productsfile> <listingsfile> [<resultsfile>]");
			System.exit(-1);
		}
		ProductCollection products = null;
		
		PrintStream outfile = System.out;

		try {
		 products  = Reader.readProducts(args[0]);
		
		 if (args.length > 2) {
			 outfile = new PrintStream(new File(args[2]),"UTF-8");
		 }
			

		 products.createManufacturerSynonyms();
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[1]),"UTF-8"));
		  
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
			System.exit(1);
		}
		
		// Write the results to results file
		if (products != null) {
		  Map<Product, List<Listing>> matchingListings = products.getMatchingListing();
		  
		  Writer.writeResults(outfile, matchingListings);
		}
		  
	}

}

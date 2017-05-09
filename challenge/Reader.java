package challenge;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

/**
 * This class provides methods for reading input files.
 * 
 * @author David
 *
 */
public class Reader {
	
	public static ProductCollection readProducts(String filename) {
		
		ProductCollection collection = new ProductCollection();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
			
			while (true) {
			
			  String line = reader.readLine();
			  
			  if (line != null) {
				  int firstParens = line.indexOf('{');
				  if (firstParens > 0) {
					  line = line.substring(firstParens);
				  }

				  JSONObject obj = new JSONObject(line);

				  Product p = new Product(obj);

				  collection.addProduct(p);
			  } else {
				  throw new EOFException("End of file " + filename);
			  }
			}
			
		} catch (EOFException e) {
			// EOF expected

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Product.ProductException e) {
			System.out.println("Error in product file:" + e.getMessage());
		}
		
		return collection;

	}

	/**
	 * Read a single listing
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static Listing readListing(BufferedReader reader) throws IOException {

			while (true) {
				
			
			  String line = reader.readLine();
			  
			  if (line == null) {
				  throw new EOFException("End of listings reader");
			  }
			  
			  // Strip off characters before the first { (file has noise on some lines)
			  for (int i = 0; i<line.length();i++) {
				  if (line.charAt(i) == '{') {
					  line = line.substring(i);
					  break;
				  } 
			  }
			
			  JSONObject obj = new JSONObject(line);
			
			  Listing l = new Listing(obj);
			


			  return l;
			}
			

	}


}

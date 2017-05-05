package challenge;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class Reader {
	
	public static ProductCollection readProducts(String filename) {
		
		ProductCollection collection = new ProductCollection();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			while (true) {
			
			  String line = reader.readLine();
			
			  if (line != null) {
				  JSONObject obj = new JSONObject(line);

				  Product p = new Product(obj);

				  //System.out.println(p.toString());

				  collection.addProduct(p);
			  } else {
				  throw new EOFException("End of file " + filename);
			  }
			}
			
		} catch (EOFException e) {

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return collection;

	}

	
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

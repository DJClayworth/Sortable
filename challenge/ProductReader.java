package challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class ProductReader {
	
	public static ProductCollection readProducts(String filename) {
		
		ProductCollection collection = new ProductCollection();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			while (true) {
			
			  String line = reader.readLine();
			
			  JSONObject obj = new JSONObject(line);
			
			  Product p = new Product(obj);
			
			  System.out.println(p.toString());
			  
			  collection.addProduct(p);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return collection;

	}

}

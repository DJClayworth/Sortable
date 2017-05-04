package challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class Matcher {

	public static void main(String[] args) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
			
			String line = reader.readLine();
			
			JSONObject obj = new JSONObject(line);
			
			Product p = new Product(obj);
			
			System.out.println(p.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

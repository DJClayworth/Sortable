package challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class Matcher {

	public static void main(String[] args) {
		
		ProductReader.readProducts("products.txt");
	}

}

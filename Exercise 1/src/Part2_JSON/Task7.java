package Part2_JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class Task7 {
	
	public static void main (String[] args) throws IOException {
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/resources/mondial-3.0-europe-countries.json")));	
		Long totalPopulation = 0l;
		while(reader.ready()) {
			String jsonLine = reader.readLine();
			Country country = gson.fromJson(jsonLine, Country.class);
			totalPopulation += country.population;			
		}
		reader.close();
		System.out.println(totalPopulation);
	}
}

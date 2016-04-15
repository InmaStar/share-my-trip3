package uo.sdi.util.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScriptReader {
	
	public static List<String> getQueries() throws Exception {
		List<String> list = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(ScriptReader.class
						.getResourceAsStream("restore.sql")))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		}
	
		return list;
	}
	
}

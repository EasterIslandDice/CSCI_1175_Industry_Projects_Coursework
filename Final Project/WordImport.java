import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class WordImport {
		private static final String fileName = "wordbank.txt";
		private ArrayList<String> wordBank = new ArrayList<String>();
		
			public void ImportWords(){
				try(InputStream in = getClass().getResourceAsStream(fileName);
					BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {
						String line = "";
						while ((line = bf.readLine()) != null)
							wordBank.add(line);
					}
				catch (Exception e){
					System.out.println("Couldn't find file " + fileName);
					System.out.println("Error Message: " + e.getMessage());
				}
	}
	public String GetRandomWord() {
		if (wordBank.isEmpty()){
			return "No Data";
		}
		return wordBank.get((int)Math.random() * wordBank.size());
	}

}
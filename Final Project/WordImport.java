import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class WordImport {
ArrayList<String> wordBank = new ArrayList<String>();

			public WordImport(){
				File fileInput = new File("wordbank.txt");
				try(Scanner input = new Scanner(fileInput)){

						while(input.hasNext()){
							wordBank.add(input.next());
					}	
					input.close();
					}
				catch (Exception e){
					System.out.println("Couldn't find file " + fileInput);
					System.out.println("Error Message: " + e.getMessage());
				}
	}
	public String GetRandomWord() {
		if (wordBank.isEmpty())
			return "No Data";
			
		return wordBank.get((int)(Math.random() * wordBank.size()));
	}

}
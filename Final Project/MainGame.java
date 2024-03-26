import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import java.util.*;
import java.io.*;

public class MainGame extends Application {
	@Override 
	public void start(Stage gameStage) {
		StackPane pane = new StackPane();

		Scene gameScene = new Scene(pane, 600, 400);
		gameStage.setTitle("Hangman");
		gameStage.setScene(gameScene);
		gameStage.show();
	}
	
public static String chooseWord(File file) throws Exception{
	Scanner textInput = new Scanner(file);
	Random r = new Random();
	int low = 0;
	int high = 25;
	int result = r.nextInt(high - low);
	ArrayList<String>[] wordBank = new ArrayList[24];
	
	for(int i = 0; i < 24; i++){
		wordBank[i].add(textInput.nextLine());
	}
	textInput.close();
	
	String chosenWord = wordBank[result].toString();
	
	return chosenWord;
	
	
	
}
}
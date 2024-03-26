import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.util.*;
import java.io.*;
import java.awt.*;

public class MainGame extends Application {
	@Override 
	public void start(Stage gameStage) {
		GridPane pane = new GridPane();
		int guessesLeft = 6;
		TextField tfGuess = new TextField("");
		TextField lt1 = new TextField("");
		TextField lt2 = new TextField("");
		TextField lt3 = new TextField("");
		TextField lt4 = new TextField("");
		TextField lt5 = new TextField("");
		TextField lt6 = new TextField("");
		TextField lt7 = new TextField("");
		lt1.setPrefWidth(25);
		lt2.setPrefWidth(25);
		lt3.setPrefWidth(25);
		lt4.setPrefWidth(25);
		lt5.setPrefWidth(25);
		lt6.setPrefWidth(25);
		lt7.setPrefWidth(25);
		tfGuess.setPrefWidth(25);
		Label lblGuess = new Label("Guess:");
		Label lblHP = new Label("Guesses Remaining: " + guessesLeft);
		pane.setAlignment(Pos.CENTER);
		pane.add(lt1, 5, 2);
		pane.add(lt2, 6, 2);
		pane.add(lt3, 7, 2);
		pane.add(lt4, 8, 2);
		pane.add(lt5, 9, 2);
		pane.add(lt6, 10, 2);
		pane.add(lt7, 11, 2);
		pane.add(lblGuess, 0, 3);
		pane.add(tfGuess, 1, 3);
		pane.add(lblHP, 1, 5);

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
	public static void startgame(){
		
	}
}
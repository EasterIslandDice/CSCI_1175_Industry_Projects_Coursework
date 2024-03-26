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
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.lang.*;
import java.util.*;

public class StartScreen extends Application {
	@Override
	public void start(Stage startScreenStage){
		
		//Buttons will pull from text files and create an array.
		Button btStart5 = new Button("Start 5-Letter Game");
		Button btStart6 = new Button("Start 6-Letter Game");
		Button btStart7 = new Button("Start 7-Letter Game");
		StackPane pane = new StackPane();
		HBox btBox = new HBox();
		btBox.getChildren().addAll(btStart5, btStart6, btStart7);
		pane.getChildren().addAll(btBox);
		btBox.setAlignment(Pos.BOTTOM_CENTER);
		
		Scanner textInput = new Scanner(System.in);
		
		//Placeholder methods
		btStart5.setOnAction(e -> MainGame.startGame(MainGame.chooseWord(FiveLetter.txt)));
		btStart6.setOnAction(e -> MainGame.startGame(MainGame.chooseWord(SixLetter.txt)));
		btStart7.setOnAction(e -> MainGame.startGame(MainGame.chooseWord(SevenLetter.txt)));
		
		Scene sceneStart = new Scene(pane, 800, 500);
		startScreenStage.setTitle("Hangman");
		startScreenStage.setScene(sceneStart);
		startScreenStage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}
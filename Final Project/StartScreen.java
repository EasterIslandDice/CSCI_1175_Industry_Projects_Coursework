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
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.util.*;

public class StartScreen extends Application {
	@Override
	public void start(Stage startScreenStage){
		
		Button btStart = new Button("Start Game");
		StackPane pane = new StackPane();
		pane.getChildren().add(btStart);
		Scene sceneStart = new Scene(pane, 400, 600);
		startScreenStage.setTitle("Hangman");
		startScreenStage.setScene(sceneStart);
		startScreenStage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}
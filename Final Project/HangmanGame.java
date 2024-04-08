import java.lang.Object;
import java.util.HashMap;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;




public class HangmanGame extends Application {
	
	private static final int wordPoints = 100;
	private SimpleStringProperty chosenWord = new SimpleStringProperty();
	private SimpleIntegerProperty lettersToGuess = new SimpleIntegerProperty();
	private SimpleIntegerProperty score = new SimpleIntegerProperty();
	private SimpleBooleanProperty canGuess = new SimpleBooleanProperty();
	private ObservableList<Node> letters;
	private HashMap<Character,Text> alphabet = new HashMap<Character, Text>();
	private HangmanImage hangman = new HangmanImage();
	WordImport words = new WordImport();
	
	public Parent GameLayout() {
		HBox rowLetters = new HBox();
		rowLetters.setAlignment(Pos.CENTER);
		letters = rowLetters.getChildren();
		
		canGuess.bind(hangman.lives.greaterThan(0).and(lettersToGuess.greaterThan(0)));
		canGuess.addListener((obs, old, newValue) -> {
			if(!newValue.booleanValue())
				StopGame();
		});
		
		Button btStart = new Button("New Game");
		btStart.disableProperty().bind(canGuess);
		btStart.setOnAction(event -> StartGame());
		
		HBox row1 = new HBox();
		HBox row2 = new HBox();
		row1.setAlignment(Pos.CENTER);
		row2.setAlignment(Pos.CENTER);
		for(int i = 0; i < 20; i++){
			row1.getChildren().add(new Letter(' '));
			row2.getChildren().add(new Letter(' '));
		}
		
		HBox alphabetGuess = new HBox(5);
		alphabetGuess.setAlignment(Pos.CENTER);
		for(char c = 'A'; c <= 'Z'; c++){
			Text t = new Text(String.valueOf(c));
			alphabet.put(c, t);
			alphabetGuess.getChildren().add(t);
		}
		Text hyphen = new Text("-");
		alphabet.put('-', hyphen);
		alphabetGuess.getChildren().add(hyphen);
		
		Text scoreDisplay = new Text();
		scoreDisplay.textProperty().bind(score.asString().concat(" Points"));
		
		HBox rowHangman = new HBox(10, btStart, scoreDisplay, hangman);
		rowHangman.setAlignment(Pos.CENTER);
		
		VBox fullGame = new VBox(10);
		fullGame.getChildren().addAll(row1, rowLetters, row2, alphabetGuess, rowHangman);
		return fullGame;
			
		}
		
		private void StopGame() {
			for(Node n : letters){
				Letter letter = (Letter) n;
				letter.Show();
			}
		}
		
		private void StartGame() {
			for(Text t : alphabet.values()){
				t.setStrikethrough(false);
				t.setFill(Color.BLACK);
			}
			
		hangman.reset();
		chosenWord.set(words.GetRandomWord().toUpperCase());
		lettersToGuess.set(chosenWord.length().get());
		
		letters.clear();
		for(char c : chosenWord.get().toCharArray()){
			letters.add(new Letter(c));
		}
		}
		
	
	private static class HangmanImage extends Parent{
		private static final int spineX = 100;
		private static final int spineStartY = 20;
		private static final int spineEndY = spineStartY + 50;
		private SimpleIntegerProperty lives = new SimpleIntegerProperty();
		
		public HangmanImage(){
			Circle head = new Circle(20);
			head.setTranslateX(spineX);
			
			Line spine = new Line();
			spine.setStartX(spineX);
			spine.setStartY(spineStartY);
			spine.setEndX(spineX);
			spine.setEndY(spineEndY);
			
			Line leftArm = new Line();
			leftArm.setStartX(spineX);
			leftArm.setStartY(spineStartY);
			leftArm.setEndX(spineX - 40);
			leftArm.setEndY(spineStartY - 20);
			
			Line rightArm = new Line();
			rightArm.setStartX(spineX);
			rightArm.setStartY(spineStartY);
			rightArm.setEndX(spineX + 40);
			rightArm.setEndY(spineStartY - 20);
			
			Line leftLeg = new Line();
			leftLeg.setStartX(spineX);
			leftLeg.setStartY(spineEndY);
			leftLeg.setEndX(spineX - 25);
			leftLeg.setEndY(spineEndY + 50);
			
			Line rightLeg = new Line();
			rightLeg.setStartX(spineX);
			rightLeg.setStartY(spineEndY);
			rightLeg.setEndX(spineX + 25);
			rightLeg.setEndY(spineEndY + 50);
			
			getChildren().addAll(head, spine, leftArm, rightArm, leftLeg, rightLeg);
			lives.set(getChildren().size());
		}
		
		public void reset() {
			getChildren().forEach(node -> node.setVisible(false));
			lives.set(getChildren().size());
		}
		
		public void WrongGuess() {
			for(Node n : getChildren()){
				if(!n.isVisible()){
					n.setVisible(true);
					lives.set(lives.get() - 1);
					break;
				}
			}
		}
	}
	private static class Letter extends StackPane {
		private Rectangle bg = new Rectangle(60, 60);
		private Text text;
		
		public Letter(char letter){
			bg.setFill(letter == ' ' ? Color.BLUE : Color.WHITE);
			bg.setStroke(Color.PURPLE);
			
			text = new Text(String.valueOf(letter).toUpperCase());
			text.setVisible(false);
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
			
		}
		
		public void Show() {
			RotateTransition rt = new RotateTransition(Duration.seconds(1), bg);
			rt.setAxis(Rotate.Y_AXIS);
			rt.setToAngle(180);
			rt.setOnFinished(event -> text.setVisible(true));
			rt.play();
		}
		
		
	
	
}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(GameLayout());
		scene.setOnKeyPressed((KeyEvent event) -> {
			if (event.getText().isEmpty())
				return;
			
			char pressed = event.getText().toUpperCase().charAt(0);
			if ((pressed < 'A' || pressed > 'Z') && pressed != '-')
				return;
			
			
			if (canGuess.get()) {
				Text t = alphabet.get(pressed);
				if(t.isStrikethrough())
					return;
			t.setFill(Color.BLUE);
			t.setStrikethrough(true);
			
			boolean found = false;
			
			for (Node n : letters){
				Letter letter = (Letter) n;
				if(letter.equals(pressed)){
					found = true;
					score.set(score.get() + wordPoints);
					lettersToGuess.set(lettersToGuess.get() - 1);
					letter.Show();
				}
			}
			
			if(!found){
				hangman.WrongGuess();
			}
		}
	});
	
	primaryStage.setResizable(false);
	primaryStage.setWidth(900);
	primaryStage.setHeight(500);
	primaryStage.setTitle("Hangman");
	primaryStage.setScene(scene);
	primaryStage.show();
	StartGame();
	}
}
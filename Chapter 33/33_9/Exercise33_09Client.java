import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Exercise33_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataInputStream fromServer;
  DataOutputStream toServer;
  
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);


try
    {
      Socket socket = new Socket("localhost", 8000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());
      
      new Thread(() ->
      {
        while (true)
        {
          try
          {
            String message = fromServer.readUTF();
            Platform.runLater( () -> taServer.appendText("\n" + message));
          }
          catch (Exception exception)
          {
            exception.printStackTrace();
          }
        }
      }).start();
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
    taClient.setOnKeyPressed(e ->
    {
      if (e.getCode() == KeyCode.ENTER)
      {
        try
        {
          toServer.writeUTF(taClient.getText().trim());
          toServer.flush();
          taClient.setText("");
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

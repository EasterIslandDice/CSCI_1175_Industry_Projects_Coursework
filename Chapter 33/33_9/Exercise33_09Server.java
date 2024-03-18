import javafx.application.Application;
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
import java.net.ServerSocket;
import java.net.Socket;

public class Exercise33_09Server extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  private DataInputStream fromClient;
  private DataOutputStream toClient;
  
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taClient.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    try
    {
      ServerSocket serverSocket = new ServerSocket(8000);
      Socket socket = serverSocket.accept();
      
      fromClient = new DataInputStream(socket.getInputStream());
      toClient = new DataOutputStream(socket.getOutputStream());
      
      // thread for receiving data
      new Thread(() ->
      {
        while (true)
        {
          try
          {
            String message = fromClient.readUTF();
            Platform.runLater( () -> taClient.appendText("\n" + message));
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
      System.exit(-1);
    }
    taServer.setOnKeyPressed(e ->
    {
      if (e.getCode() == KeyCode.ENTER)
      {
        try
        {
          toClient.writeUTF(taServer.getText().trim());
          toClient.flush();
          taServer.setText("");
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
      }
    });
    
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

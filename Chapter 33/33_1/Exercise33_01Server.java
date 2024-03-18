// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  public static final String NAME = "localhost";
  public static final int PORT = 8000;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    new Thread(() ->
        {
          try
          {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Platform.runLater(()
                -> ta.setText("Problem_01Server started at " + new Date()));
            Socket socket = serverSocket.accept();
            Platform.runLater(() ->
                ta.setText(ta.getText() + "\n"
                    + "Connected to client at " + new Date()));
            
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            
            while (true)
            {
              double annualInterestRate = fromClient.readDouble();
              double years = fromClient.readInt();
              double amount = fromClient.readDouble();
              
              double monthlyInterestRate = annualInterestRate / 1200;
              double monthlyPayment = amount * monthlyInterestRate / (1
                  - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
              double total = monthlyPayment * 12 * years;
              
              String output = "Annual Interest Rate: " + annualInterestRate;
              output += "\nNumber of Years: " + years;
              output += "\nLoan Amount: " + amount;
              output += "\nMonthly Payment: " + monthlyPayment;
              output += "\nTotal Payment: " + total;
              String finalOutput = output;
              Platform.runLater(() -> ta.setText(ta.getText()
                  + "\n" + finalOutput));
              
              toClient.writeDouble(total);
              toClient.flush();
              toClient.writeDouble(monthlyPayment);
              toClient.flush();
            }
          }
          catch (Exception exception)
          {
            exception.printStackTrace();
          }
        }).start();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

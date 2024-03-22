import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;


public class LoanCalc extends Application{
  
    @Override
    public void start(Stage stage){
        MenuBar bar = new MenuBar();

        Menu menuOperation = new Menu("Operation");
        MenuItem calculateItem = new MenuItem("Calculate");
        MenuItem exitItem = new MenuItem("Exit");
        menuOperation.getItems().addAll(calculateItem, exitItem);

        bar.getMenus().add(menuOperation);

        GridPane valuesContainer = new GridPane();
        valuesContainer.setVgap(5);

        Label investmentAmountLabel = new Label(String.format("%-30s", "Investment Amount: "));
        TextField investmentAmount = new TextField();
        investmentAmount.setPrefColumnCount(12);
        valuesContainer.add(investmentAmountLabel, 0, 0);
        valuesContainer.add(investmentAmount, 1, 0);

        Label numberOfYearsLabel = new Label(String.format("%-30s", "Number of Years: "));
        TextField numberOfYears = new TextField();
        numberOfYears.setPrefColumnCount(12);
        valuesContainer.add(numberOfYearsLabel, 0, 1);
        valuesContainer.add(numberOfYears, 1, 1);

        Label interestRateLabel = new Label(String.format("%-30s", "Annual Interest Rate: "));
        TextField interestRate = new TextField();
        interestRate.setPrefColumnCount(12);
        valuesContainer.add(interestRateLabel, 0, 2);
        valuesContainer.add(interestRate, 1, 2);


        Label futureValueLabel = new Label(String.format("%-30s", "Future Value: "));
        TextField futureValue = new TextField();
        futureValue.setPrefColumnCount(12);
        futureValue.setEditable(false);
        valuesContainer.add(futureValueLabel, 0, 3);
        valuesContainer.add(futureValue, 1, 3);

        Button calculate = new Button("Calculate");
        HBox calculateContainer = new HBox(calculate);
        calculateContainer.setAlignment(Pos.BOTTOM_RIGHT);


        VBox mainPane = new VBox(5, bar, valuesContainer, calculateContainer);

        Scene scene = new Scene(mainPane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Problem$17");
        stage.show();


        EventHandler<ActionEvent> calculateEvent = e -> {
        
          double amount; 
          double rate; 
          double years;
              
              try{
                  amount = Double.parseDouble(investmentAmount.getText().trim());
                  rate = Double.parseDouble(interestRate.getText().trim());
                  years = Double.parseDouble(numberOfYears.getText().trim());

                  double doubleFutureValue = amount * Math.pow(1 + rate/1200, years * 12);
                  futureValue.setText("$" + doubleFutureValue);
              }
              catch (Exception exception){
                
                  futureValue.setText("Please enter a valid value");
              }
        };

        calculate.setOnAction(calculateEvent);
        calculateItem.setOnAction(calculateEvent);

        exitItem.setOnAction(e -> System.exit(0));
    }
}
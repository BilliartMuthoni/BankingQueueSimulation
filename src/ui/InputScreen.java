package ui;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.SimulationConfig;

public class InputScreen {

    private final VBox root = new VBox(10);

    private RunnableWithConfig onRun;

    private final TextField numCustomers = new TextField("100");
    private final TextField arrivalLow = new TextField("1.0");
    private final TextField arrivalHigh = new TextField("8.0");
    private final TextField serviceLow = new TextField("1.0");
    private final TextField serviceHigh = new TextField("6.0");

    public InputScreen() {

        root.setPadding(new javafx.geometry.Insets(15));

        Label title = new Label("Banking Queue Simulation");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button runBtn = new Button("Run Simulation");
        Button resetBtn = new Button("Reset");

        runBtn.setOnAction(e -> runSimulation());
        resetBtn.setOnAction(e -> resetFields());

        root.getChildren().addAll(
                title,
                new Label("Number of Customers"), numCustomers,
                new Label("Arrival Lower Bound"), arrivalLow,
                new Label("Arrival Upper Bound"), arrivalHigh,
                new Label("Service Lower Bound"), serviceLow,
                new Label("Service Upper Bound"), serviceHigh,
                runBtn,
                resetBtn
        );
    }

    private void runSimulation() {

        try{

            SimulationConfig config = new SimulationConfig(
                    Double.parseDouble(arrivalLow.getText()),
                    Double.parseDouble(arrivalHigh.getText()),
                    Double.parseDouble(serviceLow.getText()),
                    Double.parseDouble(serviceHigh.getText()),
                    Integer.parseInt(numCustomers.getText())
            );

            if (onRun != null) {
                onRun.run(config);
            }
        }

        catch (NumberFormatException e) {
            showError("Invalid Input", "All fields must be valid numbers.\nCustomers must be a whole number.");
        } catch (IllegalArgumentException e) {
            showError("Invalid Configuration", e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetFields() {
        numCustomers.setText("100");
        arrivalLow.setText("1.0");
        arrivalHigh.setText("8.0");
        serviceLow.setText("1.0");
        serviceHigh.setText("6.0");
    }

    public Node getView() {
        return root;
    }

    public void setOnRun(RunnableWithConfig onRun) {
        this.onRun = onRun;
    }

    public interface RunnableWithConfig {
        void run(SimulationConfig config);
    }
}
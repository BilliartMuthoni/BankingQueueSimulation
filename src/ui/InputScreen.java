package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.SimulationConfig;
import javafx.scene.control.ScrollPane;

public class InputScreen {

    private static final String FIELD_STYLE =
            "-fx-background-radius: 5; -fx-border-color: #cfd8dc;" +
            "-fx-border-radius: 5; -fx-padding: 6;";

    private static final String CARD_STYLE =
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-radius: 8;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 2);";

    private final VBox root = new VBox(16);
    private RunnableWithConfig onRun;

    private final TextField numCustomers = new TextField("100");
    private final TextField arrivalLow   = new TextField("1.0");
    private final TextField arrivalHigh  = new TextField("8.0");
    private final TextField serviceLow   = new TextField("1.0");
    private final TextField serviceHigh  = new TextField("6.0");

    public InputScreen() {
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f7fa;");

        Label title = new Label("Banking Queue Simulation");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1a237e;");

        Label subtitle = new Label("Configure simulation parameters below");
        subtitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        VBox arrivalCard = buildCard("ARRIVAL DISTRIBUTION  (Uniform)",
                "Inter-Arrival Lower Bound (min)", arrivalLow,
                "Inter-Arrival Upper Bound (min)", arrivalHigh);

        VBox serviceCard = buildCard("SERVICE DISTRIBUTION  (Uniform)",
                "Service Lower Bound (min)", serviceLow,
                "Service Upper Bound (min)", serviceHigh);

        VBox customersCard = buildCard("SIMULATION SETTINGS",
                "Number of Customers", numCustomers,
                null, null);

        Button runBtn   = new Button("▶  Run Simulation");
        Button resetBtn = new Button("↺  Reset");

        runBtn.setStyle(
                "-fx-background-color: #1565c0; -fx-text-fill: white;" +
                "-fx-font-size: 13px; -fx-font-weight: bold;" +
                "-fx-background-radius: 6; -fx-padding: 9 24 9 24; -fx-cursor: hand;");

        resetBtn.setStyle(
                "-fx-background-color: #e0e0e0; -fx-text-fill: #333;" +
                "-fx-font-size: 13px; -fx-background-radius: 6;" +
                "-fx-padding: 9 20 9 20; -fx-cursor: hand;");

        runBtn.setOnAction(e -> runSimulation());
        resetBtn.setOnAction(e -> resetFields());

        HBox buttons = new HBox(10, runBtn, resetBtn);
        buttons.setAlignment(Pos.CENTER_LEFT);

        styleField(numCustomers);
        styleField(arrivalLow);
        styleField(arrivalHigh);
        styleField(serviceLow);
        styleField(serviceHigh);

        root.getChildren().addAll(title, subtitle, arrivalCard, serviceCard, customersCard, buttons);
    }

    private VBox buildCard(String heading, String label1, TextField field1,
                           String label2, TextField field2) {
        Label header = new Label(heading);
        header.setStyle(
                "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: white;" +
                "-fx-background-color: #1565c0; -fx-background-radius: 6 6 0 0;" +
                "-fx-padding: 6 10 6 10;");
        header.setMaxWidth(Double.MAX_VALUE);

        VBox body = new VBox(8);
        body.setPadding(new Insets(12, 14, 14, 14));

        body.getChildren().addAll(new Label(label1), field1);
        if (label2 != null && field2 != null) {
            body.getChildren().addAll(new Label(label2), field2);
        }

        for (Node n : body.getChildren()) {
            if (n instanceof Label) {
                ((Label) n).setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
            }
        }

        VBox card = new VBox(header, body);
        card.setStyle(CARD_STYLE);
        return card;
    }

    private void styleField(TextField field) {
        field.setStyle(FIELD_STYLE);
        field.setMaxWidth(Double.MAX_VALUE);
    }

    private void runSimulation() {
        try {
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
        } catch (NumberFormatException e) {
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
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #f5f7fa; -fx-background: #f5f7fa;");
        return scroll;
    }

    public void setOnRun(RunnableWithConfig onRun) {
        this.onRun = onRun;
    }

    public interface RunnableWithConfig {
        void run(SimulationConfig config);
    }
}

package ui;

import export.CsvExporter;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import model.Customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IATTableScreen {

    private final TableView<Customer> table = new TableView<>();
    private final Label countLabel = new Label("No simulation run yet.");
    private final Button exportBtn = new Button("Export to CSV (Excel)");
    private final VBox root;

    private List<Customer> lastCustomers = new ArrayList<>();

    public IATTableScreen() {

        buildColumns();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Run simulation to view results."));
        table.setStyle("-fx-background-color: white;");

        exportBtn.setDisable(true);
        exportBtn.setStyle(
                "-fx-background-color: #2e7d32; -fx-text-fill: white;" +
                "-fx-font-weight: bold; -fx-background-radius: 6;" +
                "-fx-padding: 6 14 6 14; -fx-cursor: hand;");
        exportBtn.setOnAction(e -> exportCsv());

        Label title = new Label("IAT Simulation Table");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1a237e;");

        countLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

        HBox toolbar = new HBox(10, countLabel, exportBtn);
        toolbar.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(countLabel, Priority.ALWAYS);

        root = new VBox(10, title, toolbar, table);
        root.setPadding(new Insets(16));
        root.setStyle("-fx-background-color: #f5f7fa;");
        VBox.setVgrow(table, Priority.ALWAYS);
    }

    private void buildColumns() {

        TableColumn<Customer, Number> c1 = new TableColumn<>("Customer No");
        c1.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(
                d.getValue().getCustomerNumber()));

        TableColumn<Customer, Number> c2 = new TableColumn<>("IAT");
        c2.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getInterArrivalTime()));

        TableColumn<Customer, Number> c3 = new TableColumn<>("Arrival Time");
        c3.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getArrivalTime()));

        TableColumn<Customer, Number> c4 = new TableColumn<>("Service Time");
        c4.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getServiceTime()));

        TableColumn<Customer, Number> c5 = new TableColumn<>("Start Time");
        c5.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getServiceStartTime()));

        TableColumn<Customer, Number> c6 = new TableColumn<>("End Time");
        c6.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getServiceEndTime()));

        TableColumn<Customer, Number> c7 = new TableColumn<>("Waiting Time");
        c7.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getWaitingTime()));

        TableColumn<Customer, Number> c8 = new TableColumn<>("Time in System");
        c8.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getTimeInSystem()));

        TableColumn<Customer, Number> c9 = new TableColumn<>("No. in Queue");
        c9.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(
                d.getValue().getNumberInQueue()));

        TableColumn<Customer, Number> c10 = new TableColumn<>("No. in System");
        c10.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(
                d.getValue().getNumberInSystem()));

        TableColumn<Customer, Number> c11 = new TableColumn<>("Server Idle Time");
        c11.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(
                d.getValue().getServerIdleTime()));

        formatDouble(c2); formatDouble(c3); formatDouble(c4);
        formatDouble(c5); formatDouble(c6); formatDouble(c7);
        formatDouble(c8); formatDouble(c11);

        table.getColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11);
    }

    private void formatDouble(TableColumn<Customer, Number> col) {
        col.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Number value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format("%.4f", value.doubleValue()));
            }
        });
    }

    private void exportCsv() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save CSV File");
        chooser.setInitialFileName("simulation_results.csv");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = chooser.showSaveDialog(root.getScene().getWindow());
        if (file == null) return;

        try {
            CsvExporter.export(lastCustomers, file.getAbsolutePath());
            showAlert(Alert.AlertType.INFORMATION, "Export Successful",
                    "Results saved to:\n" + file.getAbsolutePath());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Export Failed", ex.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void populate(List<Customer> customers) {
        lastCustomers = customers;
        table.setItems(FXCollections.observableArrayList(customers));
        countLabel.setText("Total Customers: " + customers.size());
        exportBtn.setDisable(false);
    }

    public Node getView() {
        return root;
    }
}

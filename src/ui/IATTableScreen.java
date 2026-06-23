package ui;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Customer;

import java.util.List;

public class IATTableScreen {

    private final TableView<Customer> table = new TableView<>();
    private final Label countLabel = new Label("No simulation run yet.");
    private final VBox root;

    public IATTableScreen() {

        buildColumns();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Run simulation to view results."));

        root = new VBox(10, countLabel, table);
        root.setPadding(new javafx.geometry.Insets(12));
        VBox.setVgrow(table, javafx.scene.layout.Priority.ALWAYS);
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

        formatDouble(c2);
        formatDouble(c3);
        formatDouble(c4);
        formatDouble(c5);
        formatDouble(c6);
        formatDouble(c7);
        formatDouble(c8);
        formatDouble(c11);

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

    public void populate(List<Customer> customers) {
        table.setItems(FXCollections.observableArrayList(customers));
        countLabel.setText("Customers: " + customers.size());
    }

    public Node getView() {
        return root;
    }
}
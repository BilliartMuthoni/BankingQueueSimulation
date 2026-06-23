package ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import statistics.QueueStatistics;

public class StatisticsScreen {

    private final VBox root;
    private final GridPane grid = new GridPane();
    private final Label placeholder = new Label("Run the simulation to see statistics.");

    public StatisticsScreen() {
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(12));

        Label title = new Label("Queue Statistics");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        root = new VBox(12, title, placeholder, grid);
        root.setPadding(new Insets(15));
    }

    public void populate(QueueStatistics stats) {
        grid.getChildren().clear();
        placeholder.setVisible(false);

        addRow(0, "Average Waiting Time",   fmt(stats.getAvgWaitingTime()));
        addRow(1, "Average Service Time",   fmt(stats.getAvgServiceTime()));
        addRow(2, "Average Time in System", fmt(stats.getAvgTimeInSystem()));
        addRow(3, "Server Utilization",     fmt(stats.getServerUtilization()));
        addRow(4, "Server Idle Time",       fmt(stats.getServerIdleTime()));
    }

    private void addRow(int row, String label, String value) {
        Label lbl = new Label(label + ":");
        lbl.setStyle("-fx-font-weight: bold;");
        grid.add(lbl, 0, row);
        grid.add(new Label(value), 1, row);
    }

    private String fmt(double v) {
        return String.format("%.4f", v);
    }

    public Node getView() {
        return root;
    }
}

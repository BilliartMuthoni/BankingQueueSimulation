package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import statistics.QueueStatistics;

public class StatisticsScreen {

    private static final String CARD_STYLE =
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-radius: 8;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 2);";

    private static final String HEADER_STYLE =
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6 10 6 10;" +
            "-fx-background-radius: 6 6 0 0;";

    private final VBox root;
    private final VBox content = new VBox(14);
    private final Label placeholder = new Label("Run the simulation to see statistics.");

    public StatisticsScreen() {
        placeholder.setStyle("-fx-font-size: 14px; -fx-text-fill: #888;");

        content.setVisible(false);

        Label title = new Label("Queue Statistics");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1a237e;");

        root = new VBox(16, title, placeholder, content);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f7fa;");
    }

    public void populate(QueueStatistics stats) {
        placeholder.setVisible(false);
        content.getChildren().clear();

        content.getChildren().addAll(
            buildCard("TIME AVERAGES", "#1565c0",
                row("Avg Waiting Time in Queue",       fmt(stats.getAvgWaitingTime()) + " min"),
                row("Avg Service Time",                fmt(stats.getAvgServiceTime()) + " min"),
                row("Avg Time in System",              fmt(stats.getAvgTimeInSystem()) + " min"),
                row("Avg Time Between Arrivals",       fmt(stats.getAvgTimeBetweenArrivals()) + " min"),
                row("Avg Wait (Customers Who Waited)", fmt(stats.getAvgWaitingTimeForWaiters()) + " min")
            ),
            buildCard("SERVER PERFORMANCE", "#2e7d32",
                row("Server Utilization (P busy)",     fmt(stats.getServerUtilization())),
                row("Proportion Server Idle",          fmt(stats.getProportionServerIdle())),
                row("Total Server Idle Time",          fmt(stats.getServerIdleTime()) + " min")
            ),
            buildCard("PROBABILITY METRICS", "#6a1b9a",
                row("P(Customer Has to Wait)",         fmt(stats.getProbabilityCustomerWaits())),
                row("P(Server is Busy)",               fmt(stats.getServerUtilization()))
            )
        );

        content.setVisible(true);
    }

    private VBox buildCard(String heading, String color, Node... rows) {
        Label header = new Label(heading);
        header.setStyle(HEADER_STYLE + "-fx-background-color: " + color + ";");
        header.setMaxWidth(Double.MAX_VALUE);

        VBox body = new VBox(8);
        body.setPadding(new Insets(10, 14, 14, 14));
        body.getChildren().addAll(rows);

        VBox card = new VBox(header, body);
        card.setStyle(CARD_STYLE);
        return card;
    }

    private HBox row(String label, String value) {
        Label lbl = new Label(label);
        lbl.setStyle("-fx-font-size: 13px; -fx-text-fill: #37474f;");
        lbl.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(lbl, Priority.ALWAYS);

        Label val = new Label(value);
        val.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #1a237e;");

        HBox row = new HBox(lbl, val);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(4, 0, 4, 0));
        row.setStyle("-fx-border-color: transparent transparent #eeeeee transparent;");
        return row;
    }

    private String fmt(double v) {
        return String.format("%.4f", v);
    }

    public Node getView() {
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #f5f7fa; -fx-background: #f5f7fa;");
        return scroll;
    }
}

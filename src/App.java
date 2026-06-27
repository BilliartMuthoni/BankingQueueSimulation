import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import model.SimulationConfig;
import simulation.BankingSimulation;
import statistics.QueueStatistics;
import ui.IATTableScreen;
import ui.InputScreen;
import ui.StatisticsScreen;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        IATTableScreen tableScreen = new IATTableScreen();
        StatisticsScreen statsScreen = new StatisticsScreen();
        InputScreen inputScreen = new InputScreen();

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 120px;");

        Tab inputTab = new Tab("Configuration", inputScreen.getView());
        Tab tableTab = new Tab("IAT Table",     tableScreen.getView());
        Tab statsTab = new Tab("Statistics",    statsScreen.getView());

        inputTab.setClosable(false);
        tableTab.setClosable(false);
        statsTab.setClosable(false);

        inputScreen.setOnRun(config -> runSimulation(config, tableScreen, statsScreen, tabPane, tableTab));

        tabPane.getTabs().addAll(inputTab, tableTab, statsTab);

        Scene scene = new Scene(tabPane, 1000, 680);
        primaryStage.setTitle("Banking Queue Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runSimulation(SimulationConfig config,
                               IATTableScreen tableScreen,
                               StatisticsScreen statsScreen,
                               TabPane tabPane,
                               Tab tableTab) {

        BankingSimulation simulation = new BankingSimulation(config);
        simulation.run();

        var customers = simulation.getCustomers();
        var server    = simulation.getServer();

        QueueStatistics stats = new QueueStatistics(customers, server);

        tableScreen.populate(customers);
        statsScreen.populate(stats);

        tabPane.getSelectionModel().select(tableTab);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

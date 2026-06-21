import model.SimulationConfig;
import statistics.QueueStatistics;
import simulation.BankingSimulation;
import ui.InputScreen;
import ui.IATTableScreen;
import ui.StatisticsScreen;

public class App {

    public static void main(String[] args) {

        InputScreen inputScreen = new InputScreen();
        inputScreen.show();

        SimulationConfig config = inputScreen.getConfig();

        if (config == null) {
            System.out.println("No configuration provided. Exiting...");
            return;
        }

        BankingSimulation simulation = new BankingSimulation(config);
        simulation.run();

        var customers = simulation.getCustomers();
        var server = simulation.getServer();

        QueueStatistics stats = new QueueStatistics(customers, server);

        IATTableScreen tableScreen = new IATTableScreen(customers);
        tableScreen.show();

        StatisticsScreen statisticsScreen = new StatisticsScreen(stats);
        statisticsScreen.show();
    }
}
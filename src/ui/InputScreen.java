package ui;

import model.SimulationConfig;

import java.util.Scanner;

public class InputScreen extends Screen {

    private SimulationConfig config;

    public InputScreen() {
        super("BANKING QUEUE SIMULATION INPUT SCREEN");
    }

    @Override
    protected void display() {

        Scanner sc = new Scanner(System.in);

        int numCustomers = readInt(sc,
                "Number of customers [100]: ", 100);

        double arrivalLow = readDouble(sc,
                "Arrival lower bound [1.0]: ", 1.0);

        double arrivalHigh = readDouble(sc,
                "Arrival upper bound [8.0]: ", 8.0);

        double serviceLow = readDouble(sc,
                "Service lower bound [1.0]: ", 1.0);

        double serviceHigh = readDouble(sc,
                "Service upper bound [6.0]: ", 6.0);

        config = new SimulationConfig(
                arrivalLow,
                arrivalHigh,
                serviceLow,
                serviceHigh,
                numCustomers,
                "FIFO"
        );

        System.out.println("Input accepted. Simulation ready.");
    }

    private int readInt(Scanner sc, String prompt, int defaultVal) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();

        if (input.isEmpty()) return defaultVal;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private double readDouble(Scanner sc, String prompt, double defaultVal) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();

        if (input.isEmpty()) return defaultVal;

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public SimulationConfig getConfig() {
        return config;
    }
}
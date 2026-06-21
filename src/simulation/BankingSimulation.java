package simulation;

import distribution.Distribution;
import distribution.UniformDistribution;

import model.Customer;
import model.Server;
import model.SimulationConfig;

import java.util.ArrayList;
import java.util.List;

public class BankingSimulation {

    private final SimulationConfig config;

    private final Distribution arrivalDist;
    private final Distribution serviceDist;

    private final Server server;
    private final List<Customer> customers;

    public BankingSimulation(SimulationConfig config) {

        this.config = config;

        // BUILD DISTRIBUTIONS FROM CONFIG
        this.arrivalDist = new UniformDistribution(
                config.getArrivalLowerBound(),
                config.getArrivalUpperBound()
        );

        this.serviceDist = new UniformDistribution(
                config.getServiceLowerBound(),
                config.getServiceUpperBound()
        );

        this.server = new Server("Teller-1");
        this.customers = new ArrayList<>();
    }

    public void run() {

        double arrivalTime = 0.0;

        for (int i = 1; i <= config.getNumberOfCustomers(); i++) {

            Customer c = new Customer(i);

            // INTER-ARRIVAL TIME
            double iat = arrivalDist.generate();
            c.setInterArrivalTime(iat);

            arrivalTime += iat;
            c.setArrivalTime(arrivalTime);

            // SERVICE TIME
            double serviceTime = serviceDist.generate();
            c.setServiceTime(serviceTime);

            // SERVER PROCESSING (FIFO LOGIC)
            server.serve(c, arrivalTime);

            customers.add(c);
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Server getServer() {
        return server;
    }

    public SimulationConfig getConfig() {
        return config;
    }
}
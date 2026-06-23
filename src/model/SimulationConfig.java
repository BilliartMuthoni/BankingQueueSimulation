package model;

public class SimulationConfig {

    // Arrival Distribution
    private double arrivalLowerBound;
    private double arrivalUpperBound;

    // Service Distribution
    private double serviceLowerBound;
    private double serviceUpperBound;

    // Simulation settings
    private int numberOfCustomers;

    public SimulationConfig(double arrivalLowerBound,
                            double arrivalUpperBound,
                            double serviceLowerBound,
                            double serviceUpperBound,
                            int numberOfCustomers) {

        if (arrivalLowerBound >= arrivalUpperBound)
            throw new IllegalArgumentException("Arrival lower bound must be < upper bound");

        if (serviceLowerBound >= serviceUpperBound)
            throw new IllegalArgumentException("Service lower bound must be < upper bound");

        if (numberOfCustomers <= 0)
            throw new IllegalArgumentException("Number of customers must be > 0");

        this.arrivalLowerBound = arrivalLowerBound;
        this.arrivalUpperBound = arrivalUpperBound;

        this.serviceLowerBound = serviceLowerBound;
        this.serviceUpperBound = serviceUpperBound;

        this.numberOfCustomers = numberOfCustomers;
    }

    public double getArrivalLowerBound() {
        return arrivalLowerBound;
    }

    public double getArrivalUpperBound() {
        return arrivalUpperBound;
    }

    public double getServiceLowerBound() {
        return serviceLowerBound;
    }

    public double getServiceUpperBound() {
        return serviceUpperBound;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

}
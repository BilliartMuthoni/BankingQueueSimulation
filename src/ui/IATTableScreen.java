package ui;

import model.Customer;

import java.util.List;

public class IATTableScreen extends Screen {

    private final List<Customer> customers;

    public IATTableScreen(List<Customer> customers) {
        super("INTER-ARRIVAL / EVENT TABLE");
        this.customers = customers;
    }

    @Override
    protected void display() {

        System.out.printf("%-6s %-10s %-12s %-12s %-14s %-14s %-12s %-12s%n",
                "ID", "IAT", "Arrival", "Service",
                "Start", "End", "Wait", "System");

        for (Customer c : customers) {

            System.out.printf("%-6d %-10s %-12s %-12s %-14s %-14s %-12s %-12s%n",
                    c.getCustomerNumber(),
                    fmt(c.getInterArrivalTime()),
                    fmt(c.getArrivalTime()),
                    fmt(c.getServiceTime()),
                    fmt(c.getServiceStartTime()),
                    fmt(c.getServiceEndTime()),
                    fmt(c.getWaitingTime()),
                    fmt(c.getTimeInSystem())
            );
        }

        System.out.println();
        System.out.println("Total customers: " + customers.size());
    }
}
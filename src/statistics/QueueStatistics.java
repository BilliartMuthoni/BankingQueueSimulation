package statistics;

import java.util.List;
import model.Customer;
import model.Server;

public class QueueStatistics {

    private double avgWaitingTime;
    private double avgServiceTime;
    private double avgTimeInSystem;
    private double serverUtilization;
    private double serverIdleTime;

    public QueueStatistics(List<Customer> customers, Server server) {

        if (customers.isEmpty()) {
            throw new IllegalArgumentException("Customer list is empty");
        }

        double totalWaiting = 0;
        double totalService = 0;
        double totalSystem = 0;

        for (Customer c : customers) {
            totalWaiting += c.getWaitingTime();
            totalService += c.getServiceTime();
            totalSystem += c.getTimeInSystem();
        }

        int n = customers.size();

        avgWaitingTime = totalWaiting / n;
        avgServiceTime = totalService / n;
        avgTimeInSystem = totalSystem / n;

        serverIdleTime = server.getTotalIdleTime();

        serverUtilization = computeUtilization(customers, server);
    }

    private double computeUtilization(List<Customer> customers, Server server) {

        double firstArrival = customers.get(0).getArrivalTime();
        double lastDeparture = 0;

        for (Customer c : customers) {
            lastDeparture = Math.max(lastDeparture, c.getServiceEndTime());
        }

        double totalTime = lastDeparture - firstArrival;

        double idleTime = server.getTotalIdleTime();

        if (totalTime <= 0) {
            return 0;
        }

        return 1 - (idleTime / totalTime);
    }

    public double getAvgWaitingTime() { 
        return avgWaitingTime; 
    }

    public double getAvgServiceTime() { 
        return avgServiceTime; 
    }

    public double getAvgTimeInSystem() { 
        return avgTimeInSystem; 
    }

    public double getServerUtilization() { 
        return serverUtilization; 
    }

    public double getServerIdleTime() { 
        return serverIdleTime; 
    }
}
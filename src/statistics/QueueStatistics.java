package statistics;

import java.util.List;
import model.Customer;
import model.Server;

public class QueueStatistics {

    private double avgWaitingTime;
    private double avgServiceTime;
    private double avgTimeInSystem;
    private double avgTimeBetweenArrivals;
    private double avgWaitingTimeForWaiters;

    private double serverUtilization;
    private double proportionServerIdle;
    private double serverIdleTime;

    private double probabilityCustomerWaits;

    public QueueStatistics(List<Customer> customers, Server server) {

        if (customers.isEmpty()) {
            throw new IllegalArgumentException("Customer list is empty");
        }

        int n = customers.size();

        double totalWaiting = 0;
        double totalService = 0;
        double totalSystem = 0;
        double totalWaitForWaiters = 0;
        int customersWhoWaited = 0;

        for (Customer c : customers) {
            totalWaiting += c.getWaitingTime();
            totalService += c.getServiceTime();
            totalSystem += c.getTimeInSystem();

            if (c.getWaitingTime() > 0) {
                customersWhoWaited++;
                totalWaitForWaiters += c.getWaitingTime();
            }
        }

        avgWaitingTime   = totalWaiting / n;
        avgServiceTime   = totalService / n;
        avgTimeInSystem  = totalSystem / n;

        probabilityCustomerWaits = (double) customersWhoWaited / n;

        avgWaitingTimeForWaiters = customersWhoWaited > 0
                ? totalWaitForWaiters / customersWhoWaited
                : 0;

        // average time between arrivals = (last arrival - first arrival) / (n - 1)
        double firstArrival = customers.get(0).getArrivalTime();
        double lastArrival  = customers.get(n - 1).getArrivalTime();
        avgTimeBetweenArrivals = n > 1 ? (lastArrival - firstArrival) / (n - 1) : 0;

        double lastDeparture = 0;
        for (Customer c : customers) {
            lastDeparture = Math.max(lastDeparture, c.getServiceEndTime());
        }

        serverIdleTime       = server.getTotalIdleTime();
        proportionServerIdle = lastDeparture > 0 ? serverIdleTime / lastDeparture : 0;
        serverUtilization    = 1 - proportionServerIdle;
    }

    public double getAvgWaitingTime()          { return avgWaitingTime; }
    public double getAvgServiceTime()          { return avgServiceTime; }
    public double getAvgTimeInSystem()         { return avgTimeInSystem; }
    public double getAvgTimeBetweenArrivals()  { return avgTimeBetweenArrivals; }
    public double getAvgWaitingTimeForWaiters(){ return avgWaitingTimeForWaiters; }
    public double getServerUtilization()       { return serverUtilization; }
    public double getProportionServerIdle()    { return proportionServerIdle; }
    public double getServerIdleTime()          { return serverIdleTime; }
    public double getProbabilityCustomerWaits(){ return probabilityCustomerWaits; }
}

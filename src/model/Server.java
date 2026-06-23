package model;

public class Server {

    private String name;
    private double nextAvailableTime = 0.0;
    private double totalIdleTime = 0.0;

    public Server(String name) {
        this.name = name;
    }

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    public double getTotalIdleTime() {
        return totalIdleTime;
    }

    public String getName() {
        return name;
    }

    public double serve(Customer c, double arrivalTime) {

        double idleTime = 0;
        if (arrivalTime > nextAvailableTime) {
            idleTime = arrivalTime - nextAvailableTime;
            totalIdleTime += idleTime;
        }

        double serviceStart = Math.max(arrivalTime, nextAvailableTime);
        double waitingTime = serviceStart - arrivalTime;
        double serviceEnd = serviceStart + c.getServiceTime();

        nextAvailableTime = serviceEnd;

        c.setServiceStartTime(serviceStart);
        c.setWaitingTime(waitingTime);
        c.setServiceEndTime(serviceEnd);
        c.setTimeInSystem(waitingTime + c.getServiceTime());
        c.setServerIdleTime(idleTime);

        return serviceEnd;
    }
}
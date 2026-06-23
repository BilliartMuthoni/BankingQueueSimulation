package model;

public class Customer {

    private int customerNumber;

    private double interArrivalTime;
    private double arrivalTime;
    private double serviceTime;

    private double serviceStartTime;
    private double serviceEndTime;

    private double waitingTime;
    private double timeInSystem;

    private int numberInQueue;
    private int numberInSystem;
    private double serverIdleTime;

    public Customer(int customerNumber) {
        if (customerNumber <= 0) {
            throw new IllegalArgumentException("Customer number must be positive");
        }
        this.customerNumber = customerNumber;
    }

    // setters
    public void setInterArrivalTime(double v) { 
        this.interArrivalTime = v; 
    }

    public void setArrivalTime(double v) { 
        this.arrivalTime = v; 
    
    }
    public void setServiceTime(double v) { 
        this.serviceTime = v; 
    }

    public void setServiceStartTime(double v) { 
        this.serviceStartTime = v; 
    }

    public void setServiceEndTime(double v) { 
        this.serviceEndTime = v; 
    }

    public void setWaitingTime(double v) { 
        this.waitingTime = v; 
    }

    public void setTimeInSystem(double v) {
        this.timeInSystem = v;
    }

    public void setNumberInQueue(int v) {
        this.numberInQueue = v;
    }

    public void setNumberInSystem(int v) {
        this.numberInSystem = v;
    }

    public void setServerIdleTime(double v) {
        this.serverIdleTime = v;
    }

    // getters
    public int getCustomerNumber() { 
        return customerNumber; 
    }

    public double getInterArrivalTime() { 
        return interArrivalTime; 
    }

    public double getArrivalTime() { 
        return arrivalTime; 
    }

    public double getServiceTime() { 
        return serviceTime; 
    }

    public double getServiceStartTime() { 
        return serviceStartTime; 
    }

    public double getServiceEndTime() { 
        return serviceEndTime; 
    }

    public double getWaitingTime() { 
        return waitingTime; 
    }

    public double getTimeInSystem() {
        return timeInSystem;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public int getNumberInSystem() {
        return numberInSystem;
    }

    public double getServerIdleTime() {
        return serverIdleTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "number=" + customerNumber +
                ", arrival=" + arrivalTime +
                ", service=" + serviceTime +
                ", wait=" + waitingTime +
                ", system=" + timeInSystem +
                '}';
    }
}
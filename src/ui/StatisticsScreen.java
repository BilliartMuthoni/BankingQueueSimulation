package ui;

import statistics.QueueStatistics;

public class StatisticsScreen extends Screen {

    private final QueueStatistics stats;

    public StatisticsScreen(QueueStatistics stats) {
        super("QUEUE STATISTICS");
        this.stats = stats;
    }

    @Override
    protected void display() {

        System.out.println("Average Waiting Time: " + fmt(stats.getAvgWaitingTime()));
        System.out.println("Average Service Time: " + fmt(stats.getAvgServiceTime()));
        System.out.println("Average Time in System: " + fmt(stats.getAvgTimeInSystem()));
        System.out.println("Server Utilization: " + fmt(stats.getServerUtilization()));
        System.out.println("Server Idle Time: " + fmt(stats.getServerIdleTime()));
    }
}
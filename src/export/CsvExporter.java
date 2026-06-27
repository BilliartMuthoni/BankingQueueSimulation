package export;

import model.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter {

    public static void export(List<Customer> customers, String filePath) throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            pw.println("Customer No,IAT,Arrival Time,Service Time,Start Time," +
                       "End Time,Waiting Time,Time in System," +
                       "No. in Queue,No. in System,Server Idle Time");

            for (Customer c : customers) {
                pw.printf("%d,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%d,%d,%.4f%n",
                        c.getCustomerNumber(),
                        c.getInterArrivalTime(),
                        c.getArrivalTime(),
                        c.getServiceTime(),
                        c.getServiceStartTime(),
                        c.getServiceEndTime(),
                        c.getWaitingTime(),
                        c.getTimeInSystem(),
                        c.getNumberInQueue(),
                        c.getNumberInSystem(),
                        c.getServerIdleTime()
                );
            }
        }
    }
}

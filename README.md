Banking Queue Simulation

ICS 4106 — Computer Simulation and Modeling  
Simulate a banking system where inter-arrival times are uniformly distributed on (1, 8) minutes and service times are uniformly distributed on (1, 6) minutes, for 100 customers.


Features

Configurable input screen for arrival and service distribution bounds
Full IAT simulation table with all queue metrics per customer
Queue statistics including averages, probabilities, and server utilization
Export results to CSV (opens in Excel)


How to Run

Open the project in IntelliJ IDEA
Ensure JavaFX SDK 21 is configured in Project Structure
Run App.java


Project Structure

src/
  App.java                  - Entry point
  model/                    - Customer, Server, SimulationConfig
  simulation/               - BankingSimulation (core logic)
  distribution/             - UniformDistribution
  statistics/               - QueueStatistics
  ui/                       - InputScreen, IATTableScreen, StatisticsScreen
  export/                   - CsvExporter


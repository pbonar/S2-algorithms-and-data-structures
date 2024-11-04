import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Processor {
    private double load;
    private static final Random random = new Random();

    public Processor() {
        this.load = 0.0;
    }

    public double getLoad() {
        return load;
    }

    public void addLoad(double taskLoad) {
        load += taskLoad;
    }

    public void resetLoad() {
        load = 0.0;
    }

    public boolean isOverloaded(double threshold) {
        return load > threshold;
    }

    public boolean isUnderThreshold(double threshold) {
        return load < threshold;
    }

    public boolean sendTaskTo(Processor processor, double threshold) {
        if (processor.isUnderThreshold(threshold)) {
            processor.addLoad(load);
            resetLoad();
            return true;
        }
        return false;
    }

    public boolean requestTaskFrom(Processor processor, double threshold) {
        if (processor.isOverloaded(threshold)) {
            return sendTaskTo(processor, threshold);
        }
        return false;
    }
}

class LoadBalancingSimulation {
    private final List<Processor> processors;
    private final double p;
    private final double r;
    private final double z;
    private int numQueries;
    private int numMigrations;
    Random random = new Random();

    public LoadBalancingSimulation(int numProcessors, double p, double r, double z) {
        this.p = p;
        this.r = r;
        this.z = z;
        this.numQueries = 0;
        this.numMigrations = 0;

        processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor());
        }
    }

    private double calculateAverageLoad() {
        double totalLoad = 0.0;
        for (Processor processor : processors) {
            totalLoad += processor.getLoad();
        }
        return totalLoad / processors.size();
    }

    private double calculateLoadDeviation(double averageLoad) {
        double deviationSum = 0.0;
        for (Processor processor : processors) {
            double deviation = processor.getLoad() - averageLoad;
            deviationSum += deviation * deviation;
        }
        return Math.sqrt(deviationSum / processors.size());
    }

    private Processor getRandomProcessor() {
        int randomIndex = random.nextInt(processors.size());
        return processors.get(randomIndex);
    }

    public void runSimulation(int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            Processor processorX = getRandomProcessor();
            double taskLoad = random.nextDouble() * z; // Generate task load between 0 and z

            // Strategy 1
            Processor processorY = getRandomProcessor();
            boolean taskSent = false;
            for (int j = 0; j < processors.size(); j++) {
                numQueries++;
                if (processorY.getLoad() < p) {
                    taskSent = processorX.sendTaskTo(processorY, p);
                    break;
                }
                processorY = getRandomProcessor();
            }
            if (!taskSent) {
                processorX.addLoad(taskLoad);
            }

            // Strategy 2
            if (processorX.isOverloaded(p)) {
                taskSent = false;
                while (!taskSent) {
                    processorY = getRandomProcessor();
                    numQueries++;
                    if (processorY.getLoad() < p) {
                        taskSent = processorX.sendTaskTo(processorY, p);
                    }
                }
            }

            // Strategy 3
            for (Processor processor : processors) {
                if (processor.isUnderThreshold(r)) {
                    taskSent = false;
                    while (!taskSent) {
                        processorY = getRandomProcessor();
                        numQueries++;
                        if (processorY.getLoad() > p) {
                            processor.requestTaskFrom(processorY, p - processor.getLoad());
                            taskSent = true;
                            numMigrations++;
                        }
                    }
                }
            }
        }

        double averageLoad = calculateAverageLoad();
        double loadDeviation = calculateLoadDeviation(averageLoad);

        System.out.println("Average Load: " + averageLoad);
        System.out.println("Load Deviation: " + loadDeviation);
        System.out.println("Number of Queries: " + numQueries);
        System.out.println("Number of Migrations: " + numMigrations);
    }
}

public class Main {
    public static void main(String[] args) {
        int N = 50; // Number of processors
        double p = 0.7; // Threshold for load balancing
        double r = 0.4; // Minimum threshold for load balancing
        double z = 0.03; // Maximum task load

        LoadBalancingSimulation simulation = new LoadBalancingSimulation(N, p, r, z);
        simulation.runSimulation(1000); // Number of tasks to execute
    }
}

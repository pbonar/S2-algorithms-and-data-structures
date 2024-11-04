package Strategies;
import java.util.ArrayList;
import java.util.Random;
import core.*;
import core.Process;

public class Strategy2 extends DistributionSimulation {
    private final Random random;
    private final double p;
    private final int z;

    public Strategy2(ArrayList<Process> taskList, int cpuCount, double p, int z) {
        super(taskList, cpuCount);

        this.random = new Random();
        this.p = p;
        this.z = z;
    }

    @Override
    public void handleNewProcess(Process proc, CPU assignedTo, ArrayList<CPU> allCpus) {
        if (assignedTo.getRealLoadNoCount() > p) {
            for (int i = 0; i < z; i++) {
                int cpuToAsk = random.nextInt(allCpus.size());
                CPU dest = allCpus.get(cpuToAsk);

                if (dest.getRealLoad() < p) {
                    migrateProcess(proc, dest);

                    break;
                }
            }
        }
    }
}

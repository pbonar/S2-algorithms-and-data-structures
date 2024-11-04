package Strategies;
import java.util.ArrayList;
import java.util.Random;

import core.*;
import core.Process;

public class Strategy1 extends DistributionSimulation {
    private final Random _random;
    private final double _p;
    private final int _z;

    public Strategy1(ArrayList<Process> taskList, int cpuCount, double p, int z) {
        super(taskList, cpuCount);

        _random = new Random();
        _p = p;
        _z = z;
    }

    @Override
    public void handleNewProcess(Process proc, CPU assignedTo, ArrayList<CPU> allCpus) {
        for (int i = 0; i < _z; i++) {
            int cpuToAsk = _random.nextInt(allCpus.size());
            CPU dest = allCpus.get(cpuToAsk);

            if (dest.getRealLoad() < _p) {
                if (dest != assignedTo)
                    migrateProcess(proc, dest);

                break;
            }
        }
    }
}

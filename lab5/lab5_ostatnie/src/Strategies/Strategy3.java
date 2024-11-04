package Strategies;
import java.util.ArrayList;
import java.util.Random;
import core.*;
import core.Process;
public class Strategy3 extends DistributionSimulation {
    private final Random random;
    private final double p, r;

    public Strategy3(ArrayList<Process> taskList, int cpuCount, double p, double r) {
        super(taskList, cpuCount);

        random = new Random();
        this.p = p;
        this.r = r;
    }

    @Override
    public void handleNewProcess(Process proc, CPU assignedTo, ArrayList<CPU> allCpus) {
        for (CPU cpu : allCpus) {
            if (cpu.getRealLoad() < r) {
                int selectedId = random.nextInt(allCpus.size());
                CPU selected = allCpus.get(selectedId);

                if (selected.getRealLoad() > p) {
                    ArrayList<Process> releasedTasks = selected.releaseTasks();
                    for (Process p : releasedTasks) {
                        cpu.assignNewProcess(p);
                        incrementMigrations();
                    }
                }
            }
        }
    }
}

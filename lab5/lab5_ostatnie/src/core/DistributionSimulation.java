package core;

import java.util.ArrayList;

public abstract class DistributionSimulation {
    private final ArrayList<CPU> CPUs;
    private final ArrayList<Process> taskList;
    private int countMigrations;
    private double totalTime;

    public DistributionSimulation(ArrayList<Process> taskList, int cpuCount) {
        CPUs = new ArrayList<>();
        this.taskList = taskList;
        totalTime = 0.0;

        for (int i = 0; i < cpuCount; i++)
            CPUs.add(new CPU());
    }

    protected void migrateProcess(Process proc, CPU destination) {
        proc.wlasciciel.removeProcess(proc);
        destination.assignNewProcess(proc);
        countMigrations++;
    }

    public int getMigrationCount() {
        return countMigrations;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void start() {
        double time = 0.0;
        int index = 0;

        while (index < taskList.size()) {
            Process nextProcess = taskList.get(index);

            double nextEventTime = 1e10;
            for (CPU c : CPUs) {
                double cpuEvent = c.getNextEventTime();
                if (cpuEvent < nextEventTime)
                    nextEventTime = cpuEvent;
            }

            if (nextEventTime < nextProcess.appearTime) { // One of the process will end earlier than next process starts
                time = nextEventTime;
                for (CPU c : CPUs) {
                    c.update(time);
                }
            } else { // Next process will start before any of the current running process will end
                time = nextProcess.appearTime;
                for (CPU c : CPUs) {
                    c.update(time);
                }

                CPUs.get(nextProcess.idWlasciciela).assignNewProcess(nextProcess);
                handleNewProcess(nextProcess, CPUs.get(nextProcess.idWlasciciela), CPUs);
                index++;
            }
        }

        double nextEventTime = 0;
        while (nextEventTime < 1e6){
            nextEventTime = 1e10;
            for (CPU c : CPUs) {
                double cpuEvent = c.getNextEventTime();
                if (cpuEvent < nextEventTime)
                    nextEventTime = cpuEvent;
            }

            if (nextEventTime < 1e6) {
                totalTime = nextEventTime;
                for (CPU c : CPUs) {
                    c.update(nextEventTime);
                }
            }
        }
    }

    public ArrayList<CPU> getCpus() {
        return CPUs;
    }

    protected void incrementMigrations() {
        countMigrations++;
    }

    public abstract void handleNewProcess(Process proc, CPU assignedTo, ArrayList<CPU> allCpus);
}
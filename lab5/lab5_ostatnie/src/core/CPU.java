package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CPU {
    private final List<Process> runningTasks;
    private double load;
    private double time;
    private double avgLoadSum;
    private int loadRequests;

    public CPU() {
        this.runningTasks = new LinkedList<>();
        this.load = 0.0;
        this.time = 0.0;
        this.avgLoadSum = 0.0;
        this.loadRequests = 0;
    }

    public int getLoadRequestCount() {
        return loadRequests;
    }

    public double getRealLoad() {
        loadRequests++;
        return getRealLoadNoCount();
    }

    public double getRealLoadNoCount() {
        return Math.min(load, 1.0);
    }

    public double getTimeScale() {
        return Math.max(load, 1.0);
    }

    public double getAverageLoad() {
        if (time == 0.0)
            return 0.0;
        return avgLoadSum / time;
    }

    public double getNextEventTime() {
        double minTime = 1e10;

        for (Process p : runningTasks) {
            if (p.timeRemaining < minTime) {
                minTime = p.timeRemaining;
            }
        }

        return time + minTime * getTimeScale();
    }

    public void update(double newTime) {
        if (newTime < time)
            throw new IllegalArgumentException("newTime must be >= than current time");

        double interval = newTime - time;
        if (interval == 0)
            return;

        double timePassed = interval / getTimeScale();
        avgLoadSum += interval * getRealLoadNoCount();

        ListIterator<Process> it = runningTasks.listIterator();
        while(it.hasNext()) {
            Process p = it.next();
            p.timeRemaining -= timePassed;

            if (Math.abs(p.timeRemaining) < 1e-8) {
                load -= p.load;
                p.wlasciciel = null;
                it.remove();
            } else if (p.timeRemaining < 0.0) {
                throw new RuntimeException("we overlooked at least one process :(");
            }
        }

        time = newTime;
    }

    public void assignNewProcess(Process p) {
        if (p.wlasciciel != null) {
            throw new RuntimeException("process already has an owner");
        }

        runningTasks.add(p);
        load += p.load;
        p.wlasciciel = this;
    }

    public void removeProcess(Process p) {
        if (runningTasks.contains(p)) {
            p.wlasciciel = null;
            load -= p.load;
            runningTasks.remove(p);
        }
    }

    public ArrayList<Process> releaseTasks() {
        double targetLoad = load * 3.0 / 4.0;
        ArrayList<Process> released = new ArrayList<>();

        ListIterator<Process> it = runningTasks.listIterator();
        while (load > targetLoad && it.hasNext()) {
            Process p = it.next();
            load -= p.load;
            p.wlasciciel = null;
            released.add(p);
            it.remove();
        }

        return released;
    }
}
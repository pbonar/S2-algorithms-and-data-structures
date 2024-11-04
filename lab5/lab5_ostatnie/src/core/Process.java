package core;

public class Process implements Comparable<Process> {
    CPU wlasciciel;
    public int idWlasciciela;
    public double load;
    public double appearTime;
    public double executionTime;
    public double timeRemaining;

    public Process(int cpuId, double cpuLoad, double appearTime, double executionTime) {
        wlasciciel = null;
        idWlasciciela = cpuId;
        load = cpuLoad;
        this.appearTime = appearTime;
        this.executionTime = executionTime;
        this.timeRemaining = executionTime;
    }

    @Override
    public int compareTo(Process o) {
        return Double.compare(appearTime, o.appearTime);
    }

    public Process cloneProcess() {
        return new Process(idWlasciciela, load, appearTime, executionTime);
    }
}
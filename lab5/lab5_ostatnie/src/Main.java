import Strategies.Strategy1;
import Strategies.Strategy2;
import Strategies.Strategy3;
import core.CPU;
import core.DistributionSimulation;
import core.Generator;
import core.Process;

import java.util.ArrayList;

public class Main {

    static int N = 10;
    static double p = 0.9; // cel maksymalnego obciazenia
    static double r = 0.4; // do strategii 3 minimalne
    static int z = 10; // ponowne proby
    private static void testStrategy(String strategyName, DistributionSimulation simulation) {
        simulation.start();

        System.out.println(strategyName);
//        System.out.print("Srednie obciazenie procesorow: ");

        double totalAverageLoad = 0.0;
        for (CPU cpu : simulation.getCpus()) {
//            System.out.printf("%.1f%% ", cpu.getAverageLoad() * 100.0-0.5);
            totalAverageLoad += cpu.getAverageLoad();
        }
        System.out.println();

        totalAverageLoad /= simulation.getCpus().size();

        System.out.printf("Srednie obciazenie: %.1f%%\n", totalAverageLoad * 100.0);

        double stDev = 0.0;
        int loadReqs = 0;
        for (CPU cpu : simulation.getCpus()) {
            stDev += Math.pow(cpu.getAverageLoad() - totalAverageLoad, 2.0);
            loadReqs += cpu.getLoadRequestCount();
        }
        stDev /= simulation.getCpus().size();
        stDev = Math.sqrt(stDev);

        System.out.printf("Odchylenie std: %.1f%%\n", stDev * 100.0);
        System.out.printf("Zapytania o obc: %d\n", loadReqs);
        System.out.printf("Liczba migracji: %d\n", simulation.getMigrationCount());
        System.out.printf("Laczny czas: %.0f\n", simulation.getTotalTime()*100);
        System.out.println();
    }

    private static ArrayList<Process> cloneList(ArrayList<Process> original) {
        ArrayList<Process> newList = new ArrayList<>();

        for (Process p : original) {
            newList.add(p.cloneProcess());
        }

        return newList;
    }


    public static void main(String[] args) {

        ArrayList<Process> generated = new ArrayList<>();
        Generator.generateProcessList(generated, N, 600);

        testStrategy("Strategia 1", new Strategy1(cloneList(generated), N, p, z));
        testStrategy("Strategia 2", new Strategy2(cloneList(generated), N, p, z));
        testStrategy("Strategia 3", new Strategy3(cloneList(generated), N, p, r));
    }
}
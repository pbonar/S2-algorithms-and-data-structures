
import java.util.Comparator;

import core.SortingAlgorithm;
import sorters.*;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) {
		wykonajTabelke(5);
		//wykonajWszystkie(1000);
	}
	static void wykonajWszystkie(int size){
		for (int i = 5; i <= 8; i++){
			wykonaj(i, size);
		}
	}
	static void wykonajTabelke(int j){
		int[] tab = {5, 10, 20, 30, 40, 60, 80, 100, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 4000, 6000, 8000, 10000};
		System.out.println("Ilość Elementów\tCzas\tOdchylenie Standardowe");
		for (int i : tab)
			wykonajT(j, i, 0);
		System.out.println("\nIlość Elementów\tIlość Porównań\tOdchylenie Standardowe");
		for (int i : tab)
			wykonajT(j, i, 1);
		System.out.println("\nIlość Elementów\tIlość Zamian\tOdchylenie Standardowe");
		for (int i : tab)
			wykonajT(j, i, 2);
	}
	static void wykonajT(int i, int size, int l) {

		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		//Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));
		//Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new ReversedIntegerArrayGenerator());
		//Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new OrderedIntegerArrayGenerator());
		//Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(1000000));
		Generator<MarkedValue<Integer>> generator = new LinkedListGenerator<Integer>(new RandomIntegerArrayGenerator(1000000));
		SortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);
		if (i == 0) {
			algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 1) {
			algorithm = new InsertionSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 2) {
			algorithm = new SelectionSortNotStable<MarkedValue<Integer>>(markedComparator);
		} else if (i == 3) {
			algorithm = new SelectionSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 4) {
			algorithm = new CocktailSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 5) {
			algorithm = new QuickSort<MarkedValue<Integer>>(markedComparator, false);
		} else if (i == 6) {
			algorithm = new QuickSort<MarkedValue<Integer>>(markedComparator, true);
		} else if (i == 7) {
			algorithm = new MergeSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 8) {
			algorithm = new QueueMergeSort<MarkedValue<Integer>>(markedComparator);
		}

		Result result = Tester.runNTimes(algorithm, generator, size, 20);
		if (l == 0)System.out.println(size + "\t" + result.averageTimeInMilliseconds() + "\t"  +  result.timeStandardDeviation());
		else if (l == 1) System.out.println(size + "\t" + result.averageComparisons() + "\t"  + result.comparisonsStandardDeviation());
		else if (l == 2) System.out.println(size + "\t" + result.averageSwaps() + "\t"  + result.swapsStandardDeviation());
	}
	static void wykonaj(int i, int size) {
		if (i == 0){
			System.out.println("BUBBLE SORT");
		} else if (i == 1) {
			System.out.println("INSERTION SORT");
		} else if (i == 2) {
			System.out.println("SELECTION SORT - not stable");
		} else if (i == 3) {
			System.out.println("SELECTION SORT - stable");
		} else if (i == 4) {
			System.out.println("COCKTAIL SORT");
		} else if (i == 5) {
			System.out.println("QUICK SORT - pierwszy");
		} else if (i == 6) {
			System.out.println("QUICK SORT - losowy");
		} else if (i == 7) {
			System.out.println("MERGE SORT");
		} else if (i == 8) {
			System.out.println("QUEUE MERGE SORT");
		}
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(1000));
		//Generator<MarkedValue<Integer>> generator = new LinkedListGenerator<Integer>(new RandomIntegerArrayGenerator(1000));
		SortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);
		if (i == 0) {
			algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 1) {
			algorithm = new InsertionSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 2) {
			algorithm = new SelectionSortNotStable<MarkedValue<Integer>>(markedComparator);
		} else if (i == 3) {
			algorithm = new SelectionSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 4) {
			algorithm = new CocktailSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 5) {
			algorithm = new QuickSort<MarkedValue<Integer>>(markedComparator, false);
		} else if (i == 6) {
			algorithm = new QuickSort<MarkedValue<Integer>>(markedComparator, true);
		} else if (i == 7) {
			algorithm = new MergeSort<MarkedValue<Integer>>(markedComparator);
		} else if (i == 8) {
			algorithm = new QueueMergeSort<MarkedValue<Integer>>(markedComparator);
		}
		Result result = Tester.runNTimes(algorithm, generator, size, 50);

		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable() + '\n');
	}
	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}
	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

}

import core.FutureIntegerComparator;
import core.IDictionary;
import implementacions.*;
import measuring.Timer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] n = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000, 512000};    //liczba powtorzen
        Timer t = new Timer();
        //IDictionary<Integer, Integer> tablica = new SkipList<>(0.5F, new FutureIntegerComparator());
        //IDictionary<Integer, Integer> tablica = new BSTDictionary<>(new FutureIntegerComparator());
        IDictionary<Integer, Integer> tablica = new RBDictionary<>();
        System.out.println("liczba\tdodawanie\tdostep\tusuwanie\tsuma");
        for (int i : n){
            System.out.print(i + "\t");
                t.start();
                for (int j = 0; j < i; j++){
                    tablica.insert(r.nextInt(100000), r.nextInt(100000));
                }
                t.stop();
                long CI = t.durationMillis();
                t.reset();
            System.out.print(CI + "\t");
                t.start();
                for (int j = 0; j < i; j++){
                    tablica.get(r.nextInt(1000000));
                }
                t.stop();
                long CG = t.durationMillis();
                t.reset();
            t.start();
            for (int j = 0; j < i; j++){
                tablica.get(r.nextInt(1000000));
            }
            t.stop();
            long CD = t.durationMillis();
            t.reset();
            System.out.println( CG + "\t" + CD + "\t" + (CI + CG + CD));
        }


//        SkipList<Integer, Integer> Skip = new SkipList<>(0.5F, new FutureIntegerComparator());
//        Skip.insert(3, 6);
//        Skip.coutall();
//        Skip.insert(10, 10);
//        Skip.coutall();
//        Skip.insert(8, 9);
//        Skip.coutall();
//        Skip.remove(8);
//        Skip.coutall();
//        System.out.println(Skip.get(10));
//        BSTDictionary dic = new BSTDictionary(new FutureIntegerComparator());
//        dic.insert(13, 5);
//        dic.insert(16, 7);
//        dic.insert(6, 8);
//        dic.cout();
//        dic.remove(16);
//        dic.cout();
    }
}
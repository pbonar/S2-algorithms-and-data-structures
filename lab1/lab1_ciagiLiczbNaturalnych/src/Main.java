import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Ile liczb chcesz dodac? ");
        int n;
        n = scaner.nextInt();
        while (n < 1){
            System.out.println("Podaj liczbe jeszcze raz");
            n = scaner.nextInt();
        }
        System.out.println("Podaj liczby:");
        int[] tab = new int[n];
        for (int i = 0; i < n; i++){
            tab[i] = scaner.nextInt();
        }
        int[][] l = longestNaturalSubstrings(tab);
        for(int i = 0; i < l.length; i++){
            for (int j = 0; j < l[i].length; j++)
                System.out.print(l[i][j] + " ");
            System.out.println();
        }
        Substring sub = longestNaturalSubstring(tab);
        System.out.println(sub.toString());
    }

    public static Substring longestNaturalSubstring(int[] numbers){
        Substring sub = new Substring();
        int ile = 0;
        for (int i = 0; i < numbers.length; i++){
            if (numbers[i] == 0){
                while (i < numbers.length && ile == numbers[i]){
                    i++;
                    ile++;
                }
                if (sub.getIndex() == -1){
                    sub.setIndex(i-ile);
                    sub.setLength(ile);
                }

                else {
                    if (sub.getLength() > ile){
                        sub.setIndex(i-ile);
                        sub.setLength(ile);
                    }
                }
                ile=0;
                i--;
            }
        }
        return sub;
    }

    public static int[][] longestNaturalSubstrings(int[] numbers){
        int ile = 0;
        int[] lista = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            lista[i] = 0;
        for (int i = 0; i < numbers.length; i++){
            if (numbers[i] == 0){
                while (i < numbers.length && lista[ile] == numbers[i]){
                    i++;
                    lista[ile]++;
                }
                ile++;
                i--;
            }
        }
        int[][] zwrot = new int[ile][];
        for (int i = 0; i < ile; i++){
            zwrot[i] = new int[lista[i]];
            for (int j = 0; j < lista[i]; j++)
                zwrot[i][j] = j;
        }
        return zwrot;
    }
}
import array2.Array2;
import array2.Array2Iterator;

public class Main {
    public static void main(String[] args) {
        Array2<Integer> array = new Array2<>(new int[]{2, 3, 5});
        array.set(1, 0, 0);
        array.set(2, 0, 1);
        array.set(3, 1, 0);
        array.set(4, 1, 1);
        array.set(5, 1, 2);
        array.set(6, 2, 0);
        array.set(7, 2, 1);
        array.set(8, 2, 2);
        array.set(9, 2, 3);
        array.set(10, 2, 4);

        Array2Iterator<Integer> it = new Array2Iterator<>(array);
        for (int j : array){
            System.out.println(j);
        }
    }
}
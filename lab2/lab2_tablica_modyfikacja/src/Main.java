import array2.Array2;
import array2.Array2Iterator;
import array2.Array2SkipIterator;

public class Main {
    public static void main(String[] args) {
        Array2<Integer> array = new Array2<Integer>(new int[]{2, 3, 5});
        array.set(1, 0, 0);
        array.set(2, 0, 1);
        array.set(3, 1, 0);
        array.set(4, 1, 1);
        array.set(5, 1, 2);
        array.set(6, 2, 0);
        array.set(7, 2, 1);
        array.set(null, 2, 2);
        array.set(9, 2, 3);
        array.set(10, 2, 4);
        //System.out.println("!" + array.get(2, 2) + "!");
        Array2Iterator<Integer> it = new Array2Iterator<Integer>(array);
        for (Integer j : array){
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("MODYFIKACJA");
        Array2SkipIterator<Integer> skipiterator = new Array2SkipIterator(array,3 );
        Integer o;
        while (skipiterator.hasNext()){
            o = skipiterator.next();
            System.out.print(o + " ");
        }

    }
}
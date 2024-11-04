import OneWayLinked.*;

public class Main {
    static OneWayLinkedListWithSentinel<Integer> lista = new OneWayLinkedListWithSentinel<Integer>();

    public static void main(String[] args) {
        System.out.println(lista.add(1));
        System.out.println(lista.add(10));
        System.out.println(lista.add(null));
        lista.cout();
        lista.add(2, 1000);
        lista.cout();
        System.out.println(lista.indexOf(1000));
        System.out.println(lista.isEmpty());
        System.out.println(lista.get(1));
        lista.cout();
        lista.remove(1);
        lista.cout();
        System.out.println(lista.indexOf(null));
        System.out.println(lista.get(0));
        lista.clear();
        System.out.println("MODYFIKACJA");
        lista.add(1);
        lista.add(10);
        lista.add(100);
        lista.add(1000);
        lista.add(10000);
        lista.cout();
        System.out.println("getFromEnd() = " + lista.getFromEnd(0));
    }
}
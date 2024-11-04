import Lista.*;

public class Main {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> lista = new TwoWayLinkedList();
        lista.add(3);
        lista.add(65);
        lista.add(32);
        lista.cout();
        lista.add(3, 43);
        lista.cout();
        lista.add(0, 100);
        lista.cout();
        System.out.println("Index of 43: " + lista.indexOf(43));
        lista.remove((Integer) 43);
        lista.remove(0);
        lista.cout();
    }
}
public class Main {
    public static void main(String[] args) {
        Array3Heap<Integer> kopiec = new Array3Heap<>(4);
        kopiec.add(10);
        kopiec.add(90);
        kopiec.add(30);
        kopiec.add(20);
        kopiec.add(30);
        kopiec.add(30);
        kopiec.add(20);
        kopiec.add(33);
        kopiec.add(35);
        kopiec.add(37);
        kopiec.add(34);
        kopiec.add(39);
        kopiec.add(30);
        System.out.println("Wyrzucamy minimum");
        kopiec.cout();
        System.out.println(kopiec.minimum());
        System.out.println("Wyrzucamy maximum");
        kopiec.cout();
        System.out.println(kopiec.maximum());
        kopiec.cout();
        System.out.println("\nTworzymy nowy kopiec");
        Array3Heap<Integer> kopiec1 = new Array3Heap<>(2);
        kopiec1.add(14);
        kopiec1.add(61);
        kopiec1.add(89);
        kopiec1.add(2);
        kopiec1.cout();
        System.out.println("Dodajemy kopce");
        kopiec.addHeap(kopiec1);
        kopiec.cout();
    }
}
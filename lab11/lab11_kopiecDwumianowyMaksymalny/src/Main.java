public class Main {
    public static void main(String[] args) {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.insert(25);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(21);
        heap.printHeap();

        System.out.println("Max key: " + heap.extractMaximum());

        heap.printHeap();
    }
}
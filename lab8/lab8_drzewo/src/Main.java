import BST.BSTree;

public class Main {
    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();

        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(14);
        bst.insert(4);
        bst.insert(7);
        bst.insert(13);

        System.out.println("Szukanie 6: " + bst.search(6));
        System.out.println("Szukanie 9: " + bst.search(9));

        System.out.println("Minimum: " + bst.findMin());
        System.out.println("Maximum: " + bst.findMax());

        System.out.println("Post-order:");
        bst.postOrderTraversal();

        System.out.println("Nastepnik 7: " + bst.findSuccessor(7));
        System.out.println("Nastepnik 10: " + bst.findSuccessor(10));

        bst.delete(8);
        System.out.println("Szukaj 8 po usunieciu: " + bst.search(8));

        System.out.println("Post-order po usunieciu:");
        bst.postOrderTraversal();
    }
}
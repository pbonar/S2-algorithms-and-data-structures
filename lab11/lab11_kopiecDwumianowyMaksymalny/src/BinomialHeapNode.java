import java.util.ArrayList;
import java.util.List;

class Node {
    int key;
    int degree;
    Node parent;
    Node child;
    Node sibling;

    public Node(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }
}

class BinomialHeap {
    private Node head;

    public BinomialHeap() {
        this.head = null;
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        if (head == null) {
            head = newNode;
        } else {
            BinomialHeap newHeap = new BinomialHeap();
            newHeap.head = newNode;
            unionHeap(newHeap);
        }
    }

    public int findMaximum() {
        if (head == null) {
            System.out.println("Heap is empty");
            return -1;
        }
        Node maxNode = head;
        Node currNode = head;
        while (currNode != null) {
            if (currNode.key > maxNode.key) {
                maxNode = currNode;
            }
            currNode = currNode.sibling;
        }

        return maxNode.key;
    }

    public int extractMaximum() {
        if (head == null) {
            System.out.println("Heap is empty");
            return -1;
        }
        Node maxNode = null;
        Node prevMaxNode = null;
        Node currNode = head;
        Node prevNode = null;

        // Szukanie maksymalnej wartości
        while (currNode != null) {
            if (maxNode == null || currNode.key > maxNode.key) {
                maxNode = currNode;
                prevMaxNode = prevNode;
            }
            prevNode = currNode;
            currNode = currNode.sibling;
        }

        // Usuwanie węzła
        if (prevMaxNode == null) {
            head = maxNode.sibling;
        } else {
            prevMaxNode.sibling = maxNode.sibling;
        }
        BinomialHeap childHeap = new BinomialHeap();
        Node child = maxNode.child;
        while (child != null) {
            Node nextSibling = child.sibling;
            child.sibling = childHeap.head;
            child.parent = null;
            childHeap.head = child;
            child = nextSibling;
        }
        unionHeap(childHeap);
        return maxNode.key;
    }

    public void unionHeap(BinomialHeap heap) {
        if (heap.head == null) {
            return;
        }

        BinomialHeap mergedHeap = mergeHeap(this, heap);

        Node prevNode = null;
        Node currNode = mergedHeap.head;
        Node nextNode = currNode.sibling;

        while (nextNode != null) {
            if (currNode.degree != nextNode.degree || (nextNode.sibling != null && nextNode.sibling.degree == currNode.degree)) {
                prevNode = currNode;
                currNode = nextNode;
            } else if (currNode.key >= nextNode.key) {
                currNode.sibling = nextNode.sibling;
                linkNodes(nextNode, currNode);
            } else {
                if (prevNode == null) {
                    mergedHeap.head = nextNode;
                } else {
                    prevNode.sibling = nextNode;
                }
                linkNodes(currNode, nextNode);
                currNode = nextNode;
            }
            nextNode = currNode.sibling;
        }
        this.head = mergedHeap.head;
    }

    private static BinomialHeap mergeHeap(BinomialHeap heap1, BinomialHeap heap2) {
        BinomialHeap mergedHeap = new BinomialHeap();
        Node node1 = heap1.head;
        Node node2 = heap2.head;
        Node last1 = node1;
        Node last2 = node2;

        if (node1 == null) {
            mergedHeap.head = node2;
        } else if (node2 == null) {
            mergedHeap.head = node1;
        } else {
            if (node1.degree <= node2.degree) {
                mergedHeap.head = node1;
            } else {
                mergedHeap.head = node2;
            }
            while (node1 != null && node2 != null) {
                if (node1.degree < node2.degree) {
                    last1 = node1;
                    node1 = node1.sibling;
                } else if (node1.degree == node2.degree) {
                    Node nextNode1 = node1.sibling;
                    Node nextNode2 = node2.sibling;

                    node1.sibling = node2;
                    node2.sibling = nextNode1;

                    last1 = node1;
                    last2 = node2;
                    node1 = nextNode1;
                    node2 = nextNode2;
                } else {
                    Node nextNode2 = node2.sibling;
                    node2.sibling = node1;
                    last1 = node1;
                    last2 = node2;
                    node1 = node2;
                    node2 = nextNode2;
                }
            }
            if (node1 != null) {
                while (node1.sibling != null) {
                    node1 = node1.sibling;
                }
                node1.sibling = node2;
            }
            else if (node2 != null){
                last1.sibling = node2;
            }
        }
        return mergedHeap;
    }

    private static void linkNodes(Node smallerNode, Node biggerNode) {
        smallerNode.parent = biggerNode;
        smallerNode.sibling = biggerNode.child;
        biggerNode.child = smallerNode;
        biggerNode.degree++;
    }

    public void printHeap() {
        if (head == null) {
            System.out.println("Heap is empty.");
            return;
        }

        Node currNode = head;
        List<Node> nodeList = new ArrayList<>();
        while (currNode != null) {
            nodeList.add(currNode);
            currNode = currNode.sibling;
        }

        for (Node node : nodeList) {
            printNode(node);
        }
    }

    private void printNode(Node node) {
        System.out.print("Degree " + node.degree + ": ");
        System.out.print(node.key + " ");
        if (node.child != null) printTree(node.child);
        System.out.println();
    }

    private void printTree(Node node) {
        System.out.print(node.key + " ");
        if (node.child != null) {
            printTree(node.child);
        }
        if (node.sibling != null) {
            printTree(node.sibling);
        }
    }
}
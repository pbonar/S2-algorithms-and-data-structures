package implementacions;

import core.IDictionary;

import java.util.Comparator;

import static java.lang.Math.min;

public class SkipList<K, V> implements IDictionary<K, V> {

    float p;
    Comparator comparator;
    SkipNode<K, V> head;
    int gdzie;
    int size = 0;
    public SkipList(float p, Comparator comparator){
        this.p = p;
        this.comparator = comparator;
        head = new SkipNode<>(null, null, p);
        head.pointers = new SkipNode[1];
    }

    @Override
    public V insert(K key, V value) {
        SkipNode<K, V> S = new SkipNode<>(key, value, p);
        //zwiekszanie heada
        if (S.pointers.length > head.pointers.length){
            SkipNode<K, V> tab[] = head.pointers;
            head.pointers = new SkipNode[S.pointers.length];
            for (int i = 0; i < head.pointers.length; i++){
                if (i < tab.length)
                    head.pointers[i] = tab[i];
            }
        }
        size++;
        return insertR(S, head, head.pointers.length-1);
    }
    public V insertR(SkipNode<K, V> S, SkipNode<K, V> current, int level){
        V toReturn = null;
        if (level > 0) {
            while (current.pointers[level] != null && current.pointers[level].pairr.compareTo(S.pairr.key, comparator) < 0) {
                current = current.pointers[level];
            }
            toReturn = insertR(S, current, level-1);
            if (current.pointers.length-1 >= level && S.pointers.length-1 >= level && toReturn != null){
                S.pointers[level] = current.pointers[level];
                current.pointers[level] = S;
            }
        } else {
            //najnizszy poziom
            while (current.pointers[0] != null && current.pointers[0].pairr.compareTo(S.pairr.key, comparator) < 0)
                current = current.pointers[0];
            if (current.pointers[0] != null && current.pointers[0].pairr.compareTo(S.pairr.key, comparator) == 0)
                return null;
            else {
                S.pointers[0] = current.pointers[0];
                current.pointers[0] = S;
                return S.pairr.value;
            }
        }
        return toReturn;
    }

    @Override
    public V get(K key) {
        SkipNode<K, V> current = head;
        for (int i = head.pointers.length-1; i >= 0; i--)
            while (current.pointers[i] != null && current.pointers[i].pairr.compareTo(key, comparator) <= 0)
                current = current.pointers[i];
        if (current.pairr.key == key)
            return current.pairr.value;
        else
            return null;
    }

    @Override
    public V remove(K key) {
        SkipNode<K, V> S = RemoveR(head, head.pointers.length-1, key);
        if (S != null) return S.pairr.value;
        else return null;
    }

    public SkipNode<K, V> RemoveR(SkipNode<K, V> current, int level, K key){
        SkipNode<K, V> S = null;
        if (level > 0) {
            while (current.pointers[level] != null && current.pointers[level].pairr.compareTo(key, comparator) < 0) {
                current = current.pointers[level];
            }
            S = RemoveR(current, level-1, key);
            if (S != null && current.pointers.length-1 >= level && S.pointers.length-1 >= level){
                current.pointers[level] = S.pointers[level];
            }
        } else {
            //najnizszy poziom
            while (current.pointers[0] != null && current.pointers[0].pairr.compareTo(key, comparator) < 0)
                current = current.pointers[0];
            if (current.pointers[0] != null && current.pointers[0].pairr.compareTo(key, comparator) == 0) {
                S = current.pointers[0];
                current.pointers[0] = S.pointers[0];
                return S;
            }
            else {
                return null;
            }
        }
        return S;
    }

    public void cout(int level){
        SkipNode<K, V> O = head.pointers[level];
        while (O != null){
//            System.out.print("(k:" + O.key + " v:" + O.value +") ");
            O = O.pointers[level];
        }
//        System.out.println();
    }
    public void coutall(){
        SkipNode<K, V> O = head;
        while (O != null){
                System.out.print("(k:" + O.pairr.key + " v:" + O.pairr.value +") ");
            for (SkipNode s : O.pointers)
                if (s != null) System.out.print(s.pairr.key + " ");
                else System.out.print("null ");
            O = O.pointers[0];
        }
        System.out.println();
        System.out.println();
    }
}

package implementacions;

import java.util.Random;

public class SkipNode<K, V> {
    public Pairr<K, V> pairr = new Pairr<>();
    public SkipNode<K, V>[] pointers;

    public SkipNode(K key, V value, float p){
        pairr.key = key;
        pairr.value = value;
        int size = 1;
        while (new Random().nextInt(100) < p*100)
            size++;
        pointers = new SkipNode[size];
        for (int i = 0; i < size; i++)
            pointers[i] = null;
//        System.out.println(toStringList());
    }
    public String toStringList(){
        String toReturn = "Klucz: "+ pairr.key+ " lista wezlow: ";
        for (int i = 0; i < pointers.length; i++) {
            if (pointers[i] != null) toReturn += pointers[i].pairr.key + " ";
            else toReturn += " null";
        }
        return toReturn;
    }
    public String toString(){
        return "Klucz: "+ this.pairr.key;
    }
}

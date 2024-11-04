package implementacions;

import core.FutureIntegerComparator;

import java.util.Comparator;

public class Pairr<K, V> {
    public K key;
    public V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int compareTo(Pairr<K, V> pair, Comparator<K> comparator){
        return comparator.compare(this.key, pair.key);
    }
    public int compareTo(K pair, Comparator<K> comparator){
        return comparator.compare(this.key, pair);
    }
}

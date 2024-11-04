package implementacions;

import core.IDictionary;

import java.util.TreeMap;

public class RBDictionary<K, V> implements IDictionary<K, V> {
    TreeMap<K, V> myDictionaty = new TreeMap<>();

    @Override
    public V insert(K key, V value) {
        return myDictionaty.put(key, value);
    }

    @Override
    public V get(K key) {
        return myDictionaty.get(key);
    }

    @Override
    public V remove(K key) {
        return myDictionaty.remove(key);
    }
}

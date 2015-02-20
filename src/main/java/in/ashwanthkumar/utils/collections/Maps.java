package in.ashwanthkumar.utils.collections;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static <K, V> Map<K, V> of(K key, V value) {
        HashMap<K, V> map = new HashMap<K, V>();
        map.put(key, value);
        return map;
    }

    public static class MapBuilder<K, V> {
        private Map<K, V> internalMap = new HashMap<K, V>();

        private MapBuilder() {}

        public MapBuilder<K, V> put(K key, V value) {
            internalMap.put(key, value);
            return this;
        }
        public Map<K, V> value() {
            return internalMap;
        }
    }
    public static <K, V> MapBuilder<K, V> builder() {
        return new MapBuilder<K, V>();
    }
}

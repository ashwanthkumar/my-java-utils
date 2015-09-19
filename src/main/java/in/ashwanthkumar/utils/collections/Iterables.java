package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static in.ashwanthkumar.utils.lang.option.Option.option;

public class Iterables {
    public static <T> boolean exists(Iterable<T> input, Predicate<T> condition) {
        for (T data : input) {
            if (condition.apply(data)) return true;
        }
        return false;
    }

    public static <T> boolean forall(Iterable<T> input, Predicate<T> condition) {
        for (T data : input) {
            if (!condition.apply(data)) return false;
        }
        return true;
    }

    public static <T> T head(Iterable<T> iterable) {
        return iterable.iterator().next();
    }

    public static <T> Option<T> headOption(Iterable<T> list) {
        Iterator<T> iterator = list.iterator();
        if(iterator.hasNext()) return option(iterator.next());
        else return option(null);
    }

    public static <T> boolean isEmpty(Iterable<T> list) {
        return list == null || !list.iterator().hasNext();
    }

    public static <T> boolean nonEmpty(Iterable<T> list) {
        return !isEmpty(list);
    }

    public static <K,V> Map<K, V> toMap(Iterable<Tuple2<K, V>> tuples) {
        HashMap<K, V> map = new HashMap<K, V>();
        for (Tuple2<K, V> tuple : tuples) {
            map.put(tuple._1(), tuple._2());
        }
        return map;
    }

    public static <T> void foreach(Iterable<T> input, Function<T, Void> condition) {
        for (T data : input) {
            condition.apply(data);
        }
    }

}

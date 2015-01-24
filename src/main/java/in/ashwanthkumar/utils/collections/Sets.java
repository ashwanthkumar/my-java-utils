package in.ashwanthkumar.utils.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sets {

    public static <T> Set<T> of(T... elements) {
        Set<T> set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    public static <T> Set<T> copy(Set<T> input) {
        HashSet<T> copy = new HashSet<T>();
        copy.addAll(input);
        return copy;
    }

    public static <T> boolean isEmpty(Set<T> set) {
        return set == null || set.isEmpty();
    }

    public static <T> boolean nonEmpty(Set<T> set) {
        return !isEmpty(set);
    }

}

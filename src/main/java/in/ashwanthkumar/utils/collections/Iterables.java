package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Predicate;

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


}

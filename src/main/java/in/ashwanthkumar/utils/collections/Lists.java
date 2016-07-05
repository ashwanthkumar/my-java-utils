package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

import java.util.*;

import static in.ashwanthkumar.utils.lang.option.Option.option;
import static in.ashwanthkumar.utils.lang.tuple.Tuple2.tuple2;

public class Lists {

    public static <T> List<T> Nil() {
        return new ArrayList<T>();
    }

    public static <T> List<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <T, U> List<U> map(Iterable<T> collection, Function<T, U> transformation) {
        ArrayList<U> transformed = new ArrayList<U>();
        for (T item : collection) {
            transformed.add(transformation.apply(item));
        }
        return transformed;
    }

    public static <T, U> List<U> map(T[] array, Function<T, U> transformation) {
        return map(of(array), transformation);
    }

    public static <T> List<T> filter(Iterable<T> collection, Predicate<T> condition) {
        ArrayList<T> filteredList = new ArrayList<T>();
        for (T item : collection) {
            if (condition.apply(item)) filteredList.add(item);
        }
        return filteredList;
    }

    public static <T> List<T> filter(T[] array, Predicate<T> condition) {
        ArrayList<T> filteredList = new ArrayList<T>();
        for (T item : array) {
            if (condition.apply(item)) filteredList.add(item);
        }
        return filteredList;
    }

    public static <T, Z> Z foldL(Iterable<T> collection, Z initialValue, Function<Tuple2<Z, T>, Z> foldFunction) {
        Z foldedValue = initialValue;
        for (T item : collection) {
            foldedValue = foldFunction.apply(tuple2(foldedValue, item));
        }

        return foldedValue;
    }

    public static <T> Option<T> find(Iterable<T> collection, Predicate<T> condition) {
        for (T item : collection) {
            if (condition.apply(item)) return option(item);
        }

        return new None<T>();
    }


    public static <K,T extends Collection<K>> List<K> flatten(Collection<T> elem) {
        ArrayList<K> flattened = new ArrayList<K>();
        for (Collection<K> data : elem) {
            flattened.addAll(data);
        }
        return flattened;
    }

    public static <T> List<T> concat(Collection<T>... elems) {
        ArrayList<T> concated = new ArrayList<T>();
        for (Collection<T> elem : elems) {
            concated.addAll(elem);
        }
        return concated;
    }

    public static <T> String mkString(Iterable<T> collection) {
        return mkString(collection, "", "", ",");
    }

    public static <T> String mkString(Iterable<T> collection, String separator) {
        return mkString(collection, "", "", separator);
    }

    public static <T> String mkString(Iterable<T> collection, String start, String end, String separator) {
        StringBuilder builder = new StringBuilder();
        builder.append(start);
        boolean empty = true;
        for (T elem : collection) {
            builder.append(String.valueOf(elem));
            builder.append(separator);
            builder.append(" ");
            empty = false;
        }
        if (!empty) {
            builder.delete(builder.length() - (separator.length() + 1), builder.length());
        }
        builder.append(end);
        return builder.toString();
    }

    public static <T> List<T> take(Iterable<T> input, int size) {
        List<T> output = new ArrayList<T>();
        int count = 0;
        Iterator<T> iterator = input.iterator();
        while(iterator.hasNext() && count < size) {
            output.add(iterator.next());
            count++;
        }
        return output;
    }

    /**
     * Takes elements from the list as long as the predicate is met and stops after that.
     */
    public static <T> List<T> takeWhile(Iterable<T> input, Predicate<T> predicate) {
        List<T> output = new ArrayList<T>();
        for (T elem : input) {
            if (predicate.apply(elem)) {
                output.add(elem);
            } else {
                break;
            }
        }
        return output;
    }
}

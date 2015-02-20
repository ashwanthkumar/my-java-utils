package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.option.Some;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static <T> String mkString(Iterable<T> collection) {
        return mkString(collection, "", "", ",");
    }

    public static <T> String mkString(Iterable<T> collection, String start, String end, String separator) {
        StringBuilder builder = new StringBuilder();
        builder.append(start);
        for (T elem : collection) {
            builder.append(String.valueOf(elem));
            builder.append(separator);
            builder.append(" ");
        }
        builder.delete(builder.length() - (separator.length() + 1), builder.length());
        builder.append(end);
        return builder.toString();
    }
}

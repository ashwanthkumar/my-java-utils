package in.ashwanthkumar.utils.collections;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static in.ashwanthkumar.utils.lang.option.Option.option;
import static in.ashwanthkumar.utils.lang.tuple.Tuple2.tuple2;

public class Lists {
    public static <T> List<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <T, U> List<U> map(List<T> list, Function<T, U> transformation) {
        ArrayList<U> transformed = new ArrayList<U>();
        for (T item : list) {
            transformed.add(transformation.apply(item));
        }
        return transformed;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        ArrayList<T> filteredList = new ArrayList<T>();
        for (T item : list) {
            if (condition.apply(item)) filteredList.add(item);
        }
        return filteredList;
    }

    public static <T, Z> Z foldL(List<T> list, Z initialValue, Function<Tuple2<Z, T>, Z> foldFunction) {
        Z foldedValue = initialValue;
        for (T item : list) {
            foldedValue = foldFunction.apply(tuple2(foldedValue, item));
        }

        return foldedValue;
    }

    public static <T> Option<T> find(List<T> list, Predicate<T> condition) {
        for (T item : list) {
            if (condition.apply(item)) return option(item);
        }

        return new None<T>();
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean nonEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> String mkString(List<T> list) {
        return mkString(list, "", "", ",");
    }

    public static <T> String mkString(List<T> list, String start, String end, String separator) {
        StringBuilder builder = new StringBuilder();
        builder.append(start);
        for (T aList : list) {
            builder.append(String.valueOf(aList));
            builder.append(separator);
            builder.append(" ");
        }
        builder.delete(builder.length() - (separator.length() + 1), builder.length());
        builder.append(end);
        return builder.toString();
    }
}

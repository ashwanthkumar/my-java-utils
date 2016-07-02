package in.ashwanthkumar.utils.func;

public class Predicates {
    public static <T> Predicate<T> True() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T input) {
                return true;
            }
        };
    }

    public static <T> Predicate<T> False() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T input) {
                return false;
            }
        };
    }
}

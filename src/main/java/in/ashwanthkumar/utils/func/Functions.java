package in.ashwanthkumar.utils.func;

public class Functions {
    public static <T> Function<T, T> identity() {
        return new Function<T, T>() {
            @Override
            public T apply(T input) {
                return input;
            }
        };
    }
}

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

    public static <T> Function<T, Void> STDOUT() {
        return new Function<T, Void>() {
            @Override
            public Void apply(T input) {
                System.out.println(input);
                return null;
            }
        };
    }

    public static <T> Function<T, Void> STDERR() {
        return new Function<T, Void>() {
            @Override
            public Void apply(T input) {
                System.err.println(input);
                return null;
            }
        };
    }
}

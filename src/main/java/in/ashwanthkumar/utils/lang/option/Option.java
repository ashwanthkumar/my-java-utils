package in.ashwanthkumar.utils.lang.option;

/**
 * Safely wrap around null values
 * @param <T>
 */
abstract public class Option<T> {
    protected T data;

    public Option(T value) {
        this.data = value;
    }

    public T getOrElse(T defaultValue) {
        if (isEmpty()) return defaultValue;
        else return data;
    }

    public boolean isDefined() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return data == null;
    }

    public abstract T get() throws IllegalStateException;

    public static <T> Option<T> option(T value) {
        if (value == null) return new None<T>();
        else return new Some<T>(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        if (data != null ? !data.equals(option.data) : option.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}

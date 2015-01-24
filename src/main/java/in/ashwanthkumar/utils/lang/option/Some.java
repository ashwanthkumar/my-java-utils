package in.ashwanthkumar.utils.lang.option;

public class Some<T> extends Option<T> {
    public Some(T value) {
        super(value);
    }

    @Override
    public T get() throws IllegalStateException {
        return data;
    }
}

package in.ashwanthkumar.utils.lang.option;

public class None<T> extends Option<T> {
    public None() {
        super(null);
    }

    @Override
    public T get() throws IllegalStateException {
        throw new IllegalStateException("get on None");
    }
}

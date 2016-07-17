package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;

import java.io.Reader;

public class Success<T> extends ParserResult<T> {
    private T result;

    public static <T> ParserResult<T> of(T result, String remaining) {
        return new Success<T>(result, remaining);
    }

    private Success(T result, String remaining) {
        super(remaining);
        this.result = result;
    }

    @Override
    public T get() {
        return result;
    }

    @Override
    public <U> ParserResult<U> map(Function<T, U> transform) {
        return Success.of(transform.apply(get()), getRemainingInput());
    }

    @Override
    public boolean successful() {
        return true;
    }

    @Override
    public String toString() {
        return "Success{" +
                "result=" + result +
                ",remaining=" + remainingInput +
                '}';
    }
}

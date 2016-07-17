package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;

public class Failure<T> extends ParserResult<T> {

    private String message;

    public static <T> ParserResult<T> of(String message, String remaining) {
        return new Failure<T>(message, remaining);
    }

    private Failure(String message, String remaining) {
        super(remaining);
        this.message = message;
    }

    @Override
    public T get() {
        throw new RuntimeException("get on Failure is not supported");
    }

    @Override
    public <U> ParserResult<U> map(Function<T, U> transform) {
        return (ParserResult<U>) this;
    }

    @Override
    public boolean successful() {
        return false;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Failure{" +
                "message='" + message + '\'' +
                '}';
    }
}

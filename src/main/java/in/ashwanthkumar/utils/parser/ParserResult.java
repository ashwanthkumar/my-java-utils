package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;

public abstract class ParserResult<T> {
    protected String remainingInput;

    public ParserResult(String remainingInput) {
        this.remainingInput = remainingInput;
    }

    /**
     * Return the underlying result of the result
     *
     * @return
     */
    abstract public T get();

    abstract public <U> ParserResult<U> map(Function<T, U> transform);

    abstract public boolean successful();

    public boolean empty() {
        return !successful();
    }

    public String getRemainingInput() {
        return remainingInput;
    }

    public ParserResult setRemainingInput(String remainingInput) {
        this.remainingInput = remainingInput;
        return this;
    }
}

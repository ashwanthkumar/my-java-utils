package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

public abstract class Parser<T> {
    abstract public ParserResult<T> parse(String input);

    /**
     * {@code this.map(transform)} succeeds if {@code this} succeeds and {@code transform} is used to convert the result of {@code this}
     *
     * @param transform Function to transform the result from T -> U
     * @return a `Parser` that -- on success -- returns the result after applying {@code transform}.
     */
    public <U> Parser<U> map(final Function<T, U> transform) {
        final Parser<T> me = this;
        return new Parser<U>() {
            @Override
            public ParserResult<U> parse(String input) {
                return me.parse(input).map(transform);
            }
        };
    }

    /**
     * {@code this.thenR(another)} succeeds if {@code this} succeeds and {@code another} succeeds on the input left over by {@code this}
     *
     * @return a `Parser` that -- on success -- returns the result of `another`.
     */
    public <U> Parser<U> thenR(final Parser<U> another) {
        final Parser<T> me = this;
        return new Parser<U>() {
            @Override
            public ParserResult<U> parse(String input) {
                ParserResult<T> myResult = me.parse(input);
                return myResult.successful() ?
                        another.parse(myResult.getRemainingInput()) :
                        Failure.<U>of(((Failure<T>) myResult).getMessage(), myResult.getRemainingInput());
            }
        }.named(this.name + " ~> " + another.name);
    }

    /**
     * {@code this.thenL(another)} succeeds if {@code this} succeeds and {@code another} succeeds on the input left over by {@code this}
     *
     * @return a `Parser` that -- on success -- returns the result of `another`.
     */
    public Parser<T> thenL(final Parser<T> another) {
        final Parser<T> me = this;
        return new Parser<T>() {
            @Override
            public ParserResult<T> parse(String input) {
                ParserResult<T> myResult = me.parse(input);
                if (myResult.successful()) {
                    ParserResult<T> anotherResult = another.parse(myResult.getRemainingInput());
                    return myResult.successful() && anotherResult.successful() ? myResult : anotherResult;
                } else {
                    return myResult;
                }
            }
        };
    }

    /**
     * {@code this.thenL(another)} succeeds if {@code this} succeeds and {@code another} succeeds on the input left over by {@code this}
     *
     * @return a `Parser` that -- on success -- returns the result of both `this` and `another`
     */
    public <U> Parser<Tuple2<T, U>> then(final Parser<U> another) {
        final Parser<T> me = this;
        return new Parser<Tuple2<T, U>>() {
            @Override
            public ParserResult<Tuple2<T, U>> parse(String input) {
                ParserResult<T> myResult = me.parse(input);
                if (myResult.successful()) {
                    ParserResult<U> anotherResult = another.parse(myResult.getRemainingInput());
                    return myResult.successful() && anotherResult.successful() ?
                            Success.of(Tuple2.tuple2(myResult.get(), anotherResult.get()), anotherResult.getRemainingInput()) :
                            Failure.<Tuple2<T, U>>of(((Failure<U>) anotherResult).getMessage(), anotherResult.getRemainingInput());
                } else {
                    return Failure.of(((Failure<T>) myResult).getMessage(), myResult.getRemainingInput());
                }
            }
        };
    }

    /**
     * {@code this.or(another)} succeeds if {@code this} succeeds or {@code another} succeeds on the given input.
     * {@code another} parser is not evaluated if {@code this} succeeds.
     *
     * @return a `Parser` that -- on success -- returns the result of either `this` or `another`
     */
    public Parser<T> or(final Parser<T> another) {
        final Parser<T> me = this;
        return new Parser<T>() {
            @Override
            public ParserResult<T> parse(String input) {
                ParserResult<T> myResult = me.parse(input);
                return myResult.successful() ? myResult : another.parse(input);
            }
        };
    }
}

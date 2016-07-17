package in.ashwanthkumar.utils.parser;

import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;

public abstract class Parser<T> {
    private String name = "";

    abstract public ParserResult<T> parse(String input);

    public Parser<T> named(String name) {
        this.name = name;
        return this;
    }

    /**
     * {@code this.map(transform)} succeeds if {@code this} succeeds and {@code transform} is used to convert the result of {@code this}
     *
     * @param transform Function to transform the result from T -&gt; U
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
     * @param another Another Parser
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
     * @param another Another Parser
     * @return a `Parser` that -- on success -- returns the result of `another`.
     */
    public <U> Parser<T> thenL(final Parser<U> another) {
        final Parser<T> me = this;
        return new Parser<T>() {
            @Override
            public ParserResult<T> parse(String input) {
                ParserResult<T> myResult = me.parse(input);
                if (myResult.successful()) {
                    ParserResult<U> anotherResult = another.parse(myResult.getRemainingInput());
                    return myResult.successful() && anotherResult.successful() ?
                            myResult.setRemainingInput(anotherResult.getRemainingInput()) :
                            Failure.<T>of(((Failure<U>) anotherResult).getMessage(), myResult.getRemainingInput());
                } else {
                    return myResult;
                }
            }
        }.named(this.name + " <~ " + another.name);
    }

    /**
     * {@code this.thenL(another)} succeeds if {@code this} succeeds and {@code another} succeeds on the input left over by {@code this}
     *
     * @param another Another Parser
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
        }.named(this.name + " ~ " + another.name);
    }

    /**
     * {@code this.or(another)} succeeds if {@code this} succeeds or {@code another} succeeds on the given input.
     * {@code another} parser is not evaluated if {@code this} succeeds.
     *
     * @param another Another Parser
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

    public Parser<T> debug() {
        final Parser<T> me = this;
        return new Parser<T>() {
            @Override
            public ParserResult<T> parse(String input) {
                System.out.println("Trying " + me.name + " at '" + input + "'");
                ParserResult<T> result = me.parse(input);
                System.out.println(me.name + " --> " + result);
                return result;
            }
        };
    }

    public Parser<T> skip(final int nChars) {
        assert nChars > 0;
        final Parser<T> me = this;
        return new Parser<T>() {
            @Override
            public ParserResult<T> parse(String input) {
                return me.parse(input.substring(nChars - 1));
            }
        };
    }
}

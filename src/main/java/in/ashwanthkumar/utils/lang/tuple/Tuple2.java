package in.ashwanthkumar.utils.lang.tuple;

public class Tuple2<A, B> implements Tuple {
    public static <A, B> Tuple2<A, B> tuple2(A left, B right) {
        return new Tuple2<A, B>(left, right);
    }

    private A _1;
    private B _2;

    public Tuple2(A _1, B _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public A _1() {
        return _1;
    }

    public B _2() {
        return _2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple2 tuple2 = (Tuple2) o;

        if (_1 != null ? !_1.equals(tuple2._1) : tuple2._1 != null) return false;
        if (_2 != null ? !_2.equals(tuple2._2) : tuple2._2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _1 != null ? _1.hashCode() : 0;
        result = 31 * result + (_2 != null ? _2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", _1, _2);
    }

    @Override
    public int productArity() {
        return 2;
    }
}

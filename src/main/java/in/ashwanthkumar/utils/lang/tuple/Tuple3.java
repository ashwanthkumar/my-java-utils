package in.ashwanthkumar.utils.lang.tuple;

public class Tuple3<A, B, C> implements Tuple {
    public static <A, B, C> Tuple3<A, B, C> tuple3(A _1, B _2, C _3) {
        return new Tuple3<A, B, C>(_1, _2, _3);
    }

    private A _1;
    private B _2;
    private C _3;

    public Tuple3(A _1, B _2, C _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    public A _1() {
        return _1;
    }

    public B _2() {
        return _2;
    }

    public C _3() {
        return _3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple3 tuple3 = (Tuple3) o;

        if (_1 != null ? !_1.equals(tuple3._1) : tuple3._1 != null) return false;
        if (_2 != null ? !_2.equals(tuple3._2) : tuple3._2 != null) return false;
        if (_3 != null ? !_3.equals(tuple3._3) : tuple3._3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _1 != null ? _1.hashCode() : 0;
        result = 31 * result + (_2 != null ? _2.hashCode() : 0);
        result = 31 * result + (_3 != null ? _3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", _1, _2, _3);
    }

    @Override
    public int productArity() {
        return 3;
    }
}

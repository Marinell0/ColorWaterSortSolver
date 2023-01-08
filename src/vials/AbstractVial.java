package vials;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractVial<T> {
    protected ArrayList<T> colors = new ArrayList<>(4);
    protected int size = 0;

    protected AbstractVial(T first, T second, T third, T fourth) {
        this(first, second, third);
        set(4, fourth);
        this.size = 4;
    }

    protected AbstractVial(T first, T second, T third) {
        this(first, second);
        set(2, third);
        this.size = 3;
    }

    protected AbstractVial(T first, T second) {
        this(first);
        set(1, second);
        this.size = 2;
    }

    protected AbstractVial(T first) {
        set(0, first);
        this.size = 1;
    }

    public abstract Stream<T> colors();

    public abstract T emptyColor();

    public final void set(int pos, T value) {
        colors.set(pos, value);
    }

    public final boolean isEmpty() {
        return colors().allMatch(c -> c.equals(emptyColor()));
    }

    public final boolean isSolved() {
        T color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }
}

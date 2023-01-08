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

    public final T get(int pos) {
        return colors.get(pos);
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isSolved() {
        T color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }

    public final AbstractVial<T> transfer(AbstractVial<T> other) {
        if (this.isEmpty()) {
            return this;
        }

        int pos = this.size - 1;
        T color = get(pos);

        while(pos > 0 && get(pos) == color) {
            other.set(other.size, color);
            other.size++;
            this.size--;
            pos--;
        }

        return this;
    }
}

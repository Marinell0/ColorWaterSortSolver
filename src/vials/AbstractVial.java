package vials;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractVial<T> {
    protected ArrayList<T> colors = new ArrayList<>(4);

    protected AbstractVial(T first, T second, T third, T fourth) {
        this(first, second, third);
        set(4, fourth);
    }

    protected AbstractVial(T first, T second, T third) {
        this(first, second);
        set(2, third);
    }

    protected AbstractVial(T first, T second) {
        this(first);
        set(1, second);
    }

    protected AbstractVial(T first) {
        set(0, first);
    }

    public abstract Stream<T> colors();

    public abstract T emptyColor();

    public void set(int pos, T value) {
        colors.set(pos, value);
    }

    public void transfer(AbstractVial<T> other) {
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals(emptyColor())) {
                colors.set(i, other.colors.get(i));
                other.colors.set(i, other.emptyColor());
                break;
            }
        }
    }

    public boolean isEmpty() {
        return colors().allMatch(c -> c.equals(emptyColor()));
    }

    public boolean isSolved() {
        T color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }
}

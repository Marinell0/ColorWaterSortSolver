package vials;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractVial<T> {
    protected ArrayList<T> colors = new ArrayList<>(4);

    protected AbstractVial(T first, T second, T third, T fourth) {
        colors.add(first);
        colors.add(second);
        colors.add(third);
        colors.add(fourth);
    }

    public abstract Stream<T> colors();

    public abstract T emptyColor();

    public void set(int pos, T value) {
        colors.set(pos, value);
    }

    public boolean isEmpty() {
        return colors().allMatch(c -> c.equals(emptyColor()));
    }

    public boolean isSolved() {
        T color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }
}

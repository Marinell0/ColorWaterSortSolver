package vials;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractVial<T> {
    private static final int MAX_SIZE = 4;
    protected ArrayList<T> colors = new ArrayList<>(MAX_SIZE);
    protected int size = 0;

    /**
     * Generates a vial with the given colors, padded with {@link AbstractVial#emptyColor()}
     * @param colors
     */
    protected AbstractVial(T ...colors) {
        this();
        for (int i = 0; i < colors.length && i < MAX_SIZE; i++) {
            this.colors.set(i, colors[i]);
            this.size++;
        }
    }

    /**
     * Generates an empty vial with no colors.
     */
    protected AbstractVial() {
        Stream<T> emptyColors = Stream.of(emptyColor(), emptyColor(), emptyColor(), emptyColor());
        colors.addAll(emptyColors.toList());
        this.size = 0;
    }

    /**
     * Stream of colors in the vial
     * @return Stream of generic colors T
     */
    public abstract Stream<T> colors();

    /**
     * The value that represents an empty color
     * @return The empty color T
     */
    public abstract T emptyColor();

    public abstract String name(T color);

    public final void set(int pos, T value) {
        if (pos == this.size - 1 && value.equals(emptyColor())) {
            this.size--;
        } else if (pos == this.size && !value.equals(emptyColor())) {
            this.size++;
        }
        colors.set(pos, value);
    }

    public final T get(int pos) {
        if (this.colors.size() <= pos || pos >= MAX_SIZE) {
            return emptyColor();
        }
        return colors.get(pos);
    }

    public final boolean isEmpty() {
        return this.size == 0 || this.colors().allMatch(c -> c.equals(emptyColor()));
    }

    public final boolean isSolved() {
        T color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }

    public final boolean transfer(AbstractVial<T> other) {
        if (this.isEmpty()) {
            return false;
        }

        int pos = this.size - 1;
        T color = get(pos);

        if (other.size > 0 && other.get(other.size - 1) != color) {
            return false;
        }

        do {
            T toTransfer = get(pos);
            other.set(other.size, toTransfer);
            this.set(this.size - 1, emptyColor());
            pos--;
        } while (pos > 0 && get(pos) == color);

        return true;
    }

    public String toString() {
        return "[" + this.size + "] - " + colors().map(this::name).reduce("", (a, b) -> {
            if (a.isEmpty()) {
                return b;
            }
            return a + " - " + b;
        });
    }
}

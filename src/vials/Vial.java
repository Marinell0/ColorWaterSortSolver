package vials;

import java.util.ArrayList;
import java.util.stream.Stream;

import colorenum.Colors;

public class Vial {
    private static final int MAX_SIZE = 4;
    private ArrayList<Colors> colors = new ArrayList<>(MAX_SIZE);
    private int size = 0;

    /**
     * Generates a vial with the given colors, padded with {@link Vial#emptyColor()}
     * @param colors
     */
    public Vial(Colors ...colors) {
        this();
        for (int i = 0; i < colors.length && i < MAX_SIZE; i++) {
            this.colors.set(i, colors[i]);
            this.size++;
        }
    }

    /**
     * Generates an empty vial with no colors.
     */
    public Vial() {
        Stream<Colors> emptyColors = Stream.of(emptyColor(), emptyColor(), emptyColor(), emptyColor());
        colors.addAll(emptyColors.toList());
        this.size = 0;
    }

    /**
     * Stream of colors in the vial
     * @return Stream of generic colors T
     */
    public Stream<Colors> colors(){
        return this.colors.stream();
    }

    /**
     * The value that represents an empty color
     * @return The empty color T
     */
    public static Colors emptyColor() {
        return Colors.EMPTY;
    }

    public static String name(Colors color) {
        return color.toString();
    }

    public final void set(int pos, Colors value) {
        if (pos == this.size - 1 && value.equals(emptyColor())) {
            this.size--;
        } else if (pos == this.size && !value.equals(emptyColor())) {
            this.size++;
        }
        colors.set(pos, value);
    }

    public final Colors get(int pos) {
        if (this.colors.size() <= pos || pos >= MAX_SIZE) {
            return emptyColor();
        }
        return colors.get(pos);
    }

    public final boolean isEmpty() {
        return this.size == 0 || this.colors().allMatch(c -> c.equals(emptyColor()));
    }

    public final boolean isSolved() {
        Colors color = colors().findFirst().orElse(emptyColor());
        return colors().allMatch(c -> c.equals(color));
    }

    public final boolean transfer(Vial other) {
        if (this.isEmpty()) {
            return false;
        }

        int pos = this.size - 1;
        Colors color = get(pos);

        if ((other.size > 0 && other.get(other.size - 1) != color) || other.size == MAX_SIZE) {
            return false;
        }

        do {
            Colors toTransfer = get(pos);
            other.set(other.size, toTransfer);
            this.set(this.size - 1, emptyColor());
            pos--;
        } while (pos > 0 && other.size != MAX_SIZE && get(pos) == color);

        return true;
    }

    public String toString() {
        return "[" + this.size + "] - " + colors().map(Colors::toString).reduce("", (a, b) -> {
            if (a.isEmpty()) {
                return b;
            }
            return a + " - " + b;
        });
    }
}

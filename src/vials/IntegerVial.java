package vials;

import java.util.stream.Stream;

public class IntegerVial extends AbstractVial<Integer> {
    public IntegerVial() {
        super(0, 0, 0, 0);
    }

    public IntegerVial(Integer first, Integer second, Integer third, Integer fourth) {
        super(first, second, third, fourth);
    }

    public Stream<Integer> colors() {
        return this.colors.stream();
    }

    @Override
    public boolean isSolved() {
        Integer color = colors.get(0);
        return colors().allMatch(c -> c.equals(color));
    }

    @Override
    public Integer emptyColor() {
        return 0;
    }

    public String toString() {
        return colors().map(c -> c.toString()).reduce("", (a, b) -> {
            if (a.isEmpty()) {
                return b;
            }
            return a + " - " + b;
        });
    }
}

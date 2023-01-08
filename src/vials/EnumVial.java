package vials;

import java.util.stream.Stream;

import colorenum.Colors;

public class EnumVial extends AbstractVial<Colors> {
    public EnumVial() {
        super(Colors.EMPTY, Colors.EMPTY, Colors.EMPTY, Colors.EMPTY);
    }

    public EnumVial(Colors first, Colors second, Colors third, Colors fourth) {
        super(first, second, third, fourth);
    }

    @Override
    public Colors emptyColor() {
        return Colors.EMPTY;
    }

    @Override
    public Stream<Colors> colors() {
        return this.colors.stream();
    }

    public String toString() {
        return colors().map(Enum::name).reduce("", (a, b) -> {
            if (a.isEmpty()) {
                return b;
            }
            return a + " - " + b;
        });
    }
}

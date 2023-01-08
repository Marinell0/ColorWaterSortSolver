package vials;

import java.util.stream.Stream;

import colorenum.Colors;

public class EnumVial extends AbstractVial<Colors> {
    public EnumVial() {
        super();
    }

    public EnumVial(Colors first) {
        super(first);
    }

    public EnumVial(Colors first, Colors second) {
        super(first, second);
    }

    public EnumVial(Colors first, Colors second, Colors third) {
        super(first, second, third);
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

    @Override
    public String name(Colors color) {
        return color.name();
    }
}

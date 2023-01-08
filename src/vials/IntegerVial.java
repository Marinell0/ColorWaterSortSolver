package vials;

import java.util.stream.Stream;

public class IntegerVial extends AbstractVial<Integer> {
    public IntegerVial() {
        super();
    }

    public IntegerVial(Integer first) {
        super(first);
    }

    public IntegerVial(Integer first, Integer second) {
        super(first, second);
    }

    public IntegerVial(Integer first, Integer second, Integer third) {
        super(first, second, third);
    }

    public IntegerVial(Integer first, Integer second, Integer third, Integer fourth) {
        super(first, second, third, fourth);
    }

    public Stream<Integer> colors() {
        return this.colors.stream();
    }

    @Override
    public Integer emptyColor() {
        return 0;
    }

    @Override
    public String name(Integer color) {
        return color.toString();
    }
}

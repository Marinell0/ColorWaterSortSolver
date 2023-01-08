package reader;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import colorenum.Colors;
import vials.Vial;

public class RandomBoard {
    private Random random;

    public RandomBoard(int seed) {
        this.random = new Random(seed);
    }

    public RandomBoard() {
        this(42);
    }

    /**
     * Returns a random game with vials.
     */
    public Vial[] game() {
        int numColors = random.nextInt(8, 13);
        int numVials = numColors + 2;
        Colors[] colors = Colors.getColors();

        Vial[] vials = new Vial[numVials];
        for (int i = 0; i < numVials; i++) {
            vials[i] = new Vial();
        }

        HashMap<Colors, Integer> colorsToUseMap = new HashMap<>();
        for (int i = 0; i < numColors; i++) {
            colorsToUseMap.put(colors[i], 4);
        }

        for (int vial = 0; vial < numColors; vial++) {
            for (int j = 0; j < 4; j++) {
                Set<Colors> colorsToUse = EnumSet.copyOf(colorsToUseMap.keySet());
                Colors color = colorsToUse.toArray(new Colors[0])[random.nextInt(colorsToUse.size())];
                vials[vial].set(j, color);
                colorsToUseMap.computeIfPresent(color, (Colors k, Integer v) -> {
                    int newValue = v - 1;
                    return newValue == 0 ? null : newValue;
                });
            }
        }

        Arrays.stream(vials).forEach(System.out::println);

        return vials;
    }
}

package reader;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import colorenum.Colors;
import vials.Vial;

public class RandomBoard implements Board {
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

        Map<Colors, Integer> colorsToUseMap = new EnumMap<>(Colors.class);
        for (int i = 0; i < numColors; i++) {
            colorsToUseMap.put(colors[i], 4);
        }

        for (int vial = 0; vial < numColors; vial++) {
            for (int j = 0; j < 4; j++) {
                Set<Colors> colorsToUse = EnumSet.copyOf(colorsToUseMap.keySet());
                int randomColor = random.nextInt(colorsToUse.size());
                Colors color = colorsToUse.toArray(new Colors[0])[randomColor];

                vials[vial].set(j, color);

                colorsToUseMap.computeIfPresent(color, (Colors k, Integer v) -> {
                    int newValue = v - 1;
                    return newValue == 0 ? null : newValue;
                });
            }
        }

        return vials;
    }
}

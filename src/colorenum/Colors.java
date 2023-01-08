package colorenum;

public enum Colors {
    BLUE,
    CYAN,
    LIGHT_GREEN,
    GREEN,
    DARK_GREEN,
    YELLOW,
    ORANGE,
    BROWN,
    RED,
    PINK,
    PURPLE,
    GREY,
    EMPTY;

    private static Colors[] colors = Colors.values();

    public static Colors[] getColors() {
        return colors;
    }
}

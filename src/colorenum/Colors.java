package colorenum;

public enum Colors {
    BLUE(0, 0, 255),
    CYAN(0, 255, 255),
    LIGHT_GREEN(144, 238, 144),
    GREEN(0, 255, 0),
    DARK_GREEN(0, 100, 0),
    YELLOW(255, 255, 0),
    ORANGE(255, 165, 0),
    BROWN(165, 42, 42),
    RED(255, 0, 0),
    PINK(255, 192, 203),
    PURPLE(128, 0, 128),
    GREY(128, 128, 128),
    EMPTY(0, 0, 0);

    private int r;
    private int g;
    private int b;

    private Colors(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private static Colors[] colors = Colors.values();

    public static Colors[] getColors() {
        return colors;
    }

    public String toString() {
        return this.consoleFGColor() + this.name() + this.consoleColorReset();
    }
    
    public String consoleFGColor() {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }

    public String consoleBGColor() {
        return "\u001B[48;2;" + r + ";" + g + ";" + b + "m";
    }

    public String consoleColorReset() {
        return "\u001B[0m";
    }
}

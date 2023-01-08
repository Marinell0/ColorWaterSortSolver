package main;

import vials.EnumVial;

public class App {
    public static void main(String[] args) {
        EnumVial[] vials = new EnumVial[8];
        for (int i = 0; i < vials.length; i++) {
            vials[i] = new EnumVial();
        }
    }
}

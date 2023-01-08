package main;

import java.util.Arrays;

import reader.Board;
import reader.RandomBoard;
import vials.Vial;

public class App {
    public static void main(String[] args) {
        Board board = new RandomBoard(2);
        Vial[] vials = board.game();

        Arrays.stream(vials).forEach(System.out::println);
    }
}

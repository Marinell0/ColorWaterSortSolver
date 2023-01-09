package main;

import java.util.Arrays;

import reader.Board;
import reader.RandomBoard;

public class App {
    public static void main(String[] args) {
        Board board = new RandomBoard(0);

        Arrays.stream(board.getVials()).forEach(System.out::println);
    }
}

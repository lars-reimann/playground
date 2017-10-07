package net.bplaced.programmierung.vierGewinnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Engine {
    
    public Engine() {
        display = new Display();
        computer = new Computer(this);
    }

    public static boolean isWonPlayer(final long mapPlayer) {
        return (mapPlayer & mapPlayer >>> 7 & mapPlayer >>> 14 & mapPlayer >>> 21) != 0 ||
               (mapPlayer & mapPlayer >>> 8 & mapPlayer >>> 16 & mapPlayer >>> 24) != 0 ||
               (mapPlayer & mapPlayer >>> 6 & mapPlayer >>> 12 & mapPlayer >>> 18 & Data.MAP_RIGHT_FOUR_COLUMNS) != 0 ||
               (mapPlayer & mapPlayer >>> 1 & mapPlayer >>> 2 & mapPlayer >>> 3 & Data.MAP_LEFT_FOUR_COLUMNS) != 0;
    }

    private long mapOccupied = 0x0000000000000000l;

    public long mapPlayerNeg = 0x0000000000000000l;

    public long mapPlayerPos = 0x0000000000000000l;
    
    private byte[] mapHeight = { 0, 0, 0, 0, 0, 0, 0 };

    int player = -1;
    
    private final Display display;
    
    private final Computer computer;

    public void doMove(final int column, final int player) {
        final int move = column + 7 * mapHeight[column];
        mapHeight[column]++;
        if (player == -1) {
            doMovePlayerNeg(move);
        } else if (player == 1) {
            doMovePlayerPos(move);
        } else {
            throw new AssertionError("Wert von Player ungueltig: " + player);
        }
        mapOccupied |= Data.MAPS_FIELD[move];
        swapPlayer();
    }

    private void doMovePlayerNeg(final int move) {
        mapPlayerNeg |= Data.MAPS_FIELD[move];
    }

    private void doMovePlayerPos(final int move) {
        mapPlayerPos |= Data.MAPS_FIELD[move];
    }

    public boolean isDraw() {
        return mapOccupied == Data.MAP_FULL;
    }

    private boolean isEnd() {
        return isDraw() || isWon();
    }

    private boolean isValid(final int column) {
        return column + 7 * mapHeight[column] < 42;
    }

    private boolean isWon() {
        return isWonPlayerNeg() || isWonPlayerPos();
    }

    private boolean isWonPlayerNeg() {
        return isWonPlayer(mapPlayerNeg);
    }
    
    public boolean isWonForPlayer(final int player) {
        return player == -1 ? isWonPlayerNeg() : isWonPlayerPos(); 
    }

    private boolean isWonPlayerPos() {
        return isWonPlayer(mapPlayerPos);
    }

    public List<Integer> genMoves() {
        final List<Integer> moves = new ArrayList<Integer>(7);
        if (isValid(3)) moves.add(3);
        if (isValid(2)) moves.add(2);
        if (isValid(4)) moves.add(4);
        if (isValid(1)) moves.add(1);
        if (isValid(5)) moves.add(5);
        if (isValid(0)) moves.add(0);
        if (isValid(6)) moves.add(6);
        return moves;
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        display.display(mapPlayerNeg, mapPlayerPos);
        while (!isEnd()) {
            System.out.print("\nZug: ");
            final int move = scanner.nextInt();
            if (isValid(move)) {
                doMove(move, player);
                display.display(mapPlayerNeg, mapPlayerPos);
                if (!isEnd()) {
                    computer.doComputerMove(player);
                    display.display(mapPlayerNeg, mapPlayerPos);
                }
            }
        }
    }

    private void swapPlayer() {
        player = -player;
    }

    void undoMove(final int column, final int player) {
        mapHeight[column]--;
        final int move = column + 7 * mapHeight[column];
        if (player == -1) {
            undoMovePlayerNeg(move);
        } else if (player == 1) {
            undoMovePlayerPos(move);
        } else {
            throw new AssertionError("Wert von Player ungueltig: " + player);
        }
        mapOccupied ^= Data.MAPS_FIELD[move];
        swapPlayer();
    }

    private void undoMovePlayerPos(final int move) {
        mapPlayerPos ^= Data.MAPS_FIELD[move];
    }

    private void undoMovePlayerNeg(final int move) {
        mapPlayerNeg ^= Data.MAPS_FIELD[move];
    }

    public void display() {
        display.display(mapPlayerNeg, mapPlayerPos);
    }
}

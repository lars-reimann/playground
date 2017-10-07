package net.bplaced.programmierung.vierGewinnt;


public final class Display {

    public void display(final long mapPlayerNeg, final long mapPlayerPos) {
        System.out.println();
        for (int i = 35; i >= 0; i -= 7) {
            for (int j = i; j < i + 7; j++) {
                if ((Data.MAPS_FIELD[j] & mapPlayerNeg) != 0) {
                    System.out.print("X");
                } else if ((Data.MAPS_FIELD[j] & mapPlayerPos) != 0) {
                    System.out.print("O");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}

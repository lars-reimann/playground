package eu.codelottery.tictactoe;

import java.util.Scanner;

public class IO {
	public static int requestMove() {
		Scanner scanner = new Scanner(System.in);
		int move = scanner.nextInt();
		scanner.close();
		return move;
	}

	public void display(final short mapPlayerNeg, final short mapPlayerPos) {
		System.out.println();
		for (int i = 6; i >= 0; i -= 3) {
			for (int j = i; j < i + 3; j++) {
				if ((Game.BOARD_FIELD[j] & mapPlayerNeg) != 0) {
					System.out.print("X");
				} else if ((Game.BOARD_FIELD[j] & mapPlayerPos) != 0) {
					System.out.print("O");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
}

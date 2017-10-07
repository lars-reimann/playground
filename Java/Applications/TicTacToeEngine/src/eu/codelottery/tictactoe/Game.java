package eu.codelottery.tictactoe;

public final class Game {

	public enum Status {
		DRAW, ONGOING, VICTORY_PLAYER_ONE, VICTORY_PLAYER_TWO;
	}
	
	public enum Player {
		PLAYER_ONE, PLAYER_TWO;
		
		public Player other() {
			if (this == PLAYER_ONE) {
				return PLAYER_TWO;
			} else {
				return PLAYER_ONE;
			}
		}
	}

	/**
	 * Codes of the individual fields. This uses little endian, so the first
	 * field has the highest value.
	 */
	public static final short BOARD_FIELD[] = { 0x0100, 0x0080, 0x0040, 0x0020,
			0x0010, 0x0008, 0x0004, 0x0002, 0x0001 };

	/**
	 * Code of a full board.
	 */
	public static final short FULL_BOARD = 0x01FF;

	/**
	 * Precomputed table of won positions.
	 */
	public static final boolean WON_BOARDS[] = { false, false, false, false,
			false, false, false, true, false, false, false, false, false,
			false, false, true, false, false, false, false, false, false,
			false, true, false, false, false, false, false, false, false, true,
			false, false, false, false, false, false, false, true, false,
			false, false, false, false, false, false, true, false, false,
			false, false, false, false, false, true, true, true, true, true,
			true, true, true, true, false, false, false, false, false, false,
			false, true, false, true, false, true, false, true, false, true,
			false, false, false, false, true, true, true, true, false, true,
			false, true, true, true, true, true, false, false, false, false,
			false, false, false, true, false, true, false, true, false, true,
			false, true, false, false, false, false, true, true, true, true,
			true, true, true, true, true, true, true, true, false, false,
			false, false, false, false, false, true, false, false, false,
			false, false, false, false, true, false, false, true, true, false,
			false, true, true, false, false, true, true, false, false, true,
			true, false, false, false, false, false, false, false, true, false,
			false, false, false, false, false, false, true, false, false, true,
			true, false, false, true, true, true, true, true, true, true, true,
			true, true, false, false, false, false, false, false, false, true,
			false, true, false, true, false, true, false, true, false, false,
			true, true, true, true, true, true, false, true, true, true, true,
			true, true, true, false, false, false, false, false, false, false,
			true, false, true, false, true, false, true, false, true, false,
			false, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, false, false, false, false, false, false,
			false, true, false, false, false, false, false, false, false, true,
			false, true, false, true, false, true, false, true, false, true,
			false, true, false, true, false, true, false, false, false, false,
			true, true, true, true, false, false, false, false, true, true,
			true, true, false, true, false, true, true, true, true, true, true,
			true, true, true, true, true, true, true, false, false, false,
			false, false, false, false, true, false, true, false, true, false,
			true, false, true, false, true, false, true, true, true, true,
			true, false, true, false, true, true, true, true, true, false,
			false, false, false, true, true, true, true, false, true, false,
			true, true, true, true, true, false, true, false, true, true, true,
			true, true, true, true, true, true, true, true, true, true, false,
			false, false, false, false, false, false, true, false, false,
			false, false, false, false, false, true, false, true, true, true,
			false, true, true, true, false, true, true, true, false, true,
			true, true, false, false, false, false, true, true, true, true,
			false, false, false, false, true, true, true, true, false, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, false, false, false, false, false, false, false, true, false,
			false, false, false, false, false, false, true, false, false,
			false, false, false, false, false, true, false, false, false,
			false, false, false, false, true, false, false, false, false,
			false, false, false, true, false, false, false, false, false,
			false, false, true, false, false, false, false, false, false,
			false, true, true, true, true, true, true, true, true, true, false,
			false, false, false, false, false, false, true, false, true, false,
			true, false, true, false, true, false, false, false, false, true,
			true, true, true, false, true, false, true, true, true, true, true,
			false, false, false, false, false, false, false, true, false, true,
			false, true, false, true, false, true, false, false, false, false,
			true, true, true, true, true, true, true, true, true, true, true,
			true, false, false, false, false, false, false, false, true, false,
			false, false, false, false, false, false, true, false, false, true,
			true, false, false, true, true, false, false, true, true, false,
			false, true, true, false, false, false, false, false, false, false,
			true, false, false, false, false, false, false, false, true, false,
			false, true, true, false, false, true, true, true, true, true,
			true, true, true, true, true, false, false, false, false, false,
			false, false, true, false, true, false, true, false, true, false,
			true, false, false, true, true, true, true, true, true, false,
			true, true, true, true, true, true, true, false, false, false,
			false, false, false, false, true, false, true, false, true, false,
			true, false, true, false, false, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, false, false,
			false, false, false, false, false, true, false, false, false,
			false, false, false, false, true, false, true, false, true, false,
			true, false, true, false, true, false, true, false, true, false,
			true, false, false, false, false, true, true, true, true, false,
			false, false, false, true, true, true, true, false, true, false,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, false, false, false, false, false, false, false, true,
			false, true, false, true, false, true, false, true, false, true,
			false, true, true, true, true, true, false, true, false, true,
			true, true, true, true, false, false, false, false, true, true,
			true, true, false, true, false, true, true, true, true, true,
			false, true, false, true, true, true, true, true, true, true, true,
			true, true, true, true, true, false, false, false, false, false,
			false, false, true, false, false, false, false, false, false,
			false, true, false, true, true, true, false, true, true, true,
			false, true, true, true, false, true, true, true, false, false,
			false, false, true, true, true, true, false, false, false, false,
			true, true, true, true, false, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true };

	private short playerOneBoard = 0x0000;

	private short playerTwoBoard = 0x0000;

	private Player current = Player.PLAYER_ONE;

	public Player getCurrent() {
		return current;
	}
	
	/**
	 * 
	 * @param move The current player's move.
	 */
	public void move(int move) {
		if (current == Player.PLAYER_ONE) {
			playerOneBoard ^= BOARD_FIELD[move];
		} else {
			playerTwoBoard ^= BOARD_FIELD[move];
		}
		current = current.other();
	}

	public short getBoard() {
		return (short) (playerOneBoard | playerTwoBoard);
	}
	
	public short getBoard(Player player) {
		if (player == Player.PLAYER_ONE) {
			return playerOneBoard;
		} else {
			return playerTwoBoard;
		}
	}
	
	public short getPlayerTwoBoard() {
		return playerTwoBoard;
	}
	
	public short getPlayerOneBoard() {
		return playerOneBoard;
	}
}

package eu.codelottery.tictactoe;

import java.util.ArrayList;
import java.util.List;

import eu.codelottery.tictactoe.Game.Player;

public final class Engine {

	private short playerOneBoard;
	private short occupiedBoard;
	private short playerTwoBoard;
	private Player computer;

	private int alphaBeta(int alpha, int beta, Player player) {
		if (isWon(computer)) {
			return 1;
		} else if (isWon(computer.other())) {
			return -1;
		} else if (isDraw()) {
			return 0;
		}
		List<Integer> moves = genMoves();
		int best = alpha;
		for (int move : moves) {
			move(player, move);
			int value = -alphaBeta(-beta, -best, player.other());
			undo(player, move);
			if (value >= beta) {
				return beta;
			}
			if (value > best)
				best = value;
		}
		return best;
	}

	public int doComputerMove(short playerOneBoard, short playerTwoBoard, Player computer) {
		this.playerOneBoard = playerOneBoard;
		this.playerTwoBoard = playerTwoBoard;
		this.computer = computer;
		return searchRoot(computer);
	}

	public List<Integer> genMoves() {
		final List<Integer> moves = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (isValid(i)) {
				moves.add(i);
			}
		}
		return moves;
	}

	/**
	 * Returns true, if the game is drawn. It might also occur, that true is
	 * returned, although the game is not a draw. Thus, it has to be checked
	 * first if either side won the game.
	 *
	 * @return if the game is drawn.
	 */
	private boolean isDraw() {
		return occupiedBoard == Game.FULL_BOARD;
	}

	/**
	 * 
	 * @param move
	 * @return
	 */
	private boolean isValid(final int move) {
		return (occupiedBoard & Game.BOARD_FIELD[move]) == 0;
	}
	
	private boolean isWon(Player player) {
		if (player == Player.PLAYER_ONE) {
			return Game.WON_BOARDS[playerOneBoard];
		} else {
			return Game.WON_BOARDS[playerTwoBoard];
		}
	}
	
	private void move(Player player, int move) {
		if (player == Player.PLAYER_ONE) {
			playerOneBoard ^= Game.BOARD_FIELD[move];
		} else {
			playerTwoBoard ^= Game.BOARD_FIELD[move];
		}
	}
	
	private void undo(Player player, int move) {
		move(player, move);
	}

	private int searchRoot(Player player) {
		List<Integer> moves = genMoves();
		int bestMove = 0;
		int best = -1;
		for (int move : moves) {
			move(player, move);
			int value = -alphaBeta(-1, -best, player);
			undo(player, move);
			if (value > best) {
				best = value;
				bestMove = move;
			}
		}
		return bestMove;
	}
}

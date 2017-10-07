package eu.codelottery.tictactoe;

import eu.codelottery.tictactoe.Game.Player;

public class Referee {
	private final Engine computer;

	private final Game game;

	private final IO human;

	public Referee() {
		game = new Game();
		computer = new Engine();
		human = new IO();
	}

	public boolean isDraw() {
		return game.getBoard() == Game.FULL_BOARD;
	}

	private boolean isEnd() {
		return isDraw() || isWon();
	}

	public boolean isValid(int move) {
		return (game.getBoard() & Game.BOARD_FIELD[move]) == 0;
	}

	private boolean isWon() {
		return Game.WON_BOARDS[game.getBoard(game.getCurrent())];
	}

	public void start() {
		human.display(game.getBoard(Player.PLAYER_ONE), game.getBoard(Player.PLAYER_TWO));
		while (!isEnd()) {
			System.out.print("\nZug: ");
			int move = IO.requestMove();
			if (isValid(move)) {
				game.move(move);
				human.display(game.getBoard(Player.PLAYER_ONE), game.getBoard(Player.PLAYER_TWO));
				if (!isEnd()) {
					computer.doComputerMove(game.getBoard(Player.PLAYER_ONE), game.getBoard(Player.PLAYER_TWO), game.getCurrent());
					human.display(game.getBoard(Player.PLAYER_ONE), game.getBoard(Player.PLAYER_TWO));
				}
			}
		}
	}

}

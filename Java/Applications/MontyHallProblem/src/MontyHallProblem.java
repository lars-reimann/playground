import java.util.Scanner;


public final class MontyHallProblem {
	private final int nDoors;
	private final Door[] doors;
	
	private static class Door {
		boolean isOpen;
		boolean isChosen;
		boolean isWinning;
		
		Door(boolean isChosen, boolean isOpen, boolean isWinning) {
			this.isChosen = isChosen;
			this.isOpen = isOpen;
			this.isWinning = isWinning;
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < nDoors; i++) {
			if (doors[i].isOpen) {
				builder.append("| | ");
			} else if (doors[i].isChosen) {
				builder.append("|x| ");
			} else {
				builder.append("|?| ");
			}
		}
		builder.append("\n");
		builder.append("Legend: | | - open and empty; |x| - chosen by user; |?| - closed");
		return builder.toString();
	}
	
	public MontyHallProblem(int nDoors) {
		if (nDoors < 3) {
			throw new IllegalArgumentException("The game needs at least 3 doors.");
		}
		this.nDoors = nDoors;
		doors = new Door[nDoors];
	}
	
	private int chooseWinningDoor() {
		return (int) (Math.random() * nDoors);
	}
	
	public void simulate() {
		int winningDoor = chooseWinningDoor();
		int userDoor = requestUserDoor();
		listAllDoors(winningDoor, userDoor);
		int[] otherDoors = listOtherDoors(winningDoor, userDoor);
		openDoor(otherDoors);
		
	}
	
	private void listAllDoors(int winningDoor, int userDoor) {
		for (int i = 0; i < nDoors; i++) {
			boolean isChosen = i == userDoor;
			boolean isOpen = false;
			boolean isWinning = i == winningDoor;
			doors[i] = new Door(isChosen, isOpen, isWinning);
		}
	}

	private void openDoor(int[] otherDoors) {
		int door = (int) (Math.random() * otherDoors.length);
	}

	private int[] listOtherDoors(int winningDoor, int userDoor) {
		int[] otherDoors = new int[nDoors - 2];
		for (int i = 0, insertPos = 0; i < nDoors; i++) {
			if (i != winningDoor && i != userDoor) {
				otherDoors[insertPos++] = i;
			}
		}
		return otherDoors;
	}

	private int requestUserDoor() {
		Scanner scanner = new Scanner(System.in);
		int door;
		do {
			System.out.println("Which door do you choose? (0-" + (nDoors - 1));
			door = scanner.nextInt();
		} while (door < 0 || door >= nDoors);
		scanner.close();
		return door;
	}
}

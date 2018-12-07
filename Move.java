package PegSolitaireJava;

public class Move {
	private int[] move;
	Move(int[] move) {
		this.move = move;
	}

	public void set_move(int[] move) {
		this.move = move;
	}

	public int[] get_move() {
		return this.move;
	}

	public int get(int index) {
		if (index < move.length) {
			return move[index];
		} else {
			return -1;
		}
	}

	public String toString() {
		return "(" + move[0] + ", " + move[1] + ", " + move[2] + ")";
	}
}

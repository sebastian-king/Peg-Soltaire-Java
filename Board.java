package PegSolitaireJava;

public class Board {
	private int cells[];
	private int number_occupied;

	Board(int[] cells) {
		this.cells = cells;
		this.number_occupied = calculate_number_occupied();
	}

	Board(int[] cells, int number_occupied) {
		this.cells = cells;
		this.number_occupied = number_occupied;
	}

	private int calculate_number_occupied() {
		int number_occupied = 0;
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == 1) {
				number_occupied++;
			}
		}
		return number_occupied;
	}

	public int get_total_cells() {
		return cells.length;
	}

	public int get_number_occupied() {
		int n = 0;
		for (int i = 0; i < 15; i++) {
			if (cells[i] == 1) {
				n++;
			}
		}
		return n;
	}

	public void set_number_occupied(int number_occupied) {
		this.number_occupied = number_occupied;
	}

	public int get_cell(int cell_number) {
		return cells[cell_number];
	}

	public void set_cell(int cell_number, int value) {
		cells[cell_number] = value;
	}

	public int[] get_all_cells() {
		return this.cells;
	}

	public Board clone() {
		return new Board(this.cells, this.number_occupied);
	}
}

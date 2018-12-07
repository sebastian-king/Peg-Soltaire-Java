package PegSolitaireJava;

public class Driver {
	public int[] sentinal = {0,0,0};
	public Move sentinal_move = new Move(sentinal);

	public int[][] moves = {
		{0, 1, 3},
		{0, 2, 5},
		{1, 3, 6},
		{1, 4, 8},
		{2, 4, 7},
		{2, 5, 9},
		{3, 6, 10},
		{3, 7, 12},
		{4, 7, 11},
		{4, 8, 13},
		{5, 8, 12},
		{5, 9, 14},
		{3, 4, 5},
		{6, 7, 8},
		{7, 8, 9},
		{10, 11, 12},
		{11, 12, 13},
		{12, 13, 14}
	};

	public Move[] step_moves = steps();

	public Move[] steps() {
		Move[] steps = new Move[moves.length * 2];
		for (int i = 0; i < moves.length * 2; i+=2) {
			steps[i] = new Move(moves[i/2]);
			int[] tmp = new int[3];
			tmp[0] = steps[i].get(2);
			tmp[1] = steps[i].get(1);
			tmp[2] = steps[i].get(0);
			steps[i+1] = new Move(tmp);
		}
		return steps;
	}

	public Board move(Board board, Move move) {
		Board moved_board = board.clone();
		if (moved_board.get_cell(move.get(0)) == 1 && moved_board.get_cell(move.get(1)) == 1 && moved_board.get_cell(move.get(2)) == 0) {
			moved_board.set_cell(move.get(0), 0);
			moved_board.set_cell(move.get(1), 0);
			moved_board.set_cell(move.get(2), 1);
			moved_board.set_number_occupied(moved_board.get_number_occupied() - 1);
			return moved_board;
		} else {
			return null;
		}
	}

	public Board init(int starting_location) {
		int[] cells = new int[15];
		for (int i = 0; i < 15; i++) {
			cells[i] = 1;
		}
		cells[starting_location] = 0;
		Board board = new Board(cells);
		return board;
	}

	public Solution solve(Board board) {
		int[] cells = new int[15]; // this is to stop the variables being statically passed by reference throughout the recursion
		for (int i = 0; i < board.get_total_cells(); i++) {
			cells[i] = board.get_cell(i);
		}

		Solution solution = new Solution(board);

		if (board.get_number_occupied() < 2) {
			solution.add_move(sentinal_move);
			return solution;
		}

		Solution current_try = new Solution(board);

		int counter = 0;
		for (int i = 0; i < step_moves.length; i++) {
			if (cells[step_moves[i].get(0)]==1 && cells[step_moves[i].get(1)] == 1 && cells[step_moves[i].get(2)] == 0) { // if there is a possible move

				cells[step_moves[i].get(0)] = 0;
				cells[step_moves[i].get(1)] = 0;
				cells[step_moves[i].get(2)] = 1;

				solution.add_move(step_moves[counter]);
				current_try = solve(new Board(cells)); // better luck next time

				if (current_try.get_moves().isEmpty()) { // backtrack if no solution
					if (solution.get_moves().size()>=1) {
						solution.del_move(solution.get_moves().size()-1);
					}
					cells[step_moves[i].get(0)] = 1;
					cells[step_moves[i].get(1)] = 1;
					cells[step_moves[i].get(2)] = 0;
				} else { // otherwise, continue
					if (current_try.size() >= 1 && current_try.get_move(current_try.size()-1) == sentinal_move) {
						current_try.del_move((current_try.size()-1));
					}
					for (int j=0; j<current_try.size(); j++) {
						solution.add_move(current_try.get_move(j));
					}
					current_try.get_moves().clear();
					return solution;
				}
			}
			counter++;
		}
		solution.get_moves().clear();
		return solution;
	}

	public void puzzle(int starting_location) {
		Board board = init(starting_location);
		Solution solution = solve(board);
		replay(solution);
	}

	public void go() {
		int n = 5;
		for (int i = 0; i < n; i++) {
			System.out.println("\n === " + i + " ===");
			puzzle(n);
		}
	}

	public void replay(Solution solution) {
		for (int i = 0; i < solution.size(); i++) {
			display(move(solution.get_board(), solution.get_move(i)));
		}
	}

	public void display(Board board) {
		int n_on_row = 1;
		int n = 0;
		for (int i = 0; i < board.get_total_cells(); i++) {
			if (n == 0) {
				for (int j = 0; j < 6 - n_on_row; j++) {
					System.out.print(" ");
				}
			}

			if (board.get_cell(i) == 1) {
				System.out.print("x ");
			} else {
				System.out.print(". ");
			}

			n++;
			if (n == n_on_row) {
				System.out.println();
				n_on_row++;
				n = 0;
			}
		}
	}
}

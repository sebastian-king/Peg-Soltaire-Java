package PegSolitaireJava;

import java.util.ArrayList;

public class Solution {
	private ArrayList<Move> moves;
	private Board board;

	Solution() {
		moves = new ArrayList<Move>();
	}

	Solution(Board board) {
		this.board = board;
		moves = new ArrayList<Move>();
	}

	Solution(ArrayList<Move> moves, Board board) {
		this.moves = moves;
		this.board = board;
	}

	public int size() {
		return this.moves.size();
	}

	public void add_move(Move move) {
		this.moves.add(move);
	}

	public Move get_move(int index) {
		return this.moves.get(index);
	}

	public void del_move(int move_index) {
		this.moves.remove(move_index);
	}

	public void del_move(Move move) {
		this.moves.remove(move);
	}

	public void set_board(Board board) {
		this.board = board;
	}

	public ArrayList<Move> get_moves() {
		return this.moves;
	}

	public Board get_board() {
		return this.board;
	}
}

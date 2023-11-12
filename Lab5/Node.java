package Lab5;

import java.util.Arrays;

public class Node {
	private int[][] state;
	private int h;
	private int g;
	private String label;
	private Node parentNode;

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Node(int row, int col) {
		this.state = new int[row][col];
	}

	// Copy a node
	public Node(Node node) {
		this.state = new int[node.state.length][node.state[0].length];
		for (int i = 0; i < node.state.length; i++) {
			for (int j = 0; j < node.state[i].length; j++) {
				state[i][j] = node.state[i][j];
			}
		}
	}

	public int getG() {
		return this.g;
	}

	public int getF() {
		return this.g + this.h;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getRow() {
		return this.state.length;
	}

	public int getColumn() {
		return this.state[0].length;
	}

	// Get the location of a tile in the board
	public int[] getLocation(int tile) {
		int[] result = new int[2];
		for (int i = 0; i < this.state.length; i++) {
			for (int j = 0; j < this.state[i].length; j++) {
				if (this.state[i][j] == tile) {
					result[0] = i;
					result[1] = j;
				}
			}
		}
		return result;
	}

	// Update the position of a tile
	public void updateTile(int row, int col, int value) {
		this.state[row][col] = value;
	}

	public Integer getTile(int row, int column) {
		try {
			int tile = this.state[row][column];
			return tile;
		} catch (Exception e) {
			return (Integer) null;
		}
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			Node that = (Node) obj;
			return Arrays.deepEquals(this.state, that.state);
		}
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				output.append(state[i][j] + " ");
			}
			output.append("\n");
		}

		return output.toString();
	}

	public Node getParent() {
		return this.parentNode;
	}

}

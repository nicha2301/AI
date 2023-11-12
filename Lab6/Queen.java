package Lab6;

public class Queen {
    private int row;
    private int column;

    public Queen(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }

    public void move() {
        if (this.row < 7)
            this.row += 1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Queen(row, column);
    }

    // check whether this Queen can attack the given Queen (q)
    public boolean isConflict(Queen q) {
        // Enter your code here
        if (row == q.getRow()) return true;
        if (column == q.getColumn()) return true;
        if (Math.abs(row - q.getRow()) == Math.abs(column - q.getColumn())) return true;
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}

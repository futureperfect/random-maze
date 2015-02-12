package io.tense.exercise.maze;

public class CartesianCell {

    public final int xCoordinate;
    public final int yCoordinate;

    public CartesianCell(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public boolean equals(CartesianCell cell) {
        return cell != null
                && this.xCoordinate == cell.xCoordinate
                && this.yCoordinate == cell.yCoordinate;
    }

    public String toString() {
        return "{" + xCoordinate + "," + yCoordinate + "}";
    }
}

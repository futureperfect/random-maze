package io.tense.exercise.maze;

public class Wall {

    public final CartesianCell c1;
    public final CartesianCell c2;

    public Wall(CartesianCell c1, CartesianCell c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public boolean equals(Wall wall) {
        return wall != null
                && this.c1.equals(wall.c1)
                && this.c2.equals(wall.c2);
    }

    public String toString() {
        return "<" + c1.toString() + ", " + c2.toString() + ">";
    }
}

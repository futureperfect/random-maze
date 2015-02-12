package io.tense.exercise.maze;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CartesianMaze {

    public final int width;
    public final int height;
    private final CartesianCell[][] cells;
    private final List<Wall> walls;

    private CartesianMaze(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new CartesianCell[width][height];
        walls = new ArrayList<Wall>();

        initializeCells(width, height);
        initializeWalls();
    }

    public static CartesianMaze withDimensions(int width, int height) {
        return new CartesianMaze(width, height);
    }

    public Iterable<CartesianCell> getCells() {
        ArrayList<CartesianCell> c = new ArrayList<>();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                c.add(cells[i][j]);
            }
        }
        return c;
    }

    public List<Wall> getWalls() {
        return Lists.newArrayList(this.walls);
    }

    public void removeWalls(Collection<Wall> wallsToRemove) {
        walls.removeAll(wallsToRemove);
    }

    private void initializeCells(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                CartesianCell c = new CartesianCell(i, j);
                cells[i][j] = c;
            }
        }
    }

    private void initializeWalls() {
        for (int j = 0; j < cells[0].length; j++) {
            for (int i = 0; i < cells.length; i++) {
                final CartesianCell sourceCell = cells[i][j];
                //for each cell if we fill in everything to the right and below we cover all internal walls
                Iterable<CartesianCell> adjacencies = getPositiveAdjacencies(sourceCell);
                for (CartesianCell adjacentCell : adjacencies) {
                    walls.add(new Wall(sourceCell, adjacentCell));
                }
            }
        }
    }

    private Iterable<CartesianCell> getPositiveAdjacencies(CartesianCell c) {
        List<CartesianCell> adjacencies = new ArrayList<>();
        if (c.yCoordinate + 1 < height) {
            adjacencies.add(cells[c.xCoordinate][c.yCoordinate + 1]);
        }
        if (c.xCoordinate + 1 < width) {
            adjacencies.add(cells[c.xCoordinate + 1][c.yCoordinate]);
        }
        return adjacencies;
    }

    public String toString() {
        return walls.toString();
    }


}

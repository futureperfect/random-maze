package io.tense.exercise.maze;

import io.tense.exercise.uf.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGenerator {

    private MazeGenerator() {
    }

    public static CartesianMaze newCartesianMazeWithSize(int width, int height) {
        CartesianMaze m = CartesianMaze.withDimensions(width, height);

        // create fast disjoint-set data structure
        UnionFind<CartesianCell> uf = new UnionFind<>(m.getCells());

        List<Wall> allMazeWalls = m.getWalls();
        Collections.shuffle(allMazeWalls);

        //collect walls we should knock out
        List<Wall> wallsToRemove = new ArrayList<>();

        // Knock out the maze wall if there wasn't already a path between either side of the wall
        allMazeWalls.stream().filter(wall -> !uf.connected(wall.c1, wall.c2)).forEach(wall -> {
            uf.union(wall.c1, wall.c2);
            wallsToRemove.add(wall);
        });

        m.removeWalls(wallsToRemove);

        return m;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CartesianMaze m = MazeGenerator.newCartesianMazeWithSize(10, 10);

        /*
        Rendering of this could be simplified at the expense of being somewhat
        unexpected in interface by making this matrix row major rather than column major
        */
        char[][] asciiMaze = ASCIIFormatter.asciiRepresentation(m);

        for (int j = 0; j < asciiMaze[0].length; j++) {
            for (int i = 0; i < asciiMaze.length; i++) {
                System.out.print(asciiMaze[i][j]);
            }
            System.out.print("\n");
        }
    }

}

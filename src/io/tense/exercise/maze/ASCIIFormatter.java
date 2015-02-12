package io.tense.exercise.maze;

public class ASCIIFormatter {

    private ASCIIFormatter() {
    }

    public static char[][] asciiRepresentation(CartesianMaze cm) {
        char[][] resultArray = new char[2 * cm.width + 1][2 * cm.height + 1];

        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[0].length; j++) {
                resultArray[i][j] = ' ';
            }
        }

        //draw in top and bottom
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i][0] = '_';
            resultArray[i][resultArray[0].length - 1] = '_';
        }

        //draw in sides
        for (int i = 0; i < resultArray[0].length; i++) {
            resultArray[0][i] = '|';
            resultArray[resultArray.length - 1][i] = '|';
        }

        //fill in walls
        for (Wall w : cm.getWalls()) {
            if (w.c1.xCoordinate == w.c2.xCoordinate) {
                //draw in horizontal wall
                resultArray[w.c1.xCoordinate * 2 + 1][w.c1.yCoordinate * 2 + 2] = '_';
            } else {
                resultArray[w.c1.xCoordinate * 2 + 2][w.c1.yCoordinate * 2 + 1] = '|';
            }
        }

        return resultArray;
    }
}
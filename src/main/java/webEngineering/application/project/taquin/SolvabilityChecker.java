package webEngineering.application.project.taquin;

import webEngineering.application.project.taquin.utils.ExpectedSolutionCalculator;

import java.io.Console;
import java.util.Vector;

public class SolvabilityChecker {

    static int[] snailTableUnfolder(int[][] board) {
        int[] unfoldTable = new int[(board.length * board.length) - 1];
        boolean goRight = true;
        boolean goDown = false;
        boolean goLeft = false;
        boolean goUp = false;

        int leftBound = -1;
        int rightBound = board.length;
        int topBound = -1;
        int botBound = board.length;

        int index = 0;
        /*while (index < (board.length * board.length) - 1) {
            if (goRight) {
                for (int j = leftBound + 1; j < rightBound; j++) {
                    if (board[topBound + 1][j] != 0) {
                        unfoldTable[index] = board[topBound + 1][j];
                        index++;
                    }
                }
                goRight = false;
                goDown = true;
                topBound++;
            } else if (goDown) {
                for (int i = topBound + 1; i < botBound; i++) {
                    if (board[i][rightBound - 1] != 0) {
                        unfoldTable[index] = board[i][rightBound - 1];
                        index++;
                    }
                }
                goDown = false;
                goLeft = true;
                rightBound--;
            } else if (goLeft) {
                for (int j = rightBound - 1; j > leftBound; j--) {
                    if (board[botBound - 1][j] != 0) {
                        unfoldTable[index] = board[botBound - 1][j];
                        index++;
                    }
                }
                goLeft = false;
                goUp = true;
                botBound--;
            } else {
                for (int i = botBound - 1; i > topBound; i--) {
                    if (board[i][leftBound + 1] != 0) {
                        unfoldTable[index] = board[i][leftBound + 1];
                        index++;
                    }
                }
                goUp = false;
                goRight = true;
                leftBound++;
            }
        }*/
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    unfoldTable[index] = board[i][j];
                    index++;
                }
            }
        }
        return (unfoldTable);
    }

    static int tileInversion(int current_i, int[] unfoldTable) {
        int res = 0;

        for (int i = current_i + 1; i < unfoldTable.length; i++) {
            if (unfoldTable[i] != 0 && unfoldTable[i] < unfoldTable[current_i])
                res++;
        }
        return (res);
    }

    static boolean calculateInversion(int[][] board) {

        int res = 0;
        int[] unfoldTable = snailTableUnfolder(board);

        for (int i = 0; i < unfoldTable.length; i++) {
            res += tileInversion(i, unfoldTable);
        }
        return (res % 2 == 0);
    }

    static boolean calculateBlankPos(int[][] board) {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    return (((board.length - i) % 2 == 0));
                }
            }
        }
        return (false); // never
    }

    public static boolean isSolvable (int[][] board)
    {
        boolean dimensionEven = board.length % 2 == 0;
        boolean inversionEven = calculateInversion(board);
        boolean blankOnEvenRow = calculateBlankPos(board);

        // ok for unsolvable 3 and 4
        //boolean solution1 = (dimensionEven && inversionEven);
        //boolean solution2 = (dimensionEven && !blankOnEvenRow && inversionEven);
        //boolean solution3 = (dimensionEven && blankOnEvenRow && !inversionEven);

        boolean solution1 = (!dimensionEven && inversionEven);
        boolean solution2 = (dimensionEven && !blankOnEvenRow && inversionEven);
        boolean solution3 = (dimensionEven && blankOnEvenRow && !inversionEven);

        boolean result = (solution1 || solution2 || solution3);
        return dimensionEven ? result : !result;
    }
}

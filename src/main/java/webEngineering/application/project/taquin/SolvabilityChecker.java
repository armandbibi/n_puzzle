package webEngineering.application.project.taquin;

public class SolvabilityChecker {

    static int tileInversion(int current_i, int current_j, int[][] board) {
        int res = 0;

        for (int i = current_i + 1; i < board.length; i++) {
            for (int j = current_j + 1; j < board.length; j++) {
                if (board[i][j] < board[current_i][current_j])
                    res++;
            }
        }
        return (res);
    }

    static boolean calculateInversion(int[][] board) {
        int res = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                res += tileInversion(i, j, board);
            }
        }
        return (res % 2 == 0);
    }

    static boolean calculateBlankPos(int[][] board) {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    return (((i + 1) % 2 == 0) ? true : false);
                }
            }
        }
        return (false); // should never get that
    }

    static boolean isSolvable (int[][] board)
    {
        boolean dimensionEven = (board.length % 2 == 0) ? true : false;
        boolean inversionEven = calculateInversion(board);
        boolean blankOnEvenRow = calculateBlankPos(board);

        return ((!dimensionEven && inversionEven)
                || (dimensionEven && !blankOnEvenRow && inversionEven)
                || (dimensionEven && blankOnEvenRow && !inversionEven));
    }
}

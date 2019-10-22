package webEngineering.application.project.taquin;

import java.util.Comparator;

public class NPuzzleTableComparator implements Comparator<NPuzzle> {

    @Override
    public int compare(NPuzzle p1,  NPuzzle p2) {
        int[][] t1 = p1.getBoard();
        int[][] t2 = p2.getBoard();

        int len = t1.length;

        for (int i = 0; i <  len; i++) {
            for (int j = 0; j< len; j++)
                if (t1[i][j] != t2[i][j])
                    return t1[i][j] - t2[i][j];
        }
        return 0;
    }
}

package webEngineering.application.project.taquinv2;


import org.junit.Test;
import webEngineering.application.project.taquinv2.utils.Direction;

import static org.junit.Assert.assertEquals;


public class StateTest {



    @Test
    public void checkEasyMatrix3By3() {

        State state = new State(3, null);
        int[][] test1= {{ 0, 0, 3 },{0,4,0},{5,0,0}};

        state.setBoardFromMatrix(test1);

        assertEquals(0x003, state.board[0]);
        assertEquals((int)(4 * Math.pow(2, 5)), state.board[1]);
        assertEquals((int)(5 * Math.pow(2, 10)), state.board[2]);

        int[][] resultMatrix = state.getBoardAsMatrix();
        assertEquals(3, resultMatrix[0][2]);
        assertEquals(4, resultMatrix[1][1]);
        assertEquals(5, resultMatrix[2][0]);
    }

    @Test
    public void checkMatrix4by4() {
        int[][] test = {
                {1,2,3,4},
                {5,6,7,8},
                {9,0,11,12},
                {13,14,15,10}};
        State state = new State(4, null);
        state.setBoardFromMatrix(test);
        int[][] result = state.getBoardAsMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(test[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testSwapUp() {

        int[][] test = {
                {1,2,3,4},
                {5,6,0,8},
                {9,10,11,12},
                {13,14,15,16}};

        int[][] expected =  {
                {1,2,0,4},
                {5,6,3,8},
                {9,10,11,12},
                {13,14,15,16}};
        State state = new State(4, null);
        state.setBoardFromMatrix(test);
        state.swap(Direction.UP);
        int[][] result = state.getBoardAsMatrix();
        int k = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }

    }

    @Test
    public void testSwapDown() {

        int[][] expected =  {
                {1,2,3,4},
                {5,6,11,8},
                {9,10,0,12},
                {13,14,15,16}};

        int[][] test = {
                {1,2,3,4},
                {5,6,0,8},
                {9,10,11,12},
                {13,14,15,16}};

        State state = new State(4, null);
        state.setBoardFromMatrix(test);
        state.swap(Direction.DOWN);
        int[][] result = state.getBoardAsMatrix();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testSwapRight() {

        int[][] expected =  {
                {1,2,3,4},
                {5,6,0,8},
                {9,10,11,12},
                {13,14,15,16}};

        int[][] test = {
                {1,2,3,4},
                {5,0,6,8},
                {9,10,11,12},
                {13,14,15,16}};

        State state = new State(4, null);
        state.setBoardFromMatrix(test);
        state.swap(Direction.RIGTH);
        int[][] result = state.getBoardAsMatrix();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testSwapLeft() {

        int[][] expected =  {
                {1,2,3,4},
                {5,0,6,8},
                {9,10,11,12},
                {13,14,15,16}};

        int[][] test = {
                {1,2,3,4},
                {5,6,0,8},
                {9,10,11,12},
                {13,14,15,16}};

        State state = new State(4, null);
        state.setBoardFromMatrix(test);
        state.swap(Direction.LEFT);
        int[][] result = state.getBoardAsMatrix();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }
    }

}

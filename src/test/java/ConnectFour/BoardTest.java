package ConnectFour;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

class BoardTest {

    private static Player playerX = new Player(1, 'X');
    private static Player playerO = new Player(1, 'O');

    @Test
    void winCheck() {
        char[][] state = {
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'X', 'X', 'O'}
        };
        Board board = new Board(state);
        assertFalse(board.getWinner(playerX, 1, 5));
        assertTrue(board.getWinner(playerO, 2, 4));
    }

    @Test
    void checkRow() {
        char[][] state = {
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'X', 'X', 'O', 'X', 'X', 'O'},
        };
        Board board = new Board(state);
        assertTrue(board.getWinner(playerX, 3, 4));
    }

    @Test
    void checkDiagonals01() {
        char[][] state = {
                {'O', 'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'X', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'X', 'O', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'X', 'X', 'O'},
        };
        Board board = new Board(state);
        assertTrue(board.getWinner(playerO, 4, 2));
        assertTrue(board.getWinner(playerX, 3, 2));
    }

    @Test
    void checkDiagonals02() {
        char[][] state = {
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'X', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'O', 'X', 'X', 'O', 'X', 'X', 'O'},
        };
        Board board = new Board(state);
        assertFalse(board.getWinner(playerX, 2, 1));
        assertFalse(board.getWinner(playerO, 3, 4));
    }

    @Test
    void checkMainDiagonal() {
        char[][] state = {
                {'-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-'},
                {'O', 'X', 'X', '-', '-', '-', '-'},
                {'O', 'X', 'O', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        };
        Board board = new Board(state);
        assertTrue(board.getWinner(playerX, 3, 3));
    }

    @Test
    void checkNextDiagonal() {
        char[][] state = {
                {'-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-', 'O', '-'},
                {'-', '-', '-', '-', 'O', '-', '-'},
                {'-', '-', '-', 'O', '-', '-', '-'},
                {'-', '-', 'O', '-', '-', '-', '-'}
        };
        Board board = new Board(state);
        assertTrue(board.getWinner(playerO, 3, 6));

    }

    @Test
    void getLines() {
        char[][] state = {
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
        };
        Board board = new Board(state);
        List<String> lines = board.getAllLines();
        for (String line : lines)
            System.out.println(line);
        assertTrue(true);
    }

}

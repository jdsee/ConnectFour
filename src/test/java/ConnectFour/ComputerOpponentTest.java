package ConnectFour;

import org.junit.Test;

import static org.junit.Assert.*;

class ComputerOpponentTest {

    private static Player playerO = new Player(1, 'O');
    private static Player playerX = new Player(2, 'X');
    private static ComputerOpponent computerX = new ComputerOpponent(playerX);
    private static ComputerOpponent computerO = new ComputerOpponent(playerO);

    @Test
    void getBestMove01() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', 'X', '·', '·', '·', '·'},
                {'·', '·', 'O', 'X', '·', '·', '·'},
                {'·', 'O', 'X', 'O', 'X', '·', '·'},
                {'O', 'X', 'X', 'X', 'O', 'O', '·'},
        };
        var board = new Board(state);
        assertEquals(4, computerX.chooseMove(board, playerO));
    }

    @Test
    void getBestMove02() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', 'X', '·', '·', '·'},
                {'·', '·', '·', 'O', 'X', 'O', '·'},
                {'·', '·', '·', 'X', 'O', 'X', '·'},
                {'·', '·', '·', 'O', 'O', 'O', '·'},
        };
        var board = new Board(state);
        assertEquals(7, computerX.chooseMove(board, playerO));
    }

    @Test
    void getBestMove03() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', 'O', 'X', '·', '·', '·'},
                {'·', '·', 'O', 'O', 'X', '·', '·'},
                {'·', '·', 'O', 'X', 'X', '·', '·'},
        };
        var board = new Board(state);
        assertEquals(3, computerX.chooseMove(board, playerO));
        assertEquals(3, computerO.chooseMove(board, playerX));
    }

    @Test
    void chooseMove01() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', 'X', 'O', '·', '·'},
                {'·', '·', 'X', 'O', 'X', '·', '·'},
                {'·', 'X', 'O', 'X', 'O', '·', '·'},
                {'O', 'X', 'O', 'X', 'X', 'X', '·'},
                {'O', 'O', 'X', 'X', 'O', 'O', '·'},
        };
        var board = new Board(state);
        assertEquals(5, computerX.chooseMove(board, playerO));
    }

    @Test
    void chooseMove02() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', 'O', 'O', '·', '·', '·'},
                {'·', '·', 'X', 'X', '·', '·', '·'},
                {'O', 'O', 'X', 'X', 'X', 'O', '·'},
        };
        var board = new Board(state);
        assertEquals(5, computerX.chooseMove(board, playerO));
        assertEquals(5, computerO.chooseMove(board, playerX));
    }

    @Test
    void chooseMove03() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', 'X', 'O', '·', '·'},
                {'·', '·', 'X', 'O', 'X', '·', '·'},
                {'O', 'O', 'O', 'X', 'O', '·', '·'},
                {'O', 'X', 'O', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'X', 'X', 'O', 'O', 'X'},
        };
        var board = new Board(state);
        assertEquals(6, computerX.chooseMove(board, playerO));
        assertEquals(1, computerO.chooseMove(board, playerX));
    }


    @Test
    void chooseMove_withoutHelpingTheOpponent() {
        char[][] state = {
                {'·', 'O', 'X', 'X', 'X', '·', '·'},
                {'·', 'X', 'O', 'O', 'X', '·', '·'},
                {'·', 'O', 'O', 'X', 'O', '·', '·'},
                {'·', 'X', 'X', 'O', 'O', 'O', '·'},
                {'·', 'O', 'O', 'X', 'X', 'X', '·'},
                {'·', 'X', 'O', 'O', 'X', 'O', '·'}
        };
        var board = new Board(state);
        assertNotEquals(6, computerX.chooseMove(board, playerO));
    }

    @Test
    void chooseMove_withoutHelpingTheOpponent2() {
        char[][] state = {
                {'·', '·', '·', 'O', '·', '·', '·'},
                {'·', '·', '·', 'O', '·', '·', '·'},
                {'·', '·', 'X', 'O', 'O', '·', '·'},
                {'·', '·', 'O', 'X', 'X', '·', '·'},
                {'·', 'O', 'O', 'O', 'O', '·', '·'},
                {'·', 'X', 'O', 'X', 'O', 'O', '·'},
        };
        var board = new Board(state);
        assertNotEquals(2, computerX.chooseMove(board, playerO));
    }

    @Test
    void chooseMove_opponentStartsInTHeMiddle() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', 'O', 'O', '·', '·', '·'},
                {'·', '·', 'X', 'O', '·', '·', '·'},
                {'·', '·', 'X', 'O', 'O', 'X', '·'},
        };
        var board = new Board(state);
        assertEquals(4, computerX.chooseMove(board, playerO));
    }

    @Test
    void chooseMoveAtStart() {

        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', 'X', '·', '·', '·'},
                {'·', '·', '·', 'O', 'O', '·', '·'},
        };
        var board = new Board(state);
        assertEquals(3, computerX.chooseMove(board, playerO));
    }

    @Test
    void chooseMoveToStart() {
        char[][] state = {
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', '·', '·', '·', '·'},
                {'·', '·', '·', 'X', 'O', '·', '·'},
                {'·', '·', 'X', 'O', 'O', '·', '·'},
        };
        var board = new Board(state);
        assertEquals(5, computerX.chooseMove(board, playerO));
    }

}
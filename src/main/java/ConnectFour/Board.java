package ConnectFour;

import ConnectFour.consoleFormat.Print;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final char[][] board;
    private int remainingTokens;

    public Board() {
        this.board = new char[6][7];
        for (int i = 0; i < this.getHeight(); i++)
            for (int j = 0; j < this.getWidth(); j++)
                this.board[i][j] = '·';
        remainingTokens = this.getWidth() * this.getHeight();
    }

    public Board(int rows, int columns) {
        this.board = new char[rows][columns];
        for (int i = 0; i < this.getHeight(); i++)
            for (int j = 0; j < this.getWidth(); j++)
                this.board[i][j] = '·';
        remainingTokens = this.getWidth() * this.getHeight();
    }

    Board(char[][] board) {
        this.board = new char[board.length][];
        System.arraycopy(board, 0, this.board, 0, board.length);
        remainingTokens = getHeight() * getWidth();
    }

    public int getWidth() {
        return this.board[0].length;
    }

    public int getHeight() {
        return this.board.length;
    }

    public List<String> getAllLines() {
        List<String> lines = new LinkedList<>();
        for (int i = 1; i <= getHeight(); i++) {
            lines.add(getHorizontal(i));
        }
        for (int i = 1; i <= getWidth(); i++) {
            lines.add(getVertical(i));
            lines.add(getMainDiagonal(getHeight() - 3, i));
            lines.add(getNextDiagonal(4, i));
        }
        return lines;
    }

    public boolean getWinner(Player player,
                             int row,
                             int col) {
        String[] lines = {
                getHorizontal(row),
                getVertical(col),
                getMainDiagonal(row, col),
                getNextDiagonal(row, col)
        };
        String regEx = String.format(".*%1$s%<s%<s%<s.*", player.getToken());
        for (String line : lines)
            if (line.matches(regEx)) return true;
        return false;
    }

    private String getMainDiagonal(int row,
                                   int col) {
        row--;
        col--;
        int length;
        if (row - col >= 0 && row - col < getHeight() - 3) {
            row -= col;
            col = 0;
            length = getHeight() - row;
        } else if (col - row > 0 && col - row < getWidth() - 3) {
            col -= row;
            row = 0;
            length = getWidth() - col;
        } else return "";
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++)
            line.append(board[row + i][col + i]);
        return line.toString();
    }

    private String getNextDiagonal(int row,
                                   int col) {
        row--;
        col--;
        int length;
        if (row + col > 2 && row + col < this.getHeight()) {
            row += col;
            col = 0;
            length = row + 1;
        } else if (col - (this.getHeight() - 1 - row) >= 0
                && col - (this.getHeight() - 1 - row) < this.getWidth() - 3) {
            col -= this.getHeight() - 1 - row;
            row = this.getHeight() - 1;
            length = this.getWidth() - col;
        } else return "";
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++)
            line.append(this.board[row - i][col + i]);
        return line.toString();
    }

    private String getVertical(int col) {
        col--;
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < this.getHeight(); i++)
            line.append(this.board[i][col]);
        return line.toString();
    }

    private String getHorizontal(int row) {
        row--;
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < this.getWidth(); i++)
            line.append(this.board[row][i]);
        return line.toString();
    }

    void show() {
        Print.seperator('–', Game.outputWidth);
        for (int i = 0; i < this.getHeight(); i++) {
            System.out.println();
            System.out.printf("%6s", this.board[i][0]);
            for (int j = 1; j < this.getWidth(); j++) {
                System.out.printf("%8s", this.board[i][j]);
            }
            System.out.println();
        }
        Print.seperator('–', Game.outputWidth);
        System.out.printf("%6d", (1));
        for (int i = 2; i <= this.getWidth(); i++) {
            System.out.printf("%8d", (i));
        }
        System.out.println();
        Print.seperator('–', Game.outputWidth);
    }

    public int put(int col,
                   char token) {
        for (int i = this.getHeight() - 1; i >= 0; i--) {
            if (this.board[i][col - 1] == '·') {
                this.board[i][col - 1] = token;
                this.remainingTokens--;
                return i + 1;
            }
        }
        return 0;
    }

    public boolean spaceLeftInCol(int col) {
        return this.board[0][col - 1] == '·';
    }

    public boolean tokensLeft() {
        return this.remainingTokens > 0;
    }

    public Board copyBoard() {
        char[][] arrayCopy = new char[this.board.length][];
        for (int i = 0; i < this.board.length; i++) {
            arrayCopy[i] = new char[this.board[i].length];
            System.arraycopy(this.board[i], 0, arrayCopy[i], 0, this.board[i].length);
        }
        Board fieldCopy = new Board(arrayCopy);
        fieldCopy.remainingTokens = this.remainingTokens;
        return fieldCopy;
    }

    void replaceTokens(Player player,
                       char newToken) {
        for (int i = 0; i < this.getHeight(); i++)
            for (int j = 0; j < this.getWidth(); j++)
                if (this.board[i][j] == player.getToken())
                    this.board[i][j] = newToken;
    }

}
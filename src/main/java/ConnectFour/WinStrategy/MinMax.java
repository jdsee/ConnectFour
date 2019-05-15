package ConnectFour.WinStrategy;

import ConnectFour.Board;
import ConnectFour.Player;

import java.util.LinkedList;
import java.util.List;

public class MinMax {

    public static State getBestMove(State root) {
        int highestValue = Integer.MIN_VALUE;
        State mostValuable = null;
        for (State move : root.getMoves()) {
            int currentValue = minValue(move);
            if (currentValue >= highestValue) {
                mostValuable = move;
                highestValue = currentValue;
            }
        }
        return mostValuable;
    }

    private static int minValue(State state) {
        if (state.isTerminal())
            return state.getEvaluation();
        int lowestValue = Integer.MAX_VALUE;
        for (State move : state.getMoves()) {
            int currentValue = maxValue(move);
            lowestValue = currentValue < lowestValue ? currentValue : lowestValue;
        }
        return lowestValue;
    }

    private static int maxValue(State state) {
        if (state.isTerminal())
            return state.getEvaluation();
        int highestValue = Integer.MIN_VALUE;
        for (State move : state.getMoves()) {
            int currentValue = minValue(move);
            highestValue = currentValue > highestValue ? currentValue : highestValue;
        }
        return highestValue;
    }

    public static class State {

        private final Board board;
        private final Player maximizer, minimizer;
        private final boolean isMaximizer;
        private final int row, col;
        private int depth = 0;

        private static int MAX_DEPTH = 6;

        public State(Board board, Player maximizer, Player minimizer) {
            this.board = board;
            this.maximizer = maximizer;
            this.minimizer = minimizer;
            this.row = 0;
            this.col = 0;
            this.isMaximizer = true;
        }

        State(Board board,
              Player maximizer,
              Player minimizer,
              int row,
              int col,
              int depth,
              boolean isMaximizer) {
            this.board = board;
            this.maximizer = maximizer;
            this.minimizer = minimizer;
            this.row = row;
            this.col = col;
            this.depth = depth;
            this.isMaximizer = isMaximizer;
        }

        public static void setMaxDepth(int maxDepth) {
            MAX_DEPTH = maxDepth < 7 ? maxDepth : 6;
        }

        private List<State> getMoves() {
            List<State> moves = new LinkedList<>();
            Player relatedPlayer = isMaximizer ? maximizer : minimizer;
            for (int i = 1; i <= board.getWidth(); i++) {
                Board nextBoard = board.copyBoard();
                if (nextBoard.spaceLeftInCol(i)) {
                    int row = nextBoard.put(i, relatedPlayer.getToken());
                    moves.add(new State(nextBoard, maximizer, minimizer, row, i, depth + 1, !isMaximizer));
                }
            }
            return moves;
        }

        private int getEvaluation() {
            List<String> lines = board.getAllLines();
            char maxToken = maximizer.getToken();
            String maxFour = String.format(".*%1$s%<s%<s%<s.*", maxToken);
            String maxThree = String.format("·*%1$s·*%<s·*%<s·*", maxToken);
            String maxTwo = String.format("·*%1$s·*%<s·*", maxToken);
            char minToken = minimizer.getToken();
            String minFour = String.format(".*%1$s%<s%<s%<s.*", minToken);
            String minThree = String.format("·*%1$s·*%<s·*%<s·*", minToken);
            String minTwo = String.format("·*%1$s·*%<s·*", minToken);
            int value = 0;
            for (String line : lines) {
                for (String maxLine : line.split("" + minToken)) {
                    if (maxLine.length() < 4) continue;
                    if (maxLine.matches(maxFour)) value += 10_001;
                    else if (maxLine.matches(maxThree)) value += 250;
                    else if (maxLine.matches(maxTwo)) value += 100;
                }
                for (String minLine : line.split("" + maxToken)) {
                    if (minLine.length() < 4) continue;
                    if (minLine.matches(minFour)) value -= 10_000;
                    else if (minLine.matches(minThree)) value -= 250;
                    else if (minLine.matches(minTwo)) value -= 100;
                }
            }
            return value;
        }

        private boolean isTerminal() {
            Player relatedPlayer = isMaximizer ? minimizer : maximizer; // -> state.isMaximizer == true: minimizer did the last move
            return depth == MAX_DEPTH
                    || !board.tokensLeft()
                    || board.getWinner(relatedPlayer, row, col);
        }

        public int getCol() {
            return col;
        }
    }
}

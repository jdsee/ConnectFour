package ConnectFour;

import ConnectFour.WinStrategy.MinMax;
import ConnectFour.consoleFormat.Print;

class ComputerOpponent {

    private Player computer;

    ComputerOpponent(Player player) {
        this.computer = player;
    }

    int move(Board board,
             Player opponent) {
        Print.chars('·', Game.outputWidth);
        System.out.println();
        Print.slow(" Der Computer ist am Zug...", 30, Game.outputWidth / 2);
        int computerMove = chooseMove(board, opponent);
        Print.slow("  Er wählt die " + computerMove + ". Spalte!  \n", 30, Game.outputWidth / 2);
        Print.chars('·', Game.outputWidth);
        System.out.println();
        return computerMove;
    }

    int chooseMove(Board board,
                   Player opponent) {
        MinMax.State root = new MinMax.State(board, computer, opponent);
        MinMax.State decision = MinMax.getBestMove(root);
        return decision.getCol();
    }

}

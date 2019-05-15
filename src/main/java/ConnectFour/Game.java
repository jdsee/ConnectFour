package ConnectFour;

import ConnectFour.consoleFormat.Print;
import ConnectFour.consoleFormat.Read;

public class Game {

    private static Board board = new Board();
    private static ComputerOpponent computer;
    public static int outputWidth = (int) (board.getWidth() * 8.5);

    public static void main(String[] args) {
        Print.fourWinsHeading();
        Player player1 = inizializeNewPlayer(1);
        Player player2;
        if (Read.nextAnswer("\nMöchtest du gegen den Computer spielen? [j][n] -> ")) {
            player2 = new Player(2, "Computer", false);
            computer = new ComputerOpponent(player2);
        } else
            player2 = inizializeNewPlayer(2);

        Print.doubleLine('–', outputWidth);
        System.out.printf("[1] %s%n[2] %s%n[0] Zufällig auwählen%n", player1.getName(), player2.getName());
        Print.seperator('–', outputWidth);
        int beginner = Read.nextSelection("Welcher Spieler soll beginnen? -> ", 2);
        beginner = beginner == 0 ? (int) (Math.random() * 2.1) : beginner;
        if (beginner == 2) {
            Player temp = player1;
            player1 = player2;
            player2 = temp;
        }
        Print.letsGoHeading();
        board.show();
        do {
            move(player1, player2);
            move(player2, player1);
        } while (board.tokensLeft());
    }

    private static void move(Player player,
                             Player opponent) {
        int choice;
        if (!player.isHuman()) {
            computer = computer == null ? new ComputerOpponent(player) : computer;
            choice = computer.move(board, opponent);
        } else choice = userMove(player, opponent);
        int row = board.put(choice, player.getToken());
        if (board.getWinner(player, row, choice)) {
            celebrateWinner(player);
            newGameRequest();
        } else if (!board.tokensLeft()) {
            drawn();
            newGameRequest();
        }
        board.show();
    }

    private static int userMove(Player player,
                                Player opponent) {
        int choice;
        movingRequest(player);
        do {
            choice = Read.nextSelection("\nSetze deinen Stein: ", board.getWidth());
            if (choice == 0) {
                Menu.mainMenu(board, player, opponent);
                board.show();
                movingRequest(player);
            } else if (!board.spaceLeftInCol(choice))
                System.out.println("––> An dieser Stelle ist kein Platz für weitere Spielsteine!");
        } while (choice < 1 || !board.spaceLeftInCol(choice));
        return choice;
    }

    private static void movingRequest(Player player) {
        System.out.println();
        Print.heading(player.getToken() + " " + player.getName() + ", Du bist am Zug!", '·', outputWidth);
        System.out.printf("%n[0] -> zum Hauptmenü | [1] bis [%d] -> Stein setzen ", board.getWidth());
    }

    static String playerNameInput(int playerNr) {
        return Read.nextString("Gib den Namen des " + playerNr + ". Spielers ein: ", 15);
    }

    static char playerTokenInput(String playerName) {
        return (Read.nextChar("Wähle ein einzelnes Zeichen als Spielstein für " + playerName + ": "));
    }

    static void newGameRequest() {
        if (Read.nextAnswer("\n\nMöchtest du ein neues Spiel beginnen? [j][n] ––> ")) {
            inizializeNewGame();
        } else exitGame();
    }

    static void inizializeNewGame() {
        board = new Board();
        outputWidth = (int) (board.getWidth() * 8.5);
        Print.letsGoHeading();
    }

    static void inizializeNewGame(int rows, int cols) {
        board = new Board(rows, cols);
        outputWidth = (int) (board.getWidth() * 8.5);
        Print.letsGoHeading();
    }

    private static Player inizializeNewPlayer(int playerNr) {
        if (Read.nextAnswer("\nSpieler " + playerNr + ":\nWillst du deinen Spieler personalisieren? [j][n] ––> "))
            return new Player(playerNr, playerNameInput(playerNr));
        else return new Player(playerNr);
    }

    private static void gameOver() {
        Print.gameOverHeading();
        board.show();
        System.out.println();
        Print.chars('*', outputWidth);
        System.out.println("\n");
    }

    private static void celebrateWinner(Player player) {
        player.won();
        gameOver();
        if (player.isHuman())
            if (player.getWinCount() < 2)
                Print.slow(player.getToken() + " " + player.getName() + ", du hast gewonnen!\n\n", 50, outputWidth);
            else {
                Print.slow(player.getToken() + " " + player.getName() + ", du hast jetzt " + player.getWinCount()
                        + " Mal hintereinander gewonnen!\n", 50, outputWidth);
                Print.slow("Glückwunsch!!!\n\n", 50, outputWidth);
            }
        else {
            Print.slow("Der Computer hat dich besiegt!\n", 50, outputWidth);
            Print.slow("Viel Glück beim nächsten Mal!\n\n", 50, outputWidth);
        }
        Print.chars('*', outputWidth);
    }

    private static void drawn() {
        gameOver();
        Print.slow("Das Spiel ist Unentschieden!\n\n", 50, outputWidth);
        Print.chars('*', outputWidth);
    }

    static void exitGame() {
        System.out.println();
        Print.slow("G O O D - B Y E !\n", 110, outputWidth);
        System.exit(0);
    }

}

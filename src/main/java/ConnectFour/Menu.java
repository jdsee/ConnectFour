package ConnectFour;

import ConnectFour.WinStrategy.MinMax;
import ConnectFour.consoleFormat.Print;
import ConnectFour.consoleFormat.Read;

class Menu {

    static void mainMenu(Board board,
                         Player movingPlayer,
                         Player opponent) {
        System.out.println();
        Print.heading("H A U P T M E N Ü", '–', Game.outputWidth);
        System.out.print("" +
                "\n[1] Spieler - Einstellungen" +
                "\n[2] Spielfeld - Einstellungen" +
                "\n–––" +
                "\n[3] Spiel fortesetzen" +
                "\n[4] Spiel neu starten" +
                "\n–––" +
                "\n[0] Spiel beenden\n\n");
        Print.seperator('–', Game.outputWidth);
        int selection = Read.nextSelection("Bitte wähle einen Menüpunkt: ", 4);
        Print.seperator('–', Game.outputWidth);
        switch (selection) {
            case 1:
                playerSettings(board, movingPlayer, opponent);
                break;
            case 2:
                boardSettings();
                break;
            case 3:
                break;
            case 4:
                Game.newGameRequest();
                break;
            case 0:
                Game.exitGame();
        }
    }

    private static void boardSettings() {

        Print.heading("Spielfeld - Einstellungen", '–', Game.outputWidth);
        System.out.print("" +
                "\n[1] 4 x 4" +
                "\n[2] 5 x 6" +
                "\n[3] 6 x 7 (Original-Format)" +
                "\n[4] 7 x 8" +
                "\n[5] manuelle Eingabe" +
                "\n–––" +
                "\n[0] zurück zum Spiel\n\n");
        Print.seperator('–', Game.outputWidth);
        int selection = Read.nextSelection("Bitte wähle eine Spielfeldgröße: ", 5);
        Print.seperator('–', Game.outputWidth);
        switch (selection) {
            case 1:
                Game.inizializeNewGame(4, 4);
                break;
            case 2:
                Game.inizializeNewGame(5, 5);
                break;
            case 3:
                Game.inizializeNewGame(6, 7);
            case 4:
                Game.inizializeNewGame(7, 8);
                break;
            case 5:
                setBoardManually();
                break;
            case 0:
                break;
        }
    }

    private static void setBoardManually() {
        System.out.println("" +
                "\nDu hast die Möglichkeit das Spielfeld auf beliebige Größe anzupassen." +
                "\nDie Anzahl der Reihen darf die Anzahl der Spalten allerdings nicht überschreiten!" +
                "\nEine manuelle Veränderung der Spielfeldgröße kann zu unerwarteten Problemen führen!");
            int rows = Read.nextInt("\nGib die gewünschte Anzahl der Reihen an: ");
            int cols;
            do {
                cols = Read.nextInt("Gib die gewünschte Anzahl der Spalten an: ");
                System.out.println(
                        cols < rows ? "Die Anzahl der Reihen darf nicht höher als die Anzahl der Spalten sein\n" : "");
            } while (cols < rows);
            Game.inizializeNewGame(rows, cols);
    }

    private static void playerSettings(Board board,
                                       Player movingPlayer,
                                       Player opponent) {
        System.out.println();
        String changeGameMod = opponent.isHuman() ?
                "Neues Spiel gegen den Computer beginnen" : "Neues Spiel gegen einen Spieler beginnen";
        Print.heading("Spieler - Einstellungen", '–', Game.outputWidth);
        System.out.print("" +
                "\n[1] Namen wechseln" +
                "\n[2] Spielstein wechseln" +
                "\n–––" +
                "\n[3] " + changeGameMod +
                "\n–––" +
                "\n[4] Computer-Level einstellen" +
                "\n–––" +
                "\n[0] zurück zum Spiel\n\n");
        Print.seperator('–', Game.outputWidth);
        int selection = Read.nextSelection("Bitte wähle einen Menüpunkt: ", 4);
        Print.seperator('–', Game.outputWidth);
        switch (selection) {
            case 1:
                movingPlayer.setName(Game.playerNameInput(movingPlayer.getPlayerNr()));
                break;
            case 2:
                char newToken = Game.playerTokenInput(movingPlayer.getName());
                board.replaceTokens(movingPlayer, newToken);
                movingPlayer.setToken(newToken);
                break;
            case 3:
                if (opponent.isHuman()) {
                    opponent.setHuman(false);
                    opponent.setName("Computer");
                    opponent.setToken('□');
                    Game.inizializeNewGame();
                } else {
                    opponent.setHuman(true);
                    opponent.setName("Spieler " + opponent.getPlayerNr());
                    Game.inizializeNewGame();
                }
                break;
            case 4:
                computerSettings();
                break;
            case 0:
                break;
        }
    }

    private static void computerSettings() {
        Print.heading("Computer-Level", '–', Game.outputWidth);
        System.out.print("" +
                "\n[1] Langweilig" +
                "\n[2] Chillig" +
                "\n[3] Krass" +
                "\n[4] Brutal!" +
                "\n–––" +
                "\n[0] zurück zum Spiel\n\n");
        Print.seperator('–', Game.outputWidth);
        int selection = Read.nextSelection("Bitte wähle einen Schwierigkeitsgrad: ", 4);
        Print.seperator('–', Game.outputWidth);
        switch (selection) {
            case 1:
                MinMax.State.setMaxDepth(1);
                break;
            case 2:
                MinMax.State.setMaxDepth(3);
                break;
            case 3:
                MinMax.State.setMaxDepth(5);
            case 4:
                MinMax.State.setMaxDepth(6);
                break;
            case 0:
                break;
        }
    }
}

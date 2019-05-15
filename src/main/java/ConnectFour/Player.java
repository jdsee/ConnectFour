package ConnectFour;

public class Player {

    private String name;
    private char token;
    private int playerNr;
    private int winCount = 0;
    private boolean isHuman = true;

    Player(int playerNr) {
        this.name = "Spieler " + playerNr;
        if (playerNr % 2 == 1)
            this.token = '◎';
        else
            this.token = '□';
        this.playerNr = playerNr;
    }

    Player(int playerNr,
           char token) {
        this.name = "Spieler " + playerNr;
        this.token = token;
        this.playerNr = playerNr;
    }

    Player(int playerNr,
           String name) {
        this.name = name;
        if (playerNr % 2 == 1)
            this.token = '◎';
        else
            this.token = '□';
        this.playerNr = playerNr;
    }

    Player(int playerNr,
           String name,
           boolean isHuman) {
        this.name = name;
        if (playerNr % 2 == 1)
            this.token = '◎';
        else
            this.token = '●';
        this.isHuman = isHuman;
        this.playerNr = playerNr;
    }

    String getName() {
        return this.name;
    }

    public char getToken() {
        return this.token;
    }

    int getPlayerNr() {
        return this.playerNr;
    }

    int getWinCount() {
        return winCount;
    }

    boolean isHuman() {
        return isHuman;
    }

    void setName(String name) {
        this.name = name;
    }

    void setToken(char token) {
        this.token = token;
    }

    void setHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    void won() {
        winCount++;
    }

}

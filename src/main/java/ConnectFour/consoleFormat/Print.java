package ConnectFour.consoleFormat;

import ConnectFour.Game;

/**
 * Formatierungs-Hilfe:
 * Die in dieser Klasse enthaltenen Methoden dienen ausschließlich der strukturierten Konsolenausgabe.
 * Sie können keine Werte verändern
 */
public class Print {

    public static void chars(char symbol, int length) {
        for (int j = 0; j < length; j++)
            System.out.print(symbol);
    }

    public static void seperator(char symbol, int length) {
        chars(symbol, length);
        System.out.println();
    }

    public static void doubleLine(char symbol, int length) {
        Print.seperator(symbol, length);
        Print.seperator(symbol, length);
    }

    public static void centered(String string, int length) {
        System.out.println();
        Print.chars(' ', (length - string.length()) / 2);
        System.out.print(string);
    }

    public static void heading(String heading, char frameSymbol, int length) {
        Print.chars(frameSymbol, length);
        Print.centered(heading + "\n", length);
        Print.chars(frameSymbol, length);
        System.out.println();
    }

    public static void wrongInput(int length) {
        Print.chars('·', length);
        System.out.println("\nDie Eingabe ist nicht korrekt. Bitte erneut eingeben!");
        Print.chars('·', length);
        System.out.println();
    }

    public static void wrongInput(String request, int length) {
        Print.chars('·', length);
        System.out.println("\nDie Eingabe ist nicht korrekt.\n" + request);
        Print.chars('·', length);
        System.out.println();
    }

    public static void slow(String output, long msPerChar, int length) {
        chars(' ', (length - output.length()) / 2);
        for (int i = 0; i < output.length(); i++) {
            System.out.print(output.charAt(i));
            try {
                Thread.sleep(msPerChar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void whiteSpace() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    public static void fourWinsHeading() {
        Print.doubleLine('–', Game.outputWidth);
        System.out.print("__________                  __      __.__               \n" +
                "\\_   ____/___  __ _________/  \\    /  \\__| ____   ______\n" +
                " |   __)/  _ \\|  |  \\_  __ \\   \\/\\/   /  |/    \\ /  ___/\n" +
                " |    \\(  <_> )  |  /|  | \\/\\        /|  |   |  \\\\___ \\ \n" +
                " \\___ / \\____/|____/ |__|    \\__/\\  / |__|___|  /____ /\n");
        Print.seperator('–', Game.outputWidth);
    }

    public static void gameOverHeading() {
        Print.doubleLine('–', Game.outputWidth);
        System.out.println("  ________                      _______                     \n" +
                " /  _____/_____    _____   ____ \\____  \\___  __ ___________ \n" +
                "/   \\  ___\\__  \\  /     \\_/ __ \\ /  |   \\  \\/ // __ \\_  __ \\\n" +
                "\\    \\_\\  \\/ __ \\|  Y Y  \\  ___//   |    \\   /\\  ___/|  | \\/\n" +
                " \\______  (____  /__|_|  /\\___  >______  /\\_/  \\___  >__|   \n" +
                "        \\/     \\/      \\/     \\/        \\/          \\/       ");
        Print.seperator('–', Game.outputWidth);
    }

    public static void letsGoHeading() {
        Print.doubleLine('–', Game.outputWidth);
        System.out.println(".____           __             ________ ________    ._.\n" +
                "|    |    _____/  |_  ______  /  _____/ \\_____  \\   | |\n" +
                "|    |  _/ __ \\   __\\/  ___/ /   \\  ___  /   |   \\  | |\n" +
                "|    |__\\  ___/|  |  \\___ \\  \\    \\_\\  \\/    |    \\  \\|\n" +
                "|_______ \\___  >__| /____  >  \\______  /\\_______  /  __\n" +
                "        \\/   \\/          \\/          \\/         \\/   \\/");
        Print.seperator('–', Game.outputWidth);
    }

}
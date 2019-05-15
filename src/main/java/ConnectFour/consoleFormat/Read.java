package ConnectFour.consoleFormat;

import ConnectFour.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Read {

    private static final Scanner userInp = new Scanner(System.in);

    /**
     * Liest die nächste Eingabe des Nutzers ein und lässt nur Integer-Werte unter dem <rangeLimit-Wert> und über 0 zu
     * Zur Benutzung bei der Auswahl von bezifferten Menüpunkten
     * ––> Für jede andere Eingabe wird eine Fehlermeldung ausgegeben und die Eingabeaufforderung wiederholt
     *
     * @param request Eingabeaufforderung als String
     * @param rangeLimit Menge der zugelassenen positiven Integer-Werte
     * @return
     */
    public static int nextSelection(String request, int rangeLimit) {
        System.out.print(request);
        try {
            int pick = userInp.nextInt();
            if (pick < 0 || pick > rangeLimit) {
                Print.wrongInput("Bitte gebe eine Zahl zwischen 1 und " + rangeLimit + " ein.", Game.outputWidth);
                return nextSelection(request, rangeLimit);
            }
            return pick;
        } catch (InputMismatchException e) {
            userInp.next();
            Print.wrongInput(Game.outputWidth);
            return nextSelection(request, rangeLimit);
        }
    }

    /**
     * Liest die nächste Eingabe des Nutzers ein und gibt für 'j' true oder 'n' false zurück
     * ––> Für jede andere Eingabe wird eine Fehlermeldung ausgegeben und die Eingabeaufforderung wiederholt
     *
     * @param request Eingabeaufforderung als String
     * @return true / false
     */
    public static boolean nextAnswer(String request) {
        System.out.print(request);
        try {
            char sign = userInp.next().charAt(0);
            if (sign != 'j' && sign != 'n') {
                Print.wrongInput(Game.outputWidth);
                return nextAnswer(request);
            }
            if (sign == 'j')
                return true;
            else return false;
        } catch (InputMismatchException e) {
            userInp.next();
            Print.wrongInput(Game.outputWidth);
            return nextAnswer(request);
        }
    }

    public static String nextString(String request, int maxStringLength) {
        System.out.println(request);
        try {
            String input = userInp.next();
            if (input.length() > maxStringLength) {
                Print.wrongInput(Game.outputWidth);
                return nextString(request, maxStringLength);
            }
            return input;
        } catch (InputMismatchException e) {
            userInp.next();
            Print.wrongInput(Game.outputWidth);
            return nextString(request, maxStringLength);
        }
    }

    public static char nextChar(String request) {
        System.out.println(request);
        try {
            return userInp.next().charAt(0);
        } catch (InputMismatchException e) {
            userInp.next();
            Print.wrongInput(Game.outputWidth);
            return nextChar(request);
        }
    }

    public static int nextInt(String request) {
        System.out.println(request);
        try {
            return userInp.nextInt();
        } catch (InputMismatchException e) {
            userInp.next();
            Print.wrongInput(Game.outputWidth);
            return nextInt(request);
        }
    }

}

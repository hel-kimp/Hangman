import java.util.Random;
import java.util.Scanner;

public class neu
{

    public static void main(String[] args) {

        String[] wortListe = {"adventskranz", "adventszeit", "bratapfel", "besinnlichkeit", "bescherung", "christbaumkugel", "christbaumschmuck", "dezember", "feuerzangenbowle", "gewuerzspekulatius", "gluehwein", "gloeckchen", "gemuetlichkeit", "heiligabend", "kerzenschein", "krippenspiel", "lichterglanz", "lametta", "lebkuchenhaus", "marzipan", "mandarinen", "mistelzweig", "nikolaustag", "nussknacker", "plaetzchen", "plaetzchenduft", "Schneeflocken", "Schneemann", "Strohstern", "tannenbaum", "tannenzweig", "weihnachtslied", "weihnachtsfest", "weihnachtsbaeckerei", "weihnachtsmann", "winterlandschaft", "wunschzettel", "weihnachtstradition", "weihnachtsgeschichte", "zimtsterne"};
        String wortRaten = wortListe[new Random().nextInt(wortListe.length)];
        char[] gerateneBuchstaben = new char[wortRaten.length()];

        int falscheEingaben = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen zu Hangman!");
        System.out.println(anzeigeWort(wortRaten, gerateneBuchstaben));


        while (falscheEingaben < 6) {
            System.out.print("Gib einen Buchstaben ein: ");
            char eingabe = scanner.next().toLowerCase().charAt(0);
            if (!Character.isLetter(eingabe)) {
                System.out.println("Schummler! Bitte gib einen Buchstaben ein.");
                continue;
            }
            if (bereitsGeraten(gerateneBuchstaben, eingabe)) {
                System.out.println("Du hast diesen Buchstaben bereits geraten. Versuche es erneut.");
                continue;
            }
            if (ueberpruefeEingabe(eingabe, wortRaten, gerateneBuchstaben)) {
                System.out.println("Richtig geraten!");
            } else {
                falscheEingaben++;
                System.out.println("Falsch geraten! Anzahl der Versuche übrig: " + (6 - falscheEingaben));
                zeichneHangman(falscheEingaben);
            }
        //war vorher hier klammer
        System.out.println(anzeigeWort(wortRaten, gerateneBuchstaben));

        if (wortRaten(gerateneBuchstaben)) {
            System.out.println("Herzlichen Glückwunsch, du hast gewonnen! Das Wort ist: " + wortRaten);
            break;
        }
    } //klammer war hier falsch geschlossen, war zu weit oben

        if (falscheEingaben == 6)
        {
            System.out.println("Du hast leider verloren. Das richtige Wort wäre gewesen: " + wortRaten);
        }
        scanner.close(); //scanner nicht geschlossen gehabt
    }


    static String anzeigeWort(String word, char[] geratenerBuchstabe) {
        StringBuilder anzeige = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (bereitsGeraten(letter, geratenerBuchstabe)) {
                anzeige.append(letter).append(" ");
            } else {
                anzeige.append("_ ");
            }
        }
        return anzeige.toString();
    }


    private static void zeichneHangman(int versuche) { //ASCII- Hangman wird gezeichnet
        System.out.println();
        System.out.println("  _____");
        System.out.println("  |   |");
        switch (versuche) {
            case 0:
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 1:
                System.out.println("  O   |");
                System.out.println("      |");
                break;
            case 2:
                System.out.println("  O   |");
                System.out.println("  |   |");
                break;
            case 3:
                System.out.println(" \\O   |");
                System.out.println("  |   |");
                break;
            case 4:
                System.out.println(" \\O/  |");
                System.out.println("  |   |");
                break;
            case 5:
                System.out.println(" \\O/  |");
                System.out.println("  |   |");
                System.out.println(" /    |");
                break;
            case 6:
                System.out.println(" \\O/  |");
                System.out.println("  |   |");
                System.out.println(" / \\  |");
                break;
        }
        System.out.println("      |");
        System.out.println("=======");
    }

    static boolean bereitsGeraten(char[] gerateneBuchstaben, char guess) {
        return bereitsGeraten(guess,gerateneBuchstaben);
    }

    //war an einer anderen Stelle schon definiert aber nicht genau,"richtig geraten war eigene teil, was eig zsm sollte
     static boolean ueberpruefeEingabe(char guess, String word, char [] gerateneBuchstaben) {
        boolean richtigGeraten = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                gerateneBuchstaben[i] = guess;
                richtigGeraten = true;
            }
        }
        return richtigGeraten;
    }
    static boolean wortRaten(char[] geratenenBuchstaben) {
        for (char letter : geratenenBuchstaben) {
            if (letter == 0) {
                return false;
            }
        }
        return true;
    }
    static boolean bereitsGeraten(char letter, char[] gerateneBuchstaben) {
        for (char geratenerBuchstabe : gerateneBuchstaben) {
            if (geratenerBuchstabe == letter) {
                return true;
            }
        }
        return false;
    }
}

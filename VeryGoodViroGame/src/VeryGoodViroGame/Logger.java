package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Logger {
    static int eltolas = 0;
    static boolean isEnabled = true;

    static void SetEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    static void NewFunctionCall() {
        if (isEnabled)
            eltolas++;
    }

    static void NewFunctionCall(String mes) {
        if (isEnabled) {
            eltolas++;
            Print(mes);
        }
    }

    static void ReturnFunction() {
        if (isEnabled)
            eltolas--;
    }

    static void Print(String mes) {
        if (isEnabled)
            System.out.println(" ".repeat(eltolas * 2) + mes);
    }

    static boolean AskQuestion(String question) {
        if (isEnabled) {
            System.out.println(" ".repeat(eltolas * 2) + question + "?(Y/N):");
            System.out.print(" ".repeat(eltolas * 2));
            String answer = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (!answer.equals("y") && !answer.equals("n")) {
                try {
                    answer = reader.readLine().toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return answer.trim().equals("y");
        }

        return false;
    }
}

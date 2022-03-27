package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Logger {
    static int eltolas = 0;
    static boolean isEnabled = true;
    static boolean isFirst = true;
    private static String separator = " ";

    static int[] eltolasok;
    static int getEltolas()
    {
        return 0;
    }

    static int putEltolas(int elt)
    {
        return 0;
    }

    static void SetEnabled(boolean enabled) {
        isEnabled = enabled;
        isFirst = enabled;
    }

    static void NewFunctionCall(String mes) {
        if (!isEnabled)
            return;

        String str; //>:(

        if (isFirst)
            str = "---" + mes + "--->[]";
        else
            str = "\n---" + mes + "--->[]";

        eltolas += str.length();
        Print(str);
        isFirst = false;
    }

    static void ReturnFunction() {
        if (isEnabled) {
            eltolas--;
            Print("[]<-------");
        }
    }

    static void Print(String mes) {
        if (isEnabled)
            System.out.println(separator.repeat(eltolas * 2) + mes);
    }

    static boolean AskQuestion(String question) {
        if (isEnabled) {
            System.out.println(separator.repeat(eltolas * 2) + question + "?(Y/N):");
            System.out.print(separator.repeat(eltolas * 2));
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

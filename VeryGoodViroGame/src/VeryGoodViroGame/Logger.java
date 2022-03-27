package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class Logger {
    static boolean isEnabled = true;
    static boolean isFirst = true;
    private static String separator = " ";

    static ArrayList<Integer> eltolasok = new ArrayList<Integer>();
    static int getEltolas()
    {
        int e = 0;
        for (int i = 0; i < eltolasok.size(); i++)
        {
            e += eltolasok.get(i);
        }
        return e;
    }

    static void putEltolas(int elt)
    {
        eltolasok.add(elt);
    }

    static void popEltolas()
    {
        if (eltolasok.isEmpty())
            return;
        eltolasok.remove(eltolasok.size()-1);
    }

    static int lastEltolas()
    {
        return eltolasok.get(eltolasok.size()-1);
    }

    static void SetEnabled(boolean enabled) {
        isEnabled = enabled;
        if (enabled)
        {
            isFirst = true;
            eltolasok = new ArrayList<Integer>();
        }
    }

    static void Start()
    {
        Timer.steppable_reg = new ArrayList<iSteppable>();
        GameManager.map = null;
        SetEnabled(true);
    }

    static void NewFunctionCall(String mes) {
        if (!isEnabled)
            return;

        String str; //>:(

        if (isFirst)
            str = "---" + mes + "--->[]";
        else
        {
            Print("[]");
            str = "[]---" + mes + "--->[]";
        }


        Print(str);
        putEltolas(str.length()-2);
        isFirst = false;
    }

    static void ReturnFunction() {
        if (isEnabled) {
            int elt = lastEltolas()-3;
            popEltolas();
            Print("[]<" + "-".repeat(elt)  + "[]");
        }
    }

    static void Print(String mes) {
        if (!isEnabled)
            return;

        //System.out.println("printing: " + getEltolas());
        //System.out.println("printing: " + mes);
        //System.out.println("printing: " + separator.repeat(getEltolas()).length());
        System.out.println(separator.repeat(getEltolas()) + mes);
    }

    static boolean AskQuestion(String question) {
        if (isEnabled) {
            String msg = separator.repeat(getEltolas()) + "[]>" + question + "?(Y/N): ";
            System.out.print(msg);
            //System.out.print(separator.repeat(getEltolas()));
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

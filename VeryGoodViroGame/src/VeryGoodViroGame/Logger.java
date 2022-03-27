package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Logger
{
    static int eltolas = 0;
    
    static void NewFunctionCall()
    {
        eltolas++;
    }
    
    static void NewFunctionCall(String mes)
    {
        eltolas++;
        Print(mes);
    }
    
    static void ReturnFunction()
    {
        eltolas--;
    }
    
    static void Print(String mes)
    {
        System.out.println(" ".repeat(eltolas * 2) + mes);
    }

    static boolean AskQuestion(String question)
    {
        System.out.println(" ".repeat(eltolas * 2) + question + "?(Y/N):");
        String answer = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!answer.equals("y") && !answer.equals("n")){
            try {
                answer = reader.readLine().toLowerCase();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        return answer.trim().equals("y");
    }
}

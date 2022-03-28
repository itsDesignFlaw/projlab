package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Logger
{
    static boolean isEnabled = true;
    static boolean isFirst = true;
    private static String separator = " ";
    static HashMap<Object, String> nameList = new HashMap<>();
    
    static ArrayList<Integer> eltolasok = new ArrayList<Integer>();
    
    static final boolean CMDBOL_INDITVA_NINCS_SZIN = true; //:(
    
    static int getEltolas()
    {
        int e = 0;
        for(int i = 0; i < eltolasok.size(); i++)
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
        if(eltolasok.isEmpty())
            return;
        eltolasok.remove(eltolasok.size() - 1);
    }
    
    static int lastEltolas()
    {
        return eltolasok.get(eltolasok.size() - 1);
    }
    
    static void SetEnabled(boolean enabled)
    {
        isEnabled = enabled;
        if(enabled)
        {
            isFirst = true;
            eltolasok = new ArrayList<Integer>();
        }
    }
    
    static void Start()
    {
        Start(true);
    }
    
    static void Start(boolean erase)
    {
        if(erase)
            Timer.steppable_reg = new ArrayList<iSteppable>();
        GameManager.map = null;
        SetEnabled(true);
        nameList = new HashMap<Object, String>();
    }
    
    static void AddObjectNames(Object[] L_oList, String[] L_sList)
    {
        
        for(int i = 0; i < L_oList.length; i++)
        {
            nameList.put(L_oList[i], L_sList[i]);
        }
        
    }
    
    static String GetObjectName(Object o)
    {
        return nameList.get(o);
    }
    
    static void NewFunctionCall(Object o, String mes)
    {
        if(!isEnabled)
            return;
        
        NewFunctionCallLegacy(GetObjectName(o), mes);
    }
    
    static void ReturnFunction()
    {
        if(isEnabled)
        {
            int elt = lastEltolas() - 3;
            popEltolas();
            Print("[]<" + "-".repeat(elt) + "[]");
        }
    }
    
    static void Print(String mes)
    {
        if(!isEnabled)
            return;
        
        //System.out.println("printing: " + getEltolas());
        //System.out.println("printing: " + mes);
        //System.out.println("printing: " + separator.repeat(getEltolas()).length());
        System.out.println(separator.repeat(getEltolas()) + mes);
    }
    
    static void NewFunctionCallLegacy(String obj, String msg)
    {
        if(!isEnabled)
            return;
        
        String str; //>:(
        
        int offset = 0;
        
        if(isFirst)
        {
            if(obj == null)
            {
                str = "---" + ANSI_RED_BACKGROUND + obj + "." + msg + ANSI_RESET + "--->[]";
                offset = -20;
            }
            else
            {
                str = "---" + ANSI_RED + obj + "." + msg + ANSI_RESET + "--->[]";
                offset = -9;
            }
        }
        else
        {
            Print("[]");
            if(obj == null)
            {
                str = "[]---" + ANSI_RED_BACKGROUND + obj + "." + msg + ANSI_RESET + "--->[]";
                offset = -20;
            }
            else
            {
                str = "[]---" + ANSI_RED + obj + "." + msg + ANSI_RESET + "--->[]";
                offset = -9;
            }
        }
        
        if(CMDBOL_INDITVA_NINCS_SZIN)
            offset = 0;
        Print(str);
        putEltolas(str.length() - 2 + offset);
        isFirst = false;
    }
    
    static boolean AskQuestion(String question)
    {
        if(isEnabled)
        {
            String msg = separator.repeat(getEltolas()) + "[]>" + question + "?(Y/N): ";
            System.out.print(msg);
            //System.out.print(separator.repeat(getEltolas()));
            String answer = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            while(!answer.equals("y") && !answer.equals("n"))
            {
                try
                {
                    answer = reader.readLine().toLowerCase();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            
            return answer.trim().equals("y");
        }
        
        return false;
    }
    
    /*public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";*/
    public static final String ANSI_RESET = "";
    public static final String ANSI_BLACK = "";
    public static final String ANSI_RED = "";
    public static final String ANSI_GREEN = "";
    public static final String ANSI_YELLOW = "";
    public static final String ANSI_BLUE = "";
    public static final String ANSI_PURPLE = "";
    public static final String ANSI_CYAN = "";
    public static final String ANSI_WHITE = "";
    
    public static final String ANSI_BLACK_BACKGROUND = "";
    public static final String ANSI_RED_BACKGROUND = "";
    public static final String ANSI_GREEN_BACKGROUND = "";
    public static final String ANSI_YELLOW_BACKGROUND = "";
    public static final String ANSI_BLUE_BACKGROUND = "";
    public static final String ANSI_PURPLE_BACKGROUND = "";
    public static final String ANSI_CYAN_BACKGROUND = "";
    public static final String ANSI_WHITE_BACKGROUND = "";
    
    
}

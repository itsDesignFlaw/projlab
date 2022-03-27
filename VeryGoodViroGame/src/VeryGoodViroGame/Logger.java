package VeryGoodViroGame;

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
}

package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.GameManager.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 *Ez a statikus osztály felelős az új játék elindításáért, és a játék befejezéséért.
 */
public class GameManager
{
    
    static Map map;

    /**
     *Ez a metódus felelős a játék elindításáért.
     */
    public static void StartGame()
    {
        Logger.NewFunctionCall("GameManager.StartGame");
        map = new Map();
        map.GenerateMap();
        Logger.ReturnFunction();
    }
    /**
     *Ez a metódus felelős a játék befejezéséért és a nyertes kihirdetéséért.
     *
     * @param winner a játék győztese
     */
    public static void EndGame(Virologist winner)
    {
        Logger.NewFunctionCall("GameManager.EndGame");
        Logger.ReturnFunction();
    }
}

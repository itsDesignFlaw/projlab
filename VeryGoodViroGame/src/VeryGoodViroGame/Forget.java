package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Forget.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 *Egy vírust reprezentál a játékban, hatására a virológus elfelejti az addig megtanult genetikai kódokat.
 */
public class Forget extends Agent
{
    /**
     * Hamissal tér vissza, hogy megakadályozza az adott virológust ágens kraftolásában.
     */
    public boolean CanCraft()
    {
        Logger.NewFunctionCall(this, "CanCraft");
        Logger.ReturnFunction();
        return false;
    }
    
}

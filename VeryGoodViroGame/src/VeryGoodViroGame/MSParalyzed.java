package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.MSParalyzed.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 * Ez az osztály a virológus bénult állapotában való mozgásáért felelős.
 */
public class MSParalyzed implements iMoveStrategy
{
    /**
     * Ez a metódus akkor hívódik meg, amikor a virológus bénult állapotban mozogni próbál,
     * ilyenkor nem tud mozogni.
     * @param v A virológus, akin a mozgást végrehajtjuk.
     * @param from a Mező amiről mozgatjuk a virológust
     * @param to a Mező amire mozgatjuk a virológust
     */
    @Override
    public void ExecuteMove(Virologist v, Field from, Field to)
    {
        Logger.NewFunctionCall("MSParalyzed.ExecuteMove");
        Logger.ReturnFunction();
    }
}

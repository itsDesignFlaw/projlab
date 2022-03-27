package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.SimpleMoveStrategy.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 * Ez az osztály felelős a virológus egyszerű mozgási stratégiájának megvalósításáért.
 */
public class MSSimple implements iMoveStrategy
{
    /**
     * Ez a metódus végrehajtja az egyszerű mozgást, a virológus oda lép, ahova a játékos irányítja.
     * @param v A virológus, akin a mozgást végrehajtjuk.
     * @param from a Mező amiről mozgatjuk a virológust
     * @param to a Mező amire mozgatjuk a virológust
     */
    @Override
    public void ExecuteMove(Virologist v, Field from, Field to)
    {
        Logger.NewFunctionCall("MSSimple.ExecuteMove");
        from.RemoveViro(v);
        to.AcceptViro(v);
        Logger.ReturnFunction();
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    //
    //  Generated by StarUML(tm) Java Add-In
    //
    //  @ Project : Untitled
    //  @ File Name : VeryGoodViroGame.VitusDanceMoveStrategy.java
    //  @ Date : 2022. 03. 27.
    //  @ Author : 
    //
    //
    /// <summary>
    /// Ez az osztály felelős a virológus vítustánc hatása alatti mozgási stratégiájánaknak a megvalósításáért.
    /// </summary>
    public class MSVitusDance : iMoveStrategy
    {
        /// <summary>
        /// Ennek a metódusnak a hatására a virológus egy véletlenszerűen választott mezőre lép.
        /// </summary>
        /// <param name="v">A virológus, akin a mozgást végrehajtjuk.</param>
        /// <param name="from">a Mező amiről mozgatjuk a virológust</param>
        /// <param name="to">a Mező amire mozgatjuk a virológust</param>
        public void ExecuteMove(Virologist v, Field from, Field to)
        {
            Logger.NewFunctionCall(this, "ExecuteMove");
            Field random = from.GetRandomNeighbour();
            from.RemoveViro(v);
            random.AcceptViro(v);
            Logger.ReturnFunction();
        }
    }
} //

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
    //  @ File Name : VeryGoodViroGame.Equipment.java
    //  @ Date : 2022. 03. 27.
    //  @ Author : 
    //
    //
    /// <summary>
    /// A virológusok által felvehető felszerelések/ruhadarabok.
    /// Alapvető célja, hogy ezeket az interaktív osztályok közös kezelését elősegítse.
    /// </summary>
    public abstract class Equipment : InvItem
    {
        protected Virologist host;
        public virtual void Use(Virologist target)
        {
            Logger.NewFunctionCall(this, "Use");
            Logger.ReturnFunction();
        }
    }
} //

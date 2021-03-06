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
    //  @ File Name : VeryGoodViroGame.Paralyze.java
    //  @ Date : 2022. 03. 27.
    //  @ Author : 
    //
    //
    /// <summary>
    /// Egy vírust reprezentál a játékban, amely mindaddig,
    /// míg egy virológusra hatást gyakorol, addig az lebénul,
    /// tehát nem tud mozogni, vagy interaktálni más entitásokkal.
    /// Felelőssége, az InvItem osztály bizonyos metódusainak felülírása.
    /// </summary>
    public class Paralyze : Agent
    {
        public Paralyze(): base()
        {
            strategy = new MSParalyzed();
        }

        /// <summary>
        /// (InvItem metódus) Mindig igazat ad vissza, tehát ilyenkor kifosztható.
        /// </summary>
        /// <returns>boolean visszatérési érték, mindig igaz</returns>
        public virtual bool IsParalyzed()
        {
            Logger.NewFunctionCall(this, "IsParalyzed");
            Logger.ReturnFunction();
            return true;
        }

        /// <summary>
        /// (InvItem metódus) Hamisat ad vissza, a virológus nem tud ágenst felkenni.
        /// </summary>
        /// <returns>boolean visszatérési érték, mindig hamis</returns>
        public virtual bool CanApplyAgent()
        {
            Logger.NewFunctionCall(this, "CanApplyAgent");
            Logger.ReturnFunction();
            return false;
        }

        /// <summary>
        /// (InvItem metódus) Hamisat ad vissza, a virológus nem tud lopni.
        /// </summary>
        /// <returns>boolean visszatérési érték, mindig hamis</returns>
        public virtual bool CanSteal()
        {
            Logger.NewFunctionCall(this, "CanSteal");
            Logger.ReturnFunction();
            return false;
        }

        /// <summary>
        /// (InvItem metódus) Hamisat ad vissza, a virológus nem tud ágenst létrehozni.
        /// </summary>
        /// <returns>boolean visszatérési érték, mindig hamis</returns>
        public virtual bool CanCraft()
        {
            Logger.NewFunctionCall(this, "CanCraft");
            Logger.ReturnFunction();
            return false;
        }

        /// <summary>
        /// (InvItem metódus) Hamisat ad vissza, a virológus nem tud adott mezőkkel a megfelelő módon interaktálni.
        /// </summary>
        /// <returns>boolean visszatérési érték, mindig hamis</returns>
        public virtual bool CanInteract()
        {
            Logger.NewFunctionCall(this, "CanInteract");
            Logger.ReturnFunction();
            return false;
        }
    }
} //

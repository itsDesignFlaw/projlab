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
    //  @ File Name : VeryGoodViroGame.EquipmentSack.java
    //  @ Date : 2022. 03. 27.
    //  @ Author : 
    //
    //
    /// <summary>
    /// A zsák virológus felszerelést reprezentálja, az ezt viselő virológus által szállítható anyagmennyiségek megnövelődnek.
    /// </summary>
    public class EquipmentSack : Equipment
    {
        //Nem lehet, hogy inkánn int?
        //Mert ez az a mennyiség, amivel megnöveli a kapacitást
        private Resource amount;
        /// <summary>
        /// 0-nál nagyobb számot ad vissza, ezzel jelezve a tartalmazó virológusnak, hogy több nyersanyagot is tud tárolni/szállítani
        /// </summary>
        public virtual int GetMaxResource()
        {
            Logger.NewFunctionCall(this, "GetMaxResource");
            Logger.ReturnFunction();
            return 20;
        }
    }
} //

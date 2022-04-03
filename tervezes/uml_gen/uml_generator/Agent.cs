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
    //  @ File Name : VeryGoodViroGame.Agent.java
    //  @ Date : 2022. 03. 27.
    //  @ Author : 
    //
    //
    /// <summary>
    /// Az összes ágens ősosztálya, az osztály felelőssége, hogy előírja,
    /// hogyan lehet felkenni az ágenseket, és felel azért is, hogy csak adott ideig fejtsék ki a hatásukat.
    /// </summary>
    public abstract class Agent : InvItem, iSteppable
    {
        int activeTime = 6969;
        //Akin van
        Virologist host;
        iMoveStrategy strategy;
        /// <summary>
        /// A source paraméterként kapott virológus megkísérli felkenni az ágenst a targetként megkapott virológusra.
        /// </summary>
        /// <param name="source">az ágenst felkenő virológus</param>
        /// <param name="target">az a virológus akire felkenik az ágenst</param>
        public virtual void Apply(Virologist source, Virologist target)
        {
            Logger.NewFunctionCall(this, "Apply");
            if (target.ApplyAgent(this, source))
            {
                target.ChangeMoveStrategy(strategy);
            }

            host = target;
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Minden időpillanatban meghívódik ez a függvény, és csökkenti az időt ameddig még aktív az ágens.
        /// </summary>
        public virtual void Step()
        {
            Logger.NewFunctionCall(this, "Step");
            activeTime--;
            if (activeTime <= 0)
            {
                host.RemoveMoveStrategy(strategy);
                host.RemoveItem(this);

                //Ezzel gáz lesz, mert nem szereti a java
                //ha foreach közben kiszedünk a listából
                //Megoldás: klónozás, vagy dead flag
                Timer.RemoveSteppable(this);
            }

            Logger.ReturnFunction();
        }

        /// <summary>
        /// Lemásolja az adott ágenst.
        /// </summary>
        public virtual Agent Clone()
        {
            Logger.NewFunctionCall(this, "Clone");
            Logger.ReturnFunction();
            return this;
        }
    }
} //

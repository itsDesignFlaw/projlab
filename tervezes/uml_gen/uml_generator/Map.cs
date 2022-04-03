using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    /// <summary>
    /// Az osztály felelős a játéktér létrehozásáért, és annak kezeléséért.
    /// </summary>
    public class Map
    {
        IList<Field> fields = new List();
        /// <summary>
        /// Ez a metódus generálja a játékteret
        /// </summary>
        public virtual void GenerateMap()
        {
            Logger.NewFunctionCall(this, "GenerateMap");
            Logger.ReturnFunction();
        }
    }
} //

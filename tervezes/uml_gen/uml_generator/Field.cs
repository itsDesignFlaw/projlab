using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    /// <summary>
    /// Az egyszerű mezőt reprezentálja, melyen a virológusok mozoghatnak. Ismernie kell, adott mezőn, mely virológusok
    /// állnak.
    /// </summary>
    public class Field
    {
        IList<Virologist> virologists;
        private IList<Field> neighbours;
        public Field()
        {
            neighbours = new List<Field>();
        }

        /// <summary>
        /// A virológus mezőre lépésekor ezt a függvényt kell meghívni a virolgóust paraméterként átadva, ezáltal tudja a
        /// mező befogadni.
        /// </summary>
        /// <param name="v">A virológus aki a mezőre lép.</param>
        public virtual void AcceptViro(Virologist v)
        {
            Logger.NewFunctionCall(this, "AcceptViro");
            virologists.Add(v);
            v.SetField(this);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Ha egy virológus rajta van egy mezőn, akkor lehetősége van a mezővel interaktálni.
        /// </summary>
        /// <param name="v">A virológus aki a mezőn van.</param>
        public virtual void Interact(Virologist v)
        {
            Logger.NewFunctionCall(this, "Interact");
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Ez a metódus felelős azért, hogy a virológust le tudjuk venni a mezőről, amikor az el akarja hagyni azt.
        /// </summary>
        /// <param name="v">A virológus aki a mezőt el akarja hagyni.</param>
        public virtual void RemoveViro(Virologist v)
        {
            Logger.NewFunctionCall(this, "RemoveViro");
            virologists.Remove(v);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Ez a metódus felelős azért, hogy a mezőnek felvegyünk egy szomszédot.
        /// </summary>
        /// <param name="f">A leendő szomszédos mező.</param>
        public virtual void AddNeighbour(Field f)
        {
            Logger.NewFunctionCall(this, "AddNeighbour");
            neighbours.Add(f);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Visszaad egyet a környező mezők közül, random.
        /// </summary>
        public virtual Field GetRandomNeighbour()
        {
            Logger.NewFunctionCall(this, "GetRandomNeighbour");
            Logger.ReturnFunction();

            //majd itt randomot kell visszaadnia
            return neighbours[0];
        }

        public virtual void Destroy()
        {
        }

        public virtual IList<Virologist> GetVirologists()
        {
            return virologists;
        }
    }
} //

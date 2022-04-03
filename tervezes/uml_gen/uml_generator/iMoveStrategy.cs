using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    /// <summary>
    /// Ez egy interface, ami előírja, hogy milyen metódusokkal kell rendelkeznie azoknak az osztályoknak,
    /// melyek entitásai az idő hatására változnak.
    /// </summary>
    public interface iMoveStrategy
    {
        /// <summary>
        /// Azt a metódust kell felüldefiniálnia az időben változó entitásoknak.
        /// </summary>
        /// <param name="v">A virológus, akin a mozgást végrehajtjuk.</param>
        /// <param name="from">a Mező amiről mozgatjuk a virológust</param>
        /// <param name="to">a Mező amire mozgatjuk a virológust</param>
        void ExecuteMove(Virologist v, Field from, Field to);
    }
}
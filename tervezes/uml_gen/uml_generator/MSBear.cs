using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class MSBear : iMoveStrategy
    {
        public void ExecuteMove(Virologist v, Field from, Field to)
        {
            Logger.NewFunctionCall(this, "ExecuteMove");
            Field random = from.GetRandomNeighbour();
            from.RemoveViro(v);
            IList<Virologist> virologists = random.GetVirologists();
            foreach (Virologist viro in virologists)
            {
                viro.ApplyAgent(new Bear(), v);
            }

            random.AcceptViro(v);
            random.Destroy();
            Logger.ReturnFunction();
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Bear : Agent
    {
        public Bear(): base()
        {
            strategy = new MSBear();
        }

        public override void Step()
        {
        }
    }
}
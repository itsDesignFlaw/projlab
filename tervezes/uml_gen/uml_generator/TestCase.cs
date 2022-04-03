using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    interface ITestCaseRun
    {
        void Run();
    }

    public class TestCase
    {
        TestCaseRun mRun;
        string name;
        string desc;
        public TestCase(string name, string desc, TestCaseRun mRun)
        {
            this.name = name;
            this.desc = desc;
            this.mRun = mRun;
        }
    }
}
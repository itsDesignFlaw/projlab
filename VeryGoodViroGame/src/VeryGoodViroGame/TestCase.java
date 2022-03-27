package VeryGoodViroGame;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

interface TestCaseSetup {
    void setup();
}
interface TestCaseRun {
    void run();
}

public class TestCase
{
    TestCaseSetup mSetup;
    TestCaseRun mRun;
    String name;
    String desc;

    public TestCase(String name, String desc, TestCaseSetup mSetup, TestCaseRun mRun)
    {
        this.name = name;
        this.desc = desc;
        this.mSetup = mSetup;
        this.mRun = mRun;
    }
}



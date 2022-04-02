package VeryGoodViroGame;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

interface TestCaseRun {
    void run();
}

public class TestCase
{
    TestCaseRun mRun;
    String name;
    String desc;

    public TestCase(String name, String desc, TestCaseRun mRun)
    {
        this.name = name;
        this.desc = desc;
        this.mRun = mRun;
    }
}



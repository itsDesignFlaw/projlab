using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Main
    {
        static void Print(string str)
        {
            System.@out.Println(str);
        }

        static bool AskRunTest()
        {
            int ID;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.@in));
            string str = "";
            while (true)
            {
                try
                {
                    System.@out.Print("Select test: ");
                    str = reader.ReadLine();
                    ID = Integer.ParseInt(str.ToLowerCase());
                    Print("\\n");
                    Tester.RunTest(ID - 1);
                    Thread.Sleep(1000);
                    System.@out.Print(".");
                    Thread.Sleep(1000);
                    System.@out.Print(".");
                    Thread.Sleep(1000);
                    System.@out.Print(".\\n");
                }
                catch (IOException ex)
                {
                    ex.PrintStackTrace();
                    return true;
                }
                catch (NumberFormatException ex)
                {
                    if (str.Trim().ToLowerCase().Equals("exit"))
                        return true;
                    Print("Invalid input: " + str);
                }
                catch (InterruptedException e)
                {
                    e.PrintStackTrace();
                }
            }
        }

        public static void Main(String[] args)
        {
            System.@out.Println("--== Biden Teszteloprogramja©®™ ==--");
            while (true)
            {
                Tester.ListTests();
                if (AskRunTest())
                    break;
            }
        }
    }
}
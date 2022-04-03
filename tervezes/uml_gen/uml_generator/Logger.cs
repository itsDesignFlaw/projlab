using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Logger
    {
        static bool isEnabled = true;
        static bool isFirst = true;
        private static string separator = " ";
        //static List<object, string> nameList = new List();
        static List<int> eltolasok = new List<int>();
        static readonly bool CMDBOL_INDITVA_NINCS_SZIN = true; //:(
        static int GetEltolas()
        {
            int e = 0;
            for (int i = 0; i < eltolasok.Count; i++)
            {
                e += eltolasok[i];
            }

            return e;
        }

        static void PutEltolas(int elt)
        {
            eltolasok.Add(elt);
        }

        static void PopEltolas()
        {
            if (eltolasok.IsEmpty())
                return;
            eltolasok.Remove(eltolasok.Count - 1);
        }

        static int LastEltolas()
        {
            return eltolasok[eltolasok.Count - 1];
        }

        static void SetEnabled(bool enabled)
        {
            isEnabled = enabled;
            if (enabled)
            {
                isFirst = true;
                eltolasok = new List<int>();
            }
        }

        static void Start()
        {
            Start(true);
        }

        static void Start(bool erase)
        {
            if (erase)
                Timer.steppable_reg = new List<iSteppable>();
            GameManager.map = null;
            SetEnabled(true);
            //nameList = new HashMap<object, string>();
        }

        static void AddObjectNames(Object[] L_oList, String[] L_sList)
        {
            for (int i = 0; i < L_oList.length; i++)
            {
                nameList.Put(L_oList[i], L_sList[i]);
            }
        }

        static string GetObjectName(object o)
        {
            return nameList[o];
        }

        public static void NewFunctionCall(object o, string mes)
        {
            if (!isEnabled)
                return;
            NewFunctionCallLegacy(GetObjectName(o), mes);
        }

        public static void ReturnFunction()
        {
            if (isEnabled)
            {
                int elt = LastEltolas() - 3;
                PopEltolas();
                if (GetEltolas() <= 1)
                {
                    Print("<" + "-".Repeat(elt + 2) + "[]");
                }
                else
                {
                    Print("[]<" + "-".Repeat(elt) + "[]");
                }
            }
        }

        static void Print(string mes)
        {
            if (!isEnabled)
                return;

            //System.out.println("printing: " + getEltolas());
            //System.out.println("printing: " + mes);
            //System.out.println("printing: " + separator.repeat(getEltolas()).length());
            System.@out.Println(separator.Repeat(GetEltolas()) + mes);
        }

        public static void NewFunctionCallLegacy(string obj, string msg)
        {
            if (!isEnabled)
                return;
            string str; //>:(
            int offset = 0;
            if (isFirst)
            {
                if (obj == null)
                {
                    str = "---" + ANSI_RED_BACKGROUND + obj + "." + msg + ANSI_RESET + "--->[]";
                    offset = -20;
                }
                else
                {
                    str = "---" + ANSI_RED + obj + "." + msg + ANSI_RESET + "--->[]";
                    offset = -9;
                }
            }
            else
            {
                Print("[]");
                if (obj == null)
                {
                    str = "[]---" + ANSI_RED_BACKGROUND + obj + "." + msg + ANSI_RESET + "--->[]";
                    offset = -20;
                }
                else
                {
                    str = "[]---" + ANSI_RED + obj + "." + msg + ANSI_RESET + "--->[]";
                    offset = -9;
                }
            }

            if (CMDBOL_INDITVA_NINCS_SZIN)
                offset = 0;
            Print(str);
            PutEltolas(str.Length() - 2 + offset);
            isFirst = false;
        }

        public static bool AskQuestion(string question)
        {
            if (isEnabled)
            {

                //System.out.print(separator.repeat(getEltolas()));
                string answer = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.@in));
                while (!answer.Equals("y") && !answer.Equals("n"))
                {
                    try
                    {
                        string msg = separator.Repeat(GetEltolas()) + "[]>" + question + "?(Y/N): ";
                        System.@out.Print(msg);
                        answer = reader.ReadLine().ToLowerCase();
                    }
                    catch (IOException e)
                    {
                        e.PrintStackTrace();
                    }
                }

                return answer.Trim().Equals("y");
            }

            return false;
        }

        /*public static final String ANSI_RESET = "\u001B[0m";
         public static final String ANSI_BLACK = "\u001B[30m";
         public static final String ANSI_RED = "\u001B[31m";
         public static final String ANSI_GREEN = "\u001B[32m";
         public static final String ANSI_YELLOW = "\u001B[33m";
         public static final String ANSI_BLUE = "\u001B[34m";
         public static final String ANSI_PURPLE = "\u001B[35m";
         public static final String ANSI_CYAN = "\u001B[36m";
         public static final String ANSI_WHITE = "\u001B[37m";
         
         public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
         public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
         public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
         public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
         public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
         public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
         public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
         public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";*/
        public static readonly string ANSI_RESET = "";
        public static readonly string ANSI_BLACK = "";
        public static readonly string ANSI_RED = "";
        public static readonly string ANSI_GREEN = "";
        public static readonly string ANSI_YELLOW = "";
        public static readonly string ANSI_BLUE = "";
        public static readonly string ANSI_PURPLE = "";
        public static readonly string ANSI_CYAN = "";
        public static readonly string ANSI_WHITE = "";
        public static readonly string ANSI_BLACK_BACKGROUND = "";
        public static readonly string ANSI_RED_BACKGROUND = "";
        public static readonly string ANSI_GREEN_BACKGROUND = "";
        public static readonly string ANSI_YELLOW_BACKGROUND = "";
        public static readonly string ANSI_BLUE_BACKGROUND = "";
        public static readonly string ANSI_PURPLE_BACKGROUND = "";
        public static readonly string ANSI_CYAN_BACKGROUND = "";
        public static readonly string ANSI_WHITE_BACKGROUND = "";
    }
}
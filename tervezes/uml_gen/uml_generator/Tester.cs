using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Tester
    {
        static List<TestCase> testcases = new List();
        static Tester()
        {

            //how to: teszteset: név, description, lambda a tesztre
            testcases.Add(new TestCase("SimpleMoveStrategy moves virologist", "Modellezzük a folyamatot, amikor a virológus bármiféle ágens hatása nélkül " + "mozog", () =>
            {

                //itt a kommunikációs diagram alapján inicializáljuk a példányokat, a 'környezetet'
                Field from = new Field();
                Field to = new Field();
                Virologist virologist = new Virologist();
                MSSimple ms = new MSSimple();
                virologist.ChangeMoveStrategy(ms);
                virologist.SetField(from);

                //Ha megvagyunk, elindítjuk a loggert (innentől loggolja a hívásokat)
                //ez visszaállítja a statikus osztályok állapotát (pl timer steppablejei, gamemanager-hez kötött map)
                Logger.Start();
                Object[] obdzss = new[]{from, to, virologist, ms};
                String[] neveqh = new[]{"f1", "f2", "v1", "ms"};

                //itt két tömbben a példányokat és a nevesítésüket rögzítjük
                //statikus osztály (gamemanager/timer) esetében nem tudunk, és nem is kell írni bele semmit
                Logger.AddObjectNames(obdzss, neveqh);

                //átadjuk a loggernek a listáinkat, így tudja milyen nevet írjon ki
                //a kommunikációs diagram utolsó metódushívása, ezzel indul meg a tesztelés
                virologist.MoveTo(to); //ezzel vége a tesztesetnek
            }));
            testcases.Add(new TestCase("VitusDanceMoveStrategy randomly moves virologist", "Modellezzük a folyamatot, " + "amikor a virológus vitustánc " + "járása közben mozog.", () =>
            {
                Field from = new Field();
                Field to = new Field();
                from.AddNeighbour(to);
                Virologist virologist = new Virologist();
                virologist.SetField(from);
                MSVitusDance dm = new MSVitusDance();
                virologist.ChangeMoveStrategy(dm);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{from, to, virologist, dm};
                String[] neveqh = new[]{"f1", "f2", "v1", "dm"};
                Logger.AddObjectNames(obdzss, neveqh);
                virologist.MoveTo(to);
            }));
            testcases.Add(new TestCase("ParalyzedMoveStrategy not moves virologist", "Modellezzük a folyamatot, amikor a " + "virológus bénult állapotba kerül," + " " + "nem tud megmozdulni.", () =>
            {
                Field from = new Field();
                Field to = new Field();
                Virologist virologist = new Virologist();
                virologist.SetField(from);
                MSParalyzed pm = new MSParalyzed();
                virologist.ChangeMoveStrategy(pm);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{from, to, virologist, pm};
                String[] neveqh = new[]{"f1", "f2", "v1", "pm"};
                Logger.AddObjectNames(obdzss, neveqh);
                virologist.MoveTo(to);
            }));
            testcases.Add(new TestCase("Virologist interacts with FieldBunker", "A virológus megpróbálja felvenni egy " + "FiledBunker mezőn található felszerelést" + ".", () =>
            {
                Virologist v = new Virologist();
                FieldBunker b = new FieldBunker();
                v.SetField(b);
                EquipmentGloves e = new EquipmentGloves();
                b.SetEquipment(e);
                b.AcceptViro(v);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, b, e};
                String[] neveqh = new[]{"v", "b", "e"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.InteractWithField();
            }));
            testcases.Add(new TestCase("Virologist interacts with FieldLab", "A virológus megpróbálja leolvasni egy " + "FieldLab mezőn található genetikai kódot.", () =>
            {
                Virologist virologist = new Virologist();
                FieldLab lab = new FieldLab();
                GeneticCode geneticCode = new GeneticCode();
                lab.SetCode(geneticCode);
                lab.AcceptViro(virologist);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{virologist, lab, geneticCode};
                String[] neveqh = new[]{"v", "l", "code"};
                Logger.AddObjectNames(obdzss, neveqh);
                virologist.InteractWithField();
            }));
            testcases.Add(new TestCase("Virologist interacts with FieldWarehouse", "A virológus megpróbálja felvenni egy " + "FieldWarehouse mezőn található " + "anyagot.", () =>
            {
                Virologist v = new Virologist();
                FieldWarehouse w = new FieldWarehouse();
                v.SetField(w);
                Resource r = new Resource();
                Resource r2 = new Resource(); //todo: diagrammok rosszak: ez a virologus resource-ja
                v.SetResource(r2);
                w.SetResource(r);
                w.AcceptViro(v);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, w, r, r2};
                String[] neveqh = new[]{"v", "w", "r", "r2"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.InteractWithField();
            }));
            testcases.Add(new TestCase("Virologist tries to put virus on a Virologist", "Annak a folyamatnak a modellje, " + "amikor egy virológus egy másik" + " " + "virológusra próbál kenni " + "egy " + "vírust.", () =>
            {

                //TODO: nincs CanApplyAgent, CanAgentBeApplied, képcsere
                Virologist v1 = new Virologist();
                Virologist v2 = new Virologist();
                Agent agent = new Forget();
                EquipmentGloves e = new EquipmentGloves();
                EquipmentSack e2 = new EquipmentSack();
                v1.AddEquipment(e);
                v2.AddEquipment(e2);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v1, v2, agent, e, e2};
                String[] neveqh = new[]{"v1", "v2", "a", "e", "e2"};
                Logger.AddObjectNames(obdzss, neveqh);
                v1.UseAgent(agent, v2);
            }));
            testcases.Add(new TestCase("Virologist puts virus on a Virologist", "Annak a folyamatnak a modellje, " + "amikor egy virológus egy másik " + "virológusra ken egy vírust.", () =>
            {
                Virologist v1 = new Virologist();
                Virologist v2 = new Virologist();
                Agent agent = new Forget();

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v1, v2, agent};
                String[] neveqh = new[]{"v1", "v2", "agent"};
                Logger.AddObjectNames(obdzss, neveqh);
                v1.UseAgent(agent, v2);
            }));
            testcases.Add(new TestCase("Virologist vaccinates a Virologist", "Lemodellezzük a folyamatot, ahogy egy " + "virológus vakcinát ad be egy másik " + "virológusnak.", () =>
            {
                Virologist v1 = new Virologist();
                Virologist v2 = new Virologist();
                Forget forget = new Forget(); // lehetne barmi mas agens is
                Vaccine vc = new Vaccine(forget);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v1, v2, forget, vc};
                String[] neveqh = new[]{"v1", "v2", "f", "vc"};
                Logger.AddObjectNames(obdzss, neveqh);
                v1.UseAgent(vc, v2);
            }));
            testcases.Add(new TestCase("Virologist tries to craft virus", "Annak a  folyamatnak a modellezése, melyben a " + "virológus megpróbál vírust kraftolni.", () =>
            {

                //TODO: nagyon nem hasonlít a 5.3.11-re
                Virologist v = new Virologist();
                GeneticCode code = new GeneticCode();
                EquipmentSack e = new EquipmentSack();
                Forget a = new Forget();
                Resource r = new Resource();
                v.SetResource(r);
                v.AddEquipment(e);
                v.LearnGeneticCode(code);
                code.SetCost(r);
                code.SetAgent(a);

                //TODO: ez nemtom h jo e remelem nem......
                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, code, e, a, r};
                String[] neveqh = new[]{"v", "code", "e", "a", "r"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.CraftVirus(code);
            }));
            testcases.Add(new TestCase("Virologist crafts virus", "Annak a folyamatnak a modellezése, melyben a " + "virológus" + " sikeresen vírust kraftol.", () =>
            {
                Virologist v = new Virologist();
                GeneticCode code = new GeneticCode();
                Forget a = new Forget();
                Resource r = new Resource();
                v.SetResource(r);
                v.LearnGeneticCode(code);
                code.SetCost(r);
                code.SetAgent(a);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, code, a, r};
                String[] neveqh = new[]{"v", "code", "a", "r"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.CraftVirus(code); //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
            }));
            testcases.Add(new TestCase("Virologist tries to steal equipment", "Lemodellezzük a folyamatot, amelyben a " + "virológus felszerelést kísérel meg lopni " + "egy másik " + "virológustól.", () =>
            {
                Virologist v = new Virologist();
                Virologist v2 = new Virologist();
                EquipmentSack e = new EquipmentSack();
                Protect a = new Protect();
                v2.AddEquipment(e);
                v.AddItem(a);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, v2, a, e};
                String[] neveqh = new[]{"v", "v2", "a", "e"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.StealEquipmentFromViro(v2, e);
            }));
            testcases.Add(new TestCase("Virologist steals equipment", "Lemodellezzük a folyamatot, amelyben a virológus " + "felszerelést lop egy másik virológustól.", () =>
            {

                //TODO:kép nem jó (Márk: Hát igazából jó, csak itt lefut végig a próbálkozás, egészen a sikerig)
                Virologist v = new Virologist();
                Virologist v2 = new Virologist();
                EquipmentSack e = new EquipmentSack();
                v2.AddEquipment(e);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, v2, e};
                String[] neveqh = new[]{"v", "v2", "e"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.StealEquipmentFromViro(v2, e);
            }));
            testcases.Add(new TestCase("Virologist tries to steal resource", "Lemodellezzük a folyamatot, amelyben a " + "virológus anyagot  kísérel meg lopni egy " + "másik virológustól.", () =>
            {
                Virologist v = new Virologist();
                Resource r = new Resource();
                Virologist v2 = new Virologist();
                Resource r2 = new Resource();
                EquipmentSack e = new EquipmentSack();
                v.SetResource(r);
                v2.SetResource(r2);
                v.AddEquipment(e);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, r, v2, r2, e};
                String[] neveqh = new[]{"v", "r", "v2", "r2", "e"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.StealResourceFromViro(v2, new Resource()); //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
            }));
            testcases.Add(new TestCase("Virologist steals resource", "Lemodellezzük a folyamatot, amelyben a virológus " + "anyagot lop egy másik virológustól.", () =>
            {

                //todo: szekvencia diagram szar: nincs rajta a resource
                //todo: itt hozzaadjuk a resourcet, pedig csak meg kéne próbálnia (Zoli: itt már ténylegesen csináljuk,
                // nem próbáljuk)
                Virologist v = new Virologist();
                Resource r = new Resource();
                Virologist v2 = new Virologist();
                Resource r2 = new Resource();
                v.SetResource(r);
                v2.SetResource(r2);

                //ENTER
                Logger.Start(); //VÉGE AZ INICIALIZÁLÁSNAK
                Object[] obdzss = new[]{v, r, v2, r2};
                String[] neveqh = new[]{"v", "r", "v2", "r2"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.StealResourceFromViro(v2, new Resource()); //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
            }));
            testcases.Add(new TestCase("Virologist checks if they are paralyzed", "Modell arra a folyamara, melyben egy " + "virológus a rajta található inventory " + "entitások " + "alapján eldönti, hogy le " + "van-e bénulva.", () =>
            {
                Virologist v = new Virologist();
                EquipmentSack e = new EquipmentSack();
                Forget a = new Forget();
                EquipmentGloves e2 = new EquipmentGloves();
                v.AddEquipment(e);
                v.AddItem(a);
                v.AddEquipment(e2);

                //ENTER
                Logger.Start(); //VÉGE AZ INICIALIZÁLÁSNAK
                Object[] obdzss = new[]{v, e, a, e2};
                String[] neveqh = new[]{"v", "e", "a", "e2"};
                Logger.AddObjectNames(obdzss, neveqh);
                v.IsParalyzed(); //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
            }));
            testcases.Add(new TestCase("Timer steps tick-based elements", "Modellezük, ahogy időzítő minden hozzá " + "regisztrált steppable példányt léptet, " + "szimulálva ezzel a játék időbeli haladását", () =>
            {
                Virologist v = new Virologist();
                Forget a = new Forget();
                a.Apply(v, v);

                //ENTER
                Logger.Start();
                Object[] obdzss = new[]{v, a};
                String[] neveqh = new[]{"v", "a"};
                Logger.AddObjectNames(obdzss, neveqh);
                Logger.SetEnabled(false);
                Timer.AddSteppable(a);
                Logger.SetEnabled(true);
                Timer.Step(); //todo: rossz a teszteset, az iMoveStrategy-s dolog miatt itt ez rossz
                //es aztan maga a tesz
            }));
            testcases.Add(new TestCase("All tests", "Minden feljebbi teszt egymás után sorrendben", () =>
            {
                for (int i = 0; i < testcases.Count; i++)
                {
                    if (testcases[i].name.Equals("All tests"))
                        continue;
                    RunTest(i);
                } //es aztan maga a tesz
            }));
        }

        static void Print(string str)
        {
            System.@out.Println(str);
        }

        static void ListTests()
        {
            int width = 60;
            Print("=".Repeat(width));
            int middlelen = (width - 10) / 2;
            Print("|ID| " + " ".Repeat(middlelen) + "name" + " ".Repeat(middlelen));
            Print("=".Repeat(width));
            for (int i = 0; i < testcases.Count; i++)
            {
                if (i < 9)
                {
                    Print("| " + (i + 1) + "| " + testcases[i].name);
                }
                else
                {
                    Print("|" + (i + 1) + "| " + testcases[i].name);
                }
            }

            Print("=".Repeat(width));
        }

        static void RunTest(int id)
        {
            if (id >= testcases.Count)
            {
                Print("Ez nem jo ID!");
                return;
            }

            TestCase curtest = testcases[id];
            Print("\\n\\n\\n\\nID: " + (id + 1) + "\\nName: " + curtest.name + "\\nDescription: " + curtest.desc + "\\n");
            Logger.SetEnabled(false);
            curtest.mRun.Run();
            Logger.SetEnabled(false);
            Print("\\n________________test over________________");
            if (Logger.GetEltolas() > 0)
                Print("\\n\\n\\n\\nBajvan bajvan! Valahol hianyzik egy visszateres!"); // EZ MAGA A TÉBOLY
        } //TODO: setEnable(false) teszt után!!
    }
}
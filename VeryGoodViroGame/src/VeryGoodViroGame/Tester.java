package VeryGoodViroGame;

import java.util.ArrayList;

public class Tester {
    static ArrayList<TestCase> testcases = new ArrayList<>();

    static {
        testcases.add(new TestCase(
                "Virologist Move",
                "Ez a teszteset leellenőrzi a virológus mozgását.",
                () -> {
                    //ENTER
                    Logger.SetEnabled(true);
                    Field from = new Field();
                    Field to = new Field();
                    Virologist virologist = new Virologist();
                    virologist.MoveTo(to);
                }
        )); //todo megbaszni goldit

        testcases.add(new TestCase(
                "SimpleMoveStrategy moves virologist",
                "Modellezzük a folyamatot, amikor a virológus bármiféle ágens hatása nélkül mozog",
                () -> {
                    Field from = new Field();
                    Field to = new Field();
                    Virologist virologist = new Virologist();
                    virologist.ChangeMoveStrategy(new MSSimple());
                    //ENTER
                    Logger.SetEnabled(true);

                    virologist.moveStrategy.ExecuteMove(virologist, from, to);
                }
        ));

        testcases.add(new TestCase(
                "VitusDanceMoveStrategy randomly moves virologist",
                "Modellezzük a folyamatot, amikor a virológus vitustánc járása közben mozog.",
                () -> {
                    Field from = new Field();
                    Field to = new Field();
                    Virologist virologist = new Virologist();
                    virologist.ChangeMoveStrategy(new MSVitusDance());
                    //ENTER
                    Logger.SetEnabled(true);
                    virologist.moveStrategy.ExecuteMove(virologist, from, to);
                }));

        testcases.add(new TestCase(
                "ParalyzedMoveStrategy not moves virologist",
                "Modellezzük a folyamatot, amikor a virológus bénult állapotba kerül, nem tud megmozdulni.",
                () -> {
                    Field from = new Field();
                    Field to = new Field();
                    Virologist virologist = new Virologist();
                    virologist.ChangeMoveStrategy(new MSParalyzed());
                    //ENTER
                    Logger.SetEnabled(true);
                    virologist.moveStrategy.ExecuteMove(virologist, from, to);

                }));

        testcases.add(new TestCase(
                "Virologist interacts with FieldBunker",
                "A virológus megpróbálja felvenni egy FiledBunker mezőn található felszerelést.",
                () -> {
                    Virologist v = new Virologist();
                    FieldBunker b = new FieldBunker();
                    EquipmentGloves e = new EquipmentGloves();
                    b.setEquipment(e);
                    b.AcceptViro(v);
                    //ENTER
                    Logger.SetEnabled(true);
                    b.Interact(v);
                }));

        testcases.add(new TestCase(
                "Virologist interacts with FieldLab",
                "A virológus megpróbálja leolvasni egy FieldLab mezőn található genetikai kódot.",
                () -> {
                    Virologist virologist = new Virologist();
                    FieldLab lab = new FieldLab();
                    GeneticCode geneticCode = new GeneticCode();
                    lab.setCode(geneticCode);
                    lab.AcceptViro(virologist);
                    //ENTER
                    Logger.SetEnabled(true);
                    lab.Interact(virologist);
                }));

        testcases.add(new TestCase(
                "Virologist interacts with FieldWarehouse",
                "A virológus megpróbálja felvenni egy FieldWarehouse mezőn található anyagot.",
                () -> {
                    Virologist v = new Virologist();
                    FieldWarehouse w = new FieldWarehouse();
                    Resource r = new Resource();
                    w.setResource(r);
                    w.AcceptViro(v);
                    //ENTER
                    Logger.SetEnabled(true);
                    w.Interact(v);
                }));

        testcases.add(new TestCase(
                "Virologist tries to put virus on a Virologist",
                "Annak a folyamatnak a modellje, amikor egy virológus egy másik virológusra próbál kenni egy vírust.",
                () -> {
                    Virologist v1 = new Virologist();
                    Virologist v2 = new Virologist();
                    Agent agent = new Forget();

                    //ENTER
                    Logger.SetEnabled(true);
                    v1.UseAgent(agent, v2);
                }));

        testcases.add(new TestCase(
                "Virologist tries puts virus on a Virologist",
                "Annak a folyamatnak a modellje, amikor egy virológus egy másik virológusra ken egy vírust.",
                () -> {
                    Virologist v1 = new Virologist();
                    Virologist v2 = new Virologist();
                    Agent agent = new Forget();// TODO meg kell különböztetni a tries to-tól
                    //ENTER
                    Logger.SetEnabled(true);
                    v1.UseAgent(agent, v2);
                }));

        testcases.add(new TestCase(
                "Virologist vaccinates a Virologist",
                "Lemodellezzük a folyamatot, ahogy egy virológus vakcinát ad be egy másik virológusnak.",
                () -> {
                    //kommunikacios diagram alapjan inicializalas:
                    Virologist v1 = new Virologist();
                    Virologist v2 = new Virologist();
                    //Vaccine vc = new Vaccine();
                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Virologist tries to craft virus",
                "Annak a  folyamatnak a modellezése, melyben a virológus megpróbál vírust kraftolni.",
                () -> {
                    //kommunikacios diagram alapjan inicializalas:
                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Virologist crafts virus",
                "Annak a folyamatnak a modellezése, melyben a virológus sikeresen vírust kraftol.",
                () -> {
                    Virologist v = new Virologist();
                    GeneticCode code = new GeneticCode();
                    EquipmentSack e = new EquipmentSack();
                    Forget a = new Forget();
                    Resource r = new Resource();
                    v.AddEquipment(e);
                    v.LearnGeneticCode(code);


                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Virologist tries to steal equipment",
                "Lemodellezzük a folyamatot, amelyben a virológus felszerelést kísérel meg lopni egy másik virológustól.",
                () -> {
                    Virologist v = new Virologist();
                    Virologist v2 = new Virologist();
                    EquipmentSack e = new EquipmentSack();
                    Protect a = new Protect();
                    v2.AddEquipment(e);
                    v.AddItem(a);
                    //ENTER
                    Logger.SetEnabled(true);
                    v.StealEquipmentFromViro(v2, e);

                }));
        testcases.add(new TestCase(
                "Virologist steals equipment",
                "Lemodellezzük a folyamatot, amelyben a virológus felszerelést lop egy másik virológustól.",
                () -> {
                    Virologist v = new Virologist();
                    Virologist v2 = new Virologist();
                    EquipmentSack e = new EquipmentSack();
                    v2.AddEquipment(e);
                    //ENTER
                    Logger.SetEnabled(true);

                    v.StealEquipmentFromViro(v2, e);
                }));
        testcases.add(new TestCase(
                "Virologist tries to steal resource",
                "Lemodellezzük a folyamatot, amelyben a virológus anyagot  kísérel meg lopni egy másik virológustól.",
                () -> {
                    //kommunikacios diagram alapjan inicializalas:
                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Virologist steals resource",
                "Lemodellezzük a folyamatot, amelyben a virológus anyagot lop egy másik virológustól.",
                () -> {
                    Virologist v = new Virologist();
                    Resource r = new Resource();
                    Virologist v2 = new Virologist();
                    Resource r2 = new Resource();

                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Virologist checks if they are paralyzed",
                "Modell arra a folyamara, melyben egy virológus a rajta található inventory entitások alapján eldönti, hogy le van-e bénulva.",
                () -> {
                    Virologist v = new Virologist();
                    EquipmentSack e = new EquipmentSack();
                    Forget a = new Forget();
                    EquipmentGloves e2 = new EquipmentGloves();
                    v.AddEquipment(e);
                    v.AddItem(a);
                    v.AddEquipment(e2);
                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK
                    v.IsParalyzed();

                    //es aztan maga a teszt szia Zoli szia Dani  szijjaaaaa szijjjjjjjasztooooook
                }));
        testcases.add(new TestCase(
                "Timer steps tick-based elements",
                "Modellezük, ahogy időzítő minden hozzá regisztrált steppable példányt léptet, szimulálva ezzel a játék időbeli haladását",
                () -> {
                    Virologist v = new Virologist();
                    Forget a = new Forget();
                    a.Apply(v, v);
                    //ENTER
                    Logger.SetEnabled(true); //VÉGE AZ INICIALIZÁLÁSNAK
                    Timer.Step();

                    //es aztan maga a tesz
                }));
    }

    static void print(String str) {
        System.out.println(str);
    }

    static void ListTests() {
        int width = 60;
        print("=".repeat(width));
        int middlelen = (width - 10) / 2;
        print("|ID| " + " ".repeat(middlelen) + "name" + " ".repeat(middlelen));
        print("=".repeat(width));
        for (int i = 0; i < testcases.size(); i++) {
            if (i < 9) {
                print("| " + (i + 1) + "| " + testcases.get(i).name);
            } else {
                print("|" + (i + 1) + "| " + testcases.get(i).name);
            }
        }
        print("=".repeat(width));
    }

    static void RunTest(int id) {
        TestCase curtest = testcases.get(id);
        if (curtest == null) {
            print("Ez nem jo ID!");
            return;
        }
        print("\n\n\n\nID: " + (id + 1) + "\nName: " + curtest.name + "\nDescription: " + curtest.desc + "\n");
        Logger.SetEnabled(false);
        curtest.mRun.run();
        print("\n________________test over________________");
    }
    //TODO: setEnable(false) teszt után!!
}

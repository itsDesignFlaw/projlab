package VeryGoodViroGame;

public class Main
{

    public static void main(String[] args)
    {
        //buziintellij //miafasz
        System.out.println("Hello Kopasz Qgli"); // TODO: grafika házi
        System.out.println("HELO");
    }

    public static void tesztteszt()
    {
        //Kommunikacios diagram alapjan:
        Map map = new Map();
        Field field = new Field();
        Field field2 = new Field();
        map.fields.set(1, field);
        map.fields.set(2, field);
        Virologist viro = new Virologist();
        viro.mezo = field;

        //teszt: szekvenciadiagram:
        //viro.movestrategy.DoMoves() --->

        Logger.NewFunctionCall("Test main test");
        if (Logger.AskQuestion("Buzi-e vagy"))
            field2.AcceptViro(viro);

    }
}

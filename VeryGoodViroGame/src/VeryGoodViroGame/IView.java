package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Field.Field;

import javax.swing.*;
import java.util.ArrayList;

public interface IView
{
    /**
     * Az egész form inicializálása, itt jön létre, és jelenik is meg
     */
    void Init();
    
    /**
     * Egy megjelnítendő objektum hozzáaadása, kötelező
     *
     * @param o Az objektum
     * @param s Típusa az objektumnak
     */
    void AddObject(Object o, String s);
    
    /**
     * Kiszed egy objektumot a megjelenítendő objektumok listájából
     *
     * @param o az objektum
     */
    void RemoveObject(Object o);
    
    /**
     * Clear, mi más lenne? Törli az összes megjelenítendő objektumot
     */
    void Clear();
    
    /**
     * Kirajzolja, mennyi resource van
     *
     * @param r Virologus resource-a
     */
    void DrawResource(Resource r);
    
    /**
     * Kirajzolja a pályát a Virológusokkal együtt
     *
     * @param current    A jelenlegi mező ,amin áll
     * @param neighbours A Mezőnek a szomszédjai
     */
    void DrawMap(Field current, java.util.List<Field> neighbours);
    
    /**
     * Kirajzolja a Genetikai kódokat
     *
     * @param codes Genetikai kódok
     */
    void DrawGeneticCodes(java.util.List<GeneticCode> codes);
    
    /**
     * Besetteli az aktív virológust
     *
     * @param name aktív virológus
     */
    void MarkActiveViro(String name);
    
    /**
     * Kirajzolja a megadott helyre a virológusokat
     *
     * @param viros     Virológusok, amiket egymás mellé rajzol
     * @param xOffset   Offset X
     * @param yOffset   Offset Y
     * @param useOffset Ha igaz, akkor a középpont az Offset, ha hamis, akkor a képernyő közepére rajzol
     */
    void DrawViros(java.util.List<Virologist> viros, int xOffset, int yOffset, boolean useOffset);
    
    /**
     * Itemek rajzol ki a HUDra
     *
     * @param items A virológus itemjei
     */
    void DrawItems(ArrayList<InvItem> items);
    
    /**
     * Kirajzolja a virológusra ható effeketeket
     *
     * @param effects Virológus effektjei
     */
    void DrawEffects(java.util.List<Agent> effects);
    
    //Újrarajzolja a panelt
    void Repaint();
    
    /**
     * Visszaadja a Framet. I am the force and the force is with me
     *
     * @return A Frame
     */
    JFrame getFrame();
}

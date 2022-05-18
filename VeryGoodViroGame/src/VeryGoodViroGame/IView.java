package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Field.Field;

import javax.swing.*;
import java.util.ArrayList;

public interface IView
{
    void Init();
    
    void AddObject(Object o, String s);
    
    void RemoveObject(Object o);
    
    void Clear();
    
    void DrawResource(Resource r);
    
    void DrawMap(Field current, java.util.List<Field> neighbours);
    
    void DrawGeneticCodes(java.util.List<GeneticCode> codes);
    
    void MarkActiveViro(String name);
    
    void DrawViros(java.util.List<Virologist> viros, int xOffset, int yOffset, boolean useOffset);
    
    void DrawItems(ArrayList<InvItem> items);
    
    void DrawEffects(java.util.List<Agent> effects);
    
    void Repaint();
    
    JFrame getFrame();
}

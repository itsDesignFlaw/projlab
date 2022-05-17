package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Equipment.Equipment;
import VeryGoodViroGame.Field.Field;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class View
{
    class ViewObject
    {
        String png;
        Object ref;
        
        ViewObject(String s, Object r)
        {
            png = s;
            ref = r;
        }
    }
    
    enum Levels
    {
        BACKGROUND, MAP, HUD
    }
    
    //Lefordítás fájlokra
    private static HashMap<String, String> images = new HashMap<>();
    private HashMap<Object, ViewObject> objects = new HashMap<>();
    private final static String ResourcePath = "/resources/";
    public ViewController controller;
    
    static
    {
        
        images.put("ami", "aminoacid.png");
        images.put("axe", "axe.png");
        images.put("bear", "bear.png");
        images.put("vaccine", "bg_vaccine.png");
        images.put("virus", "bg_virus.png");
        images.put("bunker", "bunker.png");
        images.put("coat", "coat.png");
        images.put("dance", "dance.png");
        images.put("effect_agent", "effect_agent.png");
        images.put("effect_equipment", "effect_equipment.png");
        images.put("effect_equipment_broken", "effect_equipment_broken.png");
        images.put("field", "field_simple.png");
        images.put("forget", "forget.png");
        images.put("gloves", "gloves.png");
        images.put("lab", "lab.png");
        images.put("bearlab", "lab.png");
        images.put("nuki", "nucleotid.png");
        images.put("paralyze", "paralyze.png");
        images.put("protect", "protect.png");
        images.put("sack", "sack.png");
        images.put("viro", "viro.png");
        images.put("bearviro", "viro.png");
        images.put("ware", "warehouse.png");
    }
    
    //Esetleg HashMap, és a value egy Levels, hogy melyik szinten van?
    //Vagy egy új osztály, pozíció, kép, szint
    private ArrayList<DrawableComponent> drawables = new ArrayList<>();
    
    
    JFrame frame;
    
    
    //Sajat Componens
    MapPanel panel;
    
    public void AddObject(Object o, String s)
    {
        objects.put(o, new ViewObject(images.get(s), o));
    }
    
    public View(ViewController c)
    {
        controller = c;
    }
    
    public void RemoveObject(Object o)
    {
        objects.remove(o);
    }
    
    public void Init()
    {
        frame = new JFrame("Very Good Viro Game --pre alpha test v0.0.0.-2");
        frame.setSize(1024, 768);
        ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/az.png"));
        frame.setIconImage(icon.getImage());
        
        panel = new MapPanel();
        
        //Ez a felső menübár
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("titkos üzenet");
        JMenuItem menuitem = new JMenuItem("száll a széllel");
        
        
        menu.add(menuitem);
        JMenuItem turn = new JMenuItem("End turn");
        turn.addActionListener(x -> controller.EndTurn());
        menu.add(turn);
        menubar.add(menu);
        
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setJMenuBar(menubar);
        
        //Jobb klikk menü, az osztélyban lehet módosítani a kinézetén
        //panel.setComponentPopupMenu(new ContextMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //ne kelljen ezzel foglalkozni
        frame.setVisible(true);
        panel.Reset();
        
        
        //DrawFromString("viro");
        
    }
    
    public void Clear()
    {
        panel.removeAll();
    }
    
    private BufferedImage GetImage(Object name)
    {
        try
        {
            //Ezzel a getResource móddal lehet elvileg jar fileból is beolvasni, azaz akkor is jó útvonalat ad meg
            //Minden fájl ami az src mappán belül van tuti megtalálja
            BufferedImage im = ImageIO.read(Main.class.getResource(ResourcePath + objects.get(name).png));
            return im;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public void DrawFromString(String name)
    {
        try
        {
            //Ezzel a getResource móddal lehet elvileg jar fileból is beolvasni, azaz akkor is jó útvonalat ad meg
            //Minden fájl ami az src mappán belül van tuti megtalálja
            BufferedImage im = ImageIO.read(Main.class.getResource(ResourcePath + images.get(name)));
            panel.DrawImage(im, 200, 100);
            panel.repaint();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void DrawMap(Field current, java.util.List<Field> neighbours)
    {
        BufferedImage cur = GetImage(current);
        panel.DrawImage(cur, panel.getWidth() / 2 - cur.getWidth() / 2, panel.getHeight() / 2 - cur.getHeight() / 2).setComponentPopupMenu(new FieldContext(current));
        JLabel name = new JLabel(EntityManager.GetObjectName(current));
        AddName(panel.getWidth() / 2, panel.getHeight() / 2 - cur.getHeight() / 2, name);
        int size = neighbours.size();
        for(int i = 0; i < size; i++)
        {
            BufferedImage img = GetImage(neighbours.get(i));
            double a = i * 2 * Math.PI / size - Math.PI / 4;
            int x = (int) (Math.cos(a) * (panel.getWidth() / 2 - 60) + panel.getWidth() / 2);
            int y = (int) (Math.sin(a) * (panel.getHeight() / 2 - 60) + panel.getHeight() / 2);
            //Work in Progress, ha valami jobb ötlet, nyugodtan lehet cserélni
            panel.DrawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2).addMouseListener(new FieldClick(neighbours.get(i)));
            name = new JLabel(EntityManager.GetObjectName(neighbours.get(i)));
            AddName(x, y - img.getHeight() / 2, name);
        }
    }
    
    private void AddName(int x, int y, JLabel name)
    {
        panel.add(name);
        name.setFont(name.getFont().deriveFont(16.0f));
        name.setForeground(Color.red);
        name.setSize(name.getPreferredSize());
        name.setLocation(x - name.getWidth() / 2, y - name.getHeight() - 1);
    }
    
    public void DrawGeneticCodes(java.util.List<GeneticCode> codes)
    {
    
    }
    
    private static BufferedImage rotateImage(BufferedImage buffImage, double angle)
    {
        double radian = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radian));
        double cos = Math.abs(Math.cos(radian));
        
        int width = buffImage.getWidth();
        int height = buffImage.getHeight();
        
        int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
        int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);
        
        BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = rotatedImage.createGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        // rotation around the center point
        graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
        graphics.drawImage(buffImage, 0, 0, null);
        graphics.dispose();
        
        return rotatedImage;
    }
    
    public void DrawViros(java.util.List<Virologist> viros)
    {
        int itemHudSize = viros.size() * 64;
        int leftMostPointOfItems = (panel.getWidth() - itemHudSize) / 2;
        for(int i = 0; i < viros.size(); i++)
        {
            int x = leftMostPointOfItems + i * 64;
            int y = panel.getHeight() / 2;
            //G2D.drawRect(x - 3, y, 64, 64);
            BufferedImage img = GetImage(viros.get(i));
            if(viros.get(i).dead)
            {
                panel.DrawImage(rotateImage(img, 90), x, y + img.getHeight() / 2).setComponentPopupMenu(new ViroContext(viros.get(i)));
            }
            else
            {
                panel.DrawImage(img, x, y + img.getHeight() / 2).setComponentPopupMenu(new ViroContext(viros.get(i)));
            }
            JLabel name = new JLabel(EntityManager.GetObjectName(viros.get(i)));
            AddName(x + img.getWidth() / 2-1, y + img.getHeight() + 50, name);
            name.grabFocus();
        }
    }
    
    public void DrawItems(ArrayList<InvItem> items)
    {
        int itemHudSize = items.size() * 64;
        int leftMostPointOfItems = (panel.getWidth() - itemHudSize) / 2;
        for(int i = 0; i < items.size(); i++)
        {
            int x = leftMostPointOfItems + i * 64;
            int y = panel.getHeight() - 100;
            //G2D.drawRect(x - 3, y, 64, 64);
            BufferedImage img = GetImage(items.get(i));
            JLabel l = panel.DrawImage(img, x, y);
            l.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            if(items.get(i) instanceof Agent)
                l.addMouseListener(new AgentClick((Agent) items.get(i)));
            else
                l.addMouseListener(new EquipmentClick((Equipment) items.get(i)));
            
        }
    }
    
    public void AddHUDElement(String name, int count)
    {
    
    }
    
    public void DrawEffects(java.util.List<Agent> effects)
    {
    
    }
    
    //Ez nem kell, mert beépített popup van
    public void DrawContextMenu(int x, int y)
    {
    
    }
    
    public void Repaint()
    {
        panel.repaint();
    }
    
    private class FieldClick extends MouseAdapter
    {
        Field f;
        
        FieldClick(Field f)
        {
            this.f = f;
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            controller.MoveViro(f);
        }
    }
    
    private class AgentClick extends MouseAdapter
    {
        Agent f;
        
        AgentClick(Agent f)
        {
            this.f = f;
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            controller.SetAgent(f);
        }
    }
    
    private class EquipmentClick extends MouseAdapter
    {
        Equipment f;
        
        EquipmentClick(Equipment f)
        {
            this.f = f;
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            controller.SetEquipment(f);
        }
    }
    
    class MapPanel extends JPanel
    {
        //TODO: Akár a virológusok szétszedése, hogy mindig a pálya felett legyen?
        //TODO: A JLabelös megoldás jobb, ezek már nem kellenek, legfeljebb a háttér
        BufferedImage background, map, hud;
        
        public MapPanel()
        {
            //Reset();
            JLabel label = new JLabel(new ImageIcon(Main.class.getResource("/resources/az.png")));
            
            //Esetleg egy új osztály, ami eltárolja, hogy ha kattintunk, mit hívjon meg?
            //Pl ViewController.ClickOnViro
            label.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println("Hello There");
                }
            });
            setLayout(null);
            add(label);
            label.setLocation(100, 100);
            label.setSize(50, 50);
            //Ez csak mint akód, lehet majd törölni, jelenleg csak nem látszik
            label.setVisible(false);
            
            
        }
        
        public void ShowTestCaseMap()
        {
            try
            {
                BufferedImage bg = ImageIO.read(Main.class.getResource("/resources/bg.png"));
                BufferedImage axe = ImageIO.read(Main.class.getResource("/resources/axe.png"));
                BufferedImage viro = ImageIO.read(Main.class.getResource("/resources/viro.png"));
                BufferedImage lab = ImageIO.read(Main.class.getResource("/resources/lab.png"));
                BufferedImage bunker = ImageIO.read(Main.class.getResource("/resources/bunker.png"));
                
                //createGraphics egy Graphics2d objektumot ad vissza, a getGraphics csak sima Graphicsot
                //Mindkettő ugyanúgy működik (konkrétan a getGraphics a createGraphicsot hívja meg BufferedImage esetén)
                background.createGraphics().drawImage(bg.getScaledInstance(getWidth(), getHeight(),
                        Image.SCALE_DEFAULT), 0, 0, null);
                map.createGraphics().drawImage(lab, 100, 100, null);
                map.createGraphics().drawImage(bunker, getWidth() / 2 - bunker.getWidth() / 2,
                        getHeight() / 2 - bunker.getHeight() / 2, null);
                map.createGraphics().drawImage(viro, getWidth() / 2 - viro.getWidth(), getHeight() / 2, null);
                hud.createGraphics().drawImage(axe, getWidth() / 2 - axe.getWidth() / 2,
                        getHeight() - axe.getHeight() - 10, null);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        void BgPaint()
        {
            try
            {
                BufferedImage bg = ImageIO.read(Main.class.getResource("/resources/bg.png"));
                
                //createGraphics egy Graphics2d objektumot ad vissza, a getGraphics csak sima Graphicsot
                //Mindkettő ugyanúgy működik (konkrétan a getGraphics a createGraphicsot hívja meg BufferedImage esetén)
                background.createGraphics().drawImage(bg.getScaledInstance(getWidth(), getHeight(),
                        Image.SCALE_DEFAULT), 0, 0, null);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }
        
        public void Reset()
        {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            map = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            hud = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            
            //ShowTestCaseMap();
            BgPaint();
            //Repaint invalidate helyett, az valamiért nem működött
            repaint();
        }
        
        /**
         * @param im Amit kirajzolunk
         * @param x  Pozíció X koordinátája
         * @param y  Pozíció Y koordinátája
         */
        public JLabel DrawImage(Image im, int x, int y)
        {
            //A szintek kellenek még a JLabellel is, csak máshogy kéne szervezni, esetleg Listákban tárolni?
            /*switch(level)
            {
                case BACKGROUND:
                    background.getGraphics().drawImage(im, x, y, null);
                    break;
                case MAP:
                    map.getGraphics().drawImage(im, x, y, null);
                    break;
                case HUD:
                    hud.getGraphics().drawImage(im, x, y, null);
                    break;
            }*/
            JLabel label = new JLabel(new ImageIcon(im));
            setLayout(null);
            add(label);
            label.setLocation(x, y);
            label.setSize(im.getWidth(null), im.getHeight(null));
            //label.setComponentPopupMenu(new ContextMenu());
            return label;
        }
        
        
        @Override
        public void paint(Graphics g)
        {
            
            
            //Egymás felé kirajzoljuk a 3 kép tartalmát
            g.drawImage(background, 0, 0, null);
            g.drawImage(map, 0, 0, null);
            g.drawImage(hud, 0, 0, null);
            
            paintChildren(g);
            
            //g.setColor(Color.gray);
            //g.fillRect(0, 0, getWidth(), getHeight());
        }
        
    }
    
    class ContextMenu extends JPopupMenu
    {
        public ContextMenu()
        {
            //Itt lehet több cuccot hozzáadni meg ilyenek
            JMenu menu = new JMenu("Hello there");
            menu.add(new JMenuItem("General Kenobi"));
            add(menu);
        }
    }
    
    class FieldContext extends JPopupMenu
    {
        public FieldContext(Field f)
        {
            JMenuItem interact = new JMenuItem("Interact");
            interact.addActionListener(e ->
            {
                controller.Interact();
            });
            add(interact);
        }
    }
    
    class ViroContext extends JPopupMenu
    {
        public ViroContext(Virologist v)
        {
            JMenuItem stealres = new JMenuItem("Steal Resource");
            JMenu stealeq = new JMenu("Steal Equipment");
            JMenuItem useagent = new JMenuItem("Use Agent");
            JMenuItem useeq = new JMenuItem("Use Equipment");
            
            for(Equipment equipment : v.equipments)
            {
                JMenuItem equipIter = new JMenuItem(equipment.getName());
                equipIter.addActionListener(e ->
                {
                    controller.StealEquipment(v, equipment);
                });
                stealeq.add(equipIter);
            }
            
            stealres.addActionListener(e ->
            {
                controller.StealResource(v);
            });
            
            useagent.addActionListener(e ->
            {
                controller.UseAgentOnViro(v);
            });
            useeq.addActionListener(e ->
            {
                controller.UseEquipment(v);
            });
            
            add(stealres);
            add(stealeq);
            add(useagent);
            add(useeq);
        }
    }
    
    class GeneticContext extends JPopupMenu
    {
        public GeneticContext(GeneticCode code)
        {
            JMenuItem craftVaccine = new JMenuItem("Craft vaccine");
            JMenuItem craftVirus = new JMenuItem("Craft virus");
            
            craftVaccine.addActionListener(e ->
            {
                controller.CraftVaccine(code);
            });
            
            craftVirus.addActionListener(e ->
            {
                controller.CraftVirus(code);
            });
            
            add(craftVaccine);
            add(craftVirus);
        }
    }
    
    class EqContext extends JPopupMenu
    {
        public EqContext(Equipment e)
        {
        
        }
    }
    
    //Már nem kell, de mintának jó, egér kattintás kezelése
    //Ha vizsgálni akarjuk hova kattintottuk, vagy ilyesmi, akkor lehet kell
    class PopClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            if(e.isPopupTrigger())
                doPop(e);
        }
        
        public void mouseReleased(MouseEvent e)
        {
            if(e.isPopupTrigger())
                doPop(e);
        }
        
        private void doPop(MouseEvent e)
        {
            ContextMenu menu = new ContextMenu();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}

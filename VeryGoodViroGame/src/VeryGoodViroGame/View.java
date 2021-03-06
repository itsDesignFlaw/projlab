package VeryGoodViroGame;

import VeryGoodViroGame.Agent.*;
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

import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
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
    //Objektumok, és megfeleltetéseik
    private HashMap<Object, ViewObject> objects = new HashMap<>();
    private final static String ResourcePath = "/resources/";
    public ViewController controller;
    
    static
    {
        
        images.put("ami", "aminoacid.png");
        images.put("axe", "axe.png");
        images.put("bear", "lab.png");
        images.put("vaccine", "bg_vaccine.png");
        images.put("virus", "bg_virus.png");
        images.put("bunker", "bunker.png");
        images.put("coat", "coat.png");
        images.put("dance", "dance_virus.png");
        images.put("effect_agent", "effect_agent.png");
        images.put("effect_equipment", "effect_equipment.png");
        images.put("effect_equipment_broken", "effect_equipment_broken.png");
        images.put("field", "field_simple.png");
        images.put("forget", "forget_virus.png");
        images.put("gloves", "gloves.png");
        images.put("lab", "lab.png");
        images.put("bearlab", "lab.png");
        images.put("nuki", "nucleotid.png");
        images.put("paralyze", "paralyze_virus.png");
        images.put("protect", "protect_virus.png");
        images.put("sack", "sack.png");
        images.put("viro", "viro.png");
        images.put("bearviro", "viro.png");
        images.put("ware", "warehouse.png");
        images.put("forget_vaccine", "forget_vaccine.png");
        images.put("paralyze_vaccine", "paralyze_vaccine.png");
        images.put("dance_vaccine", "dance_vaccine.png");
        images.put("protect_vaccine", "protect_vaccine.png");
        images.put("forget_code", "forget.png");
        images.put("paralyze_code", "paralyze.png");
        images.put("dance_code", "dance.png");
        images.put("protect_code", "protect.png");
        images.put("bear_virus", "bear.png");
        images.put("viro_bear", "viro_bear.png");
    }
    
    //Esetleg HashMap, és a value egy Levels, hogy melyik szinten van?
    //Vagy egy új osztály, pozíció, kép, szint
    private ArrayList<DrawableComponent> drawables = new ArrayList<>();
    private BufferedImage error404;
    
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
        try
        {
            error404 = ImageIO.read(Main.class.getResource("/resources/az.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public int Close()
    {
        JFrame popup = new JFrame();
        //default icon, custom title
        int n = JOptionPane.showConfirmDialog(popup, "Are you sure you ki akarsz lépni?", "Exit",
                JOptionPane.YES_NO_OPTION);
        
        if(n == 0)
        {
            frame.setVisible(false);
            return 0;
        }
        return -1;
        
    }
    
    public void RemoveObject(Object o)
    {
        objects.remove(o);
    }
    
    public void Init()
    {
        frame = new JFrame("Very Good Viro Game --pre alpha test v0.0.0.-1");
        frame.setSize(1024, 768);
        ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/az.png"));
        frame.setIconImage(icon.getImage());
        
        panel = new MapPanel();
        
        //Ez a felső menübár
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        JMenu action = new JMenu("Actions");
        //JMenuItem menuitem = new JMenuItem("száll a széllel");
        
        
        //menu.add(menuitem);
        JMenuItem turn = new JMenuItem("End turn");
        turn.addActionListener(x -> controller.EndTurn());
        action.add(turn);
        
        
        JMenuItem newgame = new JMenuItem("Exit");
        newgame.addActionListener(x ->
        {
            if(Close() == 0)
                controller.getMainmenu().setVisible();
        });
        menu.add(newgame);
        menubar.add(action);
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
        return GetImage(name, "");
    }
    
    public void DrawResource(Resource r)
    {
        try
        {
            Image ami = ImageIO.read(Main.class.getResource(ResourcePath + images.get("ami"))).getScaledInstance(30,
                    30, Image.SCALE_DEFAULT);
            Image nuki = ImageIO.read(Main.class.getResource(ResourcePath + images.get("nuki"))).getScaledInstance(30
                    , 30, Image.SCALE_DEFAULT);
            JLabel amicount = new JLabel(r.getAmi() + ""), nukicount = new JLabel(r.getNuki() + "");
            AddName(panel.getWidth() - 20, 50, amicount);
            AddName(panel.getWidth() - 20, 80, nukicount);
            panel.DrawImage(ami, amicount.getX() - ami.getWidth(null) - 5, amicount.getY() - 2);
            panel.DrawImage(nuki, nukicount.getX() - nuki.getWidth(null) - 5, nukicount.getY() - 2);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private BufferedImage GetImage(Object name, String offset)
    {
        try
        {
            //Ezzel a getResource móddal lehet elvileg jar fileból is beolvasni, azaz akkor is jó útvonalat ad meg
            //Minden fájl ami az src mappán belül van tuti megtalálja
            ViewObject obj = objects.get(name);
            if(obj == null)
            {
                System.out.println("View/BufferedImage.GetImage obj==null! information:\n " + name.toString());
                
                System.out.println("Does entity exist? " + EntityManager.GetObjectName(name) + "\n\t if not it " +
                                   "probably indicates that viro has invalid field set, should check for that!");
                return error404;
            }
            String file = obj.png.replace(offset, "");
            URL u = Main.class.getResource(ResourcePath + file);
            if(u == null)
            {
                System.out.println("View/BufferedImage.GetImage u==null! information:\n " + name.toString());
                return error404;
            }
            BufferedImage im = ImageIO.read(u);
            return im;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return error404;
    }
    
    public void DrawFromString(String name, int x, int y)
    {
        try
        {
            //Ezzel a getResource móddal lehet elvileg jar fileból is beolvasni, azaz akkor is jó útvonalat ad meg
            //Minden fájl ami az src mappán belül van tuti megtalálja
            BufferedImage im = ImageIO.read(Main.class.getResource(ResourcePath + images.get(name)));
            panel.DrawImage(im, x, y);
            //panel.repaint();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Kirajzolja a pályát a Virológusokkal együtt
     *
     * @param current    A jelenlegi mező ,amin áll
     * @param neighbours A Mezőnek a szomszédjai
     */
    public void DrawMap(Field current, java.util.List<Field> neighbours)
    {
        BufferedImage cur = GetImage(current);
        JLabel curr = panel.DrawImage(cur, panel.getWidth() / 2 - cur.getWidth() / 2,
                panel.getHeight() / 2 - cur.getHeight() / 2);
        curr.setComponentPopupMenu(new FieldContext(current));
        curr.addMouseListener(new FieldClick(current, curr));
        int size = neighbours.size();
        for(int i = 0; i < size; i++)
        {
            BufferedImage img = GetImage(neighbours.get(i));
            double a = i * 2 * Math.PI / size - Math.PI / 4;
            int x = (int) (Math.cos(a) * (panel.getWidth() / 2 - 100) + panel.getWidth() / 2);
            int y = (int) (Math.sin(a) * (panel.getHeight() / 2 - 100) + panel.getHeight() / 2);
            //Work in Progress, ha valami jobb ötlet, nyugodtan lehet cserélni
            JLabel FieldLabel = panel.DrawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2);
            FieldLabel.addMouseListener(new FieldClick(neighbours.get(i), FieldLabel));
            if(neighbours.get(i) == controller.GetPrevField())
                FieldLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
            
        }
        List<Virologist> viros = new ArrayList<>(current.GetVirologists());
        DrawViros(viros, 0, 0, false);
        JLabel name = new JLabel(EntityManager.GetObjectName(current));
        AddName(panel.getWidth() / 2, panel.getHeight() / 2 - cur.getHeight() / 2, name);
        panel.setComponentZOrder(name, 0);
        for(int i = 0; i < size; i++)
        {
            BufferedImage img = GetImage(neighbours.get(i));
            double a = i * 2 * Math.PI / size - Math.PI / 4;
            int x = (int) (Math.cos(a) * (panel.getWidth() / 2 - 100) + panel.getWidth() / 2);
            int y = (int) (Math.sin(a) * (panel.getHeight() / 2 - 100) + panel.getHeight() / 2);
            name = new JLabel(EntityManager.GetObjectName(neighbours.get(i)));
            AddName(x, y - img.getHeight() / 2, name);
            
            List<Virologist> virosaround = new ArrayList<>(neighbours.get(i).GetVirologists());
            DrawViros(virosaround, x - (img.getWidth() / 2), y, true);
        }
    }
    
    private void AddName(int x, int y, JLabel name)
    {
        AddName(x, y, name, Color.red);
    }
    
    private void AddName(int x, int y, JLabel name, Color col)
    {
        panel.add(name);
        name.setFont(name.getFont().deriveFont(16.0f));
        name.setForeground(col);
        name.setSize(name.getPreferredSize());
        name.setLocation(x - name.getWidth() / 2, y - name.getHeight() - 1);
    }
    
    public void DrawGeneticCodes(java.util.List<GeneticCode> codes)
    {
        int itemHudSize = codes.size() * 64;
        int leftMostPointOfItems = (panel.getHeight() - itemHudSize) / 2;
        for(int i = 0; i < codes.size(); i++)
        {
            int x = panel.getWidth() - 100;
            int y = leftMostPointOfItems + i * 64;
            //G2D.drawRect(x - 3, y, 64, 64);
            BufferedImage img = GetImage(codes.get(i).getAgent(), "_virus");
            JLabel l = panel.DrawImage(img, x, y);
            l.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            l.setComponentPopupMenu(new GeneticContext(codes.get(i)));
        }
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
    
    String ActiveViro;
    JLabel ActiveViroLabel;
    JLabel ActiveViroLabelName;
    
    public void MarkActiveViro(String name)
    {
        ActiveViro = name;
    }
    
    public void MoveCurViro(Field to)
    {
    }
    
    public void DrawViros(java.util.List<Virologist> viros, int xOffset, int yOffset, boolean useOffset)
    {
        int itemHudSize = viros.size() * 64;
        int leftMostPointOfItems = (panel.getWidth() - itemHudSize) / 2;
        for(int i = 0; i < viros.size(); i++)
        {
            int x, y;
            if(useOffset)
            {
                x = xOffset;
                y = yOffset;
            }
            else
            {
                x = leftMostPointOfItems + i * 64;
                y = panel.getHeight() / 2;
            }
            BufferedImage img = GetImage(viros.get(i));
            JLabel CurViroLabel;
            if(viros.get(i).dead)
            {
                CurViroLabel = panel.DrawImage(rotateImage(img, 90), x, y + img.getHeight() / 2);
            }
            else if(viros.get(i).IsBear())
            {
                try
                {
                    BufferedImage bear = ImageIO.read(Main.class.getResource(ResourcePath + images.get("viro_bear")));
                    CurViroLabel = panel.DrawImage(bear, x, y + bear.getHeight() / 2);
                }
                catch(IOException e)
                {
                    CurViroLabel = null;
                }
            }
            else
            {
                CurViroLabel = panel.DrawImage(img, x, y + img.getHeight() / 2);
            }
            CurViroLabel.setComponentPopupMenu(new ViroContext(viros.get(i)));
            
            String vironame = EntityManager.GetObjectName(viros.get(i));
            JLabel name = new JLabel(vironame);
            AddName(x + img.getWidth() / 2 - 1, y + img.getHeight() + 50, name);
            name.grabFocus();
            if(vironame.equals(ActiveViro))
            {
                ActiveViroLabel = CurViroLabel;
                ActiveViroLabelName = name;
                AddName(x + img.getWidth() / 2 - 1, y + img.getHeight() + 50, name, Color.yellow);
            }
            else
            {
                AddName(x + img.getWidth() / 2 - 1, y + img.getHeight() + 50, name, Color.red);
            }
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
            BufferedImage img = GetImage(items.get(i));
            JLabel l = panel.DrawImage(img, x, y);
            l.setBorder(BorderFactory.createLineBorder(Color.black, 3));
            if(items.get(i) instanceof Agent)
            {
                l.addMouseListener(new AgentClick((Agent) items.get(i)));
                if(items.get(i) == controller.GetSelectedAgent())
                {
                    l.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                }
            }
            else
            {
                l.addMouseListener(new EquipmentClick((Equipment) items.get(i)));
                l.setComponentPopupMenu(new EqContext((Equipment) items.get(i)));
                if(items.get(i) == controller.GetSelectedEq())
                {
                    l.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                }
            }
            
        }
    }
    
    public void AddHUDElement(String name, int count)
    {
    
    }
    
    public void DrawEffects(java.util.List<Agent> effects)
    {
        int itemHudSize = effects.size() * 64;
        int leftMostPointOfItems = (panel.getHeight() - itemHudSize) / 2;
        for(int i = 0; i < effects.size(); i++)
        {
            int x = 100;
            int y = leftMostPointOfItems + i * 64;
            //G2D.drawRect(x - 3, y, 64, 64);
            BufferedImage img = GetImage(effects.get(i));
            JLabel l = panel.DrawImage(img, x, y);
            l.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        }
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
        JLabel fieldIMG;
        boolean ismoving = false;
        
        FieldClick(Field f, JLabel fieldIMG)
        {
            this.f = f;
            this.fieldIMG = fieldIMG;
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getButton() != MouseEvent.BUTTON1)
                return;
            if(ismoving)
                return;
            if(controller.MoveViro(f))
                return;
            ismoving = true;
            
            panel.NiceMoveLabel(ActiveViroLabel, fieldIMG.getX(), fieldIMG.getY() + fieldIMG.getHeight() + 5);
            panel.NiceMoveLabel(ActiveViroLabelName,
                    fieldIMG.getX() + (fieldIMG.getWidth() / 2) - (ActiveViroLabelName.getWidth() / 2),
                    fieldIMG.getY() + fieldIMG.getHeight() + ActiveViroLabel.getHeight() + 5);
            ismoving = false;
            /*if(e.getButton() == MouseEvent.BUTTON1)
                controller.MoveViro(f);*/
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
            if(e.getButton() == MouseEvent.BUTTON1)
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
            if(e.getButton() == MouseEvent.BUTTON1)
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
        
        int lerp(float fraction, Integer start, Integer end)
        {
            return (int) ((start.floatValue() * (1.0 - fraction)) + (end.floatValue() * fraction));
        }
        
        public void NiceMoveLabel(JLabel label, int tox, int toy)
        {
            
            
            Timer Timo = new Timer("nicetimer");
            TimerTask Task = new TimerTask()
            {
                int i;
                
                @Override
                public void run()
                {
                    int cx = label.getLocation().x;
                    int cy = label.getLocation().y;
                    label.setLocation(lerp(0.045f, cx, tox), lerp(0.045f, cy, toy));
                    
                    i++;
                    if(i >= 40)
                    {
                        Timo.cancel();
                    }
                }
            };
            
            
            Timo.scheduleAtFixedRate(Task, 0, 20);
            
            
        }
        
        
        @Override
        public void paint(Graphics g)
        {
            
            
            //Egymás felé kirajzoljuk a 3 kép tartalmát
            g.drawImage(background, 0, 0, null);
            g.drawImage(map, 0, 0, null);
            g.drawImage(hud, 0, 0, null);
            
            paintChildren(g);
            
            
            g.setColor(Color.RED);
            g.fillRect(getWidth() / 2 - 2, getHeight() / 2 - 2, 2, 2);
            
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
                    if(controller.activeViro.GetField() == v.GetField())
                        controller.StealEquipment(v, equipment);
                });
                stealeq.add(equipIter);
            }
            
            stealres.addActionListener(e ->
            {
                if(controller.activeViro.GetField() == v.GetField())
                    controller.StealResource(v);
            });
            
            useagent.addActionListener(e ->
            {
                if(controller.activeViro.GetField() == v.GetField())
                    controller.UseAgentOnViro(v);
            });
            useeq.addActionListener(e ->
            {
                if(controller.activeViro.GetField() == v.GetField())
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
        public EqContext(Equipment eq)
        {
            
            JMenuItem dropEq = new JMenuItem("Drop");
            
            dropEq.addActionListener(e ->
            {
                controller.DropEquipment(eq);
            });
            add(dropEq);
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

package VeryGoodViroGame;

import VeryGoodViroGame.Field.Field;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class View
{
    
    enum Levels
    {
        BACKGROUND, MAP, HUD
    }
    
    //Lefordítás fájlokra
    private static HashMap<String, String> images = new HashMap<>();
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
    
    
    public void Init()
    {
        frame = new JFrame("Very Good Viro Game --pre alpha test v0.0.0.-2");
        frame.setSize(800, 600);
        ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/az.png"));
        frame.setIconImage(icon.getImage());
        
        panel = new MapPanel();
        
        //Ez a felső menübár
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("titkos üzenet");
        JMenuItem menuitem = new JMenuItem("száll a széllel");
        
        
        menu.add(menuitem);
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
    
    private BufferedImage GetImage(String name)
    {
        try
        {
            //Ezzel a getResource móddal lehet elvileg jar fileból is beolvasni, azaz akkor is jó útvonalat ad meg
            //Minden fájl ami az src mappán belül van tuti megtalálja
            BufferedImage im = ImageIO.read(Main.class.getResource(ResourcePath + images.get(name)));
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
            panel.DrawImage(Levels.MAP, im, 200, 100);
            panel.repaint();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void DrawMap(String current, java.util.List<String> neighbours)
    {
        BufferedImage cur = GetImage(current);
        panel.DrawImage(Levels.MAP, cur, panel.getWidth() / 2 - cur.getWidth() / 2,
                panel.getHeight() / 2 - cur.getHeight() / 2);
        int size = neighbours.size();
        for(int i = 0; i < size; i++)
        {
            BufferedImage img = GetImage(neighbours.get(i));
            double a = i * 2 * Math.PI / size - Math.PI / 4;
            int x = (int) (Math.cos(a) * (panel.getWidth() / 2 - 60) + panel.getWidth() / 2);
            int y = (int) (Math.sin(a) * (panel.getHeight() / 2 - 60) + panel.getHeight() / 2);
            //Work in Progress, ha valami jobb ötlet, nyugodtan lehet cserélni
            panel.DrawImage(Levels.MAP, img, x - img.getWidth() / 2, y - img.getHeight() / 2);
        }
    }
    
    public void DrawGeneticCodes(java.util.List<String> codes)
    {
    
    }
    
    public void DrawViros(java.util.List<String> viros)
    {
    
    }
    
    public void DrawItems(java.util.List<String> hud)
    {
    
    }
    
    public void AddHUDElement(String name, int count)
    {
    
    }
    
    public void DrawEffects(java.util.List<String> effects)
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
         * @param level Melyik képre akarunk rajzolni
         * @param im    Amit kirajzolunk
         * @param x     Pozíció X koordinátája
         * @param y     Pozíció Y koordinátája
         */
        public void DrawImage(Levels level, Image im, int x, int y)
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
            label.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println("Viro");
                }
            });
            setLayout(null);
            add(label);
            label.setLocation(x, y);
            label.setSize(im.getWidth(null), im.getHeight(null));
            label.setComponentPopupMenu(new ContextMenu());
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
        
        public void Resize()
        {
        
        }
    }
    
    class ContextMenu extends JPopupMenu
    {
        public ContextMenu()
        {
            //Itt lehet több cuccot hozzáadni meg ilyenek
            add(new JMenuItem("Hello there"));
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

package VeryGoodViroGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class View
{
    
    enum Levels
    {
        BACKGROUND, MAP, HUD
    }
    
    private static HashMap<String, String> images = new HashMap<>();
    private final static String ResourcePath = "/resources/";
    
    static
    {
        images.put("viro", "viro.png");
    }
    
    
    JFrame frame;
    //Talán nem kell, mert beágyazott osztály eléri a mgefelelő értékeket
    //Bár mondjuk konkrét hívásokra jó lehet
    MapPanel panel;
    //Sajat Componens
    
    
    public void Init()
    {
        frame = new JFrame("Very Good Viro Game --pre alpha test v0.0.0.-3");
        frame.setSize(800, 600);
        ImageIcon icon = new ImageIcon(Main.class.getResource("/resources/az.png"));
        frame.setIconImage(icon.getImage());
        
        panel = new MapPanel();
        
        //Ez a felső menübár
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("titkos üzenet");
        JMenuItem menuitem = new JMenuItem("kopasz kugli Goldi");
        
        menu.add(menuitem);
        menubar.add(menu);
        
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setJMenuBar(menubar);
        
        //Jobb klikk menü, az osztélyban lehet módosítani a kinézetén
        panel.setComponentPopupMenu(new ContextMenu());
        //frame.addMouseListener(new PopClickListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //ne kelljen ezzel foglalkozni
        frame.setVisible(true);
        panel.Reset();
        
        
        DrawFromString("viro");
        
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
    
    public void DrawGeneticCodes(String[] codes)
    {
    
    }
    
    public void AddHUDElement(String name, int count)
    {
    
    }
    
    //Ez nem kell, mert beépített popup van
    public void DrawContextMenu(int x, int y)
    {
    
    }
    
    class MapPanel extends JPanel
    {
        //Igazából lehet felesleges, de legalább egyértlemű, hátteret nem kell újrarajzolni
        //TODO: Akár a virológusok szétszedése, hogy mindig a pálya felett legyen?
        BufferedImage background, map, hud;
        
        public MapPanel()
        {
            //Reset();
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
        
        public void Reset()
        {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            map = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            hud = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            ShowTestCaseMap();
            //Repaint invalidate helyett, az valamiért nem működött
            repaint();
        }
    
        /**
         *
         * @param level Melyik képre akarunk rajzolni
         * @param im Amit kirajzolunk
         * @param x Pozíció X koordinátája
         * @param y Pozíció Y koordinátája
         */
        public void DrawImage(Levels level, Image im, int x, int y)
        {
            switch(level)
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
            }
        }
        
        
        @Override
        public void paint(Graphics g2)
        {
            super.paint(g2);
            Graphics2D g = (Graphics2D) g2;
            
            
            //Egymás felé kirajzoljuk a 3 kép tartalmát
            g.drawImage(background, 0, 0, null);
            g.drawImage(map, 0, 0, null);
            g.drawImage(hud, 0, 0, null);
            
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

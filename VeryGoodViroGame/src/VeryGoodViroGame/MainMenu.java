package VeryGoodViroGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class MainMenu
{
    
    JFrame frame;
    JButton start;
    JTextField textField;
    JLabel label;
    JPanel panel;
    
    private int NumberOfViros = 0;
    private  ViewController controller = null;

    public JFrame createFrame(){
        frame = new JFrame("Very Good Viro Game --pre alpha test v0.0.0.-2");
        frame.setSize(800, 600);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(800, 600);
        start = new JButton("Start");
        textField = new JTextField();

        textField.setSize(100, 10);
        label = new JLabel("Number of Virológusok:");

        start.setBounds(400 - 50, 180, 100, 30);
        label.setBounds(400 - 100, 220, 200, 40);
        textField.setBounds(400 - 20, 270, 50, 30);
        label.setHorizontalAlignment(JLabel.CENTER);
        textField.setHorizontalAlignment(JTextField.CENTER);


        //TODO: BEKRA`UND
         /*
        JLabel picLabel = null;
        try
        {
            picLabel =
                    new JLabel(new ImageIcon(ImageIO.read(Main.class.getResource("/resources/bg.png")).getScaledInstance(800, 600, Image.SCALE_DEFAULT)));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        picLabel.setLocation(0, 0);
        picLabel.setSize(400, 400);

        panel.add(picLabel);
          */


        panel.add(start);
        panel.add(label);
        panel.add(textField);

        start.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                start();
                frame.setVisible(false);
            }
        });

        textField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(isNumeric(textField.getText()))
                {
                    NumberOfViros = parseInt(textField.getText());
                }
                textField.setText("");
            }
        });


        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        //Jobb klikk menü, az osztélyban lehet módosítani a kinézetén
        //panel.setComponentPopupMenu(new ContextMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //ne kelljen ezzel foglalkozni
        frame.setVisible(true);

        return frame;
    }

    
    public MainMenu()
    {
        JFrame frame = createFrame();
        
        //DrawFromString("viro");
        
    }
    
    public static boolean isNumeric(String strNum)
    {
        if(strNum == null)
        {
            return false;
        }
        try
        {
            int i = Integer.parseInt(strNum);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    
    public void start()
    {
        if(NumberOfViros <= 0)
        {
            NumberOfViros = 2;
        }
        else if(NumberOfViros > 16)
        {
            NumberOfViros = 16;
        }

        controller = new ViewController(NumberOfViros, this);


    }

    public void setVisible()
    {

        //controller.view.frame.setVisible(false);
        if(controller.getView().Close()==0){
            frame.setVisible(true);
        }



    }
}
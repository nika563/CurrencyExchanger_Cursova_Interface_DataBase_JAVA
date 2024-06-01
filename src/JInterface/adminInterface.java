package src.JInterface;

//j_frame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
//sql
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class adminInterface extends JFrame{
    public adminInterface() {
        super("ADMIN");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when exit
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Разворачиваем окно на весь экран
        super.setBounds(500, 200, 600, 500); //size icon
        Container con = super.getContentPane(); //create blocks(button, input ...)
        con.setBackground(Color.decode("#5FB67D"));

        JPanel panel = new JPanel(null);
        JPanel menu = new JPanel();
        panel.setBackground(Color.decode("#5FB67D"));
        menu.setBackground(Color.decode("#228845"));
        menu.setPreferredSize(new Dimension(20, 20)); // Задаем предпочтительный размер
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        JLabel header = new JLabel("Menu admin".toUpperCase());
        Map<TextAttribute, Object> attributes = new HashMap<>();
        Font currentFont = header.getFont(); // Получаем текущий шрифт метки
        Font newFont = currentFont.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        attributes.put(TextAttribute.FAMILY, "Arial");
        header.setFont(newFont.deriveFont(20f)); // Устанавливаем новый шрифт и размер
        header.setForeground(Color.black);
        header.setBounds(700, 20,150,40);



        panel.add(header);
        con.add(panel);
        con.add(menu);
        //send.addActionListener(new eventSerchDataBase()); //add event to button
    }
    //class eventSerchDataBase implements ActionListener{}
}

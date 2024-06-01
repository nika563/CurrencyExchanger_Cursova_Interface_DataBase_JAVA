package src.JInterface;

//j_frame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
//sql
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class adminInterface extends JFrame implements ActionListener{
    CardLayout card;
    JButton btn1, btn2, btn3;
    JPanel card1, card2, card3, cards;

    public adminInterface() {
        // Создаем кнопки для переключения карт
        btn1 = new JButton("Переключить на Карту 1");
        btn2 = new JButton("Переключить на Карту 2");
        btn3 = new JButton("Переключить на Карту 3");

        // Создаем слушателей для кнопок
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        // Создаем панели, которые будут "картами"
        card1 = new JPanel();
        card1.add(new JLabel("Карта 1"));
        card1.setBackground(Color.GREEN);

        card2 = new JPanel();
        card2.add(new JLabel("Карта 2"));
        card2.setBackground(Color.RED);

        card3 = new JPanel();
        card3.add(new JLabel("Карта 3"));
        card3.setBackground(Color.BLUE);
        // Создаем CardLayout и добавляем к нему карты
        cards = new JPanel(new CardLayout());
        cards.add(card1, "Карта 1");
        cards.add(card2, "Карта 2");
        cards.add(card3, "Карта 3");

        // Добавляем кнопки и карты в фрейм
        add(btn1, BorderLayout.NORTH);
        add(btn2, BorderLayout.SOUTH);
        add(btn3, BorderLayout.WEST);
        add(cards, BorderLayout.CENTER);

        // Настройка фрейма
        setSize(300, 300);
        //setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        card = (CardLayout)(cards.getLayout());
        if (e.getSource() == btn1) {
            card.show(cards, "Карта 1");
        } else if (e.getSource() == btn2) {
            card.show(cards, "Карта 2");
        } else if (e.getSource() == btn3) {
            card.show(cards, "Карта 3");
        }
    }



    /*public adminInterface() {
        super("ADMIN");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when exit
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Разворачиваем окно на весь экран
        super.setBounds(500, 200, 600, 500); //size icon
        Container con = super.getContentPane(); //create blocks(button, input ...)
        con.setBackground(Color.decode("#5FB67D"));

        JPanel panel = new JPanel(null);
        JPanel menu = new JPanel(null);
        panel.setBackground(Color.decode("#5FB67D"));
        menu.setBackground(Color.decode("#228845"));
        menu.setBounds(0, 0, 150, Toolkit.getDefaultToolkit().getScreenSize().height); // Устанавливаем размер и позицию меню
        Map<TextAttribute, Object> attributes = new HashMap<>();
        CompoundBorder compound = new CompoundBorder(
                new MatteBorder(0, 0, 2, 0, Color.BLACK), // Нижняя граница
                new EmptyBorder(0, 16, 15, 0) // Отступы
        );

        //Menu admin
        JLabel header = new JLabel("Menu admin".toUpperCase());
        Font currentFontHeader = header.getFont(); // Получаем текущий шрифт метки
        Font newFont = currentFontHeader.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        attributes.put(TextAttribute.FAMILY, "Arial");
        header.setFont(newFont.deriveFont(20f)); // Устанавливаем новый шрифт и размер
        header.setForeground(Color.black);
        header.setBounds(750, 20,150,20);
        //addData
        JLabel addData = new JLabel("Add data".toUpperCase());
        addData.setBorder(compound);
        Font currentFontAddData = header.getFont(); // Получаем текущий шрифт метки
        Font newFontAddData = currentFontAddData.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        addData.setFont(newFontAddData.deriveFont(17f)); // Устанавливаем новый шрифт и размер
        addData.setForeground(Color.black);
        addData.setBounds(0, 14,150,40);
        //changeData
        JLabel changeData = new JLabel("Change data".toUpperCase());
        changeData.setBorder(compound);
        Font currentFontChangeData = header.getFont(); // Получаем текущий шрифт метки
        Font newFontChangeData = currentFontChangeData.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        changeData.setFont(newFontChangeData.deriveFont(17f)); // Устанавливаем новый шрифт и размер
        changeData.setForeground(Color.black);
        changeData.setBounds(0, 70,150,40);
        //searchData
        JLabel searchData = new JLabel("Search data".toUpperCase());
        searchData.setBorder(compound);
        Font currentFontSearchData = header.getFont(); // Получаем текущий шрифт метки
        Font newFontSearchData = currentFontSearchData.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        searchData.setFont(newFontSearchData.deriveFont(17f)); // Устанавливаем новый шрифт и размер
        searchData.setForeground(Color.black);
        searchData.setBounds(0, 125,150,40);
        //deleteData
        JLabel deleteData = new JLabel("Delete data".toUpperCase());
        deleteData.setBorder(compound);
        Font currentFontDeleteData = header.getFont(); // Получаем текущий шрифт метки
        Font newFontDeleteData = currentFontDeleteData.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        deleteData.setFont(newFontDeleteData.deriveFont(17f)); // Устанавливаем новый шрифт и размер
        deleteData.setForeground(Color.black);
        deleteData.setBounds(0, 180,150,40);


        panel.add(addData);
        panel.add(changeData);
        panel.add(searchData);
        panel.add(deleteData);
        panel.add(menu);
        panel.add(header);
        con.add(panel);
        //event
        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Обработка клика мыши для addData
            }
        });
        changeData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Обработка клика мыши для changeData
            }
        });
        searchData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Обработка клика мыши для searchData
            }
        });
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Обработка клика мыши для deleteData
            }
        });
    }*/
}

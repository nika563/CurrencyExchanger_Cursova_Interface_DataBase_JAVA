package src.JInterface;

//j_frame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
//sql
import java.sql.*;

public class adminInterface extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel, panel;
    private static JComboBox<String> comboBox, selectRoleComboBox, selectKeyCommissionComboBox;
    private static JTextField name_employee, telephone_employee, gmail_employee, login_employee, password_employee,
            name_currency, description_currency, availability_currency,
            name_service, description_service, sell_price_service, purchased_price_service;
    private static JLabel textError;
    private static JButton send;
    singUpInterface singUpInterface = new singUpInterface();
    Connection conn = singUpInterface.getConnection();
    String addRole, addRoleRightsCashier, addRoleRightsAdmin;

    public adminInterface(){
        super("ADMIN");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when exit
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Разворачиваем окно на весь экран
        super.setBounds(500, 200, 600, 500); //size icon
        Container con = super.getContentPane(); //create blocks(button, input ...)
        con.setBackground(Color.decode("#5FB67D"));

        // Инициализация CardLayout и JPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Создание и добавление панелей с кнопками
        JPanel panelWithAddData = createPanelAddData("Add_data");
        JPanel panelWithChangeData = createPanelChangeData("Change_data");
        JPanel panelWithSearchData = createPanelSearchData("Search_data");
        JPanel panelWithDeleteData = createPanelDeleteData("Delete_data");

        // Добавление панелей в CardLayout
        cardPanel.add(panelWithAddData, "Add_data");
        cardPanel.add(panelWithChangeData, "Change_data");
        cardPanel.add(panelWithSearchData, "Search_data");
        cardPanel.add(panelWithDeleteData, "Delete_data");

        add(cardPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {}
    private JPanel createPanelAddData(String cardName)  {
        panel = new JPanel(null);
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
        addData.setForeground(Color.decode("#B1FDC6"));
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
        //comboBox
        String[] menuItems = {"", "Працівник", "Валюти", "Послуга", "Курс обміну", "Тариф", "Рахунок", "Зміна", "Сума в касі", "Присутність", "Валюта та курс обміну", "Валюти та послуги"};
        comboBox = new JComboBox<>(menuItems);
        comboBox.setBounds(180, 80,230,35);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setForeground(Color.black);
        comboBox.setBackground(Color.decode("#24743F"));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JComponent c = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setOpaque(true);
                c.setFont(new Font("Arial", Font.PLAIN, 18));
                c.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                if (isSelected) {
                    c.setBackground(Color.decode("#1F9B49")); // Цвет фона
                    c.setForeground(Color.black); // Цвет текста
                }
                else{
                    c.setBackground(Color.decode("#24743F"));
                    c.setForeground(Color.black);
                }
                if (index == -1 && "".equals(value)) {
                    setText("Select item...");
                }
                return this;
            }
        });
        comboBox.setSelectedIndex(0);
        //event_comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedText = (String) comboBox.getSelectedItem();
                if ("Працівник".equals(selectedText)) {
                    deleteComponents();
                    //name_employee
                    name_employee = new JTextField("Enter name employee");
                    name_employee.setBounds(420, 80,190,35);
                    name_employee.setFont(new Font("Arial", Font.PLAIN, 18));
                    name_employee.setForeground(Color.black);
                    name_employee.setBackground(Color.decode("#24743F"));
                    name_employee.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    name_employee.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (name_employee.getText().equals("Enter name employee")) {
                                name_employee.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (name_employee.getText().isEmpty()) {
                                name_employee.setText("Enter name employee");
                            }
                        }
                    });
                    //telephone_employee
                    telephone_employee = new JTextField("Telephone (+777-77-77)");
                    telephone_employee.setBounds(620, 80,220,35);
                    telephone_employee.setFont(new Font("Arial", Font.PLAIN, 18));
                    telephone_employee.setForeground(Color.black);
                    telephone_employee.setBackground(Color.decode("#24743F"));
                    telephone_employee.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                    telephone_employee.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (telephone_employee.getText().equals("Telephone (+777-77-77)")) {
                                telephone_employee.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (telephone_employee.getText().isEmpty()) {
                                telephone_employee.setText("Telephone (+777-77-77)");
                            }
                        }
                    });
                    //gmail_employee
                    gmail_employee = new JTextField("Gmail @gmail.com");
                    gmail_employee.setBounds(850, 80,170,35);
                    gmail_employee.setFont(new Font("Arial", Font.PLAIN, 18));
                    gmail_employee.setForeground(Color.black);
                    gmail_employee.setBackground(Color.decode("#24743F"));
                    gmail_employee.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                    gmail_employee.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (gmail_employee.getText().equals("Gmail @gmail.com")) {
                                gmail_employee.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (gmail_employee.getText().isEmpty()) {
                                gmail_employee.setText("Gmail @gmail.com");
                            }
                        }
                    });
                    //login_employee
                    login_employee = new JTextField("Enter login");
                    login_employee.setBounds(1030, 80,105,35);
                    login_employee.setFont(new Font("Arial", Font.PLAIN, 18));
                    login_employee.setForeground(Color.black);
                    login_employee.setBackground(Color.decode("#24743F"));
                    login_employee.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                    login_employee.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (login_employee.getText().equals("Enter login")) {
                                login_employee.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (login_employee.getText().isEmpty()) {
                                login_employee.setText("Enter login");
                            }
                        }
                    });
                    //password_employee
                    password_employee = new JTextField("Enter password");
                    password_employee.setBounds(1145, 80,150,35);
                    password_employee.setFont(new Font("Arial", Font.PLAIN, 18));
                    password_employee.setForeground(Color.black);
                    password_employee.setBackground(Color.decode("#24743F"));
                    password_employee.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                    password_employee.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (password_employee.getText().equals("Enter password")) {
                                password_employee.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (password_employee.getText().isEmpty()) {
                                password_employee.setText("Enter password");
                            }
                        }
                    });
                    //selectRole
                    String[] selectRole = {"", "cashier", "admin"};
                    selectRoleComboBox = new JComboBox<>(selectRole);
                    selectRoleComboBox.setBounds(1305, 80,135,35);
                    selectRoleComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                    selectRoleComboBox.setForeground(Color.black);
                    selectRoleComboBox.setBackground(Color.decode("#24743F"));
                    selectRoleComboBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                            JComponent c = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            c.setOpaque(true);
                            c.setFont(new Font("Arial", Font.PLAIN, 18));
                            c.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                            if (isSelected) {
                                c.setBackground(Color.decode("#1F9B49")); // Цвет фона
                                c.setForeground(Color.black); // Цвет текста
                            }
                            else{
                                c.setBackground(Color.decode("#24743F"));
                                c.setForeground(Color.black);
                            }
                            if (index == -1 && "".equals(value)) {
                                setText("Select role");
                            }
                            return this;
                        }
                    });
                    selectRoleComboBox.setSelectedIndex(0);
                    //button
                    send = new JButton("Send");
                    send.setFont(new Font("Arial", Font.PLAIN, 18));
                    send.setForeground(Color.decode("#CADACF")); //color text
                    send.setBackground(Color.decode("#284F00"));  //color background
                    send.setBorder(BorderFactory.createLineBorder(Color.decode("#284F00"), 2));
                    send.setFocusPainted(false);
                    send.setBounds(180, 130,230,35);
                    send.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            send.setForeground(Color.decode("#CADACF")); // меняем цвет текста на черный при нажатии
                            send.setBackground(Color.decode("#1F9B49"));  //color background
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 190,230,20);

                    panel.add(textError);
                    panel.add(send);
                    panel.add(name_employee);
                    panel.add(telephone_employee);
                    panel.add(gmail_employee);
                    panel.add(login_employee);
                    panel.add(password_employee);
                    panel.add(selectRoleComboBox);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Валюти".equals(selectedText)) {
                    deleteComponents();
                    //name_currency
                    name_currency  = new JTextField("Enter name currency");
                    name_currency.setBounds(420, 80,190,35);
                    name_currency.setFont(new Font("Arial", Font.PLAIN, 18));
                    name_currency.setForeground(Color.black);
                    name_currency.setBackground(Color.decode("#24743F"));
                    name_currency.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    name_currency.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (name_currency.getText().equals("Enter name currency")) {
                                name_currency.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (name_currency.getText().isEmpty()) {
                                name_currency.setText("Enter name currency");
                            }
                        }
                    });
                    //description_currency
                    description_currency  = new JTextField("Enter description");
                    description_currency.setBounds(620, 80,190,35);
                    description_currency.setFont(new Font("Arial", Font.PLAIN, 18));
                    description_currency.setForeground(Color.black);
                    description_currency.setBackground(Color.decode("#24743F"));
                    description_currency.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    description_currency.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (description_currency.getText().equals("Enter description")) {
                                description_currency.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (description_currency.getText().isEmpty()) {
                                description_currency.setText("Enter description");
                            }
                        }
                    });
                    //availability_currency
                    availability_currency  = new JTextField("Enter availability");
                    availability_currency.setBounds(820, 80,180,35);
                    availability_currency.setFont(new Font("Arial", Font.PLAIN, 18));
                    availability_currency.setForeground(Color.black);
                    availability_currency.setBackground(Color.decode("#24743F"));
                    availability_currency.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    availability_currency.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (availability_currency.getText().equals("Enter availability")) {
                                availability_currency.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (availability_currency.getText().isEmpty()) {
                                availability_currency.setText("Enter availability");
                            }
                        }
                    });
                    //button
                    send = new JButton("Send");
                    send.setFont(new Font("Arial", Font.PLAIN, 18));
                    send.setForeground(Color.decode("#CADACF")); //color text
                    send.setBackground(Color.decode("#284F00"));  //color background
                    send.setBorder(BorderFactory.createLineBorder(Color.decode("#284F00"), 2));
                    send.setFocusPainted(false);
                    send.setBounds(180, 130,230,35);
                    send.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            send.setForeground(Color.decode("#CADACF")); // меняем цвет текста на черный при нажатии
                            send.setBackground(Color.decode("#1F9B49"));  //color background
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 190,230,20);

                    panel.add(textError);
                    panel.add(name_currency);
                    panel.add(description_currency);
                    panel.add(availability_currency);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Послуга".equals(selectedText)) {
                    deleteComponents();
                    //name_service
                    name_service  = new JTextField("Enter name service");
                    name_service.setBounds(420, 80,190,35);
                    name_service.setFont(new Font("Arial", Font.PLAIN, 18));
                    name_service.setForeground(Color.black);
                    name_service.setBackground(Color.decode("#24743F"));
                    name_service.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    name_service.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (name_service.getText().equals("Enter name service")) {
                                name_service.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (name_service.getText().isEmpty()) {
                                name_service.setText("Enter name service");
                            }
                        }
                    });
                    //description_service
                    description_service  = new JTextField("Enter description");
                    description_service.setBounds(620, 80,190,35);
                    description_service.setFont(new Font("Arial", Font.PLAIN, 18));
                    description_service.setForeground(Color.black);
                    description_service.setBackground(Color.decode("#24743F"));
                    description_service.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    description_service.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (description_service.getText().equals("Enter description")) {
                                description_service.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (description_service.getText().isEmpty()) {
                                description_service.setText("Enter description");
                            }
                        }
                    });
                    //sell_price_service
                    sell_price_service  = new JTextField("Enter sell price");
                    sell_price_service.setBounds(820, 80,180,35);
                    sell_price_service.setFont(new Font("Arial", Font.PLAIN, 18));
                    sell_price_service.setForeground(Color.black);
                    sell_price_service.setBackground(Color.decode("#24743F"));
                    sell_price_service.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    sell_price_service.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (sell_price_service.getText().equals("Enter sell price")) {
                                sell_price_service.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (sell_price_service.getText().isEmpty()) {
                                sell_price_service.setText("Enter sell price");
                            }
                        }
                    });
                    //purchased_price_service
                    purchased_price_service  = new JTextField("Enter purchased price");
                    purchased_price_service.setBounds(1010, 80,200,35);
                    purchased_price_service.setFont(new Font("Arial", Font.PLAIN, 18));
                    purchased_price_service.setForeground(Color.black);
                    purchased_price_service.setBackground(Color.decode("#24743F"));
                    purchased_price_service.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    purchased_price_service.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (purchased_price_service.getText().equals("Enter purchased price")) {
                                purchased_price_service.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (purchased_price_service.getText().isEmpty()) {
                                purchased_price_service.setText("Enter purchased price");
                            }
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);

                    //selectIdCommission
                    String[] selectKeyCommissionArray = new String[0];
                    try {
                        selectKeyCommissionArray = addDataInList().toArray(new String[0]);
                        if(selectKeyCommissionArray[0] == ""){
                            textError.setText("Please write data in commission");
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error99: " + ex);
                        if(ex instanceof NullPointerException){
                            textError.setText("Please write data in commission");
                        }
                    }
                    selectKeyCommissionComboBox = new JComboBox<>(selectKeyCommissionArray);
                    selectKeyCommissionComboBox.setBounds(1210, 80,230,35);
                    selectKeyCommissionComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                    selectKeyCommissionComboBox.setForeground(Color.black);
                    selectKeyCommissionComboBox.setBackground(Color.decode("#24743F"));
                    selectKeyCommissionComboBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                            JComponent c = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            c.setOpaque(true);
                            c.setFont(new Font("Arial", Font.PLAIN, 18));
                            c.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
                            if (isSelected) {
                                c.setBackground(Color.decode("#1F9B49")); // Цвет фона
                                c.setForeground(Color.black); // Цвет текста
                            }
                            else{
                                c.setBackground(Color.decode("#24743F"));
                                c.setForeground(Color.black);
                            }
                            if (index == -1 && "".equals(value)) {
                                setText("Select key commission");
                            }
                            return this;
                        }
                    });
                    selectKeyCommissionComboBox.setSelectedIndex(0);
                    //send
                    send = new JButton("Send");
                    send.setFont(new Font("Arial", Font.PLAIN, 18));
                    send.setForeground(Color.decode("#CADACF")); //color text
                    send.setBackground(Color.decode("#284F00"));  //color background
                    send.setBorder(BorderFactory.createLineBorder(Color.decode("#284F00"), 2));
                    send.setFocusPainted(false);
                    send.setBounds(180, 130,230,35);
                    send.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            send.setForeground(Color.decode("#CADACF")); // меняем цвет текста на черный при нажатии
                            send.setBackground(Color.decode("#1F9B49"));  //color background
                        }
                    });

                    panel.add(name_service);
                    panel.add(description_service);

                    panel.add(sell_price_service);
                    panel.add(purchased_price_service);

                    panel.add(selectKeyCommissionComboBox);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Курс обміну".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Курс обміну");
                }
                else if ("Тариф".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Тариф");
                }
                else if ("Рахунок".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Рахунок");
                }
                else if ("Зміна".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Зміна");
                }
                else if ("Сума в касі".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Сума в касі");
                }
                else if ("Присутність".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Присутність");
                }
                else if ("Валюта та курс обміну".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Валюта та курс обміну");
                }
                else if ("Валюти та послуги".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Валюти та послуги");
                }
                panel.revalidate(); // Mетод используется для того, чтобы сообщить менеджеру компоновки о том, что произошли изменения
                panel.repaint(); // Mетод запрашивает перерисовку компонента и его дочерних элементов
            }
        });

        //event
        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {cardLayout.show(cardPanel, "Add_data");}
        });
        changeData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {cardLayout.show(cardPanel, "Change_data");}
        });
        searchData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {cardLayout.show(cardPanel, "Search_data");}
        });
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {cardLayout.show(cardPanel, "Delete_data");}
        });

        panel.add(comboBox);
        panel.add(addData);
        panel.add(changeData);
        panel.add(searchData);
        panel.add(deleteData);
        panel.add(menu);
        panel.add(header);
        return panel;
    }
    class eventSendDataBase implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String selectedItem = (String) comboBox.getSelectedItem();
                if ("Працівник".equals(selectedItem)) {
                    System.out.println("Працівник eventSendDataBase");
                    String name = name_employee.getText().trim();
                    String telephone = telephone_employee.getText().trim();
                    String gmail = gmail_employee.getText().trim();
                    String login = login_employee.getText().trim();
                    String password = password_employee.getText().trim();
                    String role = (String) selectRoleComboBox.getSelectedItem();
                    addDataInDataBase(selectedItem, name, telephone, gmail, login, password, role);
                }
                else if("Валюти".equals(selectedItem)){
                    System.out.println("Валюти eventSendDataBase");

                    String nameCurrency = name_currency.getText().trim();
                    String desCurrency = description_currency.getText().trim();
                    String strAvailCurrency = availability_currency.getText().trim();

                    //System.out.println("nameCurrency: " + nameCurrency); //delete
                    //System.out.println("desCurrency: " + desCurrency); //delete
                    //System.out.println("availCurrency: " + strAvailCurrency); //delete

                    if(nameCurrency.equals("Enter name currency") || desCurrency.equals("Enter description")){
                        textError.setText("Please write data");
                        System.out.println("nameCurrency {111}"); //delete
                    }
                    else if(strAvailCurrency.equals("Enter availability")){
                        textError.setText("Please write number");
                        System.out.println("nameCurrency {222}"); //delete
                    }
                    else {
                        System.out.println("nameCurrency {333}"); //delete
                        int availCurrency = Integer.parseInt(strAvailCurrency);
                        addDataInDataBase(selectedItem, nameCurrency, desCurrency, availCurrency);
                    }
                }
                else if("Послуга".equals(selectedItem)){
                    System.out.println("Послуга eventSendDataBase");

                    String nameService = name_service.getText().trim();
                    String desService = description_service.getText().trim();
                    String strSellPriceService = sell_price_service.getText().trim();
                    String strPurchasedPriceService = purchased_price_service.getText().trim();
                    String keyCommission = (String) selectKeyCommissionComboBox.getSelectedItem();

                    if(nameService.equals("Enter name service") || desService.equals("Enter description")){
                        textError.setText("");
                        textError.setText("Please write data");
                        System.out.println("nameService {111}"); //delete
                    }
                    else if(strSellPriceService.equals("Enter sell price") || strPurchasedPriceService.equals("Enter purchased price")){
                        textError.setText("");
                        textError.setText("Please write number");
                        System.out.println("nameService {222}"); //delete
                    }
                    else if(keyCommission.equals("Select key commission")){
                        textError.setText("");
                        textError.setText("Please select key commission");
                        System.out.println("nameService {333}"); //delete
                    }
                    else {
                        System.out.println("nameService {444}"); //delete
                        int sellPriceService = Integer.parseInt(strSellPriceService);
                        int purchasedPriceService = Integer.parseInt(strPurchasedPriceService);
                        addDataInDataBase(selectedItem, nameService, desService, sellPriceService,purchasedPriceService, keyCommission);
                    }
                }
                else if("Курс обміну".equals(selectedItem)){}
                else if("Тариф".equals(selectedItem)){}
                else if("Рахунок".equals(selectedItem)){}
                else if("Зміна".equals(selectedItem)){}
                else if("Сума в касі".equals(selectedItem)){}
                else if("Присутність".equals(selectedItem)){}
                else if("Валюта та курс обміну".equals(selectedItem)){}
                else if("Валюти та послуги".equals(selectedItem)){}
            }
            catch (Exception ex){
                System.out.println("Error909: " + ex);
            }
        }
    }
    private void deleteComponents() {
        System.out.println("deleteComponents"); //delete
        //System.out.println("send: " + send); //delete
        //System.out.println("selectRoleComboBox: " + selectRoleComboBox); //delete

        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.equals(send)) {
                    panel.remove(button);
                }
            }
            else if (component instanceof JComboBox) {
                JComboBox<?> box = (JComboBox<?>) component;
                if (box.equals(selectRoleComboBox)) {
                    panel.remove(box);
                }
                //service
                else if (box.equals(selectKeyCommissionComboBox)) {
                    panel.remove(box);
                }
            }
            else if (component instanceof JLabel) {
                JLabel jLabel = (JLabel) component;
                if (jLabel.equals(textError)) {
                    panel.remove(jLabel);
                }
            }
            else if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (textField.equals(name_employee)) {
                    panel.remove(textField);
                }
                else if (textField.equals(telephone_employee)) {
                    panel.remove(textField);
                }
                else if (textField.equals(gmail_employee)) {
                    panel.remove(textField);
                }
                else if (textField.equals(login_employee)) {
                    panel.remove(textField);
                }
                else if (textField.equals(password_employee)) {
                    panel.remove(textField);
                }
                //currency
                else if (textField.equals(name_currency)) {
                    panel.remove(textField);
                }
                else if (textField.equals(availability_currency)) {
                    panel.remove(textField);
                }
                else if (textField.equals(description_currency)) {
                    panel.remove(textField);
                }
                //service
                else if (textField.equals(name_service)) {
                    panel.remove(textField);
                }
                else if (textField.equals(description_service)) {
                    panel.remove(textField);
                }
                else if (textField.equals(sell_price_service)) {
                    panel.remove(textField);
                }
                else if (textField.equals(purchased_price_service)) {
                    panel.remove(textField);
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    }
    private ArrayList<String> addDataInList() throws SQLException{
        ArrayList<String> selectIdCommission = new ArrayList<>();
        try {
            String getIdCommission = "SELECT id_commission FROM commission;";
            Statement statement = conn.createStatement();
            System.out.println("resulIdCommission: " + statement.executeQuery(getIdCommission));
            ResultSet resulIdCommission = statement.executeQuery(getIdCommission);
            while (resulIdCommission.next()) {
                selectIdCommission.add(resulIdCommission.getString("id_commission"));
            }
        }
        catch(Exception ex){
            System.out.println("Error addDataInList: " + ex);
            if(ex instanceof NullPointerException){
                selectIdCommission.add("");
            }
        }
        return selectIdCommission;
    }
    public void addDataInDataBase(Object... dataForDataBase){
        try {
            Object stringPoint = dataForDataBase[0];
            String addCurrencyDataBase, sqlAddData;
            if ("Працівник".equals(stringPoint))  {
                Object name = dataForDataBase[1];
                Object telephone = dataForDataBase[2];
                Object gmail = dataForDataBase[3];
                Object login = dataForDataBase[4];
                Object password = dataForDataBase[5];
                Object role = dataForDataBase[6];

                System.out.println("111111 {111111}"); //delete
                sqlAddData = "INSERT INTO employee(name_employee,telephone_employee,gmail_employee,login_employee," +
                        "password_employee,role_employee)" + "VALUES (" + "\'" + name + "\'," + "\'" + telephone + "\'," + "\'" + gmail + "\'," +
                        "\'" + login + "\'," + "\'" + password + "\'," + "\'" + role + "\'" + ");";

                addRole = "CREATE ROLE " + login + " WITH LOGIN PASSWORD \'" + password + "\';\n";
                addRoleRightsCashier = "GRANT SELECT (sell_score, purchased_score, id_currency_exchanger_rate, day_score, result_price_score, id_service, id_employee) ON score TO " + login + ";\n" +
                        "GRANT INSERT (sell_score, purchased_score, id_currency_exchanger_rate, day_score, result_price_score, id_service, id_employee) ON score TO " + login + ";\n" +
                        "GRANT UPDATE (sell_score, purchased_score, id_currency_exchanger_rate, day_score, result_price_score, id_service, id_employee) ON score TO " + login + ";\n" +
                        "GRANT DELETE ON score TO " + login + ";\n" +
                        "GRANT SELECT (name_employee, telephone_employee, login_employee, password_employee, role_employee) ON employee TO " + login + ";\n" +
                        "GRANT INSERT (name_employee, telephone_employee, login_employee, password_employee, role_employee) ON employee TO " + login + ";\n" +
                        "GRANT UPDATE (name_employee, telephone_employee, login_employee, password_employee, role_employee) ON employee TO " + login + ";\n" +
                        "GRANT DELETE ON employee TO " + login + ";";
                addRoleRightsAdmin = "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO " + login + ";\n" +
                        "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO " + login + ";";

                String checkDataName = "SELECT name_employee FROM employee WHERE name_employee=\'" + name + "\';";
                String checkDataTelephone = "SELECT telephone_employee FROM employee WHERE telephone_employee=\'" + telephone + "\';";
                String checkDataLogin = "SELECT login_employee FROM employee WHERE login_employee=\'" + login + "\';";
                String checkDataPassword = "SELECT password_employee FROM employee WHERE password_employee=\'" + password + "\';";

                System.out.println("Працівник 111111{00000}"); //delete

                Statement statement = conn.createStatement();
                ResultSet resultName = statement.executeQuery(checkDataName);
                Statement statement1 = conn.createStatement();
                ResultSet resultLogin = statement1.executeQuery(checkDataLogin);
                Statement statement2 = conn.createStatement();
                ResultSet resultPassword = statement2.executeQuery(checkDataPassword);
                Statement statement3 = conn.createStatement();
                ResultSet resultTelephone = statement3.executeQuery(checkDataTelephone);

                if (!resultName.next() && !resultLogin.next() && !resultPassword.next() && !resultTelephone.next()) {
                    if("cashier".equals(role) && !"Enter name employee".equals(name) && !"Telephone (+777-77-77)".equals(telephone) && !"Enter login".equals(login) && !"Enter password".equals(password)) {
                        statement.executeUpdate(sqlAddData);
                        statement.executeUpdate(addRole);
                        statement.executeUpdate(addRoleRightsCashier);
                        System.out.println("Працівник 111111{11111}"); //delete
                        textError.setText("");
                        textError.setText("Add data in Data Base");
                    }
                    else if("admin".equals(role) && !"Enter name employee".equals(name) && !"Telephone (+777-77-77)".equals(telephone) && !"Enter login".equals(login) && !"Enter password".equals(password)) {
                        statement.executeUpdate(sqlAddData);
                        statement.executeUpdate(addRole);
                        statement.executeUpdate(addRoleRightsAdmin);
                        System.out.println("Працівник 111111{22222}"); //delete
                        textError.setText("");
                        textError.setText("Add data in Data Base");
                    }
                    else{
                        textError.setText("");
                        textError.setText("Not add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("");
                        textError.setText("This name where in Database");
                    }
                    else if(!resultLogin.next()){
                        textError.setText("");
                        textError.setText("This login where in Database");
                    }
                    else if(!resultPassword.next()){
                        textError.setText("");
                        textError.setText("This password where in Database");
                    }
                    else if(!resultTelephone.next()){
                        textError.setText("");
                        textError.setText("This name telephone in Database");
                    }
                    else{
                        textError.setText("");
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
                statement1.close();
                statement2.close();
                statement3.close();
            }
            else if("Валюти".equals(stringPoint)){
                System.out.println("Валюта 111111{11111}"); //delete
                Object nameCurrency = dataForDataBase[1];
                Object desCurrency = dataForDataBase[2];
                Object availCurrency = dataForDataBase[3];

                addCurrencyDataBase = "INSERT INTO currency(name_currency, description_currency, availability_currency) VALUES (\'" + nameCurrency + "\', \'" +  desCurrency + "\', \'" + availCurrency + "\');";
                //check data
                String checkNameCurrency = "SELECT name_currency FROM currency WHERE name_currency = \'" + nameCurrency + "\';";
                String checkDesCurrency = "SELECT description_currency FROM currency WHERE description_currency = \'" + desCurrency + "\';";
                //statement
                Statement statement = conn.createStatement();
                ResultSet resultName = statement.executeQuery(checkNameCurrency);
                Statement statement1 = conn.createStatement();
                ResultSet resultDescription = statement1.executeQuery(checkDesCurrency);

                if (!resultName.next() && !resultDescription.next()) {
                    if(!"Enter name currency".equals(nameCurrency) && !"Enter availability".equals(availCurrency)) {
                        statement.executeUpdate(addCurrencyDataBase);
                        textError.setText("Add data in Data Base");
                        System.out.println("Валюта 111111{22222}"); //delete
                    }
                    else{
                        textError.setText("Not add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("This name where in Database");
                    }
                    else if(!resultDescription.next()){
                        textError.setText("This description where in Database");
                    }
                    else{
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
                statement1.close();
            }
            else if("Послуга".equals(stringPoint)){
                System.out.println("Послуга 111111{11111}"); //delete
                Object nameService = dataForDataBase[1];
                Object desService = dataForDataBase[2];
                Object sellAvailCurrency = dataForDataBase[3];
                Object purchasedAvailCurrency = dataForDataBase[4];
                Object keyCommission = dataForDataBase[5];

                addCurrencyDataBase = "INSERT INTO service(id_commission, name_service, description_service, sell_price_service, purchased_price_service) VALUES ("
                        +  keyCommission + ", \'" + nameService + "\', \'" + desService + "\'," + sellAvailCurrency + "," + purchasedAvailCurrency + ");";

                //check data
                String checkNameService = "SELECT name_service FROM service WHERE name_service = \'" + nameService + "\';";
                String checkDesService = "SELECT description_service FROM service WHERE description_service = \'" + desService + "\';";
                //statement
                Statement statement = conn.createStatement();
                ResultSet resultName = statement.executeQuery(checkNameService);
                Statement statement1 = conn.createStatement();
                ResultSet resultDescription = statement1.executeQuery(checkDesService);

                if (!resultName.next() && !resultDescription.next()) {
                    if(!"Enter name service".equals(nameService) && !"Enter sell price".equals(sellAvailCurrency) && !"Enter purchased price".equals(purchasedAvailCurrency) && !"Select key commission".equals(keyCommission)) {
                        statement.executeUpdate(addCurrencyDataBase);
                        textError.setText("");
                        textError.setText("Add data in Data Base");
                        System.out.println("Послуга 111111{22222}"); //delete
                    }
                    else{
                        textError.setText("");
                        textError.setText("Not add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("");
                        textError.setText("This name where in Database");
                    }
                    else if(!resultDescription.next()){
                        textError.setText("");
                        textError.setText("This description where in Database");
                    }
                    else{
                        textError.setText("");
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
                statement1.close();

            }
            else if("Курс обміну".equals(stringPoint)){}
            else if("Тариф".equals(stringPoint)){}
            else if("Рахунок".equals(stringPoint)){}
            else if("Зміна".equals(stringPoint)){}
            else if("Сума в касі".equals(stringPoint)){}
            else if("Присутність".equals(stringPoint)){}
            else if("Валюта та курс обміну".equals(stringPoint)){}
            else if("Валюти та послуги".equals(stringPoint)){}
        }
        catch (Exception ex){
            System.out.println("Errror: " + ex);
            textError.setText("Please write correct data");
            textError.setText("Please write correct data");
        }
    }








    private JPanel createPanelChangeData(String cardName)  {
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
        changeData.setForeground(Color.decode("#B1FDC6"));
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

        //event
        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Add_data");
            }
        });
        changeData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Change_data");
            }
        });
        searchData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Search_data");
            }
        });
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Delete_data");
            }
        });

        panel.add(addData);
        panel.add(changeData);
        panel.add(searchData);
        panel.add(deleteData);
        panel.add(menu);
        panel.add(header);
        return panel;
    }
    private JPanel createPanelSearchData(String cardName)  {
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
        searchData.setForeground(Color.decode("#B1FDC6"));
        searchData.setBounds(0, 125,150,40);
        //deleteData
        JLabel deleteData = new JLabel("Delete data".toUpperCase());
        deleteData.setBorder(compound);
        Font currentFontDeleteData = header.getFont(); // Получаем текущий шрифт метки
        Font newFontDeleteData = currentFontDeleteData.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
        deleteData.setFont(newFontDeleteData.deriveFont(17f)); // Устанавливаем новый шрифт и размер
        deleteData.setForeground(Color.black);
        deleteData.setBounds(0, 180,150,40);

        //event
        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Add_data");
            }
        });
        changeData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Change_data");
            }
        });
        searchData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Search_data");
            }
        });
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Delete_data");
            }
        });

        panel.add(addData);
        panel.add(changeData);
        panel.add(searchData);
        panel.add(deleteData);
        panel.add(menu);
        panel.add(header);
        return panel;
    }
    private JPanel createPanelDeleteData(String cardName)  {
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
        deleteData.setForeground(Color.decode("#B1FDC6"));
        deleteData.setBounds(0, 180,150,40);

        //event
        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Add_data");
            }
        });
        changeData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Change_data");
            }
        });
        searchData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Search_data");
            }
        });
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Delete_data");
            }
        });

        panel.add(addData);
        panel.add(changeData);
        panel.add(searchData);
        panel.add(deleteData);
        panel.add(menu);
        panel.add(header);
        return panel;
    }

}

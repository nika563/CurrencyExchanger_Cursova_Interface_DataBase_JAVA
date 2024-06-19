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
import com.toedter.calendar.JCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
//sql
import java.sql.*;

public class adminInterface extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel, panel;
    private static JComboBox<String> comboBox, selectRoleComboBox, selectKeyCommissionComboBox, selectKeyEmployeeShiftComboBox,
            selectKeyCurrencyCashRegisterComboBox, selectKeyShiftCashRegisterComboBox;
    private static JTextField name_employee, telephone_employee, gmail_employee, login_employee, password_employee,
            name_currency, description_currency, availability_currency,
            name_service, description_service, sell_price_service, purchased_price_service,
            name_commission, price_commission,
            price_hour_shift,
            start_price_cash_register, end_price_cash_register;
    private static JLabel textError;
    private static JButton send;
    private static JCalendar calendar_exchanger_rate, start_calendar_shift, end_calendar_shift,
            start_actual_calendar, end_actual_calendar;
    private static JSpinner timeSpinner, startTimeShift, endTimeShift;
    private static JSpinner.DateEditor textField;
    private static JFormattedTextField time_exchanger_rate, start_time_shift, end_time_shift,
            start_actual_time, end_actual_time, presence_time;
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
        String[] menuItems = {"", "Працівник", "Валюти", "Послуга", "Курс обміну", "Тариф", "Зміна", "Сума в касі", "Присутність", "Валюта та курс обміну", "Валюти та послуги"};
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
                    String[] selectKeyCommissionArray;
                    try {
                        selectKeyCommissionArray = addDataInList().toArray(new String[0]);;
                        selectKeyCommissionComboBox = new JComboBox<>(selectKeyCommissionArray);
                        selectKeyCommissionComboBox.setBounds(1220, 80,230,35);
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
                    }
                    catch (Exception ex) {
                        System.out.println("Error99: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in commission");
                        }
                    }
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
                    //calendar_exchanger_rate
                    calendar_exchanger_rate = new JCalendar();
                    calendar_exchanger_rate.setBounds(420, 80,260,210);
                    calendar_exchanger_rate.setFont(new Font("Arial", Font.PLAIN, 18));
                    calendar_exchanger_rate.setForeground(Color.black);
                    calendar_exchanger_rate.setBackground(Color.decode("#24743F"));
                    calendar_exchanger_rate.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //time_exchanger_rate
                    Date initialDate = new Date();
                    SpinnerDateModel spinnerModel = new SpinnerDateModel(initialDate, null, null, Calendar.HOUR_OF_DAY);
                    timeSpinner  = new JSpinner(spinnerModel);
                    textField = new JSpinner.DateEditor(timeSpinner , "HH:mm:ss");
                    timeSpinner.setEditor(textField);
                    time_exchanger_rate = ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField();
                    time_exchanger_rate.setBounds(690, 80,110,40);
                    time_exchanger_rate.setFont(new Font("Arial", Font.PLAIN, 18));
                    time_exchanger_rate.setForeground(Color.black);
                    time_exchanger_rate.setBackground(Color.decode("#24743F"));
                    time_exchanger_rate.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);
                    //send
                    send = new JButton("Send");
                    send.setFont(new Font("Arial", Font.PLAIN, 18));
                    send.setForeground(Color.decode("#CADACF")); //color text
                    send.setBackground(Color.decode("#284F00"));  //color background
                    send.setBorder(BorderFactory.createLineBorder(Color.decode("#284F00"), 2));
                    send.setFocusPainted(false);
                    send.setBounds(180, 130,230,35);
                    send.addMouseListener(new MouseAdapter() { public void mousePressed(MouseEvent me) {
                            send.setForeground(Color.decode("#CADACF")); // меняем цвет текста на черный при нажатии
                            send.setBackground(Color.decode("#1F9B49"));  //color background
                        } });

                    panel.add(calendar_exchanger_rate);
                    panel.add(time_exchanger_rate);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Тариф".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Тариф");
                    //name_commission
                    name_commission  = new JTextField("Enter name commission");
                    name_commission.setBounds(420, 80,220,35);
                    name_commission.setFont(new Font("Arial", Font.PLAIN, 18));
                    name_commission.setForeground(Color.black);
                    name_commission.setBackground(Color.decode("#24743F"));
                    name_commission.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    name_commission.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (name_commission.getText().equals("Enter name commission")) {
                                name_commission.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (name_commission.getText().isEmpty()) {
                                name_commission.setText("Enter name commission");
                            }
                        }
                    });
                    //purchased_price_service
                    price_commission  = new JTextField("Enter price commission");
                    price_commission.setBounds(650, 80,210,35);
                    price_commission.setFont(new Font("Arial", Font.PLAIN, 18));
                    price_commission.setForeground(Color.black);
                    price_commission.setBackground(Color.decode("#24743F"));
                    price_commission.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    price_commission.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (price_commission.getText().equals("Enter price commission")) {
                                price_commission.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (price_commission.getText().isEmpty()) {
                                price_commission.setText("Enter price commission");
                            }
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);
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

                    panel.add(name_commission);
                    panel.add(price_commission);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Зміна".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Зміна");
                    //start_calendar_shift
                    start_calendar_shift = new JCalendar();
                    start_calendar_shift.setBounds(420, 80,260,210);
                    start_calendar_shift.setFont(new Font("Arial", Font.PLAIN, 18));
                    start_calendar_shift.setForeground(Color.black);
                    start_calendar_shift.setBackground(Color.decode("#24743F"));
                    start_calendar_shift.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //time_exchanger_rate
                    Date initialDate = new Date();
                    SpinnerDateModel spinnerModel = new SpinnerDateModel(initialDate, null, null, Calendar.HOUR_OF_DAY);
                    startTimeShift  = new JSpinner(spinnerModel);
                    textField = new JSpinner.DateEditor(startTimeShift , "HH:mm:ss");
                    startTimeShift.setEditor(textField);
                    start_time_shift = ((JSpinner.DefaultEditor) startTimeShift.getEditor()).getTextField();
                    start_time_shift.setBounds(690, 80,110,40);
                    start_time_shift.setFont(new Font("Arial", Font.PLAIN, 18));
                    start_time_shift.setForeground(Color.black);
                    start_time_shift.setBackground(Color.decode("#24743F"));
                    start_time_shift.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

                    //end_calendar_shift
                    end_calendar_shift = new JCalendar();
                    end_calendar_shift.setBounds(820, 80,260,210);
                    end_calendar_shift.setFont(new Font("Arial", Font.PLAIN, 18));
                    end_calendar_shift.setForeground(Color.black);
                    end_calendar_shift.setBackground(Color.decode("#24743F"));
                    end_calendar_shift.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //end_time_shift
                    Date initialDate01 = new Date();
                    SpinnerDateModel spinnerModel01 = new SpinnerDateModel(initialDate01, null, null, Calendar.HOUR_OF_DAY);
                    endTimeShift  = new JSpinner(spinnerModel01);
                    textField = new JSpinner.DateEditor(endTimeShift , "HH:mm:ss");
                    endTimeShift.setEditor(textField);
                    end_time_shift = ((JSpinner.DefaultEditor) endTimeShift.getEditor()).getTextField();
                    end_time_shift.setBounds(1090, 80,110,40);
                    end_time_shift.setFont(new Font("Arial", Font.PLAIN, 18));
                    end_time_shift.setForeground(Color.black);
                    end_time_shift.setBackground(Color.decode("#24743F"));
                    end_time_shift.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //price_hour_shift
                    price_hour_shift  = new JTextField("Enter hour price shift");
                    price_hour_shift.setBounds(1210, 80,210,35);
                    price_hour_shift.setFont(new Font("Arial", Font.PLAIN, 18));
                    price_hour_shift.setForeground(Color.black);
                    price_hour_shift.setBackground(Color.decode("#24743F"));
                    price_hour_shift.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    price_hour_shift.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (price_hour_shift.getText().equals("Enter hour price shift")) {
                                price_hour_shift.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (price_hour_shift.getText().isEmpty()) {
                                price_hour_shift.setText("Enter hour price shift");
                            }
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);

                    //selectIdEmployee
                    String[] selectKeyEmployeeArray;
                    try {
                        selectKeyEmployeeArray = addDataEmployeeInList().toArray(new String[0]);;
                        selectKeyEmployeeShiftComboBox = new JComboBox<>(selectKeyEmployeeArray);
                        selectKeyEmployeeShiftComboBox.setBounds(1210, 130,230,35);
                        selectKeyEmployeeShiftComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                        selectKeyEmployeeShiftComboBox.setForeground(Color.black);
                        selectKeyEmployeeShiftComboBox.setBackground(Color.decode("#24743F"));
                        selectKeyEmployeeShiftComboBox.setRenderer(new DefaultListCellRenderer() {
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
                                    setText("Select key employee");
                                }
                                return this;
                            }
                        });
                        selectKeyEmployeeShiftComboBox.setSelectedIndex(0);
                    }
                    catch (Exception ex) {
                        System.out.println("Error99: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in commission");
                        }
                    }
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

                    panel.add(start_calendar_shift);
                    panel.add(start_time_shift);
                    panel.add(end_calendar_shift);
                    panel.add(end_time_shift);
                    panel.add(price_hour_shift);
                    panel.add(selectKeyEmployeeShiftComboBox);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Сума в касі".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Сума в касі");
                    //start_price_cash_register
                    start_price_cash_register  = new JTextField("Enter start price cash register");
                    start_price_cash_register.setBounds(420, 80,260,35);
                    start_price_cash_register.setFont(new Font("Arial", Font.PLAIN, 18));
                    start_price_cash_register.setForeground(Color.black);
                    start_price_cash_register.setBackground(Color.decode("#24743F"));
                    start_price_cash_register.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    start_price_cash_register.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (start_price_cash_register.getText().equals("Enter start price cash register")) {
                                start_price_cash_register.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (start_price_cash_register.getText().isEmpty()) {
                                start_price_cash_register.setText("Enter start price cash register");
                            }
                        }
                    });
                    //end_price_cash_register
                    end_price_cash_register  = new JTextField("Enter end price cash register");
                    end_price_cash_register.setBounds(420, 130,260,35);
                    end_price_cash_register.setFont(new Font("Arial", Font.PLAIN, 18));
                    end_price_cash_register.setForeground(Color.black);
                    end_price_cash_register.setBackground(Color.decode("#24743F"));
                    end_price_cash_register.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
                    end_price_cash_register.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (end_price_cash_register.getText().equals("Enter end price cash register")) {
                                end_price_cash_register.setText("");
                            }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (end_price_cash_register.getText().isEmpty()) {
                                end_price_cash_register.setText("Enter end price cash register");
                            }
                        }
                    });
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);

                    //selectKeyCurrencyArray
                    String[] selectKeyCurrencyArray;
                    try {
                        selectKeyCurrencyArray = addDataCurrencyInList().toArray(new String[0]);;
                        selectKeyCurrencyCashRegisterComboBox = new JComboBox<>(selectKeyCurrencyArray);
                        selectKeyCurrencyCashRegisterComboBox.setBounds(690, 80,230,35);
                        selectKeyCurrencyCashRegisterComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                        selectKeyCurrencyCashRegisterComboBox.setForeground(Color.black);
                        selectKeyCurrencyCashRegisterComboBox.setBackground(Color.decode("#24743F"));
                        selectKeyCurrencyCashRegisterComboBox.setRenderer(new DefaultListCellRenderer() {
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
                                    setText("Select name currency");
                                }
                                return this;
                            }
                        });
                        selectKeyCurrencyCashRegisterComboBox.setSelectedIndex(0);
                    }
                    catch (Exception ex) {
                        System.out.println("Error98: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in currency");
                        }
                    }
                    //selectKeyShiftArray
                    String[] selectKeyShiftArray;
                    try {
                        selectKeyShiftArray = addDataShiftInList().toArray(new String[0]);;
                        selectKeyShiftCashRegisterComboBox = new JComboBox<>(selectKeyShiftArray);
                        selectKeyShiftCashRegisterComboBox.setBounds(930, 80,230,35);
                        selectKeyShiftCashRegisterComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                        selectKeyShiftCashRegisterComboBox.setForeground(Color.black);
                        selectKeyShiftCashRegisterComboBox.setBackground(Color.decode("#24743F"));
                        selectKeyShiftCashRegisterComboBox.setRenderer(new DefaultListCellRenderer() {
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
                                    setText("Select key shift");
                                }
                                return this;
                            }
                        });
                        selectKeyShiftCashRegisterComboBox.setSelectedIndex(0);
                    }
                    catch (Exception ex) {
                        System.out.println("Error97: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in shift");
                        }
                    }
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

                    panel.add(start_price_cash_register);
                    panel.add(end_price_cash_register);
                    panel.add(selectKeyCurrencyCashRegisterComboBox);
                    panel.add(selectKeyShiftCashRegisterComboBox);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
                }
                else if ("Присутність".equals(selectedText)) {
                    deleteComponents();
                    System.out.println("Присутність");
                    //start_actual_calendar
                    start_actual_calendar = new JCalendar();
                    start_actual_calendar.setBounds(420, 80,260,210);
                    start_actual_calendar.setFont(new Font("Arial", Font.PLAIN, 18));
                    start_actual_calendar.setForeground(Color.black);
                    start_actual_calendar.setBackground(Color.decode("#24743F"));
                    start_actual_calendar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //start_actual_time
                    Date initialDate = new Date();
                    SpinnerDateModel spinnerModel = new SpinnerDateModel(initialDate, null, null, Calendar.HOUR_OF_DAY);
                    startTimeShift  = new JSpinner(spinnerModel);
                    textField = new JSpinner.DateEditor(startTimeShift , "HH:mm:ss");
                    startTimeShift.setEditor(textField);
                    start_actual_time = ((JSpinner.DefaultEditor) startTimeShift.getEditor()).getTextField();
                    start_actual_time.setBounds(690, 80,110,40);
                    start_actual_time.setFont(new Font("Arial", Font.PLAIN, 18));
                    start_actual_time.setForeground(Color.black);
                    start_actual_time.setBackground(Color.decode("#24743F"));
                    start_actual_time.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //end_actual_calendar
                    end_actual_calendar = new JCalendar();
                    end_actual_calendar.setBounds(420, 80,260,210);
                    end_actual_calendar.setFont(new Font("Arial", Font.PLAIN, 18));
                    end_actual_calendar.setForeground(Color.black);
                    end_actual_calendar.setBackground(Color.decode("#24743F"));
                    end_actual_calendar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //end_actual_time
                    end_actual_time = ((JSpinner.DefaultEditor) startTimeShift.getEditor()).getTextField();
                    end_actual_time.setBounds(690, 80,110,40);
                    end_actual_time.setFont(new Font("Arial", Font.PLAIN, 18));
                    end_actual_time.setForeground(Color.black);
                    end_actual_time.setBackground(Color.decode("#24743F"));
                    end_actual_time.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //presence_time
                    presence_time = ((JSpinner.DefaultEditor) startTimeShift.getEditor()).getTextField();
                    presence_time.setBounds(690, 80,110,40);
                    presence_time.setFont(new Font("Arial", Font.PLAIN, 18));
                    presence_time.setForeground(Color.black);
                    presence_time.setBackground(Color.decode("#24743F"));
                    presence_time.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    //textError
                    textError = new JLabel("");
                    textError.setFont(new Font("Arial", Font.PLAIN, 18));
                    textError.setForeground(Color.decode("#000000"));
                    textError.setBounds(180, 170,290,50);

                    //selectKeyShiftArray
                    String[] selectKeyShiftArray;
                    try {
                        selectKeyShiftArray = addDataShiftInList().toArray(new String[0]);;
                        selectKeyShiftCashRegisterComboBox = new JComboBox<>(selectKeyShiftArray);
                        selectKeyShiftCashRegisterComboBox.setBounds(930, 80,230,35);
                        selectKeyShiftCashRegisterComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                        selectKeyShiftCashRegisterComboBox.setForeground(Color.black);
                        selectKeyShiftCashRegisterComboBox.setBackground(Color.decode("#24743F"));
                        selectKeyShiftCashRegisterComboBox.setRenderer(new DefaultListCellRenderer() {
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
                                    setText("Select key shift");
                                }
                                return this;
                            }
                        });
                        selectKeyShiftCashRegisterComboBox.setSelectedIndex(0);
                    }
                    catch (Exception ex) {
                        System.out.println("Error97: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in shift");
                        }
                    }
                    //selectIdEmployee
                    String[] selectKeyEmployeeArray;
                    try {
                        selectKeyEmployeeArray = addDataEmployeeInList().toArray(new String[0]);;
                        selectKeyEmployeeShiftComboBox = new JComboBox<>(selectKeyEmployeeArray);
                        selectKeyEmployeeShiftComboBox.setBounds(1210, 130,230,35);
                        selectKeyEmployeeShiftComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
                        selectKeyEmployeeShiftComboBox.setForeground(Color.black);
                        selectKeyEmployeeShiftComboBox.setBackground(Color.decode("#24743F"));
                        selectKeyEmployeeShiftComboBox.setRenderer(new DefaultListCellRenderer() {
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
                                    setText("Select key employee");
                                }
                                return this;
                            }
                        });
                        selectKeyEmployeeShiftComboBox.setSelectedIndex(0);
                    }
                    catch (Exception ex) {
                        System.out.println("Error99: " + ex);
                        if(ex instanceof ArrayIndexOutOfBoundsException){
                            textError.setText("Please write data in commission");
                        }
                    }

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

                    panel.add(start_actual_calendar);
                    panel.add(start_actual_time);
                    //panel.add(end_actual_calendar);
                    //panel.add(end_actual_time);
                    //panel.add(presence_time);
                    //panel.add(selectKeyShiftCashRegisterComboBox);
                    //panel.add(selectKeyEmployeeShiftComboBox);
                    panel.add(textError);
                    panel.add(send);
                    send.addActionListener(new eventSendDataBase());
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
                        textError.setText("Please write data");
                        System.out.println("nameService {111}"); //delete
                    }
                    else if(strSellPriceService.equals("Enter sell price") || strPurchasedPriceService.equals("Enter purchased price")){
                        textError.setText("Please write number");
                        System.out.println("nameService {222}"); //delete
                    }
                    else if(keyCommission.equals("Select key commission")){
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
                else if("Курс обміну".equals(selectedItem)){
                    System.out.println("Курс обміну eventSendDataBase");
                    //year-month-day
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String calendarExchangerRate = dateFormat.format(calendar_exchanger_rate.getDate());
                    //hour-minutes-seconds
                    String timeExchangerRate = time_exchanger_rate.getText();
                    addDataInDataBase(selectedItem, calendarExchangerRate, timeExchangerRate);
                }
                else if("Тариф".equals(selectedItem)){
                    System.out.println("Тариф eventSendDataBase");
                    String nameCommission = name_commission.getText().trim();
                    String priceCommission = price_commission.getText().trim();

                    if(nameCommission.equals("Enter name commission")){
                        textError.setText("Please write data");
                    }
                    else if(priceCommission.equals("Enter price commission")){
                        textError.setText("Please write number");
                    }
                    else {
                        int availCommission = Integer.parseInt(priceCommission);
                        addDataInDataBase(selectedItem, nameCommission, availCommission);
                    }
                }
                else if("Зміна".equals(selectedItem)){
                    System.out.println("Зміна eventSendDataBase");
                    String priceHourShift = price_hour_shift.getText().trim();
                    //start_shift
                    SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String calendarStartShift = startDateFormat.format(start_calendar_shift.getDate());
                    //start_shift
                    String timeStartShift = start_time_shift.getText();
                    //end_shift
                    SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String calendarEndShift = endDateFormat.format(end_calendar_shift.getDate());
                    //end_shift
                    String timeEndShift = end_time_shift.getText();
                    String keyEmployeeShift = (String) selectKeyCommissionComboBox.getSelectedItem();


                    if(priceHourShift.equals("Enter price commission")){
                        textError.setText("Please write number");
                    }
                    else {
                        int availShift = Integer.parseInt(priceHourShift);
                        addDataInDataBase(selectedItem, calendarStartShift, timeStartShift, calendarEndShift, timeEndShift, availShift, keyEmployeeShift);
                    }
                }
                else if("Сума в касі".equals(selectedItem)){
                    System.out.println("Сума в касі eventSendDataBase");
                    String startPriceCashRegister = start_price_cash_register.getText().trim();
                    String endPriceCashRegister = end_price_cash_register.getText().trim();
                    //comboBoX
                    String nameCurrency = (String) selectKeyCurrencyCashRegisterComboBox.getSelectedItem();
                    String keyShift = (String) selectKeyShiftCashRegisterComboBox.getSelectedItem();

                    if(startPriceCashRegister.equals("Enter start price cash register") || endPriceCashRegister.equals("Enter end price cash register")){
                        textError.setText("Please write number");
                    }
                    else if(nameCurrency.equals("Select name currency") || keyShift.equals("Select key shift")){
                        textError.setText("Please chose data in shift or currency");
                    }
                    else {
                        int availStartPrice = Integer.parseInt(startPriceCashRegister);
                        int availEndPrice = Integer.parseInt(endPriceCashRegister);
                        addDataInDataBase(selectedItem, availStartPrice, availEndPrice, nameCurrency, keyShift);
                    }
                }
                else if("Присутність".equals(selectedItem)){
                    System.out.println("Присутність eventSendDataBase");
                    //start_actual_calendar
                    SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String startActualCalendar = startDateFormat.format(start_actual_calendar.getDate());
                    String startActualTime = start_actual_time.getText();
                    String allStart = startActualCalendar + " " + startActualTime;
                    //end_actual_calendar
                    SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String endActualCalendar = endDateFormat.format(end_actual_calendar.getDate());
                    String endActualTime = end_actual_time.getText();
                    String allEnd = endActualCalendar + " " + endActualTime;
                    //presence_time
                    String presenceTime = presence_time.getText();
                    //JComboBox
                    String keyEmployee = (String) selectKeyEmployeeShiftComboBox.getSelectedItem();
                    String keyShift = (String) selectKeyShiftCashRegisterComboBox.getSelectedItem();
                    addDataInDataBase(selectedItem, allStart, allEnd, presenceTime, keyEmployee, keyShift);
                }
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
                //employee
                else if (box.equals(selectKeyEmployeeShiftComboBox)) {
                    panel.remove(box);
                }
                //cash register
                else if (box.equals(selectKeyCurrencyCashRegisterComboBox)) {
                    panel.remove(box);
                }
                else if (box.equals(selectKeyShiftCashRegisterComboBox)) {
                    panel.remove(box);
                }
            }
            else if (component instanceof JCalendar) {
                JCalendar jCalendar = (JCalendar) component;
                //exchanger_rate
                if (jCalendar.equals(calendar_exchanger_rate)) {
                    panel.remove(jCalendar);
                }
                //shift
                else if (jCalendar.equals(start_calendar_shift)) {
                    panel.remove(jCalendar);
                }
                else if (jCalendar.equals(end_calendar_shift)) {
                    panel.remove(jCalendar);
                }
                //presence
                else if (jCalendar.equals(start_actual_calendar)) {
                    panel.remove(jCalendar);
                }
                else if (jCalendar.equals(end_actual_calendar)) {
                    panel.remove(jCalendar);
                }
            }
            else if (component instanceof JFormattedTextField) {
                JFormattedTextField jFormattedTextField = (JFormattedTextField) component;
                //exchanger_rate
                if (jFormattedTextField.equals(time_exchanger_rate)) {
                    panel.remove(jFormattedTextField);
                }
                //shift
                else if (jFormattedTextField.equals(start_time_shift)) {
                    panel.remove(jFormattedTextField);
                }
                else if (jFormattedTextField.equals(end_time_shift)) {
                    panel.remove(jFormattedTextField);
                }
                //presence
                else if (jFormattedTextField.equals(start_actual_time)) {
                    panel.remove(jFormattedTextField);
                }
                else if (jFormattedTextField.equals(end_actual_time)) {
                    panel.remove(jFormattedTextField);
                }
                else if (jFormattedTextField.equals(presence_time)) {
                    panel.remove(jFormattedTextField);
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
                //commission
                else if (textField.equals(name_commission)) {
                    panel.remove(textField);
                }
                else if (textField.equals(price_commission)) {
                    panel.remove(textField);
                }
                //shift
                else if (textField.equals(price_hour_shift)) {
                    panel.remove(textField);
                }
                //cash register
                else if (textField.equals(start_price_cash_register)) {
                    panel.remove(textField);
                }
                else if (textField.equals(end_price_cash_register)) {
                    panel.remove(textField);
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    }
    //commission
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
            if(selectIdCommission.isEmpty()){
                selectIdCommission.add(0, "");
                textError.setText("Please write data in commission");
            }
        }
        catch(Exception ex){
            System.out.println("Error addDataCommissionInList: " + ex);
            if(ex instanceof NullPointerException){
                selectIdCommission.add(0, "");
            }
        }
        return selectIdCommission;
    }
    //employee
    private ArrayList<String> addDataEmployeeInList() throws SQLException{
        ArrayList<String> selectIdEmployee = new ArrayList<>();
        try {
            String getIdEmployee = "SELECT id_employee FROM employee;";
            Statement statement = conn.createStatement();
            ResultSet resulIdEmployee = statement.executeQuery(getIdEmployee);
            while (resulIdEmployee.next()) {
                selectIdEmployee.add(resulIdEmployee.getString("id_commission"));
            }
            if(selectIdEmployee.isEmpty()){
                selectIdEmployee.add(0, "");
                textError.setText("Please write data in employee");
            }
        }
        catch(Exception ex){
            System.out.println("Error addDataEmployeeInList: " + ex);
            if(ex instanceof NullPointerException){
                selectIdEmployee.add(0, "");
            }
        }
        return selectIdEmployee;
    }
    //currency
    private ArrayList<String> addDataCurrencyInList() throws SQLException{
        ArrayList<String> selectNameCurrency = new ArrayList<>();
        try {
            String getNameCurrency = "SELECT name_currency FROM currency;";
            Statement statement = conn.createStatement();
            System.out.println("resultNameCurrency: " + statement.executeQuery(getNameCurrency)); //delete
            ResultSet resultNameCurrency = statement.executeQuery(getNameCurrency);
            while (resultNameCurrency.next()) {
                selectNameCurrency.add(resultNameCurrency.getString("name_currency"));
            }
            if(selectNameCurrency.isEmpty()){
                selectNameCurrency.add(0, "");
                textError.setText("Please write data in currency");
            }
        }
        catch(Exception ex){
            System.out.println("Error addDataCurrencyInList: " + ex);
            if(ex instanceof NullPointerException){
                selectNameCurrency.add(0, "");
            }
        }
        return selectNameCurrency;
    }
    //shift
    private ArrayList<String> addDataShiftInList() throws SQLException{
        ArrayList<String> selectIdShift = new ArrayList<>();
        try {
            String getIdCommission = "SELECT id_shift FROM shift;";
            Statement statement = conn.createStatement();
            System.out.println("resulIdCommission: " + statement.executeQuery(getIdCommission));
            ResultSet resulIdCommission = statement.executeQuery(getIdCommission);
            while (resulIdCommission.next()) {
                selectIdShift.add(resulIdCommission.getString("id_shift"));
            }
            if(selectIdShift.isEmpty()){
                selectIdShift.add(0, "");
                textError.setText("Please write data in shift");
            }
        }
        catch(Exception ex) {
            System.out.println("Error addDataShiftInList: " + ex);
            if(ex instanceof NullPointerException){
                selectIdShift.add(0, "");
            }
        }
        return selectIdShift;
    }
    public void addDataInDataBase(Object... dataForDataBase){
        try {
            Object stringPoint = dataForDataBase[0];
            String addCurrencyDataBase, sqlAddData, addAllTimeDataBase;
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
                        textError.setText("Add data in Data Base");
                    }
                    else if("admin".equals(role) && !"Enter name employee".equals(name) && !"Telephone (+777-77-77)".equals(telephone) && !"Enter login".equals(login) && !"Enter password".equals(password)) {
                        statement.executeUpdate(sqlAddData);
                        statement.executeUpdate(addRole);
                        statement.executeUpdate(addRoleRightsAdmin);
                        System.out.println("Працівник 111111{22222}"); //delete
                        textError.setText("Add data in Data Base");
                    }
                    else{
                        textError.setText("Not add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("This name where in Database");
                    }
                    else if(!resultLogin.next()){
                        textError.setText("This login where in Database");
                    }
                    else if(!resultPassword.next()){
                        textError.setText("This password where in Database");
                    }
                    else if(!resultTelephone.next()){
                        textError.setText("This name telephone in Database");
                    }
                    else{
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
                statement1.close();
                statement2.close();
                statement3.close();
            }
            else if("Валюти".equals(stringPoint)){
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
                        textError.setText("Add data in Data Base");
                        System.out.println("Послуга 111111{22222}"); //delete
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
            else if("Курс обміну".equals(stringPoint)){
                Object calendarExchangerRate = dataForDataBase[1];
                Object timeExchangerRate = dataForDataBase[2];
                Object allTime = calendarExchangerRate + " " + timeExchangerRate;
                //insert
                addAllTimeDataBase = "INSERT INTO exchanger_rate(time_exchanger_rate) VALUES (\'" +  allTime + "\');";
                //check data
                String checkAllTime = "SELECT time_exchanger_rate FROM exchanger_rate WHERE time_exchanger_rate = \'" + allTime + "\';";
                //statement
                Statement statement = conn.createStatement();
                ResultSet resultAllTime = statement.executeQuery(checkAllTime);

                if (!resultAllTime.next()) {
                    statement.executeUpdate(addAllTimeDataBase);
                    textError.setText("Add data in Data Base");
                    System.out.println("Курс обміну 111111{22222}"); //delete
                }
                else if(!resultAllTime.next()){
                    textError.setText("This time where in Database");
                }
                statement.close();
            }
            else if("Тариф".equals(stringPoint)){
                System.out.println("Тариф 111111{11111}"); //delete
                Object nameСommission = dataForDataBase[1];
                Object priceСommission = dataForDataBase[2];

                addCurrencyDataBase = "INSERT INTO commission(name_commission, price_commission) VALUES (\'" + nameСommission +  "\', " + priceСommission + ");";
                //check data
                String checkNameCommission = "SELECT name_commission FROM commission WHERE name_commission = \'" + nameСommission + "\';";
                //statement
                Statement statement = conn.createStatement();
                ResultSet resultName = statement.executeQuery(checkNameCommission);

                if (!resultName.next()) {
                    if(!"Enter name commission".equals(nameСommission) && !"Enter price commission".equals(priceСommission)) {
                        statement.executeUpdate(addCurrencyDataBase);
                        textError.setText("Add data in Data Base");
                    }
                    else{
                        textError.setText("Not add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("This name where in Database");
                    }
                    else{
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
            }
            else if("Зміна".equals(stringPoint)){
                System.out.println("Зміна 111111{11111}"); //delete
                Object calendarStartShift = dataForDataBase[1];
                Object timeStartShift = dataForDataBase[2];
                Object allTimeStart = calendarStartShift + " " + timeStartShift;
                //end
                Object calendarEndShift = dataForDataBase[3];
                Object timeEndShift = dataForDataBase[4];
                Object allTimeEnd = calendarEndShift + " " + timeEndShift;
                //priceHourShift
                Object priceHourShift = dataForDataBase[5];
                Object keyCommission = dataForDataBase[6];
                //insert
                sqlAddData = "INSERT INTO shift(id_employee, start_time_shift, end_time_shift, price_shift) VALUES (" +
                        keyCommission + ", \'" + allTimeStart + "\', \'" + allTimeEnd + "\'," + priceHourShift + ");";
                //check data
                String checkAllTimeStart = "SELECT start_time_shift FROM shift WHERE start_time_shift = \'" + allTimeStart + "\';";
                String checkAllTimeEnd = "SELECT end_time_shift FROM shift WHERE end_time_shift = \'" + allTimeEnd + "\';";
                //statement
                Statement statement = conn.createStatement();
                ResultSet resultAllTimeStart = statement.executeQuery(checkAllTimeStart);
                Statement statement1 = conn.createStatement();
                ResultSet resultAllTimeEnd = statement.executeQuery(checkAllTimeEnd);
                if(!"Enter hour price shift".equals(priceHourShift)) {
                    statement.executeUpdate(sqlAddData);
                    textError.setText("Add data in Data Base");
                }
                else{
                    if(!resultAllTimeStart.next() || !resultAllTimeEnd.next()){
                        textError.setText("This name where in Database");
                    }
                    else{
                        textError.setText("This data in Database");
                    }
                }
                statement.close();
                statement1.close();
            }
            else if("Сума в касі".equals(stringPoint)){
                System.out.println("Сума в касі 111111{11111}"); //delete
                Object availStartPrice = dataForDataBase[1];
                Object availEndPrice = dataForDataBase[2];
                Object nameCurrency = dataForDataBase[3];
                Object keyShift = dataForDataBase[4];
                //insert
                sqlAddData = "DO $$ \nDECLARE \n    currencyId INT;\nBEGIN    SELECT id_currency INTO currencyId FROM currency WHERE name_currency = \'" + nameCurrency + "\';\n" +
                                "RAISE NOTICE \'Currency ID: %\', currencyId;\n INSERT INTO amount_cash_register(id_shift, id_currency, start_amount_cash_register, end_amount_cash_register) " +
                                "VALUES (" + keyShift + ", currencyId," + availStartPrice + "," + availEndPrice + ");\n END $$;";
                Statement statement = conn.createStatement();
                if(!"Enter start price cash register".equals(availStartPrice) || !"Enter end price cash register".equals(availEndPrice)) {
                    statement.executeUpdate(sqlAddData);
                    textError.setText("Add data in Data Base");
                }
                statement.close();
            }
            else if("Присутність".equals(stringPoint)){
                System.out.println("Присутність 111111{11111}"); //delete
                Object allStart = dataForDataBase[1];
                Object allEnd = dataForDataBase[2];
                Object presenceTime = dataForDataBase[3];
                Object keyEmployee = dataForDataBase[4];
                Object keyShift = dataForDataBase[5];
                //insert
                sqlAddData = "INSERT INTO presence(id_shift, id_employee, start_presence, end_presence, hours_presence) VALUES (" +
                        keyShift + ", " + keyEmployee + ", \'" + allStart + "\', \'" + allEnd + "\', \'" + presenceTime + "\');";
                Statement statement = conn.createStatement();

                if(!"Select key shiftr".equals(selectKeyShiftCashRegisterComboBox) || !"Select key employee".equals(selectKeyEmployeeShiftComboBox)) {
                    statement.executeUpdate(sqlAddData);
                    textError.setText("Add data in Data Base");
                }
                statement.close();
            }
            else if("Валюта та курс обміну".equals(stringPoint)){}
            else if("Валюти та послуги".equals(stringPoint)){}
        }
        catch (Exception ex){
            System.out.println("Errror: " + ex);
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

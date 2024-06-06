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

public class adminInterface extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    JComboBox<String> comboBox, selectRoleComboBox;
    JTextField name_employee, telephone_employee, gmail_employee, login_employee, password_employee;
    JLabel textError;
    JButton send;
    singUpInterface singUpInterface = new singUpInterface();
    Connection conn = singUpInterface.getConnection();
    String addRole, addRoleRightsCashier, addRoleAdmin, addRoleRightsAdmin;

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
        String[] menuItems = {"", "Працівник", "Валюти", "Послуга", "Курс обміну", "Тариф",
                "Рахунок", "Зміна", "Сума в касі", "Присутність", "Валюта та курс обміну", "Валюти та послуги"};
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









        //start_temporarily_add_data_for_employee!!!!!!!!!!!!!
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
        //textError
        textError = new JLabel("");
        textError.setFont(new Font("Arial", Font.PLAIN, 18));
        textError.setForeground(Color.decode("#000000"));
        textError.setBounds(180, 210,230,20);
        //end_temporarily_add_data_for_employee!!!!!!!!!!!!!









        //event_comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedText = (String) comboBox.getSelectedItem();
                deleteComponents();
                if ("Працівник".equals(selectedText)) {
                    JButton button1 = new JButton("Кнопка 1");
                    panel.add(button1);
                }
                else if ("Валюти".equals(selectedText)) {}
                else if ("Послуга".equals(selectedText)) {}
                else if ("Курс обміну".equals(selectedText)) {}
                else if ("Тариф".equals(selectedText)) {}
                else if ("Рахунок".equals(selectedText)) {}
                else if ("Зміна".equals(selectedText)) {}
                else if ("Сума в касі".equals(selectedText)) {}
                else if ("Присутність".equals(selectedText)) {}
                else if ("Валюта та курс обміну".equals(selectedText)) {}
                else if ("Валюти та послуги".equals(selectedText)) {}
                panel.revalidate(); // Mетод используется для того, чтобы сообщить менеджеру компоновки о том, что произошли изменения
                panel.repaint(); // Mетод запрашивает перерисовку компонента и его дочерних элементов
            }
        });






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
        panel.add(send); //delete
        panel.add(name_employee); //delete
        panel.add(telephone_employee); //delete
        panel.add(gmail_employee); //delete
        panel.add(login_employee); //delete
        panel.add(password_employee); //delete
        panel.add(selectRoleComboBox); //delete
        send.addActionListener(new eventSendDataBase()); //delete

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
                    String name = name_employee.getText().trim();
                    String telephone = telephone_employee.getText().trim();
                    String gmail = gmail_employee.getText().trim();
                    String login = login_employee.getText().trim();
                    String password = password_employee.getText().trim();
                    String role = (String) selectRoleComboBox.getSelectedItem();
                    addDataInDataBase(selectedItem, name, telephone, gmail, login, password, role);
                }
                else if("Валюти".equals(selectedItem)){}
                else if("Послуга".equals(selectedItem)){}
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
                System.out.println("Error: " + ex);
            }
        }
    }
    private void deleteComponents() {
        Component[] components = cardPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.equals(send)) {
                    cardPanel.remove(button);
                }
            }
            else if (component instanceof JComboBox) {
                JComboBox<?> box = (JComboBox<?>) component;
                if (box.equals(selectRoleComboBox)) {
                    cardPanel.remove(box);
                }
            }
            else if (component instanceof JLabel) {
                JLabel jLabel = (JLabel) component;
                if (jLabel.equals(textError)) {
                    cardPanel.remove(jLabel);
                }
            }
            else if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (textField.equals(name_employee)) {
                    cardPanel.remove(textField);
                }
                else if (textField.equals(telephone_employee)) {
                    cardPanel.remove(textField);
                }
                else if (textField.equals(gmail_employee)) {
                    cardPanel.remove(textField);
                }
                else if (textField.equals(login_employee)) {
                    cardPanel.remove(textField);
                }
                else if (textField.equals(password_employee)) {
                    cardPanel.remove(textField);
                }
            }
        }
    }
    public void addDataInDataBase(String selectedItem, String name, String telephone, String gmail, String login, String password, String role){
        try {
            String sqlAddData;
            if ("Працівник".equals(selectedItem))  {
                System.out.println("Працівник 111111"); //delete
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

                Statement statement = conn.createStatement();
                ResultSet resultName = statement.executeQuery(checkDataName);
                Statement statement1 = conn.createStatement();
                ResultSet resultLogin = statement1.executeQuery(checkDataLogin);
                Statement statement2 = conn.createStatement();
                ResultSet resultPassword = statement2.executeQuery(checkDataPassword);
                Statement statement3 = conn.createStatement();
                ResultSet resultTelephone = statement3.executeQuery(checkDataTelephone);

                if (!resultName.next() && !resultLogin.next() && !resultPassword.next() && !resultTelephone.next()) {
                    if("cashier".equals(role)) {
                        statement.executeUpdate(sqlAddData);
                        statement.executeUpdate(addRole);
                        statement.executeUpdate(addRoleRightsCashier);
                        System.out.println("Працівник 111111{11111}"); //delete
                        textError.setText("Add data in Data Base");
                    }
                    else if("admin".equals(role)) {
                        statement.executeUpdate(sqlAddData);
                        statement.executeUpdate(addRole);
                        statement.executeUpdate(addRoleRightsAdmin);
                        System.out.println("Працівник 111111{22222}"); //delete
                        textError.setText("Add data in Data Base");
                    }
                }
                else{
                    if(!resultName.next()){
                        textError.setText("This name where in Database");
                    }
                    if(!resultLogin.next()){
                        textError.setText("This login where in Database");
                    }
                    if(!resultPassword.next()){
                        textError.setText("This password where in Database");
                    }
                    if(!resultTelephone.next()){
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
            else if("Валюти".equals(selectedItem)){}
            else if("Послуга".equals(selectedItem)){}
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
            System.out.println("Errror: " + ex);
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

package src.JInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//file
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException; //error file
//sql
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class singUpInterface extends JFrame {
    JTextField loginInput, passwordInput;
    JLabel textError;
    Connection conn;
    Statement statement;
    public singUpInterface(){
        super("Exchanger currency");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when exit
        super.setBounds(500, 200, 600, 500); //size icon
        Container con = super.getContentPane(); //create blocks(button, input ...)
        con.setBackground(Color.decode("#5FB67D"));

        //panel
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#5FB67D"));

        JLabel header = new JLabel("Sign up".toUpperCase());
        header.setFont(new Font("Arial",  Font.BOLD, 20));
        header.setForeground(Color.black);
        header.setBounds(250, 20,100, 50);

        //login
        JLabel loginText = new JLabel("Login:");
        loginText.setFont(new Font("Arial", Font.PLAIN, 18));
        loginText.setForeground(Color.black);
        loginText.setBounds(190, 80,50,20);

        loginInput = new JTextField("");
        loginInput.setFont(new Font("Arial", Font.PLAIN, 18));
        loginInput.setForeground(Color.black);
        loginInput.setBounds(250, 80,120,20);
        //password
        JLabel passwordText = new JLabel("Password:");
        passwordText.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordText.setForeground(Color.black);
        passwordText.setBounds(190, 120,100,20);

        passwordInput = new JTextField("");
        passwordInput.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordInput.setForeground(Color.black);
        passwordInput.setBounds(280, 120,90,20);
        //button
        JButton send = new JButton("Send");
        send.setFont(new Font("Arial", Font.PLAIN, 18));
        send.setForeground(Color.BLACK); //color text
        send.setBackground(Color.decode("#228845"));  //color background
        send.setBorder(BorderFactory.createLineBorder(Color.decode("#228845"), 2));
        send.setFocusPainted(false);
        send.setBounds(240, 160,80,30);
        //textError
        textError = new JLabel("");
        textError.setFont(new Font("Arial", Font.PLAIN, 18));
        textError.setForeground(Color.decode("#B10000"));
        textError.setBounds(190, 210,250,20);

        //call blocks to j_frame
        panel.add(header);
        panel.add(loginText);
        panel.add(loginInput);
        panel.add(passwordText);
        panel.add(passwordInput);
        panel.add(textError);
        panel.add(send);
        con.add(panel);
        send.addActionListener(new eventSingIn()); //add event to button
    }
    class eventSingIn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String login = loginInput.getText();
            String password = passwordInput.getText();
            try{
                workWithFile(login, password);
            }
            catch (Exception ex){
                System.out.println("Error2: " + ex + "\n");
            }
        }
    }


    public void workWithFile(String username, String password) throws IOException{
        String url = "jdbc:postgresql://localhost:5432/postgres";
        //work with file
        String linkService = "src/cache/cache_29052024.txt";
        File serviceFile = new File(linkService);
        BufferedReader headerFileReadCache = new BufferedReader(new FileReader(serviceFile));
        BufferedWriter headerFileWriteCache = new BufferedWriter(new FileWriter(serviceFile, true));
        String searchRole = "SELECT table_schema, table_name, privilege_type FROM information_schema.table_privileges" +
        "WHERE grantee = \'" + username + "\' AND table_name = \'employee\';";


        /*try {*/
            String line;
            if (headerFileReadCache.readLine() != null && serviceFile.exists()) {
                headerFileReadCache.close();
                BufferedReader fileReadCache = new BufferedReader(new FileReader(serviceFile));
                boolean addNewLoginPassword = false;
                while ((line = fileReadCache.readLine()) != null) {
                    String[] credentials = line.split(",");
                    String user = credentials[0];
                    String pass = credentials[1];
                    if (user.equals(username) && pass.equals(password)) {
                        conn = DriverManager.getConnection(url, username, password);
                        textError.setText("Connection successful!");
                        System.out.println("Connection successful! 11111"); //delete
                        openInterface.closeForm();
                        addNewLoginPassword = true;

                        statement = conn.createStatement();
                        boolean resultSet = statement.execute(searchRole);

                        if(resultSet == false) {
                            cashierInterface cashierFrame = new cashierInterface();
                            cashierFrame.setVisible(true);
                        }
                        else if(resultSet == true) {
                            adminInterface adminFrame = new adminInterface();
                            adminFrame.setVisible(true);
                        }
                        break;
                    }
                    else if (!user.equals(username) && pass.equals(password)) {
                        textError.setText("Not correct login");
                        System.out.println("Not correct login"); //delete
                        break;
                    }
                    else if (user.equals(username) && !pass.equals(password)) {
                        textError.setText("Not correct password");
                        System.out.println("Not correct password"); //delete
                        break;
                    }
                }
                if (!addNewLoginPassword) {
                    DriverManager.getConnection(url, username, password);
                    textError.setText("Connection successful!");
                    System.out.println("Connection successful! 22222"); //delete
                    headerFileWriteCache.write(username + "," + password + "\n");
                    openInterface.closeForm();

                    statement = conn.createStatement();
                    boolean resultSet = statement.execute(searchRole);

                    if(resultSet == false) {
                        cashierInterface cashierFrame = new cashierInterface();
                        cashierFrame.setVisible(true);
                    }
                    else if(resultSet == true) {
                        adminInterface adminFrame = new adminInterface();
                        adminFrame.setVisible(true);
                    }
                }
            }
            else if (headerFileReadCache.readLine() == null) {
                if(!serviceFile.exists()){
                    serviceFile.createNewFile();
                    System.out.println("Create file serviceFile"); //delete
                }
                DriverManager.getConnection(url, username, password);
                headerFileWriteCache.write(username + "," + password + "\n");
                textError.setText("Connection successful!");
                System.out.println("Connection successful! 33333"); //delete
                openInterface.closeForm();
                cashierInterface secondFrame = new cashierInterface();
                secondFrame.setVisible(true);
            }
        /*}*/
        /*catch (Exception ex){
            System.out.println("Error1: " + ex + "\n");
        }*/
        headerFileReadCache.close();
        headerFileWriteCache.close();
    }

    public Statement getStatement() {
        return statement;
    }
}

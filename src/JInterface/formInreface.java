package src.JInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//sql
import java.sql.Connection;
import java.sql.DriverManager;

public class formInreface extends JFrame{
    JTextField loginInput, passwordInput;
    JLabel textError;
    public formInreface(){
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
        header.setBounds(250, 20,100,40);

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
    class eventSingIn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String login = loginInput.getText();
            String password = passwordInput.getText();

            try{
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user = "postgres";
                String pass = "sa";
                if(user.equals(login) && pass.equals(password)) {
                    Connection con = DriverManager.getConnection(url, user, pass);
                    System.out.println("Connection successful!"); //delete
                    textError.setText("Connection successful!");
                    baseInterface secondFrame = new baseInterface();
                    secondFrame.setVisible(true);
                    startInterface.closeForm();
                }
                else if(!user.equals(login) && !pass.equals(password)){
                    System.out.println("Not correct login and password"); //delete
                    textError.setText("Not correct login and password");
                }
                else if(!user.equals(login)){
                    System.out.println("Not correct login"); //delete
                    textError.setText("Not correct login");
                }
                else if(!pass.equals(password)){
                    System.out.println("Not correct password"); //delete
                    textError.setText("Not correct password");
                }
            }
            catch (Exception ex){
                System.out.println("Error: " + ex);
            }
        }
    }
}

package src.JInterface;

//j_frame
import javax.swing.*;
import java.awt.*;
//sql

public class adminInterface extends JFrame{
    public adminInterface() {
        super("ADMIN");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when exit
        super.setBounds(500, 200, 600, 500); //size icon

        /*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            System.err.println("Полноэкранный режим не поддерживается");
            setSize(100, 100); //Пример размера, если полноэкранный режим не поддерживается
            setVisible(true);
        }*/

        Container con = super.getContentPane(); //create blocks(button, input ...)
        con.setBackground(Color.decode("#5FB67D"));

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#5FB67D"));

        JLabel header = new JLabel("Menu admin".toUpperCase());
        header.setFont(new Font("Arial",  Font.BOLD, 20));
        header.setForeground(Color.black);
        header.setBounds(250, 20,100,40);
    }
}

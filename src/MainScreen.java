import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {
    JPanel Panel = new JPanel();

    MainScreen(){
        //Properties for JFrame
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(600, 400));
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Phone Card Top Up System");

        //Properties for Panel
        Panel.setLayout(null);
        Panel.setSize(600, 400);
        Panel.setBackground(Color.WHITE);
        Panel.setVisible(true);

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Main Menu");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(225, 20, 150, 40);

        //Properties for Administration Button
        JButton AdministrationButton = new JButton("Administration");
        AdministrationButton.setBounds(200, 90, 200, 80);
        AdministrationButton.setFocusPainted(false);
        AdministrationButton.setFocusable(false);
        AdministrationButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Customer Button
        JButton CustomerButton = new JButton("Customer");
        CustomerButton.setBounds(200, 175, 200, 80);
        CustomerButton.setFocusPainted(false);
        CustomerButton.setFocusable(false);
        CustomerButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Customer Button
        JButton ExitButton = new JButton("Exit");
        ExitButton.setBounds(200, 260, 200, 80);
        ExitButton.setFocusPainted(false);
        ExitButton.setFocusable(false);
        ExitButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(CustomerButton);
        Panel.add(AdministrationButton);
        Panel.add(ExitButton);

        pack();
        add(Panel);//Add Panel to JFrame

        //Action when CustomerButton is Pressed
        CustomerButton.addActionListener((ActionEvent e) -> {
            System.out.println("Customer Button Pressed");

            //Remove current contents and replace with new menu
            Panel.removeAll();
            Panel.add(new Customer().GetComponent());
            Panel.validate();
            Panel.repaint();
        });

        //Action when AdminButton is Pressed
        AdministrationButton.addActionListener((ActionEvent e) -> {
            System.out.println("Admin Button Pressed");

            //Remove current contents and replace with new menu
            Panel.removeAll();
            Panel.add(new Administration().GetComponent());
            Panel.validate();
            Panel.repaint();
        });

        //Action when AdminButton is Pressed
        ExitButton.addActionListener((ActionEvent e) -> {
            System.out.println("Exit Button Pressed");

            int selection = JOptionPane.showConfirmDialog(this, "Do you want to continue?",
                    "Exit prompt", JOptionPane.YES_NO_OPTION);

            boolean isYes = (selection == JOptionPane.YES_NO_OPTION);

            if (isYes) {
                System.out.println("Program Exiting...");
                System.exit(0);
            }else{
                System.out.println("Exit Cancelled.");
            }
        });
    }

}

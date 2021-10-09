import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Malik Heron 2001158
 * Sydney Chambers 2005734
 * David White 2001610
 * Monique Bennett 2004188
 */
public class Administration {

    private final JPanel Panel = new JPanel();
    private String Provider;
    private String Password;

    //Default Constructor
    Administration() {
        //Initialize variables
        Provider = "";
        Password = "";

        //Properties for Panel
        Panel.setLayout(null);
        Panel.setSize(600, 400);
        Panel.setOpaque(false);
        Panel.setVisible(true);

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Administration Menu");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(160, 20, 300, 40);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Digicel Button
        JButton DigicelButton = new JButton("Digicel Login");
        DigicelButton.setBounds(200, 155, 200, 80);
        DigicelButton.setFocusPainted(false);
        DigicelButton.setFocusable(false);
        DigicelButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Flow Button
        JButton FlowButton = new JButton("Flow Login");
        FlowButton.setBounds(200, 70, 200, 80);
        FlowButton.setFocusPainted(false);
        FlowButton.setFocusable(false);
        FlowButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Total Number Of Customers Button
        JButton TotalNumberOfCustomersButton = new JButton("Total Number Of Customers");
        TotalNumberOfCustomersButton.setBounds(140, 240, 320, 80);
        TotalNumberOfCustomersButton.setFocusPainted(false);
        TotalNumberOfCustomersButton.setFocusable(false);
        TotalNumberOfCustomersButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(BackButton);
        Panel.add(FlowButton);
        Panel.add(DigicelButton);
        Panel.add(TotalNumberOfCustomersButton);

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new MainScreen().Panel);//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when DigicelButton is Pressed
        DigicelButton.addActionListener((ActionEvent e) -> {
            System.out.println("Digicel Button Pressed");
            SetProvider("Digicel");
            String Pass = JOptionPane.showInputDialog(Panel, null, "Password",
                    JOptionPane.INFORMATION_MESSAGE);
            SetPassword(Pass);

            if (GetPassword().equals("TheBiggerBetterNetwork2021") && GetProvider().equals("Digicel")) {
                System.out.println("Logged In");

                //Remove previous display
                Panel.removeAll();
                Panel.add(new Digicel().GetComponent());//Recreate Main menu by calling method of class Digicel
                Panel.validate();
                Panel.repaint();
            } else {
                System.err.println("Incorrect Password.");
                JOptionPane.showMessageDialog(Panel, "Incorrect Password", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        //Action when FlowButton is Pressed
        FlowButton.addActionListener((ActionEvent e) -> {
            System.out.println("FLow Button Pressed");
            SetProvider("Flow");
            String Pass = JOptionPane.showInputDialog(Panel, null, "Password",
                    JOptionPane.INFORMATION_MESSAGE);
            SetPassword(Pass);

            if (GetPassword().equals("TheWayIFlow2021") && GetProvider().equals("Flow")) {
                System.out.println("Logged In");

                //Remove previous display
                Panel.removeAll();
                Panel.add(new Flow().GetComponent());//Recreate Main menu by calling method of class Flow
                Panel.validate();
                Panel.repaint();
            } else {
                System.err.println("Incorrect Password.");
                JOptionPane.showMessageDialog(Panel, "Incorrect Password", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        //Action when TotalNumberOfCustomersButton is Pressed
        TotalNumberOfCustomersButton.addActionListener((ActionEvent e) -> {
            System.out.println("Total Number Of Customers Button Pressed");
            DisplayTotalNumberOfCustomers();
        });
    }

    //Initialization of Getters
    public String GetProvider() {
        return Provider;
    }

    public String GetPassword() {
        return Password;
    }

    //Initialization of Setters
    public void SetProvider(String Provider) {
        this.Provider = Provider;
    }

    public void SetPassword(String Password) {
        this.Password = Password;
    }

    //Get and Display Total Number of Customers for all Service Providers
    public void DisplayTotalNumberOfCustomers() {
        //Count Number of Customer for Digicel
        int DigicelTotal = Digicel.GetNumberOfCustomers();

        //Count Number of Customers for Flow
        int FlowTotal = Flow.GetNumberOfCustomers();

        //Add Totals
        int Total = (DigicelTotal + FlowTotal);

        System.out.println("Total Number of Customers: " + Total);
        JOptionPane.showMessageDialog(Panel, "Total Number of Customers is " + Total, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    //Return JPanel
    public JComponent GetComponent() {
        return Panel;
    }

}

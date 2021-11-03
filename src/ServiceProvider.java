import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Malik Heron 2001158
 * Sydney Chambers 2005734
 * David Walker 2001610
 * Monique Bennett 2004188
 */
abstract class ServiceProvider {

    protected JPanel Panel = new JPanel();
    protected ImageIcon Icon = new ImageIcon("images/topup_icon.png");
    private final int[] Denominations;
    protected int[] Prefix;
    private String CardNumber;
    private String Denomination;
    private String Status;
    protected static int NumberOfCustomers;
    protected String CompanyID;
    protected String CompanyAddress;
    Customer Customer = new Customer();

    //Default Constructor
    ServiceProvider() {
        Prefix = new int[]{0, 0, 0, 0};
        CardNumber = "";
        Denominations = new int[]{100, 200, 500, 1000};
        Denomination = "";
        Status = "";

        //Properties for Panel
        Panel.setLayout(null);
        Panel.setSize(600, 400);
        Panel.setOpaque(false);
        Panel.setVisible(true);

        //Properties for Display Company Info Button
        JButton DisplayCompanyInfoButton = new JButton("Display Company Information");
        DisplayCompanyInfoButton.setBounds(10, 180, 270, 30);
        DisplayCompanyInfoButton.setFocusPainted(false);
        DisplayCompanyInfoButton.setFocusable(false);
        DisplayCompanyInfoButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Add Customer Button
        JButton AddCustomerButton = new JButton("Add Customer");
        AddCustomerButton.setBounds(10, 230, 270, 30);
        AddCustomerButton.setFocusPainted(false);
        AddCustomerButton.setFocusable(false);
        AddCustomerButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for View Customer Base Button
        JButton ViewCustomerBaseButton = new JButton("View Customer Base");
        ViewCustomerBaseButton.setBounds(10, 280, 270, 30);
        ViewCustomerBaseButton.setFocusPainted(false);
        ViewCustomerBaseButton.setFocusable(false);
        ViewCustomerBaseButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Phone Credit Creation Button
        JButton CreatePhoneCreditButton = new JButton("Create Phone Credit");
        CreatePhoneCreditButton.setBounds(300, 180, 270, 30);
        CreatePhoneCreditButton.setFocusPainted(false);
        CreatePhoneCreditButton.setFocusable(false);
        CreatePhoneCreditButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Phone Credit Creation Button
        JButton ViewAllPhoneCreditButton = new JButton("View All Phone Credit");
        ViewAllPhoneCreditButton.setBounds(300, 230, 270, 30);
        ViewAllPhoneCreditButton.setFocusPainted(false);
        ViewAllPhoneCreditButton.setFocusable(false);
        ViewAllPhoneCreditButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Sign Out Button
        JButton SignOutButton = new JButton("Sign Out");
        SignOutButton.setBounds(300, 280, 270, 30);
        SignOutButton.setFocusPainted(false);
        SignOutButton.setFocusable(false);
        SignOutButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Add components to Panel
        Panel.add(DisplayCompanyInfoButton);
        Panel.add(AddCustomerButton);
        Panel.add(ViewCustomerBaseButton);
        Panel.add(CreatePhoneCreditButton);
        Panel.add(ViewAllPhoneCreditButton);
        Panel.add(SignOutButton);

        //Action when SignOutButton is Pressed
        SignOutButton.addActionListener((ActionEvent e) -> {
            System.out.println("Sign Out Button Pressed");

            int selection = JOptionPane.showConfirmDialog(Panel, "Do you want to continue?",
                    "Sign Out Prompt", JOptionPane.YES_NO_OPTION);

            boolean isYes = (selection == JOptionPane.YES_NO_OPTION);

            if (isYes) {
                //Remove previous display
                Panel.removeAll();
                Panel.add(new Administration().GetComponent());//Recreate Main menu
                Panel.validate();
                Panel.repaint();
            } else {
                System.out.println("Sign Out Cancelled.");
            }
        });

        //Action when DisplayCompanyInfoButton is Pressed
        DisplayCompanyInfoButton.addActionListener((ActionEvent e) -> {
            System.out.println("Display Company Info Button Pressed");
            DisplayCompanyInformation();
        });

        //Action when AddCustomerButton is Pressed
        AddCustomerButton.addActionListener((ActionEvent e) -> {
            System.out.println("Add Customer Button Pressed");
            AddCustomer();
        });

        //Action when ViewCustomerBaseButton is Pressed
        ViewCustomerBaseButton.addActionListener((ActionEvent e) -> {
            System.out.println("View Customer Base Button Pressed");
            ViewCustomerBase();
        });

        //Action when CreatePhoneCreditButton is Pressed
        CreatePhoneCreditButton.addActionListener((ActionEvent e) -> {
            System.out.println("Create Phone Credit Button Pressed");
            CreatePhoneCredit();
        });

        //Action when ViewAllPhoneCreditButton is Pressed
        ViewAllPhoneCreditButton.addActionListener((ActionEvent e) -> {
            System.out.println("View All Phone Credit Button Pressed");
            ViewAllPhoneCredit();
        });
    }

    //Initialization of Getters
    protected String GetCardNumber() {
        return CardNumber;
    }

    protected int[] GetDenominations() {
        return Denominations;
    }

    protected String GetDenomination() {
        return Denomination;
    }

    protected String GetStatus() {
        return Status;
    }

    protected String GetCompanyID() {
        return CompanyID;
    }

    protected String GetCompanyAddress() {
        return CompanyAddress;
    }

    //Initialization of Setters
    protected void SetCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    protected void SetDenomination(String Denomination) {
        this.Denomination = Denomination;
    }

    protected void SetStatus() {
        this.Status = "Available";
    }

    //Abstract Functions ----------

    //Getter for specific service provider
    abstract protected int[] GetPrefix();

    //Get and Display Company Information
    abstract protected void DisplayCompanyInformation();

    //Add a customer for specific service provider
    abstract protected void AddCustomer();

    //View all customer with all their information
    abstract protected void ViewCustomerBase();

    //Credit new phone credit with available status
    abstract protected void CreatePhoneCredit();

    //View all credit with their information
    abstract protected void ViewAllPhoneCredit();

    //Return JPanel
    public JComponent GetComponent() {
        return Panel;
    }
}

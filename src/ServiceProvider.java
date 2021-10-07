import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

abstract class ServiceProvider {
    protected JPanel Panel = new JPanel();
    protected int[] Prefix;
    private int TRN;
    private String LName;
    private String Address;
    private int TelephoneNum;
    private double Credit_Bal;

    ServiceProvider(){
        TRN = 0;
        LName = "";
        Address = "";
        TelephoneNum = 0;
        Credit_Bal = 0.00;
        Prefix = new int[]{0, 0, 0, 0};

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
        JButton CreatePhoneCreditButton = new JButton("Phone Credit Creation");
        CreatePhoneCreditButton.setBounds(300, 180, 270, 30);
        CreatePhoneCreditButton.setFocusPainted(false);
        CreatePhoneCreditButton.setFocusable(false);
        CreatePhoneCreditButton.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Phone Credit Creation Button
        JButton ViewAllPhoneCreditButton = new JButton("View Phone Credit");
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
                    "Sign Out prompt", JOptionPane.YES_NO_OPTION);

            boolean isYes = (selection == JOptionPane.YES_NO_OPTION);

            if (isYes) {
                //Remove previous display
                Panel.removeAll();
                Panel.add(new Administration().GetComponent());//Recreate Main menu
                Panel.validate();
                Panel.repaint();
            }else{
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

    protected int GetTRN(){
        return TRN;
    }

    protected String GetLastName(){
        return LName;
    }

    protected String GetAddress(){
        return Address;
    }

    protected int GetTelephoneNumber() {
        return TelephoneNum;
    }

    protected double GetCreditBalance(){
        return Credit_Bal;
    }

    protected void SetTRN(int TRN){
        this.TRN = TRN;
    }

    protected void SetLastName(String LName){
        this.LName = LName;
    }

    protected void SetAddress(String Address){
        this.Address = Address;
    }

    protected void SetTelephoneNumber(int TelephoneNum) {
        this.TelephoneNum = TelephoneNum;
    }

    protected void SetCreditBalance(double Credit_Bal){
        this.Credit_Bal = Credit_Bal;
    }

    abstract protected int[] GetPrefix();

    abstract protected void DisplayCompanyInformation();

    abstract protected void AddCustomer();

    abstract protected void ViewCustomerBase();

    abstract protected void CreatePhoneCredit();

    abstract protected void ViewAllPhoneCredit();

    public JComponent GetComponent() {
        return Panel;
    }
}

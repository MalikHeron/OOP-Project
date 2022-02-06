import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Malik Heron 2001158
 * Sydney Chambers 2005734
 * David Walker 2001610
 * Monique Bennett 2004188
 */
public class Flow extends ServiceProvider {

    private final String ParentCompanyName;

    //Default Constructor
    Flow() {
        super();

        //Initialize variables
        CompanyID = "Flow";
        ParentCompanyName = "Liberty Latin";
        CompanyAddress = "Flow Jamaica Headquarters, 2-6 Carlton Cres, Kingston";
        NumberOfCustomers = 0;
        Prefix = new int[]{601, 602, 603, 604};

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel(new ImageIcon("images/flow_logo.png"));
        MenuLabel.setBounds(50, 0, 500, 170);

        Panel.add(MenuLabel);
    }

    //Initialization of Getters
    public String GetParentCompanyName(){
        return ParentCompanyName;
    }

    public static int GetNumberOfCustomers() {
        //Open file
        File FlowCustomerFile = new File("files/Flow_Customers.txt");

        int Records = 0;
        int Count = 0;

        /*Read file line by line (One customer takes up 3 lines in a file
        so whenever count = 3 then record increases by 1
         */
        try (BufferedReader Reader = new BufferedReader(new FileReader(FlowCustomerFile))) {
            while (Reader.readLine() != null) {
                Count++;
                if (Count == 3) {
                    Records++;//Count records in file
                    Count = 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error Reading " + FlowCustomerFile);
            e.printStackTrace();
        }

        //Set Number of Customers to value of Records
        NumberOfCustomers = Records;

        return NumberOfCustomers;
    }

    public int[] GetPrefix() {
        return Prefix;
    }

    //Get and Display Company Information
    public void DisplayCompanyInformation() {
        System.out.println("Company ID: " + GetCompanyID());
        System.out.println("Parent Company Name: "+ GetParentCompanyName());
        System.out.println("Address: " + GetCompanyAddress());
        System.out.println("Number of Customers: " + GetNumberOfCustomers());

        JOptionPane.showMessageDialog(Panel, "Company ID: " + GetCompanyID() +
                        "\nParent Company Name: "+ GetParentCompanyName()+ "\nAddress: " + GetCompanyAddress() +
                            "\nNumber of Customers: " + GetNumberOfCustomers(),
                            "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    //Add a customer for service provider flow
    public void AddCustomer() {
        System.out.println("Flow Add Customer Button Pressed");

        //Remove previous display
        Panel.removeAll();

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Add Customer");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(210, 20, 250, 40);

        //Properties for TRN Label
        JLabel TRNLabel = new JLabel("TRN");
        TRNLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        TRNLabel.setBounds(10, 70, 250, 30);

        //Properties for Last Name Label
        JLabel LastNameLabel = new JLabel("Last Name");
        LastNameLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        LastNameLabel.setBounds(10, 120, 250, 30);

        //Properties for Address Label
        JLabel AddressLabel = new JLabel("Address");
        AddressLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        AddressLabel.setBounds(10, 170, 250, 30);

        //Properties for Phone Number Label
        JLabel PhoneNumberLabel = new JLabel("Phone Number");
        PhoneNumberLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        PhoneNumberLabel.setBounds(10, 220, 250, 30);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for TRN Field
        JTextField TRNField = new JTextField() {
            //Overrides addNotify
            public void addNotify() {
                super.addNotify();
                requestFocus();//Focus on this when displayed
            }
        };

        TRNField.setBounds(150, 70, 310, 40);
        TRNField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Last Name Field
        JTextField LastNameField = new JTextField();
        LastNameField.setBounds(150, 120, 310, 40);
        LastNameField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Address Field
        JTextField AddressField = new JTextField();
        AddressField.setBounds(150, 170, 310, 40);
        AddressField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Phone Number Field
        JTextField PhoneNumberField = new JTextField();
        PhoneNumberField.setBounds(150, 220, 310, 40);
        PhoneNumberField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Process Button
        JButton ProcessButton = new JButton("Process");
        ProcessButton.setBounds(200, 280, 200, 50);
        ProcessButton.setFocusPainted(false);
        ProcessButton.setFocusable(false);
        ProcessButton.setFont(new Font("times new roman", Font.PLAIN, 20));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(TRNLabel);
        Panel.add(LastNameLabel);
        Panel.add(AddressLabel);
        Panel.add(PhoneNumberLabel);
        Panel.add(TRNField);
        Panel.add(LastNameField);
        Panel.add(AddressField);
        Panel.add(PhoneNumberField);
        Panel.add(BackButton);
        Panel.add(ProcessButton);

        //Show Panel
        Panel.validate();
        Panel.repaint();

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new Flow().GetComponent());//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when ProcessButton is Pressed
        ProcessButton.addActionListener((ActionEvent e) -> {
            Verification Check = new Verification();

            //Get values from TextFields
            String TRN = TRNField.getText();
            String LastName = LastNameField.getText();
            String Address = AddressField.getText();
            String PhoneNumber = PhoneNumberField.getText();

            System.out.println("Process Button Pressed");

            if (Check.VerifyFieldLengths(TRN, LastName, Address, PhoneNumber)) {
                int Valid = Check.VerifyFields(TRN, LastName, PhoneNumber, GetPrefix(), GetCompanyID());

                if (Valid == 0) {
                    System.err.println("Invalid Length for TRN");
                    JOptionPane.showMessageDialog(Panel, "Invalid Length for TRN",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -1) {
                    System.err.println("TRN is not numerical");
                    JOptionPane.showMessageDialog(Panel, "TRN is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 1) {
                    System.err.println("Invalid number of digits for Phone Number");
                    JOptionPane.showMessageDialog(Panel, "Invalid number of digits for Phone Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -2) {
                    System.err.println("Phone Number is not numerical");
                    JOptionPane.showMessageDialog(Panel, "Phone Number is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -3) {
                    System.err.println("Last Name is not only Letters");
                    JOptionPane.showMessageDialog(Panel, "Last Name is not only Letters",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 3) {
                    System.err.println("Invalid Area Code");
                    JOptionPane.showMessageDialog(Panel, "Invalid Area Code",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 4) {
                    System.err.println("Invalid Prefix for Phone Number");
                    JOptionPane.showMessageDialog(Panel, "Invalid Prefix for Phone Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -4) {
                    System.err.println("Phone Number already exists");
                    JOptionPane.showMessageDialog(Panel, "Phone Number already exists",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 5) {
                    //Set Values
                    Customer.SetTRN(TRN);
                    Customer.SetLastName(LastName);
                    Customer.SetAddress(Address);
                    Customer.SetTelephoneNumber(PhoneNumber);
                    Customer.SetCreditBalance();

                    try {
                        System.out.println("Writing to File...");

                        //Open file in append mode
                        FileWriter Writer = new FileWriter("files/Flow_Customers.txt", true);
                        Writer.write(Customer.GetTRN() + " ");
                        Writer.write(Customer.GetLastName() + "\n");
                        Writer.write(Customer.GetAddress() + "\n");
                        Writer.write(Customer.GetTelephoneNumber() + " ");
                        Writer.write(Customer.GetCreditBalance() + "\n");
                        Writer.close();

                        System.out.println("Customer Information Saved");
                        JOptionPane.showMessageDialog(Panel, "Customer Added", "Information",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Resets Fields
                        TRNField.setText("");
                        LastNameField.setText("");
                        AddressField.setText("");
                        PhoneNumberField.setText("");
                    } catch (Exception ex) {
                        System.err.println("Could not Save Customer Information");
                        JOptionPane.showMessageDialog(Panel, "Could not Save Customer Information",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            } else {
                System.err.println("One or more fields are empty");
                JOptionPane.showMessageDialog(Panel, "One or more fields are empty",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //View all customer with all their information
    public void ViewCustomerBase() {
        System.out.println("Flow View Customer Base Button Pressed");

        try {
            //Open file
            Scanner Read = new Scanner(new File("files/Flow_Customers.txt"));

            //Column Names for Table
            String[] TableHead = {"TRN", "Last Name", "Address", "Telephone", "Balance"};

            //Properties for table
            DefaultTableModel Model = new DefaultTableModel(TableHead, 0);

            //Used for counting rows
            int Count = 0;

            while (Read.hasNext()) {
                //Insert data from file into rows
                Model.insertRow(Count, new Object[]{Read.next(), Read.nextLine(),
                        Read.nextLine(), Read.next(), Read.next()});
                Count++;
            }

            //Close File
            Read.close();

            //Create new Table
            JTable Table = new JTable(Model);
            Table.setDefaultEditor(Object.class, null);//Set to not editable
            Table.setAutoCreateRowSorter(true);//Enable sorting by columns
            Table.setVisible(true);
            Table.setOpaque(false);

            //Create new window
            JFrame Frame = new JFrame();
            Frame.setLayout(new GridLayout());
            Frame.setLocationRelativeTo(Panel);
            Frame.setVisible(true);
            Frame.setSize(600, 400);
            Frame.setMinimumSize(new Dimension(600, 400));
            Frame.setTitle("All Customers");
            Frame.setIconImage(Icon.getImage());
            Frame.pack();

            //Add table to window
            Frame.add(new JScrollPane(Table));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Credit new phone credit with available status
    public void CreatePhoneCredit() {
        System.out.println("Flow Phone Credit Creation Button Pressed");

        //Remove previous display
        Panel.removeAll();

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Create Phone Credit");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(165, 20, 270, 40);

        //Properties for Card Number Label
        JLabel CardNumberLabel = new JLabel("Card Number");
        CardNumberLabel.setFont(new Font("times new roman", Font.BOLD, 22));
        CardNumberLabel.setBounds(10, 120, 250, 30);

        //Properties for Denomination Label
        JLabel DenominationLabel = new JLabel("Denomination");
        DenominationLabel.setFont(new Font("times new roman", Font.BOLD, 22));
        DenominationLabel.setBounds(10, 170, 250, 30);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Card Number Field
        JTextField CardNumberField = new JTextField() {
            //Overrides addNotify
            public void addNotify() {
                super.addNotify();
                requestFocus();//Focus on this when displayed
            }
        };

        CardNumberField.setBounds(150, 120, 310, 40);
        CardNumberField.setFont(new Font("times new roman", Font.PLAIN, 22));

        //Properties for Denomination Field
        JTextField DenominationField = new JTextField();
        DenominationField.setBounds(150, 170, 310, 40);
        DenominationField.setFont(new Font("times new roman", Font.PLAIN, 22));

        //Properties for Process Button
        JButton ProcessButton = new JButton("Process");
        ProcessButton.setBounds(200, 280, 200, 50);
        ProcessButton.setFocusPainted(false);
        ProcessButton.setFocusable(false);
        ProcessButton.setFont(new Font("times new roman", Font.PLAIN, 22));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(CardNumberLabel);
        Panel.add(DenominationLabel);
        Panel.add(CardNumberField);
        Panel.add(DenominationField);
        Panel.add(BackButton);
        Panel.add(ProcessButton);

        //Show Panel
        Panel.validate();
        Panel.repaint();

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new Flow().GetComponent());//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when ProcessButton is Pressed
        ProcessButton.addActionListener((ActionEvent e) -> {
            Verification Check = new Verification();

            //Get values from TextFields
            String CardNum = CardNumberField.getText();
            String Denom = DenominationField.getText();

            System.out.println("Process Button Pressed");

            if (Check.VerifyFieldLengths(CardNum, Denom)) {
                int Valid = Check.VerifyFields(CardNum, Denom, CompanyID, GetDenominations());

                if (Valid == 0) {
                    System.err.println("Invalid Length for Card Number");
                    JOptionPane.showMessageDialog(Panel, "Invalid Length for Card Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -1) {
                    System.err.println("Card Number is not numerical");
                    JOptionPane.showMessageDialog(Panel, "Card Number is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -2) {
                    System.err.println("Denomination is not numerical");
                    JOptionPane.showMessageDialog(Panel, "Denomination is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 1) {
                    System.err.println("Invalid number of digits for Denomination");
                    JOptionPane.showMessageDialog(Panel, "Invalid number of digits for Denomination",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 2) {
                    System.err.println("Invalid Denomination for Card Number");
                    JOptionPane.showMessageDialog(Panel, "Invalid Denomination for Card Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -3) {
                    System.err.println("Card Number already exists");
                    JOptionPane.showMessageDialog(Panel, "Card Number already exists",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 3) {
                    //Set values
                    SetCardNumber(CardNum);
                    SetDenomination(Denom);
                    SetStatus();

                    try {
                        System.out.println("Writing to File...");

                        //Open file in append mode
                        FileWriter Writer = new FileWriter("files/Flow_CardInformation.txt", true);
                        Writer.write(GetCardNumber() + " ");
                        Writer.write(GetDenomination() + " ");
                        Writer.write(GetStatus() + "\n");
                        Writer.close();

                        System.out.println("Card Information Saved");
                        JOptionPane.showMessageDialog(Panel, "Card Added", "Information",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Reset Fields
                        CardNumberField.setText("");
                        DenominationField.setText("");
                    } catch (Exception ex) {
                        System.err.println("Could not Save Card Information");
                        JOptionPane.showMessageDialog(Panel, "Could not Save Card Information",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            } else {
                System.err.println("One or more fields are empty");
                JOptionPane.showMessageDialog(Panel, "One or more fields are empty",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //View all credit with their information
    public void ViewAllPhoneCredit() {
        System.out.println("Flow View Phone Credit Button Pressed");

        try {
            //Open file
            Scanner Read = new Scanner(new File("files/Flow_CardInformation.txt"));

            //Column Names for Table
            String[] TableHead = {"Card Number", "Denomination", "Status"};

            //Properties for table
            DefaultTableModel Model = new DefaultTableModel(TableHead, 0);

            //Used for counting rows
            int Count = 0;

            while (Read.hasNext()) {
                //Insert data from file into rows
                Model.insertRow(Count, new Object[]{Read.next(), Read.next(), Read.next()});
                Count++;
            }

            //Close File
            Read.close();

            //Create new Table
            JTable Table = new JTable(Model);
            Table.setDefaultEditor(Object.class, null);//Set to not editable
            Table.setAutoCreateRowSorter(true);//Enable sorting by columns
            Table.setVisible(true);
            Table.setOpaque(false);

            //Create new window
            JFrame Frame = new JFrame();
            Frame.setLayout(new GridLayout());
            Frame.setLocationRelativeTo(Panel);
            Frame.setVisible(true);
            Frame.setSize(600, 400);
            Frame.setMinimumSize(new Dimension(600, 400));
            Frame.setTitle("All Credit Information");
            Frame.setIconImage(Icon.getImage());
            Frame.pack();

            //Add table to window
            Frame.add(new JScrollPane(Table));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Return JPanel
    public JComponent GetComponent() {
        return Panel;
    }
}

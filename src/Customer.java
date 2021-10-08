import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Customer {

    private final JPanel Panel = new JPanel();
    private String TRN;
    private String LName;
    private String Address;
    private String TelephoneNum;
    private double Credit_Bal;
    private int AreaCode;
    private int Prefix;
    private int SerialNumber;

    //Default Constructor
    Customer() {
        //Initialize variables
        TRN = "";
        LName = "";
        Address = "";
        TelephoneNum = "";
        Credit_Bal = 0.00;
        AreaCode = 0;
        Prefix = 0;
        SerialNumber = 0;

        //Properties for Panel
        Panel.setLayout(null);
        Panel.setSize(600, 400);
        Panel.setOpaque(false);
        Panel.setVisible(true);

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Customer Menu");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(195, 20, 250, 40);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Add Credit Button
        JButton AddCreditButton = new JButton("Add Credit");
        AddCreditButton.setBounds(200, 130, 200, 80);
        AddCreditButton.setFocusPainted(false);
        AddCreditButton.setFocusable(false);
        AddCreditButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Check Balance Button
        JButton CheckBalanceButton = new JButton("Check Balance");
        CheckBalanceButton.setBounds(200, 240, 200, 80);
        CheckBalanceButton.setFocusPainted(false);
        CheckBalanceButton.setFocusable(false);
        CheckBalanceButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(BackButton);
        Panel.add(AddCreditButton);
        Panel.add(CheckBalanceButton);

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new MainScreen().Panel);//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when AddCreditButton is Pressed
        AddCreditButton.addActionListener((ActionEvent e) -> {
            System.out.println("Add Credit Button Pressed");
            AddCredit();
        });

        //Action when CheckBalanceButton is Pressed
        CheckBalanceButton.addActionListener((ActionEvent e) -> {
            System.out.println("Check Balance Button Pressed");
            CheckBalance();
        });
    }

    //Initialization of Getters
    public String GetTRN() {
        return TRN;
    }

    public String GetLastName() {
        return LName;
    }

    public String GetAddress() {
        return Address;
    }

    public String GetTelephoneNumber() {
        return TelephoneNum;
    }

    public double GetCreditBalance() {
        return Credit_Bal;
    }

    private int GetAreaCode() {
        return AreaCode;
    }

    private int GetPrefix() {
        return Prefix;
    }

    private int GetSerialNumber() {
        return SerialNumber;
    }

    //Initialization of Setters
    public void SetTRN(String TRN) {
        this.TRN = TRN;
    }

    public void SetLastName(String LName) {
        this.LName = LName;
    }

    public void SetAddress(String Address) {
        this.Address = Address;
    }

    public void SetTelephoneNumber(String TelephoneNum) {
        this.TelephoneNum = TelephoneNum;
    }

    public void SetCreditBalance() {
        this.Credit_Bal = 100.0;
    }

    private void SetAreaCode(int AreaCode) {
        this.AreaCode = AreaCode;
    }

    private void SetPrefix(int Prefix) {
        this.Prefix = Prefix;
    }

    private void SetSerialNumber(int SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    //Allows Customer to Increase their Credit Balance
    private void AddCredit() {
        //Remove previous display
        Panel.removeAll();

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Add Credit");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(210, 20, 250, 40);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Input Field
        JTextField InputField = new JTextField() {
            //Overrides addNotify
            public void addNotify() {
                super.addNotify();
                requestFocus();//Focus on this when displayed
            }
        };

        InputField.setBounds(150, 90, 310, 40);
        InputField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Process Button
        JButton ProcessButton = new JButton("Process");
        ProcessButton.setBounds(200, 240, 200, 80);
        ProcessButton.setFocusPainted(false);
        ProcessButton.setFocusable(false);
        ProcessButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(BackButton);
        Panel.add(InputField);
        Panel.add(ProcessButton);

        //Show Panel
        Panel.validate();
        Panel.repaint();

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new Customer().GetComponent());//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when ProcessButton is Pressed
        ProcessButton.addActionListener((ActionEvent e) -> {
            Verification Check = new Verification();

            char Asterisk1;
            char Asterisk2;
            char Asterisk3;
            char Pound;
            String Str;

            System.out.println("Process Button Pressed");

            try {
                //Check if asterisks and pound key are in correct position
                Asterisk1 = InputField.getText().charAt(0);
                Asterisk2 = InputField.getText().charAt(4);
                Asterisk3 = InputField.getText().charAt(18);
                Pound = InputField.getText().charAt(29);
                Str = InputField.getText();

                //Validate Input
                if (Asterisk1 == '*' && Asterisk2 == '*' && Asterisk3 == '*' && Pound == '#' && Str.length() == 30) {
                    //Get Info Entered
                    String DialNumber = InputField.getText().substring(1, 4);
                    String CardNumber = InputField.getText().substring(5, 18);

                    //Call Setters
                    SetAreaCode(Integer.parseInt(InputField.getText().substring(19, 22)));
                    SetPrefix(Integer.parseInt(InputField.getText().substring(22, 25)));
                    SetSerialNumber(Integer.parseInt(InputField.getText().substring(25, 29)));

                    //Check if Dial Number is valid for operation
                    boolean Valid = Check.VerifyDialNumber(Integer.parseInt(DialNumber), 1);

                    if (!Valid) {
                        JOptionPane.showMessageDialog(Panel, "Invalid Dial Number",
                                "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        //Create Phone Number String
                        String PhoneNumber = GetAreaCode() + "" + GetPrefix() + "" + GetSerialNumber();

                        //Check if Phone Number Exits
                        int Exists = Check.VerifyPhoneNumber(PhoneNumber, GetPrefix());

                        if (Exists == 1) {
                            //Check if Card Number Exits for Provider Digicel
                            Exists = Check.VerifyCardNumber(CardNumber, "Digicel");

                            if (Exists >= 100) {
                                System.out.println("Topping Up Digicel Number");

                                //add credit to balance
                                double NewBalance = CalculateBalance(GetBalance(PhoneNumber, "Digicel"), CardNumber,
                                        Exists, PhoneNumber, "Digicel");

                                //Display New Balance
                                System.out.println("New Balance: $" + NewBalance);
                                JOptionPane.showMessageDialog(Panel, "$" + NewBalance,
                                        "Balance", JOptionPane.INFORMATION_MESSAGE);
                            } else if (Exists == 2) {
                                System.out.println("Card Number already used.");
                                JOptionPane.showMessageDialog(Panel, "Card Number already used",
                                        "Error", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(Panel, "Invalid Card Number",
                                        "Error", JOptionPane.WARNING_MESSAGE);
                            }

                            //Clear InputField
                            InputField.setText("");
                        } else if (Exists == 2) {
                            //Check if Card Number Exits for Provider Flow
                            Exists = Check.VerifyCardNumber(CardNumber, "Flow");

                            if (Exists >= 100) {
                                System.out.println("Topping Up Flow Number");

                                //add credit to balance
                                double NewBalance = CalculateBalance(GetBalance(PhoneNumber, "Flow"), CardNumber,
                                        Exists, PhoneNumber, "Flow");

                                //Display New Balance
                                System.out.println("New Balance: $" + NewBalance);
                                JOptionPane.showMessageDialog(Panel, "$" + NewBalance,
                                        "Balance", JOptionPane.INFORMATION_MESSAGE);
                            } else if (Exists == 2) {
                                System.out.println("Card Number already used.");
                            } else {
                                JOptionPane.showMessageDialog(Panel, "Invalid Card Number",
                                        "Error", JOptionPane.WARNING_MESSAGE);
                            }

                            //Clear InputField
                            InputField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(Panel, "Phone Number does not belong to a Provider",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    //Output
                    System.out.println("Dial Number: " + DialNumber);
                    System.out.println("Card Number: " + CardNumber);
                    System.out.println("Area Code: " + GetAreaCode());
                    System.out.println("Prefix: " + GetPrefix());
                    System.out.println("Serial Number: " + GetSerialNumber());
                } else {
                    System.err.println("Invalid Format.");
                    JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Invalid Format");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //Allows Customers to Check their Credit Balance
    private void CheckBalance() {
        //Remove previous display
        Panel.removeAll();

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Check Balance");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(200, 20, 200, 40);

        //Properties for Back Button
        JButton BackButton = new JButton("<");
        BackButton.setBounds(10, 20, 50, 30);
        BackButton.setFocusPainted(false);
        BackButton.setFocusable(false);
        BackButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Input Field
        JTextField InputField = new JTextField() {
            //Overrides addNotify
            public void addNotify() {
                super.addNotify();
                requestFocus();//Focus on this when displayed
            }
        };

        InputField.setBounds(150, 90, 310, 40);
        InputField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Process Button
        JButton ProcessButton = new JButton("Process");
        ProcessButton.setBounds(200, 240, 200, 80);
        ProcessButton.setFocusPainted(false);
        ProcessButton.setFocusable(false);
        ProcessButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        Panel.add(MenuLabel);
        Panel.add(BackButton);
        Panel.add(InputField);
        Panel.add(ProcessButton);

        //Show Panel
        Panel.validate();
        Panel.repaint();

        //Action when BackButton is Pressed
        BackButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            Panel.removeAll();
            Panel.add(new Customer().GetComponent());//Recreate Main menu
            Panel.validate();
            Panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when ProcessButton is Pressed
        ProcessButton.addActionListener((ActionEvent e) -> {
            Verification Check = new Verification();

            //Check if asterisks and pound key are in correct position
            char Asterisk1;
            char Asterisk2;
            char Pound;
            String Str;

            System.out.println("Process Button Pressed");

            try {
                //Check if asterisks and pound key are in correct position
                Asterisk1 = InputField.getText().charAt(0);
                Asterisk2 = InputField.getText().charAt(4);
                Pound = InputField.getText().charAt(15);
                Str = InputField.getText();

                //Validate Input
                if (Asterisk1 == '*' && Asterisk2 == '*' && Pound == '#' && Str.length() == 16) {
                    //Get Info Entered
                    String DialNumber = InputField.getText().substring(1, 4);

                    //Call Setters
                    SetAreaCode(Integer.parseInt(InputField.getText().substring(5, 8)));
                    SetPrefix(Integer.parseInt(InputField.getText().substring(8, 11)));
                    SetSerialNumber(Integer.parseInt(InputField.getText().substring(11, 15)));

                    boolean Valid = Check.VerifyDialNumber(Integer.parseInt(DialNumber), 2);

                    if (!Valid) {
                        JOptionPane.showMessageDialog(Panel, "Invalid Dial Number",
                                "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        //Create Phone Number String
                        String PhoneNumber = GetAreaCode() + "" + GetPrefix() + "" + GetSerialNumber();

                        //Check if Phone Number Exits
                        int Exists = Check.VerifyPhoneNumber(PhoneNumber, GetPrefix());

                        if (Exists == 1) {
                            //Get and Display Balance from Provider Digicel
                            JOptionPane.showMessageDialog(Panel, "$" + GetBalance(PhoneNumber, "Digicel"),
                                    "Balance", JOptionPane.INFORMATION_MESSAGE);

                            //Clear InputField
                            InputField.setText("");
                        } else if (Exists == 2) {
                            //Get and Display Balance from Provider Flow
                            JOptionPane.showMessageDialog(Panel, "$" + GetBalance(PhoneNumber, "Flow"),
                                    "Balance", JOptionPane.INFORMATION_MESSAGE);

                            //Clear InputField
                            InputField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(Panel, "Phone Number does not belong to a Provider",
                                    "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    //Output
                    System.out.println("Dial Number: " + DialNumber);
                    System.out.println("Area Code: " + GetAreaCode());
                    System.out.println("Prefix: " + GetPrefix());
                    System.out.println("Serial Number: " + GetSerialNumber());
                } else {
                    System.err.println("Invalid Format.");
                    JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Invalid Format");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //Get Balance of Specified Phone Number
    private double GetBalance(String PhoneNumber, String Provider) {
        double Balance = 0.00;

        try {
            String TRN;
            String LName;
            String Address;
            double CreditBal;
            String Telephone;

            if (Provider.equals("Digicel")) {
                System.out.println("Getting Balance for Digicel Number");

                //Open Files
                Scanner Read = new Scanner(new File("files/Digicel_Customers.txt"));

                while (Read.hasNext()) {
                    TRN = Read.next();
                    LName = Read.nextLine();
                    Address = Read.nextLine();
                    Telephone = Read.next();
                    CreditBal = Read.nextDouble();

                    System.out.println(TRN + LName);
                    System.out.println(Address);
                    System.out.println(Telephone + " " + CreditBal);

                    if (Telephone.equals(PhoneNumber)) {
                        System.out.println("Balance Retrieved.");
                        Balance = CreditBal;
                        break;
                    }
                }

                //Close file
                Read.close();
            } else if (Provider.equals("Flow")) {
                System.out.println("Getting Balance for Flow Number");

                //Read File
                Scanner Read = new Scanner(new File("files/Flow_Customers.txt"));

                while (Read.hasNext()) {
                    TRN = Read.next();
                    LName = Read.nextLine();
                    Address = Read.nextLine();
                    Telephone = Read.next();
                    CreditBal = Read.nextDouble();

                    System.out.println(TRN + LName);
                    System.out.println(Address);
                    System.out.println(Telephone + " " + CreditBal);

                    if (Telephone.equals(PhoneNumber)) {
                        System.out.println("Balance Retrieved.");
                        Balance = CreditBal;
                        break;
                    }
                }

                //Close file
                Read.close();
            }
        } catch (Exception ex) {
            System.err.println("Error Retrieving Balance.");
            ex.printStackTrace();
        }

        return Balance;
    }

    //Add Credit to Balance
    private double CalculateBalance(double Balance, String CardNumber, int Denomination, String PhoneNumber, String Provider) {
        double NewBalance = 0;

        try {
            String TRN;
            String LName;
            String Address;
            double CreditBal;
            String Telephone;

            //Provider is Digicel
            if (Provider.equals("Digicel")) {
                System.out.println("Getting Balance for Digicel Number");

                //Open Files
                Scanner Read = new Scanner(new File("files/Digicel_Customers.txt"));
                FileWriter Writer = new FileWriter("files/Temp_Digicel_Customers.txt");

                while (Read.hasNext()) {
                    TRN = Read.next();
                    LName = Read.nextLine();
                    Address = Read.nextLine();
                    Telephone = Read.next();
                    CreditBal = Read.nextDouble();

                    //Phone number found
                    if (Telephone.equals(PhoneNumber)) {
                        NewBalance = Balance + Denomination;

                        Writer.write(TRN);
                        Writer.write(LName + "\n");
                        Writer.write(Address + "\n");
                        Writer.write(Telephone + " ");
                        Writer.write(NewBalance + "\n");

                        //Change Status of Card to Used
                        UpdateCardInformation(CardNumber, Provider);

                        System.out.println("New Balance Stored.");
                    } else {
                        Writer.write(TRN);
                        Writer.write(LName + "\n");
                        Writer.write(Address + "\n");
                        Writer.write(Telephone + " ");
                        Writer.write(CreditBal + "\n");
                    }
                }

                //Close files
                Read.close();
                Writer.close();

                //Locate file
                File DigiFile = new File("files/Digicel_Customers.txt");

                //File exists
                if (DigiFile.exists()) {
                    //Attempt to delete file
                    if (DigiFile.delete()) {
                        System.out.println(DigiFile + " Deleted");

                        //Locate file
                        File TempDigiFile = new File("files/Temp_Digicel_Customers.txt");

                        //File exists
                        if (TempDigiFile.exists()) {
                            //Attempt to rename file
                            if (TempDigiFile.renameTo(new File("files/Digicel_Customers.txt"))) {
                                System.out.println(TempDigiFile + " Renamed");
                            } else {
                                System.err.println("Error renaming " + TempDigiFile);
                            }
                        }
                    } else {
                        System.err.println("Error deleting " + DigiFile);
                    }
                }
                //Provider is Flow
            } else if (Provider.equals("Flow")) {
                System.out.println("Getting Balance for Flow Number");

                //Open Files
                Scanner Read = new Scanner(new File("files/Flow_Customers.txt"));
                FileWriter Writer = new FileWriter("files/Temp_Flow_Customers.txt");

                while (Read.hasNext()) {
                    TRN = Read.next();
                    LName = Read.nextLine();
                    Address = Read.nextLine();
                    Telephone = Read.next();
                    CreditBal = Read.nextDouble();

                    //Phone number found
                    if (Telephone.equals(PhoneNumber)) {
                        NewBalance = Balance + Denomination;

                        Writer.write(TRN);
                        Writer.write(LName + "\n");
                        Writer.write(Address + "\n");
                        Writer.write(Telephone + " ");
                        Writer.write(NewBalance + "\n");

                        //Change Status of Card to Used
                        UpdateCardInformation(CardNumber, Provider);

                        System.out.println("New Balance Stored.");
                    } else {
                        Writer.write(TRN);
                        Writer.write(LName + "\n");
                        Writer.write(Address + "\n");
                        Writer.write(Telephone + " ");
                        Writer.write(CreditBal + "\n");
                    }
                }

                //Close file
                Read.close();
                Writer.close();

                //Locate file
                File FlowFile = new File("files/Flow_Customers.txt");

                //File exists
                if (FlowFile.exists()) {
                    //Attempt to delete file
                    if (FlowFile.delete()) {
                        System.out.println(FlowFile + " Deleted");

                        //Locate file
                        File TempFlowFile = new File("files/Temp_Flow_Customers.txt");

                        //File exists
                        if (TempFlowFile.exists()) {
                            //Attempt to rename file
                            if (TempFlowFile.renameTo(new File("files/Flow_Customers.txt"))) {
                                System.out.println(TempFlowFile + " Renamed");
                            } else {
                                System.err.println("Error renaming " + TempFlowFile);
                            }
                        }
                    } else {
                        System.err.println("Error deleting " + FlowFile);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error Calculating Balance.");
            ex.printStackTrace();
        }

        //Return New Balance
        return NewBalance;
    }

    //Change Status of Credit Card
    private void UpdateCardInformation(String CardNumber, String Provider) {
        try {
            //Provider Digicel
            if (Provider.equals("Digicel")) {
                //Open Files
                Scanner Read = new Scanner(new File("files/Digicel_CardInformation.txt"));
                FileWriter Writer = new FileWriter("files/Temp_Digicel_CardInformation.txt");

                String CardNum;
                int Denom;
                String Status;

                while (Read.hasNext()) {
                    CardNum = Read.next();
                    Denom = Read.nextInt();
                    Status = Read.next();

                    if (CardNum.equals(CardNumber)) {
                        Writer.write(CardNum + " ");
                        Writer.write(Denom + " ");
                        Writer.write("Used\n");
                    } else {
                        Writer.write(CardNum + " ");
                        Writer.write(Denom + " ");
                        Writer.write(Status + "\n");
                    }
                }

                //Close files
                Read.close();
                Writer.close();

                //Locate file
                File DigiFile = new File("files/Digicel_CardInformation.txt");

                //File exists
                if (DigiFile.exists()) {
                    //Attempt to delete file
                    if (DigiFile.delete()) {
                        System.out.println(DigiFile + " Deleted");

                        //Locate file
                        File TempDigiFile = new File("files/Temp_Digicel_CardInformation.txt");

                        //File exists
                        if (TempDigiFile.exists()) {
                            //Attempt to rename file
                            if (TempDigiFile.renameTo(new File("files/Digicel_CardInformation.txt"))) {
                                System.out.println(TempDigiFile + " Renamed");
                            } else {
                                System.err.println("Error renaming " + TempDigiFile);
                            }
                        }
                    } else {
                        System.err.println("Error deleting " + DigiFile);
                    }
                }
            } else if (Provider.equals("Flow")) {
                //Open Files
                Scanner Read = new Scanner(new File("files/Flow_CardInformation.txt"));
                FileWriter Writer = new FileWriter("files/Temp_Flow_CardInformation.txt");

                String CardNum;
                int Denom;
                String Status;

                while (Read.hasNext()) {
                    CardNum = Read.next();
                    Denom = Read.nextInt();
                    Status = Read.next();

                    if (CardNum.equals(CardNumber)) {
                        Writer.write(CardNum + " ");
                        Writer.write(Denom + " ");
                        Writer.write("Used\n");
                    } else {
                        Writer.write(CardNum + " ");
                        Writer.write(Denom + " ");
                        Writer.write(Status + "\n");
                    }
                }

                //Close files
                Read.close();
                Writer.close();

                //Locate file
                File DigiFile = new File("files/Flow_CardInformation.txt");

                //File exists
                if (DigiFile.exists()) {
                    //Attempt to delete file
                    if (DigiFile.delete()) {
                        System.out.println(DigiFile + " Deleted");

                        //Locate file
                        File TempDigiFile = new File("files/Temp_Flow_CardInformation.txt");

                        //File exists
                        if (TempDigiFile.exists()) {
                            //Attempt to rename file
                            if (TempDigiFile.renameTo(new File("files/Flow_CardInformation.txt"))) {
                                System.out.println(TempDigiFile + " Renamed");
                            } else {
                                System.err.println("Error renaming " + TempDigiFile);
                            }
                        }
                    } else {
                        System.err.println("Error deleting " + DigiFile);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Return JPanel
    public JComponent GetComponent() {
        return Panel;
    }
}

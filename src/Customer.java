import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Customer {
    private final JPanel Panel = new JPanel();
    private int AreaCode;
    private int Prefix;
    private int SerialNumber;

    Customer() {
        //Initialize variables
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
        MenuLabel.setBounds(195, 20, 210, 40);

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

    private int GetAreaCode(){
        return AreaCode;
    }

    private int GetPrefix(){
        return Prefix;
    }

    private int GetSerialNumber(){
        return SerialNumber;
    }

    private void SetAreaCode(int AreaCode){
        this.AreaCode = AreaCode;
    }

    private void SetPrefix(int Prefix){
        this.Prefix = Prefix;
    }

    private void SetSerialNumber(int SerialNumber){
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
                    String VoucherNumber = InputField.getText().substring(5, 18);

                    //Call Setters
                    SetAreaCode(Integer.parseInt(InputField.getText().substring(19, 22)));
                    SetPrefix(Integer.parseInt(InputField.getText().substring(22, 25)));
                    SetSerialNumber(Integer.parseInt(InputField.getText().substring(25, 29)));

                    try {
                        int[] DigiPrefix = new Digicel().GetPrefix();
                        int[] FlowPrefix = new Flow().GetPrefix();
                        String PhoneNumber = GetAreaCode()+ ""+ GetPrefix()+ ""+GetSerialNumber();

                        System.out.println("Phone Number: "+ PhoneNumber);
                        int Index = 0;
                        int TRN;
                        String LName;
                        String Address;
                        float CreditBal;
                        int Telephone;

                        while (Index < 4) {
                            if (DigiPrefix[Index] == GetPrefix()) {
                                //Open Files
                                Scanner read = new Scanner(new File("file/Digicel_Customers.txt"));

                                while (read.hasNext()) {
                                    TRN = read.nextInt();
                                    LName = read.next();
                                    Address = read.next();
                                    Telephone = read.nextInt();
                                    CreditBal = read.nextFloat();

                                    String Tele = String.valueOf(Telephone);
                                    System.out.println("Tele: "+ Tele);

                                    if (Tele.equals(PhoneNumber)) {
                                        System.out.println("Equals");
                                    }
                                }
                            } else if (FlowPrefix[Index] == GetPrefix()) {
                                //Read File
                                Scanner read = new Scanner(new File("file/Digicel_Customers.txt"));

                            }

                            Index++;
                        }
                        //Open Files
                        FileWriter DigicelFile = new FileWriter("Flow_Customers.txt", true);
                        FileWriter CardFile = new FileWriter("Flow_CardInformation.txt", true);

                    }catch(Exception ex) {
                        System.err.println(ex);
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(Panel, "Telephone Number does not belong to a Service Provider",
                        "Error", JOptionPane.WARNING_MESSAGE);

                    //Output
                    System.out.println("Dial Number: " + DialNumber);
                    System.out.println("Voucher Number: " + VoucherNumber);
                    System.out.println("Area Code: " + GetAreaCode());
                    System.out.println("Prefix: " + GetPrefix());
                    System.out.println("Serial Number: " + GetSerialNumber());

                    //Clear InputField
                    InputField.setText("");
                }else{
                    System.err.println("Invalid Format.");
                    JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Invalid Format.");
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

                    //Output
                    System.out.println("Dial Number: " + DialNumber);
                    System.out.println("Area Code: " + GetAreaCode());
                    System.out.println("Prefix: " + GetPrefix());
                    System.out.println("Serial Number: " + GetSerialNumber());

                    //Clear InputField
                    InputField.setText("");
                }else{
                    System.err.println("Invalid Format.");
                    JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Invalid Format.");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Panel, "Invalid Format", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public JComponent GetComponent() {
        return Panel;
    }
}

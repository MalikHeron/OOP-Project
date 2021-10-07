import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Digicel extends ServiceProvider {

    private final String CompanyID;
    private final String CAddress;
    private static int NumberOfCustomers;

    Digicel(){
        super();

        //Initialize variables
        CompanyID = "Digicel";
        CAddress = "14 Ocean Blvd, Kingston";
        NumberOfCustomers = 0;
        Prefix = new int[]{301, 302, 303, 304};

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel(new ImageIcon("images/digicel_logo.jpg"));
        MenuLabel.setBounds(50, 0, 500, 170);

        super.Panel.add(MenuLabel);
    }

    private String GetCompanyID(){
        return CompanyID;
    }

    private String GetCompanyAddress(){
        return CAddress;
    }

    public static int GetNumberOfCustomers(){
        File DigicelCustomerFile = new File("files/Digicel_Customers.txt");

        int Records = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(DigicelCustomerFile))) {
            while (reader.readLine() != null) {
                Records++;//Count records in file
            }
        } catch (Exception e) {
            System.err.println("Error Reading "+ DigicelCustomerFile);
            e.printStackTrace();
        }

        NumberOfCustomers = Records;

        return NumberOfCustomers;
    }

    public int[] GetPrefix(){
        return Prefix;
    }

    protected void DisplayCompanyInformation(){
        System.out.println("Company ID: "+ GetCompanyID());
        System.out.println("Address: "+ GetCompanyAddress());
        System.out.println("Number of Customers: "+ GetNumberOfCustomers());
    }

    protected void AddCustomer(){
        System.out.println("Digicel Add Customer Button Pressed");
        /*super.SetTRN();
        super.SetLastName();
        super.SetAddress();
        super.SetTelephoneNumber();
        super.SetCreditBalance();*/
    }

    protected void ViewCustomerBase(){
        System.out.println("Digicel View Customer Base Button Pressed");
    }

    protected void CreatePhoneCredit(){
        System.out.println("Digicel Phone Credit Creation Button Pressed");
    }

    protected void ViewAllPhoneCredit(){
        System.out.println("Digicel View Phone Credit Button Pressed");
    }

    public JComponent GetComponent() {
        return super.Panel;
    }
}

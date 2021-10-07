import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Flow extends ServiceProvider {

    private final String CompanyID;
    private final String CAddress;
    private static int NumberOfCustomers;

    Flow(){
        super();

        //Initialize variables
        CompanyID = "Flow";
        CAddress = "Flow Jamaica Headquarters, 2-6 Carlton Cres, Kingston";
        NumberOfCustomers = 0;
        Prefix = new int[]{ 601, 602, 603, 604};

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel(new ImageIcon("images/flow_logo.png"));
        MenuLabel.setBounds(50, 0, 500, 170);

        super.Panel.add(MenuLabel);
    }

    private String GetCompanyID(){
        return CompanyID;
    }

    private String GetCAddress(){
        return CAddress;
    }

    public static int GetNumberOfCustomers(){
        File FlowCustomerFile = new File("files/Flow_Customers.txt");

        int Records = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FlowCustomerFile))) {
            while (reader.readLine() != null) {
                Records++;//Count records in file
            }
        } catch (Exception e) {
            System.err.println("Error Reading "+ FlowCustomerFile);
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
        System.out.println("Address: "+ GetCAddress());
        System.out.println("Number of Customers: "+ GetNumberOfCustomers());
    }

    protected void AddCustomer(){
        System.out.println("Flow Add Customer Button Pressed");
        /*super.SetTRN();
        super.SetLastName();
        super.SetAddress();
        super.SetTelephoneNumber();
        super.SetCreditBalance();*/
    }

    protected void ViewCustomerBase(){

    }

    protected void CreatePhoneCredit(){

    }

    protected void ViewAllPhoneCredit(){

    }

    public JComponent GetComponent() {
        return super.Panel;
    }
}

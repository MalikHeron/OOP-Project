import javax.swing.*;
import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//match system feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        InitializeFiles();
        new MainScreen().setVisible(true);//display window
    }

    //Check if files exist, if not they are created
    private static void InitializeFiles() {
        try {
            File DigicelCustomerFile = new File("files/Digicel_Customers.txt");

            if (DigicelCustomerFile.exists()) {
                System.out.println(DigicelCustomerFile + " Exists.");
            } else {
                if (DigicelCustomerFile.createNewFile()) {
                    System.out.println(DigicelCustomerFile + " Created.");
                } else {
                    System.err.println(DigicelCustomerFile + " Creation Failed.");
                }
            }

            File FlowCustomerFile = new File("files/Flow_Customers.txt");

            if (FlowCustomerFile.exists()) {
                System.out.println(FlowCustomerFile + " Exists.");
            } else {
                if (FlowCustomerFile.createNewFile()) {
                    System.out.println(FlowCustomerFile + " Created.");
                } else {
                    System.err.println(FlowCustomerFile + " Creation Failed.");
                }
            }

            File DigicelCardInformation = new File("files/Digicel_CardInformation.txt");

            if (DigicelCardInformation.exists()) {
                System.out.println(DigicelCardInformation + " Exists.");
            } else {
                if (DigicelCardInformation.createNewFile()) {
                    System.out.println(DigicelCardInformation + " Created.");
                } else {
                    System.err.println(DigicelCardInformation + " Creation Failed.");
                }
            }

            File FlowCardInformation = new File("files/Flow_CardInformation.txt");

            if (FlowCardInformation.exists()) {
                System.out.println(FlowCardInformation + " Exists.");
            } else {
                if (FlowCardInformation.createNewFile()) {
                    System.out.println(FlowCardInformation + " Created.");
                } else {
                    System.err.println(FlowCardInformation + " Creation Failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

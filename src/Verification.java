import java.io.File;
import java.util.Scanner;

public class Verification {

    //Verify Field Lengths when adding a customer
    public boolean VerifyFieldLengths(String TRN, String LastName, String Address, String Telephone) {
        boolean Valid = false;

        int Length = TRN.length();
        int Length2 = LastName.length();
        int Length3 = Address.length();
        int Length4 = Telephone.length();

        if (Length > 0 && Length2 > 0 && Length3 > 0 && Length4 > 0) {
            Valid = true;
        }

        return Valid;
    }

    //Verify TRN and Phone Number when adding a Customer
    public int VerifyFields(String TRN, String Telephone, int[] CPrefix, String CompanyID) {
        int Valid = 0;
        int Index = 0;
        int Prefix = Integer.parseInt(Telephone.substring(3, 6));

        System.out.println("Prefix: " + Prefix);

        if (TRN.length() == 9) {
            Valid = 1;

            //Check if TRN is only digits
            for (int i = 0; i < TRN.length(); i++) {
                boolean Flag = Character.isDigit(TRN.charAt(i));

                if (!Flag) {
                    Valid = -1;
                }
            }

            if (Telephone.length() == 10 && Valid == 1) {
                Valid = 2;

                while (Index < 4) {
                    System.out.println("Index: " + Index);

                    if (CPrefix[Index] == Prefix && CompanyID.equals("Digicel")) {
                        System.out.println("Valid Digicel Number");

                        int Exists = VerifyPhoneNumber(Telephone, Prefix);

                        if (Exists == 0) {
                            Valid = 3;
                        } else {
                            Valid = -2;
                        }

                        break;
                    } else if (CPrefix[Index] == Prefix && CompanyID.equals("Flow")) {
                        System.out.println("Valid Flow Number");

                        int Exists = VerifyPhoneNumber(Telephone, Prefix);

                        if (Exists == 0) {
                            Valid = 3;
                        } else {
                            Valid = -2;
                        }

                        break;
                    }

                    Index++;
                }
            }
        }

        return Valid;
    }

    //Verify Field Lengths when adding a credit card
    public boolean VerifyFieldLengths(String CardNumber, String Denomination) {
        boolean Valid = false;

        int Length = CardNumber.length();
        int Length2 = Denomination.length();

        if (Length > 0 && Length2 > 0) {
            Valid = true;
        }

        return Valid;
    }

    //Verify CardNumber and Denomination when adding a Customer
    public int VerifyFields(String CardNumber, String Denom, String CompanyID, int[] Denomination) {
        int Valid = 0;
        int Index = 0;

        System.out.println("Denomination: " + Denom);

        if (CardNumber.length() == 13) {
            Valid = 1;

            //Check if Card Number is only digits
            for (int i = 0; i < CardNumber.length(); i++) {
                boolean Flag = Character.isDigit(CardNumber.charAt(i));

                if (!Flag) {
                    Valid = -1;
                }
            }

            //Check if Denomination Number is only digits
            for (int i = 0; i < Denom.length(); i++) {
                boolean Flag = Character.isDigit(Denom.charAt(i));

                if (!Flag) {
                    Valid = -2;
                }
            }

            if (Denom.length() >= 3 && Denom.length() <= 4 && Valid == 1) {
                Valid = 2;

                int Denom1 = Integer.parseInt(Denom);

                while (Index < 3) {
                    System.out.println("Index: " + Index);

                    if (Denomination[Index] == Denom1 && CompanyID.equals("Digicel")) {
                        System.out.println("Valid Digicel Credit Card");

                        int Exists = VerifyCardNumber(CardNumber, CompanyID);

                        if (Exists == 0) {
                            Valid = 3;
                        } else {
                            Valid = -3;
                        }

                        break;
                    } else if (Denomination[Index] == Denom1 && CompanyID.equals("Flow")) {
                        System.out.println("Valid Flow Credit Card");

                        int Exists = VerifyCardNumber(CardNumber, CompanyID);

                        if (Exists == 0) {
                            Valid = 3;
                        } else {
                            Valid = -3;
                        }

                        break;
                    }
                    Index++;
                }
            }
        }

        return Valid;
    }

    //Verify Dial Number when trying to Add Credit
    public boolean VerifyDialNumber(int DialNumber, int Option) {
        boolean State = false;

        if (Option == 1) {
            if (DialNumber == 121) {
                State = true;
            }
        } else if (Option == 2) {
            if (DialNumber == 120) {
                State = true;
            }
        }

        return State;
    }

    //Verify Customer PhoneNumber when trying to Add Credit
    public int VerifyPhoneNumber(String PhoneNumber, int Prefix) {
        int Exists = 0;

        try {
            int[] DigiPrefix = new Digicel().GetPrefix();
            int[] FlowPrefix = new Flow().GetPrefix();
            int Index = 0;
            String TRN;
            String LName;
            String Address;
            double CreditBal;
            String Telephone;

            while (Index < 4) {
                System.out.println("Index: " + Index);

                if (DigiPrefix[Index] == Prefix) {
                    System.out.println("Verifying Digicel Number");

                    //Open Files
                    Scanner read = new Scanner(new File("files/Digicel_Customers.txt"));

                    while (read.hasNext()) {
                        TRN = read.next();
                        LName = read.nextLine();
                        Address = read.nextLine();
                        Telephone = read.next();
                        CreditBal = read.nextDouble();

                        System.out.println(TRN + LName);
                        System.out.println(Address);
                        System.out.println(Telephone + " " + CreditBal);

                        if (Telephone.equals(PhoneNumber)) {
                            System.out.println("Phone Number Exists for Digicel.");
                            Exists = 1;
                            break;
                        }
                    }

                    //Close file
                    read.close();
                } else if (FlowPrefix[Index] == Prefix) {
                    System.out.println("Verifying Flow Number");

                    //Read File
                    Scanner read = new Scanner(new File("files/Flow_Customers.txt"));

                    while (read.hasNext()) {
                        TRN = read.next();
                        LName = read.nextLine();
                        Address = read.nextLine();
                        Telephone = read.next();
                        CreditBal = read.nextDouble();

                        System.out.println(TRN + LName);
                        System.out.println(Address);
                        System.out.println(Telephone + " " + CreditBal);

                        if (Telephone.equals(PhoneNumber)) {
                            System.out.println("Phone Number Exists for Flow.");
                            Exists = 2;
                            break;
                        }
                    }

                    //Close file
                    read.close();
                }

                Index++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Exists;
    }

    //Verify CardNumber when trying to Add Credit
    public int VerifyCardNumber(String CardNumber, String Provider) {
        String Filename = "";
        int Exists = 0;

        try {
            String CardNum;
            int Denomination;
            String Status;

            if (Provider.equals("Digicel")) {
                Filename = "files/Digicel_CardInformation.txt";
            } else if (Provider.equals("Flow")) {
                Filename = "files/Flow_CardInformation.txt";
            }

            //Open File
            Scanner read = new Scanner(new File(Filename));

            while (read.hasNext()) {
                System.out.println("Verifying Card Information");

                CardNum = read.next();
                Denomination = read.nextInt();
                Status = read.next();

                System.out.println(CardNum + " " + Denomination + " " + Status);

                if (CardNum.equals(CardNumber) && Status.equalsIgnoreCase("Available")) {
                    System.out.println("Card Number Exists and is Available");
                    Exists = Denomination;
                    break;
                } else if (CardNum.equals(CardNumber) && Status.equalsIgnoreCase("Used")) {
                    System.out.println("Card Number Exists and is Used");
                    Exists = 2;
                    break;
                }
            }

            //Close file
            read.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Exists;
    }
}
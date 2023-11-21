import java.util.Scanner;

public class Gets {
    Scanner EmailIn = new Scanner(System.in), PassIn = new Scanner(System.in), optionIn = new Scanner(System.in);
    Scanner NoteIn = new Scanner(System.in), NewEmailIn = new Scanner(System.in), NewPassIn = new Scanner(System.in);
    Scanner NewAccountName = new Scanner(System.in);

    //Log in Gets
    String GetNewEmail(String NewEmail){
        System.out.println("----------");
        System.out.println("Enter New Email : ");
        NewEmail = EmailIn.nextLine();

        return NewEmail;
    }

    String GetNewPassword(String NewPass){
        System.out.println("----------");
        System.out.println("Enter New Password : ");
        NewPass = PassIn.nextLine();

        return NewPass;
    }

    //logic for getting the users email input for logging in
    String GetEmail(String email){
        System.out.println("--------------");
        System.out.println("Enter Email : ");
        email = EmailIn.nextLine();

        return email;
    }

    //logic for getting the users password input for logging in
    String GetPassword(String pass){
        System.out.println("--------------");
        System.out.println("Enter Password : ");
        pass = PassIn.nextLine();

        return pass;
    }

    //Gets For Main Software

    String GetNewEmailToStore(String newemail){
        System.out.println("--------------");
        System.out.println("Enter New Email You Want Stored : ");
        newemail = NewEmailIn.nextLine();

        return newemail;
    }

    String GetNewPasswordToStore(String newpassword){
        System.out.println("--------------");
        System.out.println("Enter New Password You Want Stored : ");
        newpassword = NewPassIn.nextLine();

        return newpassword;
    }

    String GetNewAccountName(String accountName){
        System.out.println("--------------");
        System.out.println("Enter What the Account is for That You Want Stored : " + "e.g(Gmail, Outlook, Github)");
        accountName = NewAccountName.nextLine();

        return accountName;
    }

    String GetNewNoteName(String newNoteName){
        System.out.println("--------------");
        System.out.println("Enter New Note Name : ");
        newNoteName = NoteIn.nextLine();

        return newNoteName;
    }

    String GetNewNote(String note){
        System.out.println("--------------");
        System.out.println("Enter New Note : ");
        note = NoteIn.nextLine();

        return note;
    }
}

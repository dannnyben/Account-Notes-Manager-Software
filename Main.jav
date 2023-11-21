import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Main Program Variables
    String Email = "";
    String Password = "";

    Boolean HasAccount = false;
    Boolean logged_in = false;

    Gets gets = new Gets();

    //Storage Arrays for the Users data
    ArrayList<Accounts> StoredAccounts = new ArrayList<>();
    ArrayList<Note> StoredNotes = new ArrayList<>();

    public static void main(String[] args) {
        Main m = new Main();
        m.ReadLogIn();
        m.ReadNotes();
        m.AccountCheck();
    }

    //function for checking that the user has an account
    //if they do then the user will be brought to the log in menu
    //if not then the user gets brought to the create account menu
    void AccountCheck(){

        Boolean DoesUserHaveAccount = false;

        HasAccount = AccountChecker(DoesUserHaveAccount);

        if(!HasAccount){
            CreateAccount();
        }
        else{
            LogIn();
        }
    }

    //the logic for the account check
    //checks if the main email and password have anything stored in them
    Boolean AccountChecker(Boolean hasAccount){
        int HasEmail = Email.length();
        int HasPassword = Password.length();

        if(HasEmail > 0){
            hasAccount = true;
        }
        else{
            hasAccount = false;
        }

        return hasAccount;
    }

    //main function for dealing with the user logging in to the software
    //with the max log in attempts being 3
    void LogIn(){
        int LogInAttempts = 1;
        logged_in = false;

        do{
            System.out.println("----------");
            System.out.println("Log IN");
            String userEmail = gets.GetEmail("Email");

            if(userEmail.equals(Email) && logged_in.equals(false)){
                String userPass = gets.GetPassword("Password");

                if(userPass.equals(Password) && logged_in.equals(false)){
                    logged_in = true;
                    LoggedIn();
                }
                else{
                    LogInAttempts += 1;
                }
            }
            else{
                LogInAttempts += 1;
            }
        }while(LogInAttempts <= 3 && !logged_in);

        if(LogInAttempts >= 3){
            System.out.println("Too Many Attempts");
        }
    }

    void LoggedIn(){
        System.out.println("Logged In");
        Options();
    }

    void CreateAccount(){

        Email = gets.GetNewEmail("NewEmail");
        Password = gets.GetNewPassword("NewPassword");

        System.out.println("Account Created");

        WriteLogIn();
    }


    //END OF LOGGING IN SECTION

    void Options(){
        int option = 0;
        System.out.println("1. Store Email & Password");
        System.out.println("2. Store New Note");
        System.out.println("3. See Stored Accounts");
        System.out.println("4. See Stored Notes");
        System.out.println("4. Log Out");

        option = gets.optionIn.nextInt();

        switch (option){
            case 1:
                CreateNewAccountInfo();
            case 2:
                CreateNewNote();
            case 3:
                ShowAccountsInfo();
            case 4:
                ShowNotes();
            case 5:
                LogOut();
        }
    }

    void CreateNewNote(){
        Note newNote = new Note();

        newNote.NoteName = gets.GetNewNoteName("NoteName");
        newNote.Note = gets.GetNewNote("NewNote");

        StoredNotes.add(newNote);
        System.out.println("--------------");
        System.out.println("Your new note is now safe");
        WriteNotes();
        Options();
    }

    void ShowNotes(){
        for(int i = 0; i < StoredNotes.size(); i++){
            System.out.println("--------------");
            System.out.println(StoredNotes.get(i).NoteNumber);
            System.out.println(StoredNotes.get(i).NoteName);
            System.out.println(StoredNotes.get(i).Note);
            System.out.println("--------------");
        }
        Options();
    }

    void CreateNewAccountInfo(){
        Accounts newAccount = new Accounts();

        newAccount.AccountNumber += 1;
        newAccount.AccountName = gets.GetNewAccountName("AccountName");
        newAccount.Email = gets.GetNewEmailToStore("NewEmail");
        newAccount.Password = gets.GetNewPasswordToStore("NewPassword");

        StoredAccounts.add(newAccount);
        System.out.println("--------------");
        System.out.println("Your Account Info is now safe");
        Options();
    }

    void ShowAccountsInfo(){
        for(int i = 0; i < StoredAccounts.size(); i++){
            System.out.println("--------------");
            System.out.println(StoredAccounts.get(i).AccountNumber);
            System.out.println(StoredAccounts.get(i).AccountName);
            System.out.println(StoredAccounts.get(i).Email);
            System.out.println(StoredAccounts.get(i).Password);
            System.out.println("--------------");
        }
        Options();
    }

    void LogOut(){
        LogIn();
        logged_in = false;
    }

    void WriteLogIn(){
        try{
            File FileEmail = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\softwareAccount\\AccountInfo[Email].txt");
            File FilePass = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\softwareAccount\\AccountInfo[Password].txt");
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(FileEmail));
            BufferedWriter outputWriter2 = new BufferedWriter(new FileWriter(FilePass));

            if(FileEmail.exists() && FilePass.exists()){

                if(!Email.isEmpty()){
                    outputWriter.write(Email);
                }

                if(!Password.isEmpty()){
                    outputWriter2.write(Password);
                }

                outputWriter.close();
                outputWriter2.close();
            }

            LogIn();
        }
        catch(IOException e){
            System.out.println("Invalid Path" + e);
        }
    }

    void ReadLogIn(){
        File FileEmail = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\softwareAccount\\AccountInfo[Email].txt");
        File FilePassword = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\softwareAccount\\AccountInfo[Password].txt");

        if(FileEmail.exists() || FilePassword.exists()){
            try{
                Scanner Reader = new Scanner(FileEmail);
                Scanner Reader2 = new Scanner(FilePassword);

                Email = Reader.nextLine();

                Password = Reader2.nextLine();
            }
            catch(FileNotFoundException e){
                System.out.println("An Error Occurred");
                e.printStackTrace();
            }
        }
    }

    void WriteNotes(){

        for(int i = 0; i <= StoredNotes.size() - 1; i++){
            try{
                File FileNotes = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\Notes\\Notes" + (i + 1) + ".txt");
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter(FileNotes));

                if(FileNotes.exists()){
                    for(int j = 0; j < StoredNotes.size(); j++){
                        StoredNotes.get(i).NoteNumber += 1;
                        outputWriter.write(StoredNotes.get(i).NoteNumber + System.lineSeparator());
                        outputWriter.write(StoredNotes.get(i).NoteName + System.lineSeparator());
                        outputWriter.write(StoredNotes.get(i).Note + System.lineSeparator());

                        outputWriter.close();
                    }
                }
            }
            catch(IOException e){
                System.out.println("Invalid Path" + e);
            }
        }

        LogIn();
    }

    void ReadNotes(){
        for(int i = 0; i <= 10; i++){
            File FileNotes = new File("C:\\Users\\dbenn\\Desktop\\PasswordManagerSoftware\\StoredInfo\\Notes\\Notes" + i + ".txt");

            if(FileNotes.exists()){
                try{
                    Scanner Reader = new Scanner(FileNotes);

                    Note newnote = new Note();

                    newnote.NoteNumber = Reader.nextInt();
                    newnote.NoteName = Reader.nextLine();
                    newnote.Note = Reader.nextLine();

                    StoredNotes.add(newnote);
                }
                catch(FileNotFoundException e){
                    System.out.println("An Error Occurred");
                    e.printStackTrace();
                }
            }
        }
    }
}

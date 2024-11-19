package example.exam;

import java.util.Scanner;

import service.adminimpl;
import service.userimpl;

public class App 
{
	public static final adminimpl ad = new adminimpl();
	public static final userimpl us = new userimpl();
	public static final Scanner sc= new Scanner(System.in);
	
	public static void main( String[] args )
    {
        while(true)
        {
        	System.out.println("Welcome to the Exam Control Center");
        	System.out.println("Select Role");
        	System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
            case 1:
                usermenu();
                break;                
            case 2:
            	adminmenu();
                break;                	
            case 3:
                System.out.println("Exiting the application...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void usermenu() {
    	while(true)
    	{
    		System.out.println("1. User SignIn");
            System.out.println("2. User SignUp");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch(ch) {
            case 1:
            	userLogin();
            	break;
            case 2:
            	userRegistration();
            	break;
            case 3:
            	return;
            default:
                System.out.println("Invalid choice. Please try again.");
            		
            }
    	}
    }
    
    
    
    private static void userLogin() {
    	System.out.println("Enter E-mail");
    	String email=sc.nextLine();
    	System.out.println("Enter Password");
    	String password=sc.nextLine();
    	boolean success = us.userLogin(email, password);
    	if(success==true)
    	{
    		System.out.println("User Logged In Successfully");
    		useroptions();
    	}
    	else
    	{
    		System.out.println("Invalid E-mail or Password");
    	}
    }
    private static void useroptions() {
    	while(true) {
			System.out.println("1. Update Details");
    		System.out.println("2. View Questions");
    		System.out.println("3. Take Exam");
    		System.out.println("4. Exit");
    		System.out.print("Select an option: ");
            int ch1 = sc.nextInt();
            sc.nextLine();
            switch(ch1) {
            case 1:
            	updatedetails();
            	break;
            case 2:
            	viewQuestions();
            	break;
            case 3:
            	takeexam();
            	break;
            case 4:
            	return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
		}
    }
    
    private static void updatedetails() {
    	System.out.println("Enter new Name");
    	String name=sc.nextLine();
    	System.out.println("Enter new E-Mail");
    	String email=sc.nextLine();
    	System.out.println("Enter new Password");
    	String password=sc.nextLine();
    	us.updateUser(name, email, password);
    }
    
    private static void viewQuestions() {
    	us.viewQues();
    }
    
    private static void takeexam() {
    	int[] arr=us.takeExam();
    	System.out.println("Exam Over !! Marks Obtained : "+arr[0]+"/"+arr[1]);
    }
    
    private static void userRegistration() {
    	System.out.println("Enter the Name of user");
    	String name=sc.nextLine();
    	System.out.println("Enter the E-mail of user");
    	String email=sc.nextLine();
    	System.out.println("Enter the Password of user");
    	String password=sc.nextLine();
    	us.registerUser(name, email, password);
    }

    private static void adminmenu() {
    	{
    		while(true) {
    			System.out.println("1. Admin SignIn");
    	        System.out.println("2. Admin SignUp");
    	        System.out.println("3. Exit");
    	        System.out.print("Select an option: ");
    	        int ch2 = sc.nextInt();
    	        sc.nextLine();
    	        switch(ch2) {
    	        case 1:
    	        	adminLogin();
    	        	break;
    	        case 2:
    	        	adminRegistration();
    	        	break;
    	        case 3:
    	        	return;
    	        default:
                    System.out.println("Invalid choice. Please try again.");
    		    }
    		}
    	}
    }
    
    private static void adminLogin() {
    	System.out.println("Enter E-mail");
    	String email=sc.nextLine();
    	System.out.println("Enter Password");
    	String password=sc.nextLine();
    	boolean success = ad.loginAdmin(email, password);
    	if(success==true)
    	{
    		System.out.println("Admin Logged In Successfully");
    		adminoptions();
    	}
    	else
    	{
    		System.out.println("Invalid E-mail or Password");
    	}
    }
    
    private static void adminoptions() {
    	while(true)
		{
			System.out.println("1. Register User Details");
    		System.out.println("2. Update User Details");
    		System.out.println("3. Remove User Details");
    		System.out.println("4. Add Question");
    		System.out.println("5. Update Question");
    		System.out.println("6. Remove Question");
    		System.out.println("7. Exit");
    		System.out.print("Select an option: ");
            int ch1 = sc.nextInt();
            sc.nextLine();
            switch(ch1) {
            case 1:
            	registerNewUser();
            	break;
            case 2:
            	updateExistingUser();
            	break;
            case 3:
            	removeExistingUser();
            	break;
            case 4:
            	addQuestion();
            	break;
            case 5:
            	updateQuestion();
            	break;
            case 6:
                removeQuestion();
                break;
            case 7:
            	return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
		}
    }
    
    private static void registerNewUser() {
    	System.out.println("Enter the Name of user");
    	String name=sc.nextLine();
    	System.out.println("Enter the E-mail of user");
    	String email=sc.nextLine();
    	System.out.println("Enter the Password of user");
    	String password=sc.nextLine();
    	ad.registerUser(name, email, password);
    }
    
    private static void updateExistingUser() {
    	System.out.println("Enter the User-ID of the User to Update :");
    	int user_id=sc.nextInt();
    	sc.nextLine();
    	System.out.println("Enter the Updated Name of user :");
    	String name=sc.nextLine();
    	System.out.println("Enter the Updated E-mail of user :");
    	String email=sc.nextLine();
    	System.out.println("Enter the Updated Password of user :");
    	String password=sc.nextLine();
    	ad.updateUser(user_id, name, email, password);
    }
    
    private static void removeExistingUser() {
    	System.out.println("Enter the User-ID of the User to Delete :");
    	int user_id=sc.nextInt();
    	sc.nextLine();
    	ad.removeUser(user_id);
    }
    
    private static void addQuestion() {
    	System.out.println("Enter Question : ");
    	String ques=sc.nextLine();
    	System.out.println("Enter Option 1 : ");
    	String op1=sc.nextLine();
    	System.out.println("Enter Option 2 : ");
    	String op2=sc.nextLine();
    	System.out.println("Enter Option 3 : ");
    	String op3=sc.nextLine();
    	System.out.println("Enter Option 4 : ");
    	String op4=sc.nextLine();
    	System.out.println("Enter Correct Option :");
    	int corr_op=sc.nextInt();
    	sc.nextLine();
    	ad.addques(ques, op1, op2, op3, op4, corr_op);
    }
    
    private static void updateQuestion() {
    	System.out.println("Enter Question ID to Update : ");
    	int ques_id=sc.nextInt();
    	sc.nextLine();
    	System.out.println("Enter Question : ");
    	String ques=sc.nextLine();
    	System.out.println("Enter Option 1 : ");
    	String op1=sc.nextLine();
    	System.out.println("Enter Option 2 : ");
    	String op2=sc.nextLine();
    	System.out.println("Enter Option 3 : ");
    	String op3=sc.nextLine();
    	System.out.println("Enter Option 4 : ");
    	String op4=sc.nextLine();
    	System.out.println("Enter Correct Option :");
    	int corr_op=sc.nextInt();
    	sc.nextLine();
    	ad.modifyques(ques_id, ques, op1, op2, op3, op4, corr_op);
    }
    
    private static void removeQuestion() {
    	System.out.println("Enter Question ID to Remove : ");
    	int ques_id=sc.nextInt();
    	sc.nextLine();
    	ad.removeques(ques_id);
    }
    
    private static void adminRegistration() {
    	System.out.println("Enter the Name of Admin");
    	String name=sc.nextLine();
    	System.out.println("Enter the E-mail of Admin");
    	String email=sc.nextLine();
    	System.out.println("Enter the Password of Admin");
    	String password=sc.nextLine();
    	ad.registerAdmin(email, password, name);
    }
}
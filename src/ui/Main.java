package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.EmptyFieldException;
import exceptions.ExistingDocumentException;
import model.ControlSystem;

public class Main {
	
	
	private ControlSystem cs;
	
	private Scanner dataReader;
	
	public Main() {
		cs = new ControlSystem();
		dataReader = new Scanner(System.in);
		employeeMenu();
	}
	
	public void employeeMenu() {
		int options = 0;
		do {
			try {
				System.out.println();
				System.out.println("Welcome to the user menu.");
				System.out.println("Choose the option that you want the program does.");
				System.out.println("1. Register an user.");
				System.out.println("2. Generate a shift.");
				System.out.println("3. Assign shift to the user.");
				System.out.println("4. Search a user by its document number.");
				System.out.println("5. Consult the next shift to attend.");
				System.out.println("6. Attends a user's shift.");
				System.out.println("7. Exit from the menu.");
				System.out.println();
				options = dataReader.nextInt();
				dataReader.nextLine();
				switch( options ) {
				case 1:
					menuAddUser();
					break;
				case 2:
					menuGenerateShift();
					break;
				case 3:
					menuAssignShiftToUser();
					break;
				case 4:
					menuSearchUser();
					break;
				case 5:
					menuConsultShiftToAttend();
					break;
				case 6:
					menuAttendUserShift();
					break;
				default:
					System.out.println("Thanks for using the program.");
					break;
				}
			}catch( InputMismatchException e ) {
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}
		}while( options != 7 );
	}
	
	public void menuAddUser() {
		int option = 0;
		boolean xd = true;
		String typeOfDocument = "";
		do {
			try {
				System.out.println();
				System.out.println("Enter the option for the type of document.");
				System.out.println("1. Identity card.");
				System.out.println("2. Citizenship card.");
				System.out.println("3. Foreigner id.");
				option = dataReader.nextInt();
				dataReader.nextLine();
				switch(option) {
				case 1:
					typeOfDocument = cs.IDENTITY_CARD;
					break;
				case 2:
					typeOfDocument = cs.CITIZENSHIP_CARD;
					break;
				case 3:
					typeOfDocument = cs.FOREIGNER_ID;
					break;
				}
				System.out.println("Type the document number.");
				String documentNumber = dataReader.nextLine();
				System.out.println("Type the user's names.");
				String names = dataReader.nextLine();
				System.out.println("Type the user's last names.");
				String lastNames = dataReader.nextLine();
				System.out.println("Type the user's phone.");
				String phone = dataReader.nextLine();
				System.out.println("Type the user's address.");
				String address = dataReader.nextLine();
				if( typeOfDocument.equals("") ) {
					throw new EmptyFieldException("The field <<Type of document>> can't be empty.");
				}else if( documentNumber.equals("") ){
					throw new EmptyFieldException("The field <<Document number>> can't be empty.");
				}else if( names.equals("") ) {
					throw new EmptyFieldException("The field <<Names>> can't be empty.");
				}else if( lastNames.equals("") ) {
					throw new EmptyFieldException("The field <<Last names>> can't be empty.");
				}
				if( cs.existingUser(documentNumber) ) {
					throw new ExistingDocumentException("The user is already registered.");
				}else {
					cs.addUser(typeOfDocument, documentNumber, names, lastNames, phone, address);
					System.out.println();
					System.out.println("The user was successfully added.");
				}
				xd = false;
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}catch( EmptyFieldException e ) {
				System.out.println(e.getMessage());
				System.out.println("Please type the user's information again.");
			}catch( ExistingDocumentException e  ) {
				System.out.println();
				System.out.println(e.getMessage());
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				dataReader.nextLine();
			}
		}while( xd );
	}
	
	public void menuGenerateShift() {
		System.out.println("The shift generated was: " + cs.addShift());
	}
	
	public void menuAssignShiftToUser() {
		boolean xd = true;
		do {
			System.out.println("Type the document number from the user.");
			String documentNumber = dataReader.nextLine();
			System.out.println("Type the shift you want to assign to the user.");
			String dataShift = dataReader.nextLine();
			if( cs.existingUser(documentNumber) ) {
				if( cs.existingShift(dataShift) ) {
					System.out.println("The shift was successfully assigned.");
					cs.assignShiftToUser(documentNumber, dataShift);
				}else {
					String shift = cs.addShift();
					System.out.println("The shift you tried to assign to the user doesn't exists.");
					System.out.println("This is the shift that the system has assigned to the user: " + shift);
					cs.assignShiftToUser(documentNumber, shift);
				}
			}else {
				System.out.println("It's necessary to add the user to the system.");
				menuAddUser();
				if( cs.existingShift(dataShift) ) {
					System.out.println("The shift was successfully assigned.");
					cs.assignShiftToUser(documentNumber, dataShift);
				}else {
					String shift = cs.addShift();
					System.out.println("The shift you tried to assign to the user doesn't exists.");
					System.out.println("This is the shift that the system has assigned to the user: " + shift);
					cs.assignShiftToUser(documentNumber, shift);
				}
			}
			xd = false;
		}while( xd );
	}
	
	public void menuSearchUser() {
		System.out.println("Type the document number of the user.");
		String documentNumber = dataReader.nextLine();
		if( cs.searchUser(documentNumber) == null ) {
			System.out.println("The user was not found.");
		}else {
			System.out.println("The user was successfully found.");
			System.out.println(cs.searchUser(documentNumber));
		}
	}
	
	public void menuConsultShiftToAttend() {
		if( cs.getShifts().isEmpty() || cs.consultShiftsStatus() == false ) {
			System.out.println("There are not shifts assigned to the users.");
		}else {
			System.out.println("The next shift to attend is: " + cs.consultShiftToAttend());
		}
	}
	
	public void menuAttendUserShift() {
		int option1 = 0;
		boolean xd = true;
		do {
			try {
				if( cs.consultShiftsStatus() == false ){
					System.out.println("There are not shifts assigned to the users.");
					xd = false;
				}else{
					System.out.println("The current shift to attend is: " + cs.consultShiftToAttend());
					System.out.println("What would you like to do?");
					System.out.println("1. Attend the current shift.");
					System.out.println("2. The user wasn't ");
					option1 = dataReader.nextInt();
					dataReader.nextLine();
					cs.attendUserShift(option1, cs.consultShiftToAttend());
					System.out.println("The shift was successfully attended.");
					xd = false;
				}	
			}catch( InputMismatchException e ) {
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}
		}while( xd );
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

	
}

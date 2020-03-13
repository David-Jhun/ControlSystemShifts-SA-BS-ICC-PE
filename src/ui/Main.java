package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		dataReader.close();
	}
	
	public void employeeMenu() {
		int options = 0;
		do {
			try {
				System.out.println();
				System.out.println("Welcome to the user menu.");
				System.out.println("Choose the option that you want the program does.");
				System.out.println("1. Add an user.");
				System.out.println("2. Add a new type of shift.");
				System.out.println("3. Add a shift.");
				System.out.println("4. .");
				System.out.println("5. .");
				System.out.println("6. .");
				System.out.println("7. .");
				System.out.println("8. Exit from the menu.");
				System.out.println();
				options = dataReader.nextInt();
				dataReader.nextLine();
				switch( options ) {
				case 1:
					menuAddUser();
					break;
				case 2:
					menuAddTypeOfShift();
					break;
				case 3:
					menuAddShift();
					break;
				case 4:
					menuSearchUser();
					break;
				case 5:
					menuConsultShiftToAttend();
					break;
				case 6:
					break;
				case 7:
					break;
				case 9:
					System.out.println(cs.getUsers().get(0));
					break;
				default:
					if( options != 8 ) {
						System.out.println("Enter a valid option.");
					}else {
						System.out.println("Thanks for using the program.");
					}
					try {
						cs.saveSystemInformation();
					}catch( FileNotFoundException e ) {
						System.out.println("");
						System.out.println("The file does not exist.");
						System.out.println("");	
					}catch( IOException e ) {
						System.out.println("");
						System.out.println("The file's name is not correct.");
						System.out.println("");	
					}
					break;
				}
			}catch( InputMismatchException e ) {
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}
		}while( options != 8 );
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
	
	public void menuAddTypeOfShift() {
		boolean xd = true;
		do {
			try {
				System.out.print("Type the name of the new Shift: ");
				String name = dataReader.nextLine();
				System.out.print("Type the shift's duration: ");
				double duration = dataReader.nextDouble();
				dataReader.nextLine();
				cs.addTypeOfShift(name, duration);
				System.out.println("The new type of shift was added.");
				xd = false;
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				System.out.println();
				dataReader.nextLine();
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				System.out.println();
				dataReader.nextLine();
			}
		}while( xd );
	}
	
	public void menuAddShift() {
		boolean done = true;
		do {
			try {
				System.out.println("Type the number of the shift.");
				System.out.println();
				System.out.println(cs.showSpecialShifts());
				int option = dataReader.nextInt();
				dataReader.nextLine();
				cs.addShift(option);
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				System.out.println();
				dataReader.nextLine();
			}catch( IndexOutOfBoundsException e ) {
				System.out.println();
				System.out.println("Enter the number on the screen.");
				System.out.println();
				dataReader.nextLine();
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				System.out.println();
				dataReader.nextLine();
			}
		}while( !done );
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
	
	public static void main(String[] args) {
		Main main = new Main();
	}

	
}

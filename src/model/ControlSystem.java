package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import exceptions.ExistingDocumentException;

public class ControlSystem {
	
	
	public final static String IDENTITY_CARD = "Identity card";
	public final static String CITIZENSHIP_CARD = "Citizenship card";
	public final static String FOREIGNER_ID = "Foreigner Id";
	
	private int letter;
	private int number;
	
	private ArrayList<User> users;
	
	private ArrayList<Shift> shifts;
	
	private ArrayList<TypeOfShift> specialShifts;
	
	public ControlSystem(){
		letter  = 65;
		number = 0;
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
		specialShifts = new ArrayList<TypeOfShift>();
		try {
			loadUsersInformation();
		}catch( Exception e ) {
			File newOneForUsers = new File("./data/users_information.txt");
		}
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	
	public ArrayList<TypeOfShift> getSpecialShifts() {
		return specialShifts;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void setSpecialShifts(ArrayList<TypeOfShift> specialShifts) {
		this.specialShifts = specialShifts;
	}

	public void addUser(String typeOfDocument, String documentNumber, String names, String lastNames, String phone, String address) throws ExistingDocumentException, FileNotFoundException, IOException{
		User user = new User(typeOfDocument, documentNumber, names, lastNames, phone, address);
		users.add(user);
	}
	
	public User searchUser(String documentNumber) throws NullPointerException{
		User user = null;
		for(int i = 0; i < users.size() ; i++) {
			if(users.get(i).getDocumentNumber().compareTo(documentNumber) == 0) {
				user = users.get(i);
			}
		}
		return user;
	}
	
	public void sortUsersBySelectionSort() {
		for( int i = 0 ; i < users.size() - 1 ; i++ ) {
			User minor = users.get(i);
			int which = i;
			for( int j = i + 1 ; j < users.size() ; j++ ) {
				if( users.get(j).compareTo(minor) < 0 ) {
					minor = users.get(j);
					which = j;
				}
			}
			User temp = users.get(i);
			users.set(i, minor);
			users.set(which, temp);
		}
	}
	
	public void sortUsersByBubbleSort() {
		for( int i = users.size() ; i > 0 ; i-- ) {
			for( int j = 0 ; j < i - 1 ; j++ ) {
				if( users.get(j).compareTo(users.get(j + 1)) > 0 ) {
					User temp = users.get(j);
					users.set(j, users.get(j + 1));
					users.set(j + 1, temp);
				}
			}
		}
	}
	
	public void sortUsersByInsertionSort() {
		for( int i = 1 ; i < users.size() ; i++ ) {
			User insert = users.get(i);
			boolean done = false;
			for( int j = i ; j > 0 && !(done) ; j-- ) {
				User current = users.get(j - 1);
				if( current.compareTo(insert) > 0 ) {
					users.set(j, current);
					users.set(j - 1, insert);
				}else {
					done = true;
				}
			}
		}
	}
	
	public void sortUsersByTOD_DN() {
		Comparator<User> xd = new CompareUsersByTOD_DN();
		users.sort(xd);
	}
	
	public void sortUsersByAD_PE() {
		Comparator<User> xd = new CompareUsersByAD_PE();
		users.sort(xd);
	}
	
	public User binarySearchUsers( String documentNumber ) {
		sortUsersByInsertionSort();
		User u = null;
		boolean founded = false;
		int start = 0;
		int end = users.size() - 1;
		while( start <= end && !(founded) ) {
			int mid = ( start + end ) / 2;
			if( users.get(mid).getDocumentNumber().compareTo(documentNumber) == 0 ) {
				u = users.get(mid);
				founded = true;
			}else if( users.get(mid).getDocumentNumber().compareTo(documentNumber) > 0 ) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		return u;
	}
	
	public void generateRandomUsers( int quantity ) throws IOException{
		File f = new File("./data/RandomUsers.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		Random r = new Random();
		int counter = 0;
		String line = "";
		do {
			String typeOfDocument = "";
			switch( r.nextInt(4) ) {
			case 1:
				typeOfDocument = IDENTITY_CARD;
				break;
			case 2:
				typeOfDocument = CITIZENSHIP_CARD;
				break;
			case 3:
				typeOfDocument = FOREIGNER_ID;
				break;
			}
			line = br.readLine();
			String[] data = line.split(",");
			String documentNumber = data[0];
			String names = data[1];
			String lastNames = data[2];
			String phone = data[3];
			String address = data[4];
			User u = new User(typeOfDocument, documentNumber, names, lastNames, phone, address);
			users.add(u);
			counter++;
		}while( counter <= quantity && line != null );
		fr.close();
		br.close();
	}
	
	public void addTypeOfShift( String name, double duration ) throws IOException {
		TypeOfShift newOne = new TypeOfShift(name, duration);
		specialShifts.add(newOne);
	}

	public void addShift( int option ) {
		User user = null;
		if( !(users.isEmpty()) && !(specialShifts.isEmpty()) ) {
			if( letter > 90 )
				letter = 65;
			if( number > 99 ) {
				number = 0;
				changeLetter();
				if( letter > 90 )
					letter = 65;
			}
			user = searchAvailableUser();
			Shift shift = new Shift(letter, number, specialShifts.get(option - 1), user);
			user.setAvailable(false);
			user.getMyShifts().add(shift);
			shifts.add(shift);
			changeNumber();
		}
	}

	public String consultShiftToAttend() {
		String xd = "";
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAssigned() == true ) {
				xd = shifts.get(i).getComplete();
			}
		}
		return xd;
	}
	
	public boolean consultShiftsStatus() {
		boolean status = false;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAssigned() == true ) {
				status = true;
			}
		}
		return status;
	}
	
	public void attendShift() {
	
	}

	public void saveSystemInformation(){
		for( int i = 0 ; i < users.size() ; i++ ) {
			users.get(i).setAvailable(true);
		}
	}

	public void serializeUsers() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/users_information.txt"));
		oos.writeObject(users);
		oos.close();
	}

	public void loadUsersInformation() throws FileNotFoundException, ClassNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/users_information.txt"));
		ArrayList<User> savedU = (ArrayList<User>) ois.readObject();
		setUsers(savedU);
		ois.close();
	}

	public boolean existingUser(String documentNumber) {
		boolean status = false;
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).getDocumentNumber().compareTo(documentNumber) == 0 )
				status = true;
		}
		return status;
	}
	
	public boolean existingShift(String dataShift) {
		boolean status = false;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).getComplete().compareTo(dataShift) == 0 ) 
				status = true;
		}
		return status;
	}
	
	public void showUsersWithSpecificShiftFile( String shift ) throws FileNotFoundException {
		File newFile = new File("./data/users_with_specific_shift");
		PrintWriter writer = new PrintWriter( newFile );
		writer.println("Users that had assigned the shift: " + shift);
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).possessedSpecificShift(shift) ) {
				writer.println();
				writer.println(users.get(i).getDocumentNumber());
				writer.println(users.get(i).getNames());
				writer.println();
			}
		}
		writer.close();
	}
	
	public String showUsersWithSpecificShif( String shift ) {
		String data = "";
		data += "\nUsers that had assigned the shift: " + shift;
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).possessedSpecificShift(shift) ) {
				data += "\n";
				data += "\n" + users.get(i).getDocumentNumber();
				data += "\n" + users.get(i).getNames();
				data += "\n";
			}
		}
		return data;
	}
	
	public String showSpecialShifts() {
		String data = "";
		for( int i = 0 ; i < specialShifts.size() ; i++ ) {
			data += (i + 1) + ") " + specialShifts.get(i);
		}
		return data;
	}
	
	public User searchAvailableUser() {
		User available = null;
		boolean status = false;
		for( int i = 0 ; i < users.size() && !status ; i++ ) {
			if( users.get(i).isAvailable() ) {
				available = users.get(i);
				status = true;
			}
		}
		return available;
	}
	
	public void changeLetter() {
		letter++;
	}
	
	public void changeNumber() {
		number++;
	}

}

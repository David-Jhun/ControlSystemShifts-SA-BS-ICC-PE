package model;

import java.util.ArrayList;

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
	
	public ControlSystem() {
		letter  = 65;
		number = 0;
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
		specialShifts = new ArrayList<TypeOfShift>();
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

	public void addUser(String typeOfDocument, String documentNumber, String names, String lastNames, String phone, String address) throws ExistingDocumentException{
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
	
	public User binarySearchUsers( String documentNumber ) {
		sortUsersByInsertionSort();
		User u = null;
		boolean founded = false;
		int start = 0;
		int end = users.size() - 1;
		while( start <= end && !(founded) ) {
			int mid = ( start + end ) / 2;
			if( users.get(mid).getDocumentNumber().equals(documentNumber) ) {
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
	
	public String addShift() {
		String data = "";
		if( letter > 90 )
			letter = 65;
		if( number > 99 ) {
			number = 0;
			changeLetter();
			if( letter > 90 )
				letter = 65;
		}
		Shift shift = new Shift(letter, number);
		data = "" + shift;
		shifts.add(shift);
		changeNumber();
		return data;
	}
	
	public void addSpecialShift( String name, double duration ) {
		TypeOfShift special = new TypeOfShift( name, duration );
		specialShifts.add(special);
	}
	
	public void assignShiftToUser(String documentNumber, String dataShift) {
		Shift shift = searchUnassignShift(dataShift);
		User user = searchUser(documentNumber);
		user.setShift(shift);
		user.getShift().setAssigned(true);
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
	
	public void attendUserShift(int option, String shift) {
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).getShift().getComplete().equals(shift) ) {
				if( option == 1 ) {
					users.get(i).getShift().setAttended(true);
				}else if( option == 2 ) {
					users.get(i).getShift().setNotAttended(true);
				}
			}
		}
		removeAttendedShifts();
	}
	
	public Shift searchUnassignShift( String dataShift ) throws NullPointerException{
		Shift shift = null;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAssigned() == false && shifts.get(i).getComplete().compareTo(dataShift) == 0 ) {
				shift = shifts.get(i);
			}
		}
		return shift;
	}
	
	public void removeAttendedShifts() {
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAttended() == true || shifts.get(i).isNotAttended() == true ) {
				shifts.remove(i);
			}
		}
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
	
	public void changeLetter() {
		letter++;
	}
	
	public void changeNumber() {
		number++;
	}

}

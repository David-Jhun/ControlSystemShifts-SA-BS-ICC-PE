package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Comparable<User>, Serializable{
	
	private String typeOfDocument;
	private String documentNumber;
	private String names;
	private String lastNames;
	private String phone;
	private String address;
	private boolean available;
	
	private ArrayList<Shift> userShifts;

	public User(String typeOfDocument, String documentNumber, String names, String lastNames, String phone, String address) {
		this.typeOfDocument = typeOfDocument;
		this.documentNumber = documentNumber;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.address = address;
		available = true;
		userShifts = new ArrayList<Shift>();
	}

	public String getTypeOfDocument() {
		return typeOfDocument;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public String getNames() {
		return names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public ArrayList<Shift> getUserShifts() {
		return userShifts;
	}

	@Override
	public String toString() {
		String data = "";
		data += "\nUser";
		data += "\nType of document: " + typeOfDocument + " " + "Document number: " + documentNumber;
		data += "\nNames: " + names;
		data += "\nLast names: " + lastNames;
		return data;
	}

	@Override
	public int compareTo(User user) {
		int comparation = 0;
		if( names.compareTo(user.names) > 0 ) {
			comparation = 1;
		}else if( names.compareTo(user.names) < 0 ) {
			comparation = -1;
		}else {
			if( lastNames.compareTo(user.lastNames) > 0 ) {
				comparation = 1;
			}else if( lastNames.compareTo(user.lastNames) < 0 ) {
				comparation = -1;
			}
		}
		return comparation;
	}

}

package model;

public class User implements Comparable<User>{
	
	private String typeOfDocument;
	private String documentNumber;
	private String names;
	private String lastNames;
	private String phone;
	private String address;
	
	private Shift shift;
	
	public User(String typeOfDocument, String documentNumber, String names, String lastNames, String phone, String address) {
		this.typeOfDocument = typeOfDocument;
		this.documentNumber = documentNumber;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.address = address;
		shift = null;
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
	
	public Shift getShift() {
		return shift;
	}
	
	public void setShift(Shift shift) {
		this.shift = shift;
	}

	@Override
	public String toString() {
		String data = "";
		data += "\nType of document: " + typeOfDocument + " " + "Document number: " + documentNumber;
		data += "\nNames: " + names;
		data += "\nLast names: " + lastNames;
		return data;
	}

	@Override
	public int compareTo(User aUser) {
		if( documentNumber.compareTo(aUser.documentNumber) > 0 )
			return 1;
		else if( documentNumber.compareTo(aUser.documentNumber) < 0 )
			return -1;
		else
			return 0;
	}

}

package model;

public class Shift {
	
	private boolean attended;
	private boolean notAttended;
	private boolean assigned;
	private char letter;
	private int number;
	private String complete;
	
	public Shift(int letter, int number) {
		attended = false;
		notAttended = false;
		assigned = false;
		this.letter = (char)letter;
		this.number = number;
		complete = setInformation();
	}

	public boolean isAttended() {
		return attended;
	}

	public boolean isNotAttended() {
		return notAttended;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public char getLetter() {
		return letter;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public void setNotAttended(boolean notAttended) {
		this.notAttended = notAttended;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getComplete() {
		return complete;
	}
	
	public String setInformation() {
		String data = "";
		if( number < 10 ) {
			switch(number) {
			case 0:
				data = letter + "00";
				break;
			case 1:
				data = letter + "01";
				break;
			case 2:
				data = letter + "02";
				break;
			case 3:
				data = letter + "03";
				break;
			case 4:
				data = letter + "04";
				break;
			case 5:
				data = letter + "05";
				break;
			case 6:
				data = letter + "06";
				break;
			case 7:
				data = letter + "07";
				break;
			case 8:
				data = letter + "08";
				break;
			case 9:
				data = letter + "09";
				break;
			}
		}else {
			data = "" + letter + number;
		}
		return data;
	}

	@Override
	public String toString() {
		return setInformation();
	}

}

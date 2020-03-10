package model;

import java.util.Comparator;

public class CompareUsersByTOD_DN implements Comparator<User>{

	@Override
	public int compare(User user1, User user2) {
		int comparation = 0;
		if( user1.getTypeOfDocument().compareToIgnoreCase(user2.getTypeOfDocument()) < 0 ) {
			comparation = -1;
		}else if( user1.getTypeOfDocument().compareToIgnoreCase(user2.getTypeOfDocument()) > 0 ) {
			comparation = 1;
		}else {
			if( user1.getDocumentNumber().compareTo(user2.getDocumentNumber()) < 0 ) {
				comparation = -1;
			}else if( user1.getDocumentNumber().compareTo(user2.getDocumentNumber()) > 0 ) {
				comparation = 1;
			}
		}
		return comparation;
	}

}

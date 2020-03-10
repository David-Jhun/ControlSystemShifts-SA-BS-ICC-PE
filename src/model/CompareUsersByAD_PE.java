package model;

import java.util.Comparator;

public class CompareUsersByAD_PE implements Comparator<User>{

	@Override
	public int compare(User user1, User user2) {
		int comparation = 0;
		if( user1.getAddress().compareTo(user2.getAddress()) < 0 )
			comparation = -1;
		else if( user1.getAddress().compareTo(user2.getAddress()) > 0 )
			comparation = 1;
		else {
			if( user1.getPhone().compareTo(user2.getPhone()) < 0 ) {
				comparation = -1;
			}else if( user1.getPhone().compareTo(user2.getPhone()) > 0 ) {
				comparation = 1;
			}
		}
		return comparation;
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.ControlSystem;
import model.User;
import exceptions.ExistingDocumentException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ControlSystemTest {
/**
	private ControlSystem cs;
	
	private void setupStage1() {
		cs = new ControlSystem();
	}
	
	private void setupStage2() throws ExistingDocumentException {
		cs = new ControlSystem();
		cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
	}
	
	private void setupStage3() throws ExistingDocumentException{
		cs = new ControlSystem();
		cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
		cs.addUser(cs.FOREIGNER_ID, "135791", "Josh", "Stuart", "", "");
		cs.addUser(cs.IDENTITY_CARD, "246810", "Antonio", "Uchiha", "", "");
	}
	
	private void setupStage4() {
		cs = new ControlSystem();
		for( int i = 0 ; i < 3 ; i++ ) {
			cs.changeLetter();
		}
		for( int i = 0 ; i < 100 ; i++ ) {
			cs.changeNumber();
		}
	}
	
	private void setupStage5() {
		cs = new ControlSystem();
		for( int i = 0 ; i < 25 ; i++ ) {
			cs.changeLetter();
		}
		for( int i = 0 ; i < 100 ; i++) {
			cs.changeNumber();
		}
	}
	
	private void setupStage6() {
		try {
			setupStage2();
		}catch( ExistingDocumentException e ) {
			fail();
		}
		cs.addShift();
		cs.assignShiftToUser("654789", "A00");
	}
	
	private void setupStage7() {
		try {
			setupStage3();
			for( int i = 0 ; i < 50 ; i++ ) {
				cs.addShift();
			}
			cs.assignShiftToUser("654789", "A00");
			cs.assignShiftToUser("135791", "A10");
			cs.assignShiftToUser("246810", "A48");
			cs.attendUserShift(1, "A10");
		}catch( ExistingDocumentException e ) {
			fail();
		}
	}
	
	@Test
	public void testAddUser() {
		setupStage1();
		try {
			cs.addUser(cs.CITIZENSHIP_CARD, "123456", "Pepe", "Morales", "3137886655", "Valle del lili");
		}catch( ExistingDocumentException e ) {
			fail();
		}
		assertEquals(cs.CITIZENSHIP_CARD, cs.getUsers().get(0).getTypeOfDocument());
		assertEquals("123456", cs.getUsers().get(0).getDocumentNumber());
		assertEquals("Pepe", cs.getUsers().get(0).getNames());
		assertEquals("Morales", cs.getUsers().get(0).getLastNames());
		assertEquals("3137886655", cs.getUsers().get(0).getPhone());
		assertEquals("3137886655", cs.getUsers().get(0).getPhone());
		assertEquals("Valle del lili", cs.getUsers().get(0).getAddress());
		assertEquals(1, cs.getUsers().size());
		//-------------------------------------------------------------------------------
		try {
			setupStage2();
			cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
			throw new ExistingDocumentException("The user is already registered.");
		}catch( ExistingDocumentException e ) {
			assertTrue(true);
		}
		//-------------------------------------------------------------------------------
		try {
			setupStage3();
			cs.addUser(cs.CITIZENSHIP_CARD, "1006048", "Luis", "Hurtado", "", "");
		}catch( ExistingDocumentException e ) {
			fail();
		}
		assertEquals(4, cs.getUsers().size());
		//-------------------------------------------------------------------------------
	}
	
	@Test
	public void testSearchUser() {
		User user = null;
		try {
			setupStage2();
			user = cs.searchUser("654789");
			assertEquals("Identity card", user.getTypeOfDocument());
			assertEquals("654789", user.getDocumentNumber());
			assertEquals("Andres", user.getNames());
		}catch( NullPointerException e ) {
			fail();
		}catch ( ExistingDocumentException e ) {
			fail();
		}
		//-------------------------------------------------------------------------------
		setupStage1();
		try {
			user = cs.searchUser("654789");
		}catch( NullPointerException e ) {
			assertTrue(true);
		}
		//-------------------------------------------------------------------------------
		try {
			setupStage3();
			user = cs.searchUser("246810");
			assertEquals("246810", user.getDocumentNumber());
			assertEquals("Antonio", user.getNames());
		}catch ( ExistingDocumentException e ) {
			fail();
		}
	}
	
	/**@Test
	public void testAddShift() {
		setupStage1();
		assertEquals("A00", cs.addShift());
		//-------------------------------------------------------------------------------
		setupStage4();
		assertEquals("E00", cs.addShift());
		//-------------------------------------------------------------------------------
		setupStage5();
		assertEquals("A00", cs.addShift());
		//-------------------------------------------------------------------------------
		setupStage6();
		assertEquals("A01", cs.addShift());
	}
	
	@Test
	public void testAssignShiftToUser() {
		setupStage6();
		assertEquals(cs.consultShiftToAttend(), cs.getUsers().get(0).getShift().getComplete());
		//-------------------------------------------------------------------------------
		try {
			setupStage2();
			cs.addShift();
			cs.addShift();
			cs.assignShiftToUser("654789", "A01");
			assertEquals("A01", cs.consultShiftToAttend());
		}catch ( ExistingDocumentException e ) {
			fail();
		}
	}
	
	@Test
	public void testConsultShiftToAttend() {
		setupStage6();
		assertEquals("A00", cs.consultShiftToAttend());
	}
	
	@Test
	public void testAttendUserShift() {
		setupStage7();
		cs.attendUserShift(2, "A49");
		assertEquals(49, cs.getShifts().size());
	}
**/
}

package order_management;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RepManagementImplTest {
	RepManagementInterface repMng;
	
	@Before
	public void setUp() throws Exception {
		repMng=new RepManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		repMng = null;
	}

	@Test
	public void createREPTest() {
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("the name of the client is wrong", rep.getClientName().equals("Ericson"));
		assertTrue("the name of the user is wrong", rep.getUname().equals("cso"));
	}
	
	@Test
	public void addREPTest() {
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		
		ArrayList<Rep> list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
	}
	
	@Test
	public void addTwoElementsREPTest() {
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		rep = repMng.createRep("cso", "Dell");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		
		ArrayList<Rep> list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
	}
	
	@Test
	public void addTwoElementsIDREPTest() {
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		assertFalse("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		
		ArrayList<Rep> list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
	}
	
	
	@Test
	public void listElementsREPTest() {
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		rep = repMng.createRep("cso", "Dell");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(repMng.createRep("scso", "Google")));
		
		ArrayList<Rep> list = repMng.listRep("scso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("RepManagementImpl acess to a bad element", !list.contains(rep));
		list = repMng.listRep("all");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
		
	}
	
	@Test
	public void deleteREPTest(){
		Rep rep = repMng.createRep("cso", "Ericson");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		
		ArrayList<Rep> list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
		
		rep = repMng.createRep("cso", "Dell");
		assertTrue("RepManagementImpl can't add a new rep", repMng.addRep(rep));
		
		list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("RepManagementImpl acess to a bad element", list.contains(rep));
		
		ArrayList<Rep> history = repMng.getHistory();
		assertTrue("RepManagementImpl acess to a bad history", history.size() == 0);
		repMng.deleteRep(rep.getIdentifier());
		list = repMng.listRep("cso");
		assertTrue("RepManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("RepManagementImpl acess to a delete element", list.contains(rep));
		
		history = repMng.getHistory();
		assertTrue("RepManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("RepManagementImpl can't acess to a delete element", history.contains(rep));	
		
	}

}

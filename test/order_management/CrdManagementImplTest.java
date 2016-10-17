package order_management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrdManagementImplTest {
	CrdManagementInterface CrdMng;
	
	@Before
	public void setUp() throws Exception {
		CrdMng=new CrdManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		CrdMng = null;
	}

	@Test
	public void createCrdTest() {
		Rep rep = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep.getState().equals(RepState.CREATED));
		Crd crd = CrdMng.createCrd(rep);
		assertTrue("Bad initialisation of the state for Crd",crd == null);
		rep.setState(RepState.APPROVED);
		crd = CrdMng.createCrd(rep);
		assertTrue("the name of the client is wrong", crd.getClientName().equals("client1"));
		assertTrue("the name of the client is wrong", crd.getIdentifier() == rep.getIdentifier());
	
		ArrayList<Crd> list = CrdMng.getAllCrd();
		assertTrue("CrdManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd));
	}
	
	@Test
	public void addTwoElementsCrdTest() {
		Rep rep1 = new Rep("cso","client1");
		rep1.setState(RepState.APPROVED);
		Crd crd1 = CrdMng.createCrd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1 != null);
		Rep rep2 = new Rep("cso", "Dell");
		rep2.setState(RepState.APPROVED);
		Crd crd2 = CrdMng.createCrd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2 != null);
		
		ArrayList<Crd> list = CrdMng.getAllCrd();
		assertTrue("CrdManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd2));
	}
	
	@Test
	public void getCrdByRepCrdTest() {
		Rep rep1 = new Rep("cso","client1");
		rep1.setState(RepState.APPROVED);
		Crd crd1 = CrdMng.createCrd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1 != null);
		Rep rep2 = new Rep("cso", "Dell");
		rep2.setState(RepState.APPROVED);
		Crd crd2 = CrdMng.createCrd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2 != null);
		
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep1).equals(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep2).equals(crd2));
	}
	
	@Test
	public void getCrdByIdCrdTest() {
		Rep rep1 = new Rep("cso","client1");
		rep1.setState(RepState.APPROVED);
		Crd crd1 = CrdMng.createCrd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1 != null);
		Rep rep2 = new Rep("cso", "Dell");
		rep2.setState(RepState.APPROVED);
		Crd crd2 = CrdMng.createCrd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2 != null);
		
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep1.getIdentifier()).equals(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep2.getIdentifier()).equals(crd2));
	}
	
	@Test
	public void deleteCrdTest(){
		Rep rep1 = new Rep("cso","client1");
		rep1.setState(RepState.APPROVED);
		Crd crd1 = CrdMng.createCrd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1 != null);
		Rep rep2 = new Rep("cso", "Dell");
		rep2.setState(RepState.APPROVED);
		Crd crd2 = CrdMng.createCrd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2 != null);
		
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep1).equals(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", CrdMng.getCrd(rep2).equals(crd2));
		
		ArrayList<Crd> list = CrdMng.getAllCrd();
		assertTrue("CrdManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd2));
		
		ArrayList<Crd> history = CrdMng.getCrdHistory();
		assertTrue("CrdManagementImpl acess to a bad history", history.size() == 0);
		CrdMng.deleteCrd(crd1.getIdentifier());
		list = CrdMng.getAllCrd();
		assertTrue("CrdManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("CrdManagementImpl acess to a delete element", list.contains(crd1));
		assertTrue("CrdManagementImpl acess to a bad element", list.contains(crd2));
		
		history = CrdMng.getCrdHistory();
		assertTrue("CrdManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("CrdManagementImpl can't acess to a delete element", history.contains(crd1));	
		
	}

}

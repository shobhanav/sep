package order_management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FinancialRequestManagementImplTest {
	FinancialRequestManagementInterface FinancialRequestMng;
	
	@Before
	public void setUp() throws Exception {
		FinancialRequestMng=new FinancialRequestManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		FinancialRequestMng = null;
	}

	@Test
	public void createFinancialRequestTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("the name of the user is wrong", financialRequest.getProject_reference() == rep.getIdentifier());
	}
	
	@Test
	public void addFinancialRequestTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
	}
	
	@Test
	public void addTwoElementsFinancialRequestTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
	}
	
	@Test
	public void addTwoElementsIDFinancialRequestTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		assertFalse("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
	}
	
	
	@Test
	public void listElementsFinancialRequestTeamTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		financialRequest = FinancialRequestMng.createFinancialRequest(crd,"SM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getFinancialRequestUser("SM");
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", !list.contains(financialRequest));
		list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
	}
	
	@Test
	public void listElementsFinancialRequestCdrTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd1 = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd1,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		financialRequest = FinancialRequestMng.createFinancialRequest(crd1,"SM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		rep = new Rep("cso","client2");
		Crd crd2 = new Crd(rep);
		financialRequest = FinancialRequestMng.createFinancialRequest(crd2,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getFinancialRequestCrd(crd1);
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", !list.contains(financialRequest));
		list = FinancialRequestMng.getFinancialRequestCrd(crd2);
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
		list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 3);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
	}
	
		@Test
	public void addGetFinancialRequestTest() {
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		assertTrue("FinancialRequestManagementImpl access invalid at the new FinancialRequest", FinancialRequestMng.getFinancialRequest(financialRequest.getIdentifier()).equals(financialRequest));
	}
	
	
	
	@Test
	public void deleteFinancialRequestTest(){
		Rep rep = new Rep("cso","client1");
		Crd crd = new Crd(rep);
		FinancialRequest financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		ArrayList<FinancialRequest> list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
		
		rep = new Rep("cso","client2");
		crd = new Crd(rep);
		financialRequest = FinancialRequestMng.createFinancialRequest(crd,"PM");
		assertTrue("FinancialRequestManagementImpl can't add a new FinancialRequest", FinancialRequestMng.addFinancialRequest(financialRequest));
		
		list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("FinancialRequestManagementImpl acess to a bad element", list.contains(financialRequest));
		
		ArrayList<FinancialRequest> history = FinancialRequestMng.getFinancialRequestHistory();
		assertTrue("FinancialRequestManagementImpl acess to a bad history", history.size() == 0);
		FinancialRequestMng.deleteFinancialRequest(financialRequest.getIdentifier());
		list = FinancialRequestMng.getAllFinancialRequest();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("FinancialRequestManagementImpl acess to a delete element", list.contains(financialRequest));
		
		history = FinancialRequestMng.getFinancialRequestHistory();
		assertTrue("FinancialRequestManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("FinancialRequestManagementImpl can't acess to a delete element", history.contains(financialRequest));	
		
	}
}

package order_management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeRequestManagementImplTest {
	EmployeeRequestManagementInterface EmployeeRequestMng;
	
	@Before
	public void setUp() throws Exception {
		EmployeeRequestMng=new EmployeeRequestManagementImpl();
	}

	@After
	public void tearDown() throws Exception {
		EmployeeRequestMng = null;
	}

	@Test
	public void createEmployeeRequestTest() {
		EmployeeRequest employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("the name of the user is wrong", employeeRequest.getUname().equals("PM"));
	}
	
	@Test
	public void addEmployeeRequestTest() {
		EmployeeRequest employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		
		ArrayList<EmployeeRequest> list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("EmployeeRequestManagementImpl acess to a bad element", list.contains(employeeRequest));
	}
	
	@Test
	public void addTwoElementsEmployeeRequestTest() {
		EmployeeRequest employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		
		ArrayList<EmployeeRequest> list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("EmployeeRequestManagementImpl acess to a bad element", list.contains(employeeRequest));
	}
	
	@Test
	public void addTwoElementsIDEmployeeRequestTest() {
		EmployeeRequest employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		assertFalse("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		
		ArrayList<EmployeeRequest> list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("EmployeeRequestManagementImpl acess to a bad element", list.contains(employeeRequest));
	}
	
	
	@Test
	public void deleteEmployeeRequestTest(){
		EmployeeRequest employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		
		ArrayList<EmployeeRequest> list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 1);
		assertTrue("EmployeeRequestManagementImpl acess to a bad element", list.contains(employeeRequest));
		
		employeeRequest = EmployeeRequestMng.createEmployeeRequest("PM");
		assertTrue("EmployeeRequestManagementImpl can't add a new EmployeeRequest", EmployeeRequestMng.addEmployeeRequest(employeeRequest));
		
		list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 2);
		assertTrue("EmployeeRequestManagementImpl acess to a bad element", list.contains(employeeRequest));
		
		ArrayList<EmployeeRequest> history = EmployeeRequestMng.getEmployeeRequestHistory();
		assertTrue("EmployeeRequestManagementImpl acess to a bad history", history.size() == 0);
		EmployeeRequestMng.deleteEmployeeRequest(employeeRequest.getIdentifier());
		list = EmployeeRequestMng.getAllEmployeeRequest();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", list.size() == 1);
		assertFalse("EmployeeRequestManagementImpl acess to a delete element", list.contains(employeeRequest));
		
		history = EmployeeRequestMng.getEmployeeRequestHistory();
		assertTrue("EmployeeRequestManagementImpl acess to a bad list", history.size() == 1);
		assertTrue("EmployeeRequestManagementImpl can't acess to a delete element", history.contains(employeeRequest));	
		
	}

}

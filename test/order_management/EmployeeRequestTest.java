package order_management;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmployeeRequestTest {

	@Test
	public void stateCreateTest() {
		EmployeeRequest employeeRequest1 = new EmployeeRequest("cso");
		assertTrue("Bad initialisation of the state for EmployeeRequest",employeeRequest1.getState().equals(EmployeeRequestState.CREATED));
	}
	
	@Test
	public void identifierTest() {
		EmployeeRequest employeeRequest1 = new EmployeeRequest("cso");
		EmployeeRequest employeeRequest2 = new EmployeeRequest("cso");
		assertTrue("Bad initialisation of the identifier for EmployeeRequest",employeeRequest1.getIdentifier() != employeeRequest2.getIdentifier());
	}

}

package order_management;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskTest {
	@Test
	public void stateCreateTest() {
		Rep rep1 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1.getState().equals(CrdState.CREATED));
		Task task1 = new Task(crd1, "Decoration");
		assertTrue("Bad initialisation of the state for Task",task1.getState().equals(TaskState.CREATED));
	}
	
	@Test
	public void identifierTest() {
		Rep rep1 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1.getState().equals(CrdState.CREATED));
		Task task1 = new Task(crd1, "Decoration");
		assertTrue("Bad initialisation of the state for Task",task1.getState().equals(TaskState.CREATED));
		Rep rep2 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep2.getState().equals(RepState.CREATED));
		rep2.setState(RepState.APPROVED);
		Crd crd2 = new Crd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2.getState().equals(CrdState.CREATED));
		Task task2 = new Task(crd2, "Decoration");
		assertTrue("Bad initialisation of the state for Task",task2.getState().equals(TaskState.CREATED));
		assertTrue("Bad initialisation of the state for Task",task1.getId_crd() == crd1.getIdentifier());
		assertTrue("Bad initialisation of the state for Task",task2.getId_crd() == crd2.getIdentifier());
		assertTrue("Bad initialisation of the identifier for REP",task2.getIdentifier() != task1.getIdentifier());
	}
}

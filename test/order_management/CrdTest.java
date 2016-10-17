package order_management;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CrdTest {
	@Test
	public void stateCreateTest() {
		Rep rep1 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1.getState().equals(CrdState.CREATED));
	}
	
	@Test
	public void identifierTest() {
		Rep rep1 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd",crd1.getState().equals(CrdState.CREATED));
		Rep rep2 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep2.getState().equals(RepState.CREATED));
		rep2.setState(RepState.APPROVED);
		Crd crd2 = new Crd(rep2);
		assertTrue("Bad initialisation of the state for Crd",crd2.getState().equals(CrdState.CREATED));
		assertTrue("Bad initialisation of the identifier for REP",crd1.getIdentifier() != crd2.getIdentifier());
	}
}

package order_management;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FinancialRequestTest {

	@Test
	public void stateCreateTest() {
		FinancialRequest FinancialRequest1 = new FinancialRequest(1, "cso");
		assertTrue("Bad initialisation of the state for FinancialRequest",
				FinancialRequest1.getState().equals(FinancialRequestState.CREATED));
	}

	@Test
	public void identifierTest() {
		FinancialRequest FinancialRequest1 = new FinancialRequest(1, "cso");
		FinancialRequest FinancialRequest2 = new FinancialRequest(1, "cso");
		assertTrue("Bad initialisation of the identifier for FinancialRequest",
				FinancialRequest1.getIdentifier() != FinancialRequest2.getIdentifier());
	}

	@Test
	public void stateCreateTestCrd() {
		Rep rep1 = new Rep("cso", "client1");
		assertTrue("Bad initialisation of the state for REP", rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd", crd1.getState().equals(CrdState.CREATED));
		FinancialRequest FinancialRequest1 = new FinancialRequest(crd1, "cso");
		assertTrue("Bad initialisation of the state for FinancialRequest",
				FinancialRequest1.getState().equals(FinancialRequestState.CREATED));
	}

	@Test
	public void identifierTestCrd() {
		Rep rep1 = new Rep("cso", "client1");
		assertTrue("Bad initialisation of the state for REP", rep1.getState().equals(RepState.CREATED));
		rep1.setState(RepState.APPROVED);
		Crd crd1 = new Crd(rep1);
		assertTrue("Bad initialisation of the state for Crd", crd1.getState().equals(CrdState.CREATED));
		FinancialRequest FinancialRequest1 = new FinancialRequest(crd1, "cso");
		Rep rep2 = new Rep("cso", "client1");
		assertTrue("Bad initialisation of the state for REP", rep2.getState().equals(RepState.CREATED));
		rep2.setState(RepState.APPROVED);
		Crd crd2 = new Crd(rep2);
		assertTrue("Bad initialisation of the state for Crd", crd2.getState().equals(CrdState.CREATED));
		FinancialRequest FinancialRequest2 = new FinancialRequest(crd2, "cso");
		assertTrue("Bad initialisation of the identifier for FinancialRequest",
				FinancialRequest1.getIdentifier() != FinancialRequest2.getIdentifier());
		FinancialRequest FinancialRequest3 = new FinancialRequest(1, "cso");
		assertTrue("Bad initialisation of the identifier for FinancialRequest",
				FinancialRequest3.getIdentifier() != FinancialRequest2.getIdentifier());
		assertTrue("Bad initialisation of the identifier for FinancialRequest",
				FinancialRequest3.getIdentifier() != FinancialRequest1.getIdentifier());
	}
}

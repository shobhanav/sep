package order_management;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RepTest {

	@Test
	public void stateCreateTest() {
		Rep rep1 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the state for REP",rep1.getState().equals(RepState.CREATED));
	}
	
	@Test
	public void identifierTest() {
		Rep rep1 = new Rep("cso","client1");
		Rep rep2 = new Rep("cso","client1");
		assertTrue("Bad initialisation of the identifier for REP",rep1.getIdentifier() != rep2.getIdentifier());
	}
	
}

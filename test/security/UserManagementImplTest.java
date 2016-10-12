package security;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class UserManagementImplTest {
	
	UserManagementInterface userManagement;
	private HashMap<String, String> authnMap = new HashMap<String, String>();
	private HashMap<String, ArrayList<String>> authzMap = new HashMap<String, ArrayList<String>>();

	@Before
	public void setUp() throws Exception {
		populateUserdb();
		userManagement = new UserManagementImpl(authnMap, authzMap);
	}

	@After
	public void tearDown() throws Exception {
		userManagement = null;
	}
	
	private void populateUserdb(){
		//authentication map
		authnMap.put("cso", "password1");
		authnMap.put("vp", "password2");
		authnMap.put("scso", "password3");
		authnMap.put("fm", "password4");
		
		//authorization map
		ArrayList<String> vpRoles = new ArrayList<String>();
		vpRoles.add("all");
		authzMap.put("vp", vpRoles );
		
		ArrayList<String> csoRoles = new ArrayList<String>();
		csoRoles.add("create");
		csoRoles.add("view");
		authzMap.put("cso", csoRoles );
		
		ArrayList<String> scsoRoles = new ArrayList<String>();
		scsoRoles.add("create");
		scsoRoles.add("view");
		scsoRoles.add("modify");
		authzMap.put("scso", scsoRoles );
		
		ArrayList<String> fmRoles = new ArrayList<String>();		
		fmRoles.add("view");
		fmRoles.add("modify");
		authzMap.put("fm", scsoRoles );
		
	}
	
	/**
	 * Verifies successful authentication of a valid user
	 */
	@Test
	public void testValidUserAuthentication() {
		boolean isAuthenticated = userManagement.isAuthenticated("cso", "password1");
		assertTrue("The user should have been successfully validated",isAuthenticated);
	}

	/*
	 * Verifies unsuccessful authentication of a valid user
	 */
	@Test
	public void testInvalidUserAuthentication() {
		boolean isAuthenticated = userManagement.isAuthenticated("cso", "password");
		assertFalse("The user should have been unsuccessfully validated",isAuthenticated);
	}
	
	/*
	 * Verifies get good role for a valid user
	 */
	@Test
	public void testRoleUserAuthentication() {
		ArrayList<String> role = userManagement.getRoles("cso");
		boolean goodRole = role.contains("create") && role.contains("view") && (role.size() == 2);
		assertTrue("The user should have been successfully validated",goodRole);
	}

	/*
	 * Verifies add user for a valid user
	 */
	@Test
	public void testAddUserAuthentication() {
		boolean addUser = userManagement.addUser("RHm","password5");
		assertTrue("The user should have been unsuccessfully added",addUser);
		addUser = authnMap.containsKey("RHm") ;
		assertTrue("The user should have been unsuccessfully added",addUser);
		addUser = authnMap.get("RHm").equals("password5");
		assertTrue("The password should have been unsuccessfull",addUser);
	}
	
	/*
	 * Verifies remove user 
	 */
	@Test
	public void testRemoveUserAuthentication() {
		boolean removeUser = userManagement.removeUser("cso");
		assertTrue("The user should have been unsuccessfully removed",removeUser);
		removeUser = authnMap.containsKey("cso") ;
		assertFalse("The user should have been unsuccessfully removed",removeUser);
	}
	
	/*
	 * Verifies to change the password of the user
	 */
	@Test
	public void testChangePasswordUserAuthentication() {
		boolean changePass = userManagement.changePassword("cso", "password1", "password6");
		assertTrue("The user should have been unsuccessfully changed password",changePass);
		changePass = authnMap.get("cso").equals( "password6") ;
		assertTrue("The user should have been unsuccessfully changed password", changePass);
	}
	
	/*
	 * Verifies to add role for the user
	 */
	@Test
	public void addRoleUserAuthentication() {
		boolean addRole = userManagement.addRole("cso", "create");
		assertFalse("The user should have been successfully added the same role",addRole);
		addRole = userManagement.addRole("cso", "all");
		assertTrue("The user should have been unsuccessfully added role",addRole);
		addRole = authzMap.get("cso").contains( "all") ;
		assertTrue("The user should have been unsuccessfully added role", addRole);
	}
	
	/*
	 * Verifies to remove role for the user
	 */
	@Test
	public void removeRoleUserAuthentication() {
		boolean removeRole = userManagement.removeRole("cso", "create");
		assertTrue("The user should have been unsuccessfully ",removeRole);
		removeRole = userManagement.removeRole("cso", "all");
		assertFalse("The user should have been unsuccessfully changed password",removeRole);
		removeRole = authzMap.get("cso").contains( "create") ;
		assertFalse("The user should have been unsuccessfully added", removeRole);
	}
	
	/*
	 * Verifies using different function
	 */
	@Test
	public void VerificationUserAuthentication() {
		boolean verification = userManagement.addUser("RHm", "password7");
		assertTrue("The user should have been unsuccessfully added",verification);
		verification = userManagement.isAuthenticated("RHm", "password1");
		assertFalse("The user should have been unsuccessfully added",verification);
		verification = userManagement.isAuthenticated("RHm", "password7");
		assertTrue("The user should have been unsuccessfully added",verification);
		verification = authnMap.get("RHm").equals("password7");
		assertTrue("The password should have been unsuccessfull",verification);
	}
}

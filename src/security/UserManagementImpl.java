package security;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManagementImpl implements UserManagementInterface {
	
	private HashMap<String,String> authnMap;
	private HashMap<String,ArrayList<String>> authzMap;
	
	public UserManagementImpl() {
		// TODO Auto-generated constructor stub
		initUsers(authnMap);
		initRoles(authzMap);
	}
	
	private void initRoles(HashMap<String, ArrayList<String>> authzMap2) {
		// authorization map
		authzMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> vpRoles = new ArrayList<String>();
		vpRoles.add("vp");
		authzMap.put("vp", vpRoles);

		ArrayList<String> csoRoles = new ArrayList<String>();		
		csoRoles.add("cso");
		authzMap.put("cso1", csoRoles);
		authzMap.put("cso2", csoRoles);

		ArrayList<String> scsoRoles = new ArrayList<String>();		
		scsoRoles.add("scso");
		authzMap.put("scso", scsoRoles);

		ArrayList<String> fmRoles = new ArrayList<String>();
		fmRoles.add("fm");
		authzMap.put("fm", fmRoles);
		
		ArrayList<String> adminRoles = new ArrayList<String>();		
		adminRoles.add("admin");
		authzMap.put("admin", adminRoles);
	}

	private void initUsers(HashMap<String, String> authnMap2) {
		authnMap = new HashMap<String, String>();
		authnMap.put("cso1", "password");
		authnMap.put("cso2", "password");
		authnMap.put("vp", "password");
		authnMap.put("scso", "password");
		authnMap.put("fm", "password");
		authnMap.put("admin", "password");
		authnMap.put("prod_manager", "password");
		authnMap.put("service_manager", "password");		
	}

	UserManagementImpl(HashMap<String,String> authnMap, HashMap<String,ArrayList<String>> authzMap) {
		this.authnMap = authnMap;
		this.authzMap = authzMap;
	}	
	

	@Override
	public boolean isAuthenticated(String username, String password) {
		if(username == null || password == null){
			return false;
		}
		return (authnMap.containsKey(username) && authnMap.get(username).equals(password));
	}

	@Override
	public ArrayList<String> getRoles(String username) {
		if (authzMap.containsKey(username)){
			return authzMap.get(username);
		}
		return null;
	}

	@Override
	public boolean addUser(String uName, String password) {
		if (authnMap.containsKey(uName)){
			return false;
		}
		authnMap.put(uName, password);
		authzMap.put(uName, new ArrayList<String>());
		return true;
	}

	@Override
	public boolean removeUser(String uName) {
		if (! authnMap.containsKey(uName)){
			return false;
		}
		authnMap.remove(uName);
		return true;
	}

	@Override
	public boolean changePassword(String uName, String old_password, String new_password) {
		if (! authnMap.containsKey(uName) && authnMap.get(uName).equals(old_password)){
			return false;
		}
		authnMap.put(uName,new_password);
		return true;
	}

	@Override
	public boolean addRole(String uName, String role) {
		if (!authzMap.containsKey(uName)){
			return false;
		}
		ArrayList<String> roles = authzMap.get(uName);
		if (roles.contains(role)){
			return false;
		}
		roles.add(role);
		return true;
	}

	@Override
	public boolean removeRole(String uName, String role) {
		if (! authzMap.containsKey(uName)){
			return false;
		}
		ArrayList<String> roles = authzMap.get(uName);
		if (! roles.contains(role)){
			return false;
		}
		roles.remove(role);
		return true;
	}
	
	

}

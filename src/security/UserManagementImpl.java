package security;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManagementImpl implements UserManagementInterface {
	
	private HashMap<String,String> authnMap;
	private HashMap<String,ArrayList<String>> authzMap;
	
	public UserManagementImpl() {
		// TODO Auto-generated constructor stub
	}
	
	UserManagementImpl(HashMap<String,String> authnMap, HashMap<String,ArrayList<String>> authzMap) {
		this.authnMap = authnMap;
		this.authzMap = authzMap;
	}	
	

	@Override
	public boolean isAuthenticated(String username, String password) {
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
		if (roles != null && roles.contains(role)){
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

package security;

import java.util.ArrayList;

public interface UserManagementInterface {
	
	public boolean isAuthenticated(String username, String password);
	
	public ArrayList<String> getRoles(String username);
	
	public boolean addUser(String uName, String password);
	
	public boolean removeUser(String uName);
	
	public boolean changePassword(String uName, String old_password, String new_password);
	
	public boolean addRole(String uName, String role);
	
	public boolean removeRole(String uName, String role);

}

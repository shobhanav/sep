package framework;

import order_management.CrdManagementInterface;
import order_management.RepManagementImpl;
import order_management.RepManagementInterface;
import security.UserManagementImpl;
import security.UserManagementInterface;

public class ServiceLocator {
	
	private static RepManagementInterface rep = new RepManagementImpl();
	private static CrdManagementInterface crd = null;
	private static UserManagementInterface user = new UserManagementImpl();
	
	public static RepManagementInterface getRepService(){
		return rep;
	}
	
	public static CrdManagementInterface getCrdService(){
		return crd;
	}
	
	public static UserManagementInterface getSecurityService(){
		return user;
	}
	
	
	}


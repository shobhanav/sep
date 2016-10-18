package framework;

import order_management.CrdManagementImpl;
import order_management.CrdManagementInterface;
import order_management.RepManagementImpl;
import order_management.RepManagementInterface;
import order_management.TaskManagementImpl;
import order_management.TaskManagementInterface;
import security.UserManagementImpl;
import security.UserManagementInterface;

public class ServiceLocator {
	
	private static RepManagementInterface rep = new RepManagementImpl();
	private static CrdManagementInterface crd = new CrdManagementImpl();
	private static UserManagementInterface user = new UserManagementImpl();
	private static TaskManagementInterface task = new TaskManagementImpl();
	
	public static RepManagementInterface getRepService(){
		return rep;
	}
	
	public static CrdManagementInterface getCrdService(){
		return crd;
	}
	
	public static UserManagementInterface getSecurityService(){
		return user;
	}
	
	public static TaskManagementInterface getTaskService(){
		return task;
	}
	
	}


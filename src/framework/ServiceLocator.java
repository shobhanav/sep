package framework;

import order_management.CrdManagementImpl;
import order_management.CrdManagementInterface;
import order_management.EmployeeRequestManagementImpl;
import order_management.EmployeeRequestManagementInterface;
import order_management.FinancialRequestManagementImpl;
import order_management.FinancialRequestManagementInterface;
import order_management.RepManagementImpl;
import order_management.RepManagementInterface;
import order_management.SubTaskManagementImpl;
import order_management.SubTaskManagementInterface;
import order_management.TaskManagementImpl;
import order_management.TaskManagementInterface;
import security.UserManagementImpl;
import security.UserManagementInterface;

public class ServiceLocator {
	
	private static RepManagementInterface rep = new RepManagementImpl();
	private static CrdManagementInterface crd = new CrdManagementImpl();
	private static UserManagementInterface user = new UserManagementImpl();
	private static TaskManagementInterface task = new TaskManagementImpl();
	private static  FinancialRequestManagementInterface financial = new FinancialRequestManagementImpl();
	private static  EmployeeRequestManagementInterface employee = new EmployeeRequestManagementImpl();
	private static SubTaskManagementInterface subtask = new SubTaskManagementImpl();
	
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
	
	public static FinancialRequestManagementInterface getFinancialService(){
		return financial;
	}
	
	public static EmployeeRequestManagementInterface getEmployeeService(){
		return employee;
	}
	
	public static SubTaskManagementInterface getSubTaskService(){
		return subtask;
	}
}


package order_management;

import java.util.ArrayList;

public interface TaskManagementInterface {

	public Task createTasks( Crd crd, String team);
	
	public boolean addTask(Task task);
	
	public Task getTask (Crd crd, String team);
	
	public Task getTask (int id);
	
	public ArrayList<Task> getTaskCrd (Crd crd);
	
	public ArrayList<Task> getTaskTeam(String team);
	
	public ArrayList<Task> getAllTask();
	
	public void deleteTask(int id);
	
	public ArrayList<Task> getTaskHistory();
}

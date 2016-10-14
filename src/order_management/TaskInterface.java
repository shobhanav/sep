package order_management;

import java.util.ArrayList;

public interface TaskInterface {

	public Task createTasks( Crd crd);
	
	public boolean addTask(Task task);
	
	public Task getCrd(Crd crd, String team);
	
	public ArrayList<Task> getAllTask();
}

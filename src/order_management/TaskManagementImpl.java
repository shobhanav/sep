package order_management;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskManagementImpl implements TaskManagementInterface{

	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<Task> historyTasks = new ArrayList<Task>();
	
	@Override
	public Task createTasks(Crd crd, String team) {
		return new Task(crd,team);
	}

	@Override
	public boolean addTask(Task task) {
		if (task == null || getTask(task.getIdentifier()) != null){
			return false;
		}
		return tasks.add(task);
	}

	@Override
	public Task getTask(Crd crd, String team) {
		for(Task task : tasks){
			if (task.getId_crd() == crd.getIdentifier() && task.getTeam().equals(team)){
				return task;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Task> getAllTask() {
		return tasks;
	}

	@Override
	public Task getTask(int id) {
		for(Task task : tasks){
			if (task.getIdentifier() == id){
				return task;
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<Task> getTaskCrd (Crd crd ) {
		ArrayList<Task> tasksCrd = new ArrayList<Task>();
		for(Task task : tasks){
			if (task.getId_crd() == crd.getIdentifier()){
				tasksCrd.add(task);
			}
		}
		return tasksCrd;
	}

	@Override
	public ArrayList<Task> getTaskTeam(String team) {
		ArrayList<Task> tasksTeam = new ArrayList<Task>();
		for(Task task : tasks){
			if (task.getTeam().equals(team)){
				tasksTeam.add(task);
			}
		}
		return tasksTeam;
	}

	@Override
	public void deleteTask(int id) {
		for(Iterator<Task> itTask = tasks.iterator(); itTask.hasNext();){
			Task task = itTask.next();
			if(task.getIdentifier()==id){
				historyTasks.add(task);
				itTask.remove();
			}
		}
	}

	@Override
	public ArrayList<Task> getTaskHistory() {
		return historyTasks;
	}

}

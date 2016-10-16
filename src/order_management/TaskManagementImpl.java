package order_management;

import java.util.ArrayList;

public class TaskManagementImpl implements TaskManagementInterface{

	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<Task> historyTasks = new ArrayList<Task>();
	
	@Override
	public Task createTasks(Crd crd) {
		return new Task(crd);
	}

	@Override
	public boolean addTask(Task task) {
		tasks.add(task);
		return true;
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
		for(Task task: tasks){
			if(task.getIdentifier()==id){
				historyTasks.add(task);
				tasks.remove(task);
			}
		}
	}

}

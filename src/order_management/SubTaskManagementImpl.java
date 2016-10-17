package order_management;

import java.util.ArrayList;
import java.util.Iterator;

public class SubTaskManagementImpl implements SubTaskManagementInterface{
	
	private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();
	private ArrayList<SubTask> historySubTasks = new ArrayList<SubTask>();
	
	@Override
	public SubTask createSubTasks(Task task, String teamMember) {
		return new SubTask(task, teamMember);
	}

	@Override
	public boolean addSubTask(SubTask subTask) {
		if (subTask == null || getSubTask(subTask.getIdentifier()) != null){
			return false;
		}
		return subtasks.add(subTask);
	}

	@Override
	public SubTask getSubTask(int id) {
		for(SubTask subtask : subtasks){
			if (subtask.getIdentifier() == id){
				return subtask;
			}
		}
		return null;
	}

	@Override
	public ArrayList<SubTask> getSubTask(Task task, String teamMember) {
		ArrayList<SubTask> subTaskTeamMember = new ArrayList<SubTask>();
		for(SubTask subtask : subtasks){
			if (subtask.getId_task() == task.getIdentifier() && subtask.getTeamMember().equals(teamMember)){
				subTaskTeamMember.add(subtask);
			}
		}
		return subTaskTeamMember;
	}

	@Override
	public ArrayList<SubTask> getSubTaskTeam(String team) {
		ArrayList<SubTask> subTaskTeam = new ArrayList<SubTask>();
		for(SubTask subtask : subtasks){
			if (subtask.getTeam().equals(team)){
				subTaskTeam.add(subtask);
			}
		}
		return subTaskTeam;
	}

	@Override
	public ArrayList<SubTask> getSubTaskTask(Task task) {
		ArrayList<SubTask> subTaskTask = new ArrayList<SubTask>();
		for(SubTask subtask : subtasks){
			if (subtask.getId_task() == task.getIdentifier()){
				subTaskTask.add(subtask);
			}
		}
		return subTaskTask;
	}
	
	@Override
	public ArrayList<SubTask> getSubTaskCrd(Crd crd) {
		ArrayList<SubTask> subTaskCrd = new ArrayList<SubTask>();
		for(SubTask subtask : subtasks){
			if (subtask.getId_crd() == crd.getIdentifier()){
				subTaskCrd.add(subtask);
			}
		}
		return subTaskCrd;
	}
	
	@Override
	public ArrayList<SubTask> getSubTaskTeamMember(String teamMember) {
		ArrayList<SubTask> subTaskTeamMember =new ArrayList<SubTask>();
		for(SubTask subtask : subtasks){
			if (subtask.getTeamMember().equals(teamMember)){
				subTaskTeamMember.add(subtask);
			}
		}
		return subTaskTeamMember;
	}

	@Override
	public ArrayList<SubTask> getAllSubTask() {
		return subtasks;
	}

	@Override
	public void deleteSubTask(int id) {
		for(Iterator<SubTask> itSubTask =subtasks.iterator();itSubTask.hasNext();){
			SubTask subtask = itSubTask.next();
			if(subtask.getIdentifier()==id){
				historySubTasks.add(subtask);
				itSubTask.remove();
			}
		}
	}

	@Override
	public ArrayList<SubTask> getSubTaskHistory() {
		return historySubTasks;
	}

}

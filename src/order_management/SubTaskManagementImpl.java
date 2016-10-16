package order_management;

import java.util.ArrayList;

public class SubTaskManagementImpl implements SubTaskManagementInterface{
	
	private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();
	private ArrayList<SubTask> historySubTasks = new ArrayList<SubTask>();
	
	@Override
	public SubTask createsubTasks(Task task) {
		return new SubTask(task);
	}

	@Override
	public boolean addsubTask(SubTask subTask) {
		subtasks.add(subTask);
		return true;
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
		for(SubTask subtask: subtasks){
			if(subtask.getIdentifier()==id){
				historySubTasks.add(subtask);
				subtasks.remove(subtask);
			}
		}
	}

}

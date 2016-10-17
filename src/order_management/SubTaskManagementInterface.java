package order_management;

import java.util.ArrayList;

public interface SubTaskManagementInterface {

	public SubTask createSubTasks( Task task, String teamMember);
	
	public boolean addSubTask(SubTask subTask);
	
	public SubTask getSubTask(int id);
	
	public ArrayList<SubTask> getSubTask(Task task, String teamMember);
	
	public ArrayList<SubTask> getSubTaskTask(Task task);
	
	public ArrayList<SubTask> getSubTaskCrd(Crd crd);
	
	public ArrayList<SubTask> getSubTaskTeam(String team);
	
	public ArrayList<SubTask> getSubTaskTeamMember(String teamMember);
	
	public ArrayList<SubTask> getAllSubTask();
	
	public void deleteSubTask(int id);
	
	public ArrayList<SubTask> getSubTaskHistory();
}
